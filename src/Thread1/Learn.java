package Thread1;
class Bus implements Runnable {
    int available = 2, passenger;

    Bus(int passenger) {
        this.passenger = passenger;
    }

    public synchronized void run() {
        String name = Thread.currentThread().getName();
        if (available >= passenger) {
            System.out.println(name + "   Reversed Seat");
            available-=passenger;
        } else {
            System.out.println(name+"   Sorry seat not available");
        }
    }
}
    public class Learn {
        public static void main(String args[]) {
            Bus r = new Bus(2);
            Thread t1 = new Thread(r);
            Thread t2 = new Thread(r);
            Thread t3 = new Thread(r);
            t1.setName("Ram");
            t2.setName("Shym");
            t3.setName("Raju");
            t1.start();
            t2.start();
            t3.start();
        }
    }
