package xray.leetcode.array;

import java.util.HashSet;
import java.util.Set;

/*
 * 
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.

For example,
Given [100, 4, 200, 1, 3, 2],
The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.

Your algorithm should run in O(n) complexity.
 * 
 *while straight forward implementation would be sorting then scan, extend the length while consecutive 
 * 
 * IN SHORT

For each number in the array, expand to its left and right for the longest expansion. 
This looks like O(n^2)
However, while expanding, we can remove the ones we have visited from the list, during expansion, as their length already have been covered. 


 */
public class LongestConsecutiveSequence {
    public int longestConsecutive(int[] num) {
        if((num==null)||(num.length==0) ) {
            return 0;
        }
        //runs in O(n) time and O(n) space
        Set<Integer> set = new HashSet<Integer>();
        for(Integer i : num){
            set.add(i);
        }
        
        int max = 0;
        for(Integer n : num){
            int length = 0;
            //left
            int i = n;
            while(set.contains(i)){
                length ++; //for the current one
                set.remove(i);  //remember to remove the visited ones, as it won't be necessary to look at it in the main loop
                i --;
            }
            
            //right
            i = n+1;
            while(set.contains(i)){
                length ++; //for the current one
                set.remove(i);
                i ++;
            }
            max = Math.max(max, length);
        }
        return max;
    }
}
