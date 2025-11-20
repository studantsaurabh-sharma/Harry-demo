package Exception;

public class MultipleCatch {
    public static void main(String args[]) {
        try {
            int a = 3, b = 0, c;
            System.out.println(c = a / b);
            int arr[] = {4, 5, 4,};
            System.out.println(arr[3]);
            String str = null;
            int v = Integer.parseInt(str);
            System.out.println(c);
        }
        catch(ArithmeticException a){
            System.out.println("can't divid ");
        }catch(NullPointerException t){
            System.out.println("String can't be Converted to Integer");
        }catch(ArrayIndexOutOfBoundsException p){
            System.out.println("Your  result is Out Of Bounds ");
        }
    }
}
