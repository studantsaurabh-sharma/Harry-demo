package Practicee;
import java.util.*;
public class Stringgg {
    /*
//1. Write a program to reverse a string without using built-in methods.
    String you="saurabh";
    for(int a=you.length()-1;a>=0;a--){
        System.out.print(you.charAt(a));
    }

    // 2. Write a program to check if two strings are anagrams

            String sau="saurabh";
            String sau1="sauarha";
            if(sau.length()!=sau1.length()){
                System.out.println("Not Anagram");
            }
            sau=sau.toLowerCase();
            sau1=sau1.toLowerCase();
            int arr[]=new int[26];
            for(int i=0;i<sau.length();i++){
                char s=sau.charAt(i);
                arr[s-'a']++;
            }
            for(int i=0;i<sau1.length();i++){
                char s=sau1.charAt(i);
                arr[s-'a']--;
            }
            boolean x=true;
            for(int i=0;i<26;i++){
                if(arr[i]!=0){
                    x=false;
                }
            }
            if(x){
                System.out.println("Anagram");
            }   else
                System.out.println("Not Anagram");
    //Write a program to check if a number is prime or not.
    Scanner sc=new Scanner(System.in);
    int x=sc.nextInt();
    boolean isprime=true;
    if(1<=0){
        isprime=false;
    }else{
        for(int i=2;i<=x/2;i++){
            if(x%i==0){
                isprime=false;
            }
        }
    }
    if(isprime){
        System.out.println("prime");
    }else{
        System.out.println("notPrime");
    }


    //Write a program to reverse a number.
    Scanner sc = new Scanner(System.in);
    int num=sc.nextInt();
    int rev=0;
    while(num!=0){
        int paisha=num%10;
        rev=rev*10+paisha;
        num=num/10;
    }
    System.out.println(rev);


    // Replace all vowels in a string with *
    //Q9. Find the first vowel in a string.
    //👉 Example: "skyapple" → "a{
public static void main(String args[]){
    String l= "skyapple";
    for(int a=0;a<l.length();a++){
        char ch = l.charAt(a);
        if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u'){
            System.out.println(ch);
            break;
        }
    }
}


//Write a program to find duplicate elements in an array
public static void main (String args[]){
    int arr[]={5,765,23,8,10,5,2,23,8};
    for(int a=0;a<arr.length;a++){
        for(int t=a+1;t<arr.length;t++){
            if(arr[a]==arr[t]){
                System.out.println(arr[a]);
            }
        }
    }
}
        public static void main(String[] args) {
            String str = "hello world";

            System.out.println("Duplicate letters and their frequency:\n");

            for (int i = 0; i < str.length(); i++) {
                char ch = str.charAt(i);
                int count = 0;

                // ignore spaces
                if (ch == ' ') {
                    continue;
                }

                // count how many times ch appears
                for (int j = 0; j < str.length(); j++) {
                    if (str.charAt(j) == ch) {
                        count++;
                    }
                }

                // check if this character was already printed before
                boolean alreadyPrinted = false;
                for (int k = 0; k < i; k++) {
                    if (str.charAt(k) == ch) {
                        alreadyPrinted = true;
                        break;
                    }
                }

                // print only if duplicate and not printed before
                if (count > 1 && !alreadyPrinted) {
                    System.out.println(ch + " = " + count + " times");
                }
            }
        }


    // ✅ Method that returns the longest word
    public static void main(String args[]) {
            String wo = "Hello Saurabh How Are Youu";
            String one = "";
            String two = "";
            char ch;
            for (int a = 0; a <= wo.length(); a++) {
                    if (a < wo.length()) {
                            ch = wo.charAt(a);
                    } else {
                            ch = ' ';
                    }
                    if (ch != ' ') {
                            one += ch;
                    } else {

                            if (two.length() < one.length()) {
                                    two = one;
                            }
                            one = "";
                    }
            }
            System.out.println(two);
    }
    //Capitalize the first letter of each word in a sentence.
    //Example:"hello world
    public static void main(String args[]){
            String p="hello saurabh how are you";
            String result="";
            for(int a=0;a<p.length();a++){
                    char ch=p.charAt(a);
                    if(a==0||p.charAt(a-1)==' '){
                          result+=Character.toUpperCase(ch);
                    }else{
                            result+=ch;
                    }
            }
            System.out.println(result);
    }

    // Find the word with the maximum vowels in a sentence.
    // 👉 Example: "I love programming
    public static void main(String args[]) {
        String wo = "I Love Programming";
        String one = "";
        String two = "";
        char ch;
        for (int a = 0; a <= wo.length(); a++) {
            if (a < wo.length()) {
                ch = wo.charAt(a);
            } else {
                ch = ' ';
            }
            if (ch != ' ') {
                one += ch;
            } else {
                if (two.length() < one.length()) {
                    two = one;
                }
                one = "";
            }
        }
        System.out.println(two);
    }

    //Write a program to find the second largest number in an array.
    public static void main(String args[]){
        int v[];
        int arr[]={45,4,63,653,600000000,78,};
        for(int a=0;a<arr.length;a++){
            if(arr[0]<arr[a]){
                arr[0]=arr[a];
            }
        }
        System.out.println(arr[0]);
    }

    // Online Java Compiler
// Use this editor to write, compile and run your Java code online
    public static void main(String[] args) {

        String str = "hellooakk99123!!!!@";

        int vowels = 0;
        int consonants = 0;
        int digits = 0;
        int special = 0;
        for (int a = 0; a < str.length(); a++) {
            char ch = str.charAt(a);
            if (ch >= 'a' && ch <= 'z') {
                if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                    vowels++;
                }else{
                    consonants++;
                }
            }
            else if(ch>='1'&&ch<='9'){
                digits++;
            }else{
                special++;
            }
        }
        System.out.println("Vowels: " + vowels);
        System.out.println("Consonants: " + consonants);
        System.out.println("Digits: " + digits);
        System.out.println("Special Characters: " + special);
    }
    //Write a program to find duplicate elements in an array.
    public static void main(String args[]){
        int ar=0;
        int arr[]={1,2,3,4,2,3,5};
        for(int a=0;a<arr.length;a++){
            for(int b=a+1;b<arr.length;b++){
                if(arr[a]==arr[b]){
                    System.out.println(arr[b]);
                    break;

                }
            }
        }
    }

     */
    //Convert alternate words to uppercase.
    //👉 "i love javascript coding
    public static void main(String args[]) {
        char ch;
        int count=0;
        String ab="";
        String alt = "i love javascript coding";
        for(int a=0;a<=alt.length();a++){
            if(a<alt.length()){
              ch=alt.charAt(a);
            }else{
                ch=' ';
            }if(ch!=' ') {
                ab += ch;
                count++;
            }
            if(count/2!=0){
                ab.toUpperCase();
                System.out.print(ab+" ");
            }else{
                System.out.print(ab+" ");
            }
        }

    }
}
