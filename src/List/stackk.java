package List;
import java.util.Stack;
public class stackk{
    public static void main(String args[]){
        Stack<String>P=new Stack<>();
        P.push("Apple");
        P.push("Lion");
        P.push("Tiger");
        P.push("Harry");
        System.out.println("Stack"+P);
        System.out.println(P.peek());
        P.pop();
        System.out.println(P);
        System.out.println(P.peek());
    }
}