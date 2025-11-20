package Exception;
public class NumberFormat{
    public static void main(String args[]){
        String str="Harry";
        try {
            int a = Integer.parseInt(str);
            System.out.println("a");
        }catch(NumberFormatException a){
            System.out.println("String "+str+" can't be Converted to Integer");
        }
    }
}