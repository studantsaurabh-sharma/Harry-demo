package List;
import java.util.*;
public class Arraylist{
    public static void main(String args[]){
        ArrayList<Integer>O=new ArrayList<>();
        O.add(345);
        O.add(53);
        O.add(55);
        O.add(233);
        O.add(94);
        O.add(10);
        O.set(0,23);
        O.get(4);
        O.remove(4);
        System.out.println(O);
        O.remove(Integer.valueOf(30));
        System.out.println(O.contains(55));
        System.out.println(O);
        System.out.println("ArrayList suru karte  hai ");
       System.out.println(O.size());
        System.out.println();
        for(int i=0;i<=O.size()-1;i++) {
            System.out.println(O.get(i));
        }
        for(int sau:O);
        System.out.println(O);

    }
}