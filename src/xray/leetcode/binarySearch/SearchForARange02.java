package xray.leetcode.binarySearch;

/*
 * Given a sorted array of integers, find the starting and ending position of a given target value.
Your algorithm's runtime complexity must be in the order of O(log n).
If the target is not found in the array, return [-1, -1].
For example,
Given [5, 7, 7, 8, 8, 10] and target value 8,
return [3, 4].


IN SHORT: like a binary search but instead finding the range of the dup target. 
So when < and > apply normal Binary Search, 
When we have a hit in the middle, we look at mid - 1 and mid + 1, if they are dups, binary search in the left for left index, binary search in the right for right boundary



 * 
 * IDEA: binary search, while found left or right, continue to find the other part
 * 
 */
public class SearchForARange02 {
    public int[] searchRange(int[] A, int target) {
        int[] res = {-1,-1};  //TIP use an array for the result, and pass through recursion
        if(A==null||A.length==0){ //TIP nothing to find, not possibly a match, 
            return res;            
        }
        searchRange(A, 0, A.length, target, res, false, false); //using the inclusion boundaries
        return res;
    }
    
    private void searchRange(int[]A, int left, int right, int target, int[] res, boolean searchLeft, boolean searchRight){
        if(left>=right){ //TIP: Protection against invalid ranges, convenient, correspond to left < right
            return;
        }
        int mid = (left + right) / 2;
        if(target==A[mid]){
    		if( (mid-1<0)||(A[mid-1]!=target) ){   
    			res[0] = mid;
    		}else{
    			searchLeft = true;
    		}
    		if( (mid+1>=A.length)||(A[mid+1]!=target) ){   
    			res[1] = mid;
    		}else{
    			searchRight = true;
    		}
    		if( (res[0]!=-1)&&(res[1]!=-1)){
    			return;
    		}
        }
        /*This one cannot use iteration as the search left and right cannot be done at the same time.
         * It is like: find the equal then fork to find left and right
         */
        if(target<A[mid]||searchLeft ){ 
        	searchRange(A, left, mid-1,target,res, searchLeft, searchRight);
        }
        if(target>A[mid]||searchRight ){
        	searchRange(A, mid+1, right,target,res, searchLeft, searchRight);
        }
    }
}
