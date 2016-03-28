package xray.leetcode.binarySearch;

public class FindMinimuminRotatedSortedArrayII01 {
    public int findMin(int[] num) {
        if((num==null)||(num.length==0)){
            return -1;
        }
        int start = 0;
        int end = num.length - 1; //inclusive index
        //why using num[start]>=num[end]?? because if num[start]<num[end] we know that the start to end is sorted, then the first must be the one
        //note that dup won't get into this, as the start++ only happens when we have 3 same at start, mid and end
        while(start<end&&num[start]>=num[end] ){  //if start==end, then we found it; if the array is sorted, then left most is min
            int mid = (start + end) / 2;
            if(num[mid]>num[end]){ //because inclusive, mid could be start, so mid must be compared to end
            	start = mid + 1;
            }else if(num[start]>num[mid]){
                //3, 1, 2 : we cannot exclude mid, as mid could be the min
            	end = mid;
            }else{
            	start++;
            }
        }
        return num[start];
    }
}
