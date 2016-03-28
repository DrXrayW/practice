package xray.leetcode.math;

import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        if(numbers==null){
            return null;
        }
        if(numbers.length==0){
            return null;
        }
        Map<Integer, Integer> lookup = new HashMap<Integer, Integer>();
        for(int i=0;i<numbers.length;i++){
            int key = target - numbers[i];
            Integer idx = lookup.get(key);
            if(idx!=null){
                return new int[]{idx+1, i+1};
            }
            lookup.put(numbers[i], i);
        }
        return null;
    }
}
