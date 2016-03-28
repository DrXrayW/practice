package xray.leetcode.sort;

public class MergeSortedeArray {
    public void merge(int A[], int m, int B[], int n) {
        //ask about null and empty arrays
        
        int put = m + n - 1;
        int tailA = m - 1;
        int tailB = n - 1;
        if( (m>0)&&(n>0) ){
            while(put>=0){
                if( A[tailA]>=B[tailB] ){
                    A[put] = A[tailA];
                    tailA--;
                    put--;
                    if(tailA<0){
                        break;
                    }
                }else{
                    A[put] = B[tailB];
                    tailB--;
                    put--;
                    if(tailB<0){
                        break;
                    }
                }
            }
        }
        
        while(tailA>=0){
            A[put] = A[tailA];
            tailA--;
            put --;
        }
        
        while(tailB>=0){
            A[put] = B[tailB];
            tailB--;
            put --;
        }

    }
}
