package Queue;
import java .util.Comparator;
import java.util.PriorityQueue;
public class PriorityQueuee{
    public static void main(String args[]){
        PriorityQueue<Integer>P=new PriorityQueue<>();
        P.offer(12);
        P.offer(8);
        P.offer(24);
        System.out.println(P);
        P.poll();
        System.out.println(P);
        P.offer(29);
        P.offer(3);
        System.out.println(P);
        System.out.println(P.peek());
        //jab apko data ko priority Queue ke basis par manage
        // karna ho to Priority  queue ka use karo
        // isme by default smallest element has highest
        // prority ager app chate ho to comparator and collection
        // ka use karna ho example := collections.reverseOrder and comparator.reverseorder
    }
}

