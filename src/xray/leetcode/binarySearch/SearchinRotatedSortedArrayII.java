package xray.leetcode.binarySearch;

public class SearchinRotatedSortedArrayII {
	/*
	 * 	 * //allow dup
		IN SHORT like the other one, but when target equal to A[mid], start ++

	 * allowing duplicated would result in extreme cases like:
	 * [3,3,3,3,3,3,3,1,2,3,3]
	 * 
	 * 
	 * where we have no idea where the 1,2 maybe, as we are seeing 3 in start, med and end
	 * 
	 * the solution is to limit the sorted judgment to strict < or > 
	 * 
	 * Note that the complexity is now O(n)
	 * 
	 * Why start++ helps?
	 * 
	 * 
	 * because when A[start]==A[mid]==A[end-1]
	 * that means either the left is a constant sequence or the right is a constant sequence (if not both)
	 * so with start ++, and if there is a different value, the start or the mid will run into one. 
	 *  
	 */
    public boolean search(int[] A, int target) {
        if( (A==null)|| (A.length==0) ){
            return false;
        }
        
        int start = 0;
        int end = A.length; //note this trick: end is one larger than the end index, so that your while can be start!=end
        
        while(start!=end){
            int mid = (start + end) / 2;
            if(A[mid] == target){
                //return mid;
                return true;
            }
            
            if(A[start]<A[mid]){  //left is sorted
                if( (A[start]<=target)&&(target<=A[mid]) ) {
                    end = mid;
                }else{
                    start = mid + 1;                    
                }
            }else if(A[start]>A[mid]){ //right is sorted
                if( (A[mid]<=target)&&(target<=A[end-1]) ) {
                    start = mid + 1;
                }else{
                    end = mid;                    
                }
            }else{
                start ++;
            }
        }
        return false;
    }
}
