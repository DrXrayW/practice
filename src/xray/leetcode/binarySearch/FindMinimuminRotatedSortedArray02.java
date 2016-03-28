package xray.leetcode.binarySearch;

/*
 * min must be in the range where left is higher than the right, 
 * otherwise, it is the left most 
 * 
 */
public class FindMinimuminRotatedSortedArray02 {
    public int findMin(int[] num) {
        if((num==null)||(num.length==0)){
            return -1;
        }
        int left = 0;
        int right = num.length - 2; //inclusive index
        //in this question, we can assume no dup, so no = used is fine
        while(left<=right&&num[left]>num[right+1] ){  //if start==end, then we found it; if the array is sorted, then left most is min
            int mid = (left + right) / 2;
            if(num[mid]>num[right+1]){ //because inclusive, mid could be start, so mid must be compared to end
            	left = mid + 1;
            }else{
                //3, 1, 2 : we cannot exclude mid, as mid could be the min
            	right = mid - 1;
            }
        }
        return num[left];
    }
}
