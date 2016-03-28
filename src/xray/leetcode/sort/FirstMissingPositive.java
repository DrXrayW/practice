package xray.leetcode.sort;
/*
 * 
 * Given an unsorted integer array, find the first missing positive integer.

For example,
Given [1,2,0] return 3,
and [3,4,-1,1] return 2.

Your algorithm should run in O(n) time and uses constant space.

IN SHORT: 
every one put in their seat, unless the seat is already taken (by another same number). 
so if a seat is taken by some one else, the number is missing.
if not missing anything the number missing is n + 1, which is length + 1;

 */
public class FirstMissingPositive {
    public int firstMissingPositive(int[] A) {
        bucketSort(A);
        for(int i=0;i<A.length;i++){
            if(A[i]!=i+1){
                return i+1;
            }
        }
        //length = 3: 1 2 3, missing 4, length + 1 
        return A.length + 1; 
    }
    
    /*
     * Position i:  0 1 2 3 4 5 
     * target:      1 2 3 4 5 6
     * so A[i] = i + 1, is its target
     * so when it is not sorted, 
     * A[i] should be put into A[i] - 1 position, unless, it is already taken (  A[i] == A[A[i] -1] )
     */
    
    private void bucketSort(int []A){
        for(int i=0;i<A.length;i++){
            while(A[i]!=i+1){
                if( A[i]<=0 || A[i]>A.length || A[i]==A[A[i]-1] ){ 
                    break;
                }
                //swap A[i], with A[A[i] - 1], that is its right position
                int tmp = A[A[i]-1];
                A[A[i]-1] = A[i];
                A[i] = tmp;
            }
        }
    }
}
