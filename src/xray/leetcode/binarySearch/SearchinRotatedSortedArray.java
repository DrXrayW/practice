package xray.leetcode.binarySearch;

/*
 * 
 * IN SHORT, if a part is sorted, but contains the target, then it must be there, 
 * if does not contain the target, then must not be there. 
 * 
 * 
 *  0   1   2   3   4
 * [5] [6] [1] [3] [4]
 * length = 5
 * 
 *  ^       ^       ^
 *  
 *  1. if A[start] <= A[end], then the part is sorted
 *  2. for sorted part, we know for sure that whether the value is in or not
 *  3. left or right, must be one sorted the other one not sorted.
 *  
 *  Using start = start index, end = end index + 1
 *  on case [1]
 *  start = 0, end = 1
 *  mid = 0
 *  
 *  So when there is only one, mid is checked
 *  
 *  left: start = start = 0, end = mid = 0
 *  right: start = mid + 1 = 1, end = end = 1
 *  
 *  this will end the loop, if using start!=end as loop condition
 *  
 *  so must make sure the values are used correctly
 *  
 *  splitting mid, always exclude mid in the next iteration
 * 
 */
public class SearchinRotatedSortedArray {
    public int search(int[] A, int target) {
        if( (A==null)|| (A.length==0) ){
            return -1;
        }
        
        int left = 0;
        int right = A.length; //note this trick: end is one larger than the end index, so that your while can be start!=end
        
        while(left!=right){
            int mid = (left + right) / 2;
            if(A[mid] == target){
                return mid;
            }
            
            if(A[left]<=A[mid]){  //left is sorted
                if( (A[left]<=target)&&(target<=A[mid]) ) {
                    right = mid;
                }else{
                    left = mid + 1;                    
                }
            }else{ //right is sorted
                if( (A[mid]<=target)&&(target<=A[right-1]) ) {
                    left = mid + 1;
                }else{
                    right = mid;                    
                }
            }
        }
        return -1;
    }
}
