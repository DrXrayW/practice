package xray.leetcode.binarySearch;

public class FindMinimuminRotatedSortedArrayII {
    public int findMin(int[] num) {
        if((num==null)||(num.length==0)){
            return -1;
        }
        int start = 0;
        int end = num.length - 1; //start and end are indexes
        int min = num[0];
        while(start< (end - 1) ){
            int mid = (start + end) / 2;
            if(num[start] < num[mid]){ //the left part is sorted, so take its min and check the right part
                min = Math.min(min, num[start]); 
                start = mid + 1;
            }else if(num[start] > num[mid] ){ //left part is not sorted, taking the current mid as min, and continue on the left part
                min = Math.min(min, num[mid]);
                end = mid - 1;
            }else{
                start ++;
            }
        }
        //the following takes care of when there is only 1 or 2 items in the list
        //case 1: start=0, end = 0;
        //case 2: start=0, end = 1;
        min = Math.min(min, num[start]);
        min = Math.min(min, num[end]);
        return min;
    }
}
