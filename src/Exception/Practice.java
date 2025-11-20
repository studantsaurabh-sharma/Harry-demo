package Exception;
import java.util.*;
/*class Hello extends Exception{
    public  Hello(String Message){
        super(Message);
    }
}
public class Practice{
    public static void main(int age) throws Hello{
        if(age<18){
            throw new Hello("Age must be 18");
        }else{
            System.out.println("Age is valid for registration");
        }
    }

    public static void main(String args[]){
     try{
         main(66);
     }catch(Hello e){
         System.out.println("Caught Exception"+e.getMessage());
        }

    }

}
import java.util.InputMismatchException;
import java.util.Scanner;
public class Practice {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Enter first number:");
            double num1 = sc.nextDouble();
            System.out.println("Enter your second number");
            double num2 = sc.nextDouble();
            System.out.println("Enter operation (+, -, *, /): ");
            char op = sc.next().charAt(0);// isme bare me thoda janana baki hai
            double Result;
            switch (op) {
                case '+':
                    Result = num1 + num2;
                    System.out.println(Result + "Resutl");
                    break;
                case '-':
                    Result = num1 - num2;
                    System.out.println(Result + "= Result");
                    break;
                case '/':
                    if (num2 == 0) {
                        throw new ArithmeticException("Can't divide by Zero");
                    }
                    Result = num1 / num2;
                    System.out.println(Result + "Result");
                    break;
                case '%':
                    Result = num1 % num2;
                    System.out.println(Result + "Result");
                    break;
                case '*':
                    Result = num1 * num2;
                    break;
                default:
                    throw new IllegalArgumentException("Invalid operation");
            }
        } catch (ArithmeticException e) {
            System.out.println("Error" + e.getMessage());
        }catch(InputMismatchException e){
            System.out.println("Error: please enter valid numeric values.");
        }catch(IllegalArgumentException e){
            System.out.println("Error:+"+e.getMessage());
        }catch(Exception e){
            System.out.println("Unexpected Error:"+e.getMessage());
        }finally{
            sc.close();
            System.out.println("Calculator Program ended,:");
        }
    }
}
//Create your own exception class InsufficientBalanceException.
class InsufficientBalanceException extends Exception{
    public InsufficientBalanceException(String Message){
        super(Message);
    }
}
public class Practice{

    public static void Bank(int Balance)throws InsufficientBalanceException{
        Scanner sc=new Scanner(System.in);
        System.out.println("welcome to the bank ");
        System.out.println("Enter your withdraw Amount");
        int Withdraw =sc.nextInt();
        if(Withdraw>Balance){
            throw new InsufficientBalanceException("Balance is Insufficient ");
        }else{
           Balance= Balance-Withdraw;
           System.out.println("Your balance is = "+Balance);
        }
    }
    public static void main(String args[]){b
      try{
          Bank(5000);
      }catch(InsufficientBalanceException e){
          System.out.println(e.getMessage());
      }
    }
}
 Throw an exception if array size is negative using IllegalArgumentException.
public class Practice{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        int size=sc.nextInt();
        if(size<0){
            throw new IllegalArgumentException("Array size cannot be negative");
        }else{
            int arr[]=new int[size];
            System.out.println("Array created with size " + size);
        }
    }
}
// Nest try-catch blocks and handle multiple types of exceptions.

public class Practice{
    public static void main(String args[]){
        try{
            int a=8,b=0,c;
            try {
                c = a / b;
            }catch(ArithmeticException e){
                System.out.println("con't divide kya zero ");
            }
            String name=null;
            try{
                System.out.println(name.toUpperCase());
            }catch(NullPointerException e){
                System.out.println("String.toUpperCase() because name is null");

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
// Use multiple catch blocks for different exception types.
public class Practice{
    public static void main(String args[]){
        int a=4,b=0,c;
        try{
            c=a/b;
        }catch(ArithmeticException e){
            System.out.println("can't divide by zero ");
        }catch(NullPointerException e){
            System.out.println(e);
        }catch(ArrayIndexOutOfBoundsException e){
            System.out.println(e);
        }
    }
}
// Use throws keyword to delegate an exception from one method to another.

public class Practice{
    public static void Ex()throws ArithmeticException{
        int a=5/0;
    }
    public static void x()throws ArithmeticException{
        Ex();
    }
    public static void main(String args[]){
        try{
            x();
        }catch(ArithmeticException e){
            System.out.println("Can't divide by Zero  ");
        }
        System.out.println("Program Ended");
    }
}
//. Create a program that validates password and throws InvalidPasswordException.
class InvalidPasswordException extends Exception{
    public InvalidPasswordException(String Message){
        super (Message);
    }
}
public class Practice{
    public static void Pass(int Password)throws InvalidPasswordException{

       if(Password != 12345){
           throw new InvalidPasswordException("Try Again");
       }else{
           System.out.println("Welcome saurabh sharma ");
       }
    }
   public static void main(String args[]){
       try{
           Pass(12345);
       }catch(InvalidPasswordException e){
           System.out.println(e.getMessage());
       }
   }
}
//Handle exceptions in a banking system (withdraw/deposit).
//Haan, withdraw ke case me custom exception banana better rahega, jaise InsufficientBalanceException.
//
// Reason: Ye specific error hai jo normal Exception ya RuntimeException se alag hai.
//
// Agar paisa kam hai aur user withdraw karne ki koshish karta hai, to custom exception clearly batayega ki problem kya hai.
//
//Deposit me usually exception ki zarurat nahi, sirf input validation karna hota hai.
//
//Agar chaho to mai tumhare liye poora banking system ka code custom exception ke saath bana du.

////Create a method that parses an integer and throws NumberFormatException for wrong  input
public class Practice{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
             String Number=sc.nextLine();
             try {
                 int num = Integer.parseInt(Number);
                 System.out.println(num);
             }catch(NumberFormatException e){
           System.out.println("Enter valid number");
        }
    }
}
//15. Demonstrate exception chaining.
public class Practice {
    public static void main(String args[]) {
        long sum = 0;
        // 1. Sum of first 100000000 numbers
        for (int i = 1; i <= 100000000; i++) {
            sum += i;
        }
        System.out.println("Sum: " + sum);

        // 2. Count numbers ending in 7 up to 50000000
        int count = 0;
        for (int i = 1; i <= 50000000; i++) {
            if (i % 10 == 7) {   // check last digit
                count++;
            }
        }
        System.out.println("Count of numbers ending in 7: " + count);
    }
}
// 25. Create a game where invalid move throws exception.
class InvalidMove extends Exception{
    public InvalidMove(String Message){
        super(Message);
    }
}
public class Practice{
    public static void main(String args[]){
        Scanner sc=new Scanner(System.in);
        Random r=new Random();
        System.out.println("Welcome to my game");
        int Num=r.nextInt(10)+1;
        boolean win=false;
        while(!win){
            try{
                System.out.println("Enter your number 1 to10");
                int Guess=sc.nextInt();
                if(Guess<1||Guess>10){
                    throw new InvalidMove("Terko nhi malum ki 1 to 1o number dalna hai ");
                }
                if(Guess==Num){
                    System.out.println("congratulation");
                    win=true;
                }else{
                    System.out.println("Try again");
                }
            }catch(InvalidMove e){
                System.out.println(e.getMessage());
            }
        }
    }

class Practice {

    // Method that returns an Exception object
    public static Exception getMyException() {
        // yaha hum ek Exception object bana rahe hai
        Exception ex = new Exception("This is my custom exception object");
        return ex;  // method exception object ko return karega
    }

    public static void main(String[] args) {
        // method call karke exception object ko le liya
        Exception myEx = getMyException();

        // ab exception object ke message ko print kar rahe hai
        System.out.println("Exception messa
        ge: " + myEx.getMessage());


    }
}
//Create a class `Employee` with encapsulation and print all details
class Empoyee{
    private String Name;
    private  int Id;
    private int Age;
    public void getName(String name){
        Name= name;
    }
    public void getId(int id){
        Id= id;
    }
    public void getAge(int age){
        Age= age;
    }
    public int setId(){
        return Id;
    }
    public int setAge(){
        return Age;
    }
    public String setName(){
        return Name;
    }
}
public class Practice{
    public static void main(String args[]){
        Empoyee x=new Empoyee();
        x.getName("saurabh");  
        x.getAge(44);
        x.getId(54);
        System.out.println(x.setAge());
        System.out.println(x.setId());
        System.out.println(x.setName());
    }
}
//Create a thread using `Thread` class and another using `Runnable`
class sau extends Thread{
    public void run(){
        System.out.println("This is a Extends Thread");
    }
}
 class harry implements Runnable{
    public void run(){
        System.out.println("This is a Runnable Thread");
    }
}
public class Practice{
    public static void main(String args[]){
        System.out.println("Welcome to the Thread");
        sau m=new sau();
        m.start();
        harry h=new harry();
        Thread t=new Thread(h);
        t.start();
    }
}
// Demonstrate thread sleep and interruption.
class Ravi extends Thread{
    public void run(){
        try{
            for(int i=0;i<=10;i++){
                System.out.println("Thread start ho gy a hai ");
                Thread.sleep(2000);
            }
            } catch(InterruptedException a){
            System.out.println(a);
        }

    }
}
public class Practice{
    public static void main(String args[]){
        Ravi v=new Ravi();
        v.start();
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        v.interrupt();
    }
}
 */
//Create a program to print even and odd numbers using two threads
//
class PrintNumber {
    private int num = 1;
    private int max = 10;

    public synchronized void printOdd() {
        while (num <= max) {
            if (num % 2 == 1) {
                System.out.println("Odd: " + num);
                num++;
                notify();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public synchronized void printEven() {
        while (num <= max) {
            if (num % 2 == 0) {
                System.out.println("Even: " + num);
                num++;
                notify();
            } else {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

// Runnable class for Odd Numbers
class OddPrinter implements Runnable {
    private PrintNumber obj;

    public OddPrinter(PrintNumber obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        obj.printOdd();
    }
}

// Runnable class for Even Numbers
class EvenPrinter implements Runnable {
    private PrintNumber obj;

    public EvenPrinter(PrintNumber obj) {
        this.obj = obj;
    }

    @Override
    public void run() {
        obj.printEven();
    }
}

// Main Class
public class Practice {
    public static void main(String[] args) {
        PrintNumber obj = new PrintNumber();

        Thread t1 = new Thread(new OddPrinter(obj));
        Thread t2 = new Thread(new EvenPrinter(obj));

        t1.start();
        t2.start();
    }
}

