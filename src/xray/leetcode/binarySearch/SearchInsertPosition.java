package xray.leetcode.binarySearch;

public class SearchInsertPosition {
    public int searchInsert(int[] A, int target) {
        if(A==null){
            return -1;
        }
        if(A.length==0){
            return 0;
        }
        return searchInsert(A, target, 0, A.length-1);
    }
    
    private int searchInsert(int[] A, int target, int start, int end){
        if(start == end){
            if(A[start]<target){
                return start+1;  //tip 1: only when the current one is smaller, the index should be next
            }else{
                return start;  
            }
        }
        
        int mid = (start + end) / 2;
        if(A[mid] == target){
            return mid;
        }
        
        if(target < A[mid]){
            if(start <= (mid-1)){
                return searchInsert(A, target, start, mid-1);
            }else{
                return mid;
            }
        }else{
            return searchInsert(A, target, mid+1, end);
        }
    }
}
