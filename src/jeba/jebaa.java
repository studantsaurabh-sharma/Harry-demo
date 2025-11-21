package jeba;
//) Create a method that takes an array of strings
// and returns an ArrayList containing the same strings.
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
public class jebaa {
    @NotNull
    public static ArrayList<String> convertToArrayList(String[]arr){
        ArrayList<String>list=new ArrayList<>();
        for(String s:arr){
            list.add(s);
        }
        return list;
    }
    public static void main(String args[]){
        String[]arr={"apple","banana","mango"};
        ArrayList<String> result =convertToArrayList(arr);
       System.out.println(result);
       System.out.println("saurabh");
    }
}
