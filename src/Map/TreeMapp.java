package Map;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TreeMapp{
    public static void main(String[] args) {
        String str = "programming";
        StringBuilder repeatedChars = new StringBuilder();
        Map<Character, Integer> charCount = new HashMap<>();
        Set<Character> added = new HashSet<>();

        // Step 1: Count frequency of each character
        for (char ch : str.toCharArray()) {
            charCount.put(ch, charCount.getOrDefault(ch, 0) + 1);
        }

        // Step 2: Add only unique repeated characters to result
        for (char ch : str.toCharArray()) {
            if (charCount.get(ch) > 1 && !added.contains(ch)) {
                repeatedChars.append(ch);
                added.add(ch);
            }
        }

        System.out.println("Unique Repeated Characters: " + repeatedChars.toString());
        System.out.println("Total Unique Repeated Characters: " + added.size());
    }
}
