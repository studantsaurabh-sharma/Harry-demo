package Set;
import java.util.LinkedHashSet;
public class LinkedHashSett {
    public static void main(String args[]){
        LinkedHashSet<Integer>H=new LinkedHashSet<>();
        H.add(23);
        H.add(75);
        H.add(64);
        H.add(2);
        System.out.println( H.contains(2));
        System.out.println(H.contains(74));
        System.out.println(H);
        System.out.println(H.isEmpty());
        System.out.println(H.size());
        H.clear();
        System.out.println(H);
    }
}
