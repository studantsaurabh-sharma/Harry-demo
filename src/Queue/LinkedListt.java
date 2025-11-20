package Queue;
import java.util.LinkedList;
import java.util.Queue;
public class LinkedListt{
    public static void main(String args[]){
        Queue<Integer> Q=new LinkedList<>();
        Q.offer(74);
        Q.offer(84);
        Q.offer(55);
        Q.offer(97);
        System.out.println(Q);
        System.out.println(Q.poll());
        System.out.println(Q);
        System.out.println(Q.peek());
    }
}