package Exception;

public class NullPointer {
    public static void main(String args[]){
        String str=null;
        try {
            System.out.println(str.toUpperCase());
        }catch(NullPointerException e){
            System.out.println(" null can't Casted");
            }

    }
}
