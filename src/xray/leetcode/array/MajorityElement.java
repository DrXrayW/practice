package xray.leetcode.array;
import java.util.*;
import java.util.Map.*;
/*
 * Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

You may assume that the array is non-empty and the majority element always exist in the array.

IDEA: count and output max

 */
public class MajorityElement {
    public int majorityElement(int[] num) {
        if(num==null||num.length==0){
            return -1;
        }
        
        //count
        Map<Integer, Integer> counts = new HashMap<>();
        for(int i=0;i<num.length;i++){
            if(counts.containsKey(num[i])){
                counts.put(num[i], counts.get(num[i]) + 1 );
            }else{
                counts.put(num[i], 1);
            }
        }
        int max = 0;
        int maxFreqValue = 0;
        for(Entry<Integer, Integer> entry : counts.entrySet()){
            if(max<entry.getValue()){
                max = entry.getValue();
                maxFreqValue = entry.getKey();
            }
        }
        
        return maxFreqValue;
    }
}
