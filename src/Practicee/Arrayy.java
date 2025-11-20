package Practicee;

public class Arrayy {
    public static void main(String args[]) {
        /*
        // 2. Write a program to sort an array without using Arrays.sort()
        int arr[]={5,65,23,74,563,444,34534,436345,34};
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++){
                if(arr[i]<arr[j]){
                    int sau=arr[i];
                    arr[i]=arr[j];
                    arr[j]=sau;
                }

            }
        }
        for(int x:arr){
            System.out.print(x+" ");
        }
        System.out.println();
        for(int k=arr.length-1;k>=0;k--){
            System.out.print(arr[k]+" ");
        }


        ////1. Write a program to find the largest and smallest elements in an array
        int arr[]={54,64,36,74,843,87,4,2,11111};
        int o=arr[0];
        for(int i=0;i<arr.length;i++){

            if( arr[i]>o){
               o=arr[i];
            }
        }
        System.out.println(o);
    }
     Write a program to reverse an array in place.. isko kal dekhata hu


        //. Write a program to find duplicate elements in an array.
        int arr[]={45,63,63,7,9,5,9,45};
        for(int a=0;a<arr.length;a++){
            for(int x=0;x<arr.length;x++){
                if(arr[a]==arr[x]){
                    System.out.println( arr[x]);
                }
            }
        }


        //Write a program to find the largest and smallest elements in an array.
        int arr[] = {34, 44, 65, 66};
        int largest = arr[0];
        int smallest = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > largest) {
                largest = arr[i];
            }
            if (arr[i] < smallest) {
                smallest = arr[i];
            }
        }
        System.out.println(largest);
        System.out.println(smallest);

         */
        ////Write a program to remove all spaces from a string without using replace().
        String ar="hello saurabh how are you";
        for(int a=0;a<ar.length();a++){
            char ch=ar.charAt(a);
            if(ch==' '){
                continue;
            }else{
                System.out.print(ch);
            }
        }
    }
}

