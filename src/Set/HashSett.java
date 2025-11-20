package Set;
import java .util.Set;
import java.util.HashSet;
public class HashSett {
    public static void main(String args[]){
        HashSet<Integer>H=new HashSet<>();
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
