public class YO {
    public static void main(String args[]) {
        int[] A = {0,2,4,1,3};
        for(int i = 0; i < A.length; i++){
            A[i] = A[(A[i] + 3) % A.length];
            System.out.println(A[1]);
        }
    }
}


