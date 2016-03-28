package xray.leetcode.sort;

public class MergeSortedeArray01 {
    public void merge(int A[], int m, int B[], int n) {
        //ask about null and empty arrays
        
        int put = m + n - 1;
        int tailA = m - 1;
        int tailB = n - 1;
        //put from end so no overwriting problem
        while( (tailA>=0)&&(tailB>=0) )  {
            if( A[tailA]>=B[tailB] ){
                A[put] = A[tailA];
                tailA--;
            }else{
                A[put] = B[tailB];
                tailB--;
            }
            put--;
        }
        
        while(tailB>=0){
            A[put] = B[tailB];
            tailB--;
            put --;
        }

        /* the result is put in A, so no need to do it for A
        while(tailA>=0){
            A[put] = A[tailA];
            tailA--;
            put --;
        }
        */
    }
}
