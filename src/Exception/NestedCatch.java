package Exception;

public class NestedCatch {
    public static void main(String args[]){
        try {
            int a = 3, b = 0, c;
            System.out.println(c = a / b);
        }catch(ArithmeticException u){
            System.out.println("This is a ArithmeticException");
            try{
                int arr[] = {4, 5, 4,};
                System.out.println(arr[3]);
            }catch(ArrayIndexOutOfBoundsException t){
                System.out.println("ArrayOutOfBoundsException");
            }
        }
    }
}
