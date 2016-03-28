package xray.leetcode.math;

/*
 * sorted array, 
 * shrinking window
 */
public class TwoSumII {
	public int[] twoSum(int[] numbers, int target) {
		// Assume input is already sorted.
		int i = 0, j = numbers.length - 1;
		while (i < j) {
			int sum = numbers[i] + numbers[j];
			if (sum < target) {
				i++;
			} else if (target < sum) {
				j--;
			} else {
				return new int[] { i + 1, j + 1 }; //+1 because it is using 1 based start
			}
		}
		throw new IllegalArgumentException("No two sum solution");
	}
}
