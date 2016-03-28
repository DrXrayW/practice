package xray.leetcode.binarySearch;

public class SearchInsertPosition01 {
    public int searchInsert(int[] A, int target) {
        if(A==null){
            return -1;
        }
        if(A.length==0){
            return 0;
        }
        
        int start = 0;
        int end = A.length - 1; //inclusive
        
        while(start<=end){ //update index is better than recursion!!
            int mid = (start + end) / 2; // start + end will never be end, unless they are the same
            if(A[mid]<target){ 
                start = mid + 1; //this is straightforward: target belongs to the right 
            }else{ //target <= A[mid], even == target should go left, i.e. taking the position of equal
            	/*
            	 * because we want to insert target left to all other numbers that are larger or equal
            	 * so that even an equal should put the target to the left part.
            	 * until the start 0 is reached
            	 * 
            	 */
                end = mid - 1; 
            }
        }
        
        return start;
    }
}
