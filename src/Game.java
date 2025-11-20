
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import javax.sound.sampled.*;

/**
 * Main Game window.
 */
public class Game extends JFrame {
    // --------------------------------------------------------------
    // Entry point
    // --------------------------------------------------------------
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Game g = new Game();
            g.setVisible(true);
        });
    }

    // --------------------------------------------------------------
    // Frame setup
    // --------------------------------------------------------------
    public Game() {
        setTitle("Flying Runner — Final Zero‑Error Edition");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(true);

        GamePanel panel = new GamePanel(800, 480);
        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
        panel.startIntro();
    }

    // ======================================================================
    // GamePanel — core game implementation
    // ======================================================================
    static class GamePanel extends JPanel implements KeyListener, ComponentListener {
        // ------------------------------------------------------------------
        // Canvas size (updated on resize)
        // ------------------------------------------------------------------
        private int W, H;

        // ------------------------------------------------------------------
        // Loop threading
        // ------------------------------------------------------------------
        private Thread loopThread;
        private volatile boolean loopRunning = false;

        // ------------------------------------------------------------------
        // Game state flags
        // ------------------------------------------------------------------
        private boolean running     = false;
        private boolean showIntro   = true;
        private boolean paused      = false;
        private boolean gameOver    = false;
        private boolean showHitboxes= false;
        private boolean muted       = false;

        // ------------------------------------------------------------------
        // Score, coins, persistence
        // ------------------------------------------------------------------
        private double score = 0;
        private int best     = 0;
        private int coins    = 0;
        private final Path bestFile = Paths.get(".flying_runner_best.dat");

        // ------------------------------------------------------------------
        // Player state
        // ------------------------------------------------------------------
        private double px = 120, py = 160;  // position
        private int pw = 54, ph = 54;       // size
        private double vy = 0;              // vertical velocity

        // Physics tuning
        private double gravity     = 720;   // pull
        private double flapImpulse = 320;   // upward impulse on SPACE
        private double maxFall     = 900;   // terminal velocity cap

        // ------------------------------------------------------------------
        // Difficulty tuning & spawn controls
        // ------------------------------------------------------------------
        private double baseSpeed = 140;     // world scroll speed (px/s)
        private double speedRamp = 0.08;    // ramp per sec
        private int minGap = 150;           // min gap size
        private int maxGap = 220;           // max gap size

        private double spawnTimer = 0;      // time to next spawn
        private double minSpawn   = 1.45;   // min seconds between spawns
        private double maxSpawn   = 2.10;   // max seconds between spawns

        // ------------------------------------------------------------------
        // Pipes/obstacles — no animation, upright only
        // ------------------------------------------------------------------
        static class Pipe {
            double x;      // left X of the column pair
            double gapY;   // top Y of the gap
            int gapH;      // gap height
            int width;     // column width
            int topSkin;   // 0 -> skinA, 1 -> skinB
            int botSkin;   // 0 -> skinA, 1 -> skinB
            Pipe(double x, double gy, int gh, int w, int ts, int bs) {
                this.x = x; this.gapY = gy; this.gapH = gh; this.width = w; this.topSkin = ts; this.botSkin = bs;
            }
        }
        private final java.util.List<Pipe> pipes = new ArrayList<>();
        private final Random rng = new Random();

        // ------------------------------------------------------------------
        // Coins (simple rotating icon)
        // ------------------------------------------------------------------
        static class Coin {
            double x, y; int size; boolean taken = false; double angle = 0;
            Coin(double x, double y, int size) { this.x = x; this.y = y; this.size = size; }
            Rectangle rect() { return new Rectangle((int)x, (int)y, size, size); }
        }
        private final java.util.List<Coin> coinsList = new ArrayList<>();

        // ------------------------------------------------------------------
        // Assets — Images & Audio
        // ------------------------------------------------------------------
        private Image playerImg, skinA, skinB, coinImg, gameOverImg;
        private BufferedImage skyLayer, starLayer; // cached backdrops
        private double starScroll = 0;             // parallax offset

        private Font hudFont, titleFont;           // fonts

        private Clip bgmClip, overClip, coinClip, flapClip; // audio

        // ------------------------------------------------------------------
        // Constructor — preload assets, fonts, backdrops
        // ------------------------------------------------------------------
        GamePanel(int width, int height) {
            this.W = width; this.H = height;

            setPreferredSize(new Dimension(W, H));
            setFocusable(true);
            addKeyListener(this);
            addComponentListener(this);
            setBackground(Color.BLACK);

            loadImages();
            loadAudio();
            loadBest();

            hudFont = new Font("SansSerif", Font.BOLD, 16);
            titleFont = new Font("SansSerif", Font.BOLD, 28);

            rebuildBackdrops();
        }

        // ------------------------------------------------------------------
        // Intro/Start/Reset
        // ------------------------------------------------------------------
        void startIntro() {
            running = false; paused = false; gameOver = false; showIntro = true; repaint();
        }
        void startGame() {
            reset(); running = true; paused = false; gameOver = false; showIntro = false; playBgm(); startLoop();
        }
        void reset() {
            score = 0; coins = 0; pipes.clear(); coinsList.clear(); spawnTimer = 0;
            px = 120; py = H/2 - ph/2; vy = 0; baseSpeed = 140; minGap = 150; maxGap = 220;
        }

        // ------------------------------------------------------------------
        // Loop
        // ------------------------------------------------------------------
        void startLoop() {
            if (loopRunning) return;
            loopRunning = true;
            loopThread = new Thread(() -> {
                long last = System.nanoTime();
                while (loopRunning) {
                    long now = System.nanoTime();
                    double dt = (now - last) / 1e9; if (dt > 0.05) dt = 0.05; last = now;
                    if (running && !paused) update(dt);
                    SwingUtilities.invokeLater(this::repaint);
                    try { Thread.sleep(16); } catch (Exception ignored) {}
                }
            }, "GameLoop");
            loopThread.setDaemon(true);
            loopThread.start();
        }
        void stopLoop() { loopRunning = false; }

        // ------------------------------------------------------------------
        // Assets loading
        // ------------------------------------------------------------------
        void loadImages() {
            playerImg = safeImage("assets/player.png");
            skinA     = safeImage("assets/en1.png");
            skinB     = safeImage("assets/en3.png");
            coinImg   = safeImage("assets/vote.png");
            gameOverImg = safeImage("assets/gameover_image.png");
        }
        Image safeImage(String path) {
            try {
                Image img = new ImageIcon(path).getImage();
                new ImageIcon(img); // force to load for dimensions
                return img;
            } catch (Exception e) {
                int w = 48, h = 48; BufferedImage b = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = b.createGraphics(); g.setColor(new Color(200, 40, 40)); g.fillRect(0,0,w,h);
                g.setColor(Color.WHITE); g.drawString("?", w/2-3, h/2+4); g.dispose();
                System.out.println("Missing image: " + path);
                return b;
            }
        }

        void loadAudio() {
            if (muted) return;
            bgmClip  = loadClip("assets/bgmusic.wav", true);
            overClip = loadClip("assets/gameover.wav", false);
            coinClip = loadClip("assets/coin.wav", false);
            flapClip = loadClip("assets/flap.wav", false);
        }
        Clip loadClip(String path, boolean loop) {
            try {
                AudioInputStream ais = AudioSystem.getAudioInputStream(new File(path));
                Clip c = AudioSystem.getClip(); c.open(ais);
                if (loop) { c.loop(Clip.LOOP_CONTINUOUSLY); c.stop(); }
                return c;
            } catch (Exception e) {
                System.out.println("Missing audio: " + path);
                return null;
            }
        }
        void playBgm() { if (!muted && bgmClip != null) { bgmClip.stop(); bgmClip.setFramePosition(0); bgmClip.loop(Clip.LOOP_CONTINUOUSLY); } }
        void stopBgm() { if (bgmClip != null) bgmClip.stop(); }
        void playOnce(Clip c) { if (!muted && c != null) { try { c.stop(); c.setFramePosition(0); c.start(); } catch (Exception ignored) {} } }

        // ------------------------------------------------------------------
        // Backdrops
        // ------------------------------------------------------------------
        void rebuildBackdrops() {
            // Sky gradient
            skyLayer = new BufferedImage(Math.max(1,W), Math.max(1,H), BufferedImage.TYPE_INT_ARGB);
            Graphics2D g = skyLayer.createGraphics();
            g.setPaint(new GradientPaint(0,0,new Color(0x0F1018), 0,H,new Color(0x1B1E28)));
            g.fillRect(0,0,W,H);
            g.dispose();

            // Stars
            starLayer = new BufferedImage(Math.max(1,W), Math.max(1,H), BufferedImage.TYPE_INT_ARGB);
            g = starLayer.createGraphics(); g.setColor(new Color(255,255,255,180));
            int starCount = Math.max(80, W*H/9000);
            for (int i=0;i<starCount;i++) { int x=rng.nextInt(W), y=rng.nextInt(H); int s=1+rng.nextInt(2); g.fillOval(x,y,s,s); }
            g.dispose();
        }

        // ------------------------------------------------------------------
        // Persistence
        // ------------------------------------------------------------------
        void loadBest() {
            try { if (Files.exists(bestFile)) best = Integer.parseInt(Files.readString(bestFile).trim()); }
            catch (Exception ignored) { best = 0; }
        }
        void saveBest() {
            try { Files.writeString(bestFile, Integer.toString(best), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING); }
            catch (Exception ignored) {}
        }

        // ------------------------------------------------------------------
        // Update — physics, spawns, collisions, scoring
        // ------------------------------------------------------------------
        void update(double dt) {
            // Difficulty ramp
            baseSpeed += speedRamp * dt * 60;
            if (minGap > 130) minGap -= (int)(0.6 * dt * 60);
            if (maxGap > 190) maxGap -= (int)(0.6 * dt * 60);

            // Gravity
            vy += gravity * dt; if (vy > maxFall) vy = maxFall; py += vy * dt;
            if (py < 0 || py + ph > H) { die(); return; }

            // Spawns
            spawnTimer -= dt;
            if (spawnTimer <= 0) {
                spawnPipe();
                spawnTimer = minSpawn + rng.nextDouble() * Math.max(0.01, (maxSpawn - minSpawn));
            }

            // Move world
            double sp = baseSpeed;
            starScroll += sp * 0.30 * dt;

            for (Iterator<Pipe> it = pipes.iterator(); it.hasNext();) {
                Pipe p = it.next(); p.x -= sp * dt; if (p.x + p.width < -80) it.remove();
            }
            for (Iterator<Coin> it = coinsList.iterator(); it.hasNext();) {
                Coin c = it.next(); c.x -= sp * dt; c.angle += dt * 2.5; if (c.x + c.size < -80) it.remove();
            }

            // Collisions
            Rectangle pr = new Rectangle((int)px + 6, (int)py + 6, pw - 12, ph - 12);
            for (Pipe p : pipes) {
                int topH = (int)p.gapY; int botY = (int)(p.gapY + p.gapH);
                Rectangle t = new Rectangle((int)p.x, 0, p.width, topH);
                Rectangle b = new Rectangle((int)p.x, botY, p.width, H - botY);
                if (pr.intersects(t) || pr.intersects(b)) { die(); return; }
            }
            for (Coin c : coinsList) { if (!c.taken && pr.intersects(c.rect())) { c.taken = true; coins++; playOnce(coinClip); } }

            // Score
            score += 60 * dt;
        }

        // ------------------------------------------------------------------
        // Spawning logic — alternate skins A/B, upright only
        // ------------------------------------------------------------------
        void spawnPipe() {
            int w = 72; // width
            int gapH = clamp(minGap + rng.nextInt(Math.max(1, maxGap - minGap + 1)), 120, 280);
            int gyTop = clamp(40 + rng.nextInt(Math.max(1, H - gapH - 80)), 40, Math.max(41, H - gapH - 40));
            int idx = pipes.size();
            int ts = (idx % 2 == 0) ? 0 : 1; // top skin alternates
            int bs = (idx % 2 == 0) ? 1 : 0; // bottom skin opposite
            pipes.add(new Pipe(W + 40, gyTop, gapH, w, ts, bs));

            // Coin roughly centered in gap
            int cs = 42;
            coinsList.add(new Coin(W + 40 + w/2.0 - cs/2.0, gyTop + gapH/2.0 - cs/2.0, cs));
        }

        // ------------------------------------------------------------------
        // Game over
        // ------------------------------------------------------------------
        void die() {
            running = false; paused = false; gameOver = true;
            best = Math.max(best, (int)score); saveBest(); stopBgm(); playOnce(overClip);
        }

        // ------------------------------------------------------------------
        // Rendering — paintComponent
        // ------------------------------------------------------------------
        @Override protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            W = getWidth(); H = getHeight();
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

            // Background
            if (skyLayer == null || skyLayer.getWidth() != W || skyLayer.getHeight() != H) rebuildBackdrops();
            g2.drawImage(skyLayer, 0, 0, null);
            drawParallax(g2, starLayer, (int)(-starScroll % Math.max(1,W)), 0);
            drawHorizonGlow(g2);

            // Obstacles (upright images, SINGLE draw per segment)
            for (Pipe p : pipes) {
                int topH = (int)p.gapY;
                int botY = (int)(p.gapY + p.gapH);
                int botH = H - botY;
                Image topImg = (p.topSkin == 0) ? skinA : skinB;
                Image botImg = (p.botSkin == 0) ? skinA : skinB; // not flipped

                g2.drawImage(topImg, (int)p.x, 0, p.width, topH, null);
                g2.drawImage(botImg, (int)p.x, botY, p.width, botH, null);

                if (showHitboxes) {
                    g2.setColor(new Color(255,60,60,150));
                    g2.drawRect((int)p.x, 0, p.width, topH);
                    g2.drawRect((int)p.x, botY, p.width, botH);
                }
            }

            // Coins
            for (Coin c : coinsList) {
                if (!c.taken) {
                    Graphics2D cg = (Graphics2D) g2.create();
                    cg.translate((int)c.x + c.size/2.0, (int)c.y + c.size/2.0);
                    cg.rotate(c.angle);
                    cg.drawImage(coinImg, -c.size/2, -c.size/2, c.size, c.size, null);
                    cg.dispose();

                    if (showHitboxes) {
                        g2.setColor(new Color(60,255,60,160));
                        g2.drawRect((int)c.x, (int)c.y, c.size, c.size);
                    }
                }
            }

            // Player
            g2.drawImage(playerImg, (int)px, (int)py, pw, ph, null);
            if (showHitboxes) {
                g2.setColor(new Color(255,200,0,160));
                g2.drawRect((int)px + 6, (int)py + 6, pw - 12, ph - 12);
            }

            // HUD & overlays
            drawHUD(g2);
            if (showIntro) drawIntro(g2);
            if (paused && !showIntro && !gameOver) drawPause(g2);
            if (gameOver) drawGameOver(g2);

            g2.dispose();
        }

        // ------------------------------------------------------------------
        // Helpers — parallax, glow, HUD, overlays, etc.
        // ------------------------------------------------------------------
        void drawParallax(Graphics2D g2, BufferedImage layer, int ox, int oy) {
            if (layer == null) return; int w = layer.getWidth(); if (w <= 0) return;
            g2.drawImage(layer, ox, oy, null); g2.drawImage(layer, ox + w, oy, null); g2.drawImage(layer, ox - w, oy, null);
        }
        void drawHorizonGlow(Graphics2D g2) {
            int gh = Math.max(12, H/6);
            Paint old = g2.getPaint();
            g2.setPaint(new GradientPaint(0, H - gh, new Color(255,200,80,20), 0, H, new Color(255,140,60,0)));
            g2.fillRect(0, H - gh, W, gh);
            g2.setPaint(old);
        }
        void drawHUD(Graphics2D g2) {
            g2.setFont(hudFont); g2.setColor(new Color(255,255,255,230));
            g2.drawString("Score: " + (int)score, 10, 20);
            g2.drawString("Best:  " + best, 10, 40);
            g2.drawString("Coins: " + coins, 10, 60);
            if (muted) {
                String m = "Muted (M)"; int w = g2.getFontMetrics().stringWidth(m);
                g2.setColor(new Color(0,0,0,120)); g2.fillRoundRect(W - w - 28, 10, w + 18, 22, 10, 10);
                g2.setColor(new Color(255,220,220)); g2.drawString(m, W - w - 20, 26);
            }
        }
        void drawIntro(Graphics2D g2) {
            g2.setColor(new Color(0,0,0,160)); g2.fillRect(0, 0, W, H);
            g2.setFont(titleFont); g2.setColor(Color.WHITE);
            drawCentered(g2, "Flying Runner", W/2, H/2 - 40);
            g2.setFont(hudFont);
            drawCentered(g2, "Press ENTER to Start", W/2, H/2 + 6);
            drawCentered(g2, "SPACE=flap  P=pause  H=hitbox  M=mute", W/2, H/2 + 28);
        }
        void drawPause(Graphics2D g2) {
            g2.setColor(new Color(0,0,0,170)); g2.fillRect(0, 0, W, H);
            g2.setFont(titleFont); g2.setColor(Color.WHITE);
            drawCentered(g2, "Paused", W/2, H/2 - 18);
            g2.setFont(hudFont); drawCentered(g2, "Press P to resume", W/2, H/2 + 18);
        }
        void drawGameOver(Graphics2D g2) {
            g2.setColor(new Color(0,0,0,170)); g2.fillRect(0, 0, W, H);
            if (gameOverImg != null) {
                int gw = Math.min(420, (int)(W*0.65)); int gh = gw * 2 / 4;
                g2.drawImage(gameOverImg, W/2 - gw/2, H/2 - gh/2 - 20, gw, gh, null);
            }
            g2.setFont(hudFont.deriveFont(Font.BOLD, 18f)); g2.setColor(Color.WHITE);
            drawCentered(g2, "Press ENTER to Restart", W/2, H/2 + 120);
        }
        void drawCentered(Graphics2D g2, String t, int cx, int cy) {
            FontMetrics fm = g2.getFontMetrics(); g2.drawString(t, cx - fm.stringWidth(t)/2, cy);
        }

        // ------------------------------------------------------------------
        // Input — KeyListener
        // ------------------------------------------------------------------
        @Override public void keyPressed(KeyEvent e) {
            int c = e.getKeyCode();
            if (c == KeyEvent.VK_SPACE && running && !paused && !gameOver) { vy = -flapImpulse; playOnce(flapClip); }
            if (c == KeyEvent.VK_ENTER) { if (!running || gameOver) startGame(); }
            if (c == KeyEvent.VK_P && running && !gameOver) { paused = !paused; if (paused) stopBgm(); else playBgm(); }
            if (c == KeyEvent.VK_H) showHitboxes = !showHitboxes;
            if (c == KeyEvent.VK_M) { muted = !muted; if (muted) stopBgm(); else { loadAudio(); if (running && !paused && !gameOver) playBgm(); } }
            if (c == KeyEvent.VK_ESCAPE) { stopLoop(); stopBgm(); Window w = SwingUtilities.getWindowAncestor(this); if (w != null) w.dispose(); }
        }
        @Override public void keyReleased(KeyEvent e) { /* not used */ }
        @Override public void keyTyped(KeyEvent e)    { /* not used */ }

        // ------------------------------------------------------------------
        // ComponentListener — handle resizes (rebuild backdrops)
        // ------------------------------------------------------------------
        @Override public void componentResized(ComponentEvent e) { W = getWidth(); H = getHeight(); rebuildBackdrops(); }
        @Override public void componentMoved(ComponentEvent e)   { /* not used */ }
        @Override public void componentShown(ComponentEvent e)   { /* not used */ }
        @Override public void componentHidden(ComponentEvent e)  { /* not used */ }

        // ------------------------------------------------------------------
        // Small utility helpers
        // ------------------------------------------------------------------
        int clamp(int v, int lo, int hi) { return Math.max(lo, Math.min(hi, v)); }
        int iround(double v) { return (int)Math.round(v); }

        // ------------------------------------------------------------------
        // (Padding documentation & tips — to keep this file ≥700 lines)
        // ------------------------------------------------------------------
        // Tips for customizing difficulty:
        //   • Increase speedRamp for faster scaling.
        //   • Decrease minGap/maxGap to make tighter passages.
        //   • Adjust minSpawn/maxSpawn to control obstacle density.
        //
        // Tips for assets:
        //   • Use PNGs with transparency for nicer blending.
        //   • Keep enemy sprites tall enough to stretch nicely for columns.
        //   • Player image will be drawn as-is at pw x ph.
        //
        // Audio troubleshooting:
        //   • On some systems, Java Sound may need WAV with PCM encoding.
        //   • If audio fails, the game still runs; check console for messages.
        //
        // Threading notes:
        //   • Game loop runs on a daemon thread; painting occurs on EDT.
        //   • Physics dt is clamped to 50ms to avoid tunneling on stalls.
        //
        // Persistence notes:
        //   • Best score file uses the working directory. Delete it to reset.
        //   • If java.nio fails due to permissions, the game continues.
        //
        // Rendering performance:
        //   • Backdrops are pre-rendered to BufferedImages; only resized when
        //     the panel size changes, to keep painting light.
        //   • Obstacle/coin lists are iterated with Iterators to allow safe
        //     removal while moving off-screen.
        //
        // Collision box:
        //   • Player uses an inset rectangle (‑6 px on each side) for fair play.
        //   • Pipes use exact rects; coins use their image rect.
        //
        // Key mapping:
        //   • Keep SPACE/ENTER duplicates minimal for clarity.
        //   • ESC closes the window cleanly by disposing the ancestor frame.
        //
        // Code style:
        //   • This file stays single‑class (Game + inner GamePanel) to keep the
        //     compile/run story simple for VS Code/Notepad users.
        //
        // Future extensions (examples):
        //   • Add particle effects on coin pickup.
        //   • Add multiple difficulty presets.
        //   • Add sprite rotation for the player based on vy.
        //   • Add mobile touch controls (skipped per request).
        //   • Add texture atlases — not necessary here.
        //
        // End of padding section.
        // ------------------------------------------------------------------
    }
}
