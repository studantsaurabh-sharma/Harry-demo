package Opps;

/*
class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class PractiseSet {

    public static void Bank(int Balance) throws InsufficientBalanceException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Welcome to the bank");
        System.out.println("Enter your withdraw Amount");

        int Withdraw = sc.nextInt();

        if (Withdraw > Balance) {
            throw new InsufficientBalanceException("Balance is Insufficient");
        } else {
            Balance = Balance - Withdraw;
            System.out.println("Your balance is = " + Balance);
        }
    }

    public static void main(String args[]) {
        try {
            Bank(5000);
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        }
    }
}
class Employee {
    private int Age ;
    private String Name;
    private int Roll ;

    public void setName(String Name) {
        this.Name = Name;
    }

    public void setAge(int Age) {
        this.Age = Age;
    }
    public void setRoll(int Roll){
      this.Roll=Roll;
    }
    public String getName(){
        return Name;
    }
    public int getRoll(){
        return Roll;
    }
    public int getAge(){
        return Age;
    }
}
public class PractiseSet{
    public static void main(String args []){
        Employee R=new Employee();
       R.setName("saurabh");
       R.setAge(78);
       R.setRoll(22);
       System.out.println(R.getName());
        }
    }
// Demonstrate method overriding with base and derived class
class Human{
     void name(){
        System.out.println("saurabh");
    }
    public  void age(){
        System.out.println("65");
    }
}
class Tiger extends Human{

      void name(){
        System.out.println("Tiger ginda hai ");
    }
    @Override
    public  void age(){
        System.out.println("85");
    }
}
public class PractiseSet{
    public static void main(String args[]){
        Tiger T=new Tiger();
        T.name();
    }
}
//4. Create a bank account class with inheritance.
class BankAccount{
    String AccountHolder;
    double Balance;
    BankAccount(String AccountHolder,double Balance){
        this.Balance=Balance;
        this.AccountHolder=AccountHolder;
    }
    void deposit(double Amount){
        Balance+=Amount;
        System.out.println(Amount + " deposited. New balance: " + Balance);
    }
    void withdraw(double Amount){
        if(Balance<=Amount){
            Balance-=Amount;
            System.out.println("Your balance is a withdraw your new balance is a "+Balance);
        }
    }
    void display() {
        System.out.println("Account Holder: " + AccountHolder);
        System.out.println("Balance: " + Balance);
    }
}
class SavingAccount extends BankAccount{

    SavingAccount(String AccountHolder, double Balance){
        super(AccountHolder,Balance);
    }
    void deposit(double Amount){
        Balance+=Amount;
        System.out.println(Amount + " deposited. New balance: " + Balance);
    }
    void withdraw(double Amount){
        if(Balance<=Amount){
            Balance-=Amount;
            System.out.println("Your balance is a withdraw your new balance is a "+Balance);
        }
    }
    void display() {
        System.out.println("Account Holder: " + AccountHolder);
        System.out.println("Balance: " + Balance);
    }
}

public class PractiseSet{
    public static void main(String args[]){
        SavingAccount T=new SavingAccount("Harry",5000);
        T.withdraw(400);
        T.deposit(3999);
        T.display();
    }
}
//Implement multiple inheritance using interface.
interface A {
    void r();
}
interface B{
    void namaste();
}
class C implements  A,B {
    public void r() {
        System.out.println("Hello sir ");
    }

    public void namaste() {
        System.out.println("Namaste sir ");
    }
}
public class PractiseSet {
    public static void main(String args[]) {
        C c=new C();
        c.r();
        c.namaste();

    }
}
// Abstract class with Template Method Pattern ..  ye shamjha me nhi aya hai time mile to dkeh lena
abstract class Game {

    // Template Method
    public final void play() {
        initialize();   // Step 1
        startPlay();    // Step 2d
        endPlay();      // Step 3
    }

    // Common method
    public void initialize() {
        System.out.println("Game Initialized.");
    }

    // Abstract methods (subclass must implement)
    abstract void startPlay();
    abstract void endPlay();
}

// Subclass 1
class Cricket extends Game {
    void startPlay() {
        System.out.println("Cricket Game Started. Enjoy the game!");
    }

    void endPlay() {
        System.out.println("Cricket Game Finished!");
    }
}

// Subclass 2
class Football extends Game {
    void startPlay() {
        System.out.println("Football Game Started. Enjoy the game!");
    }

    void endPlay() {
        System.out.println("Football Game Finished!");
    }
}

// Main Class
public class PractiseSet {
    public static void main(String[] args) {
        Game g1 = new Cricket();
        
        g1.play();

        System.out.println();

        Game g2 = new Football();
        g2.play();
    }
}

//. Create a mini ride-booking system with OOPs principles.
// ye karna hi hai shamajh me aya
public class PractiseSet{
    public static void main(String args[]){
        class User{
            private int userId;
            private String name;
            private String phone;
            private String location;
            User(String name, String userId, String phone,String location){
            }
        }
    }
}
 */
import java.util.ArrayList;
import java.util.Arrays;

public class PractiseSet{

    // Method that takes a String array and returns an ArrayList
    public static ArrayList<String> convertToArrayList(String[] arr) {

        // Step 1: Array ko list me convert karna
        ArrayList<String> list = new ArrayList<>(Arrays.asList(arr));

        // Step 2: Converted ArrayList wapas return karna
        return list;
    }

    public static void main(String[] args) {

        // Example input array
        String[] names = {"Harry", "Saurabh", "Java"};

        // Method ko call kar rahe hain
        ArrayList<String> result = convertToArrayList(names);

        // Output print karna
        System.out.println(result);
    }
}
