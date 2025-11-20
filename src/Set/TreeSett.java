package Set;
import java.util.TreeSet;
public class TreeSett{
    public static void main(String args[]){
        TreeSet<Integer> T=new TreeSet<>();
        T.add(74);
        T.add(84);
        T.add(84);
        T.add(74);
        T.add(23);
        T.add(1);
        System.out.println(T);
        System.out.println(T.pollFirst());
        System.out.println(T.pollLast());
        System.out.println(91);
        System.out.println(T.last());
        
    }
}
