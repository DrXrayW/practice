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
public class SearchForARange {
    public int[] searchRange(int[] A, int target) {
        int[] res = {-1,-1};  //TIP use an array for the result, and pass through recursion
        if(A==null||A.length==0){ //TIP nothing to find, not possibly a match, 
            return res;            
        }
        searchRange(A, 0, A.length-1, target, res); //using the inclusion boundaries
        return res;
    }
    
    private void searchRange(int[]A, int left, int right, int target, int[] res){
        if(left>right){ //TIP: Protection against invalid ranges, convenient 
            return;
        }
        int mid = (left + right) / 2;
        if(A[mid]==target){  //TIP when we have a match: 
            if( (mid-1>=0)&&(A[mid-1]==target) ){   //TIP when its left side exists and also hit the target 
                searchRange(A, left, mid - 1, target, res); //TIP need to find the first target, note that mid - 1 is a look ahead, so not excluded in next round
            }else{
                res[0] = mid; //TIP otherwise, it is the left boundary
            }
            if( (mid+1<A.length)&&(A[mid+1]==target) ){ //TIP when its right side exists and also hit the target
                searchRange(A, mid + 1, right, target, res); //need to find the last target, again, include mid + 1
            }else{
                res[1] = mid; //TIP otherwise, it is the right boundary (could be the same as the left one)
            }
            
            if( (res[0]!=-1) && (res[1]!=-1) ){ //after the above set to res, if res are all found, return! 
                return;
            }
            
        }else if(target<A[mid]){ //TIP when strict not found, like binary search go left and right
            searchRange(A, left, mid - 1, target, res);
        }else{ //A[mid] > target
            searchRange(A, mid + 1, right, target, res);
        }
    }
}
