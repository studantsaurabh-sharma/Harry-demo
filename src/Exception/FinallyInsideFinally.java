package Exception;
public class FinallyInsideFinally{
    public static void main(String args[]){
        int a=4,b=0,c;
        try{
            System.out.println(c=a/b);
        }catch(ArithmeticException e){
            System.out.println("Can't divide by zero");
        }
        finally{
            try{
                int arr[]={4,4,5,3};
                System.out.println(arr[5]);
            }catch(ArrayIndexOutOfBoundsException u){
                System.out.println("ArryOutOfboundExecption");
            }
        }
    }
}