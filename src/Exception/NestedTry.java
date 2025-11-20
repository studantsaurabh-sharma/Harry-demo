package Exception;
//Jab ek try block ke andar dusra try-catch block hota hai,
// to usse Nested Try Block kehte hain.
//Yeh tab useful hota hai jab kisi specific
// part of code me
// aur bhi exception aane ke chances ho aur tum usko alagd se handle karna chahte ho.
public class NestedTry {
    public static void main(String args[]) {
        try {
            int a = 3, b = 0, c;
            try {
                String str = null;
                System.out.println(str.toUpperCase());
            } catch (NullPointerException e) {
                System.out.println("Null string can't be converted to Integer");
            }
            System.out.println(c = a / b);
        }catch(ArithmeticException u){
          System.out.println("Cannot divide by zero");
        }
    }
}

