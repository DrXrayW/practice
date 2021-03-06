package xray.leetcode.math;

import java.util.*;

/*
 * add number
 * find val as sum of two
 * allow dup number
 * 
 */
public class TwoSumIII {
    private Map<Integer, Integer> map = new HashMap<>();
	public void add(int input) {
		int count = map.containsKey(input) ? map.get(input) : 0;
		map.put(input, count + 1);
	}
	
	public boolean find(int val) {
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			int num = entry.getKey();
			int y = val - num;
			if (y == num) {
				// For duplicates, ensure there are at least two individual numbers.
				if (entry.getValue() >= 2){
					return true;
				}
			}else if (map.containsKey(y)) {
				return true;
			}
		}
		return false;
	}
}
