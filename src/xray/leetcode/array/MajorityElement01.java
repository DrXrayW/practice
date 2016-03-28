package xray.leetcode.array;
import java.util.*;
import java.util.Map.*;
/*
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

IDEA: count and output max

 Moore voting algorithm: We maintain a current candidate and a counter initialized to 0. As we iterate the array, we look at the current element x:
If the counter is 0, we set the current candidate to x and the counter to 1.
If the counter is not 0, we increment or decrement the counter based on whether x is the current candidate.

 */
public class MajorityElement01 {
    public int majorityElement(int[] num) {
        if(num==null||num.length==0){
            return -1; //exception maybe, or assert
        }
        
        int vote = 1;
        int majority = num[0];
        for(int i=1;i<num.length;i++){
            if(num[i]==majority){
                vote++;
            }else{
                vote --;
                if(vote==0){
                    majority = num[i];
                    vote = 1;
                }
            }
        }
        return majority;
    }
}
