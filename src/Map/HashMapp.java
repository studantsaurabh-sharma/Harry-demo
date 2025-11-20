package Map;
import java.util.Map;
import java.util.HashMap;
public class HashMapp{
    public static void main(String args[]){
        HashMap<String,Integer>M=new HashMap<>();
        M.put("One",1);
        M.put("Two",2);
        M.put("Three",3);
        M.put("Fourt",4);
        M.put("Five",5);
        M.put("Five",9);
//        if(!M.containsKey("Two")){
//            M.put("two",21);
//        }
        M.putIfAbsent("two",45);
        M.put("two",44);
        System.out.println(M);
        for(Map.Entry<String ,Integer> e: M.entrySet()){
            System.out.println(e);
            System.out.println(e.getKey());
            System.out.println(e.getValue());
            for(String key: M.keySet()){
                System.out.println(key);
               System.out.println(M.containsValue(3));
            }
        }
    }
}
