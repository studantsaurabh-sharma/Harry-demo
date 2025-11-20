package Exception;

public class MultipleTryCatch {
    public static void main(String args[]){
        String str=null;
        try{
            System.out.println(str.toUpperCase());
        }catch(NullPointerException e){
            System.out.println(" Null Can't Casted");
        }
        try{
            int a=4,b=0,c;
            c=a/b;
            System.out.println(c);
        }catch(ArithmeticException o){
            System.out.println("Can't divid by Zero");
        }
    }
}
