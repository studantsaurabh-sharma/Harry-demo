package Queue;
import java.util.ArrayDeque;
public class ArrayDequee{
    public static void main(String args[]){
        ArrayDeque<Integer>D=new ArrayDeque<>();
        D.offer(7474);
        D.offer(5);
        D.offerLast(84);
        D.offerLast(844);
        System.out.println(D);
        System.out.println("GetFirst:=  "+D.getFirst());
        System.out.println(D);
        D.addFirst(10);
        System.out.println(D);
        System.out.println(D.getLast());
        System.out.println(D);
        System.out.println(D.peekLast());
        System.out.println(D.peekFirst());
        System.out.println(D.pollLast());
        //iska method yaad karna padega
    }
}
