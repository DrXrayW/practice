package xray.leetcode.binarySearch;

/**
 * Note that there is assumption that the array increases then decreases, without equal elements
 * 
 * It is binary search, while checking the mid and comparing to its left and right.
 * 
 * !! do not confuse index with content!!!
 * 
 * @author xray
 *
 */

public class FindPeakElement {
    public int findPeakElement(int[] num) {
        if( (num==null)||(num.length==0) ) {
            return -1; //error handling
        }
        int left = 0;
        int right = num.length;
        while(left!=right){
            int mid = (left + right) / 2;
            int leftValue = Integer.MIN_VALUE;   //trick, when left or right doesn't exist, let them be min, so that we still get peak when the length is 1, or 2
            int rightValue = Integer.MIN_VALUE;
            if( (mid-1)>= 0 ) {
                leftValue = num[mid - 1];
            }
            
            if( (mid+1)<num.length) {
                rightValue = num[mid + 1];
            }
            
            if(leftValue>num[mid]){
                right = mid;
            }else if(num[mid] < rightValue){
                left = mid + 1;
            }else{
                return mid;
            }
        }
        return -1;
    }
}
