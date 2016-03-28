package xray.leetcode.dp;

public class CoinPickingPizza {
	public static void main(String[] args) {
		CoinPickingPizza s = new CoinPickingPizza();
		int[] a = new int[]{2, 9, 1, 8, 1, 7, 1, 1};
		int max = s.maxPick(a);
		System.out.println(max);
		return;
	}
	
	private int maxPick(int[] a) {
		if(a==null){return 0;}
		int len = a.length;
		int sum=0;
		for(int ai : a){
			sum += ai;
		}
		int max = -1;
		Integer[][] dp = new Integer[len][len];
		for(int i=0;i<len;i++){
			max = Math.max(max, sum - maxPick(a, len, i+1, i+len-1, sum-a[i], dp)); //do not forget to use sum - for both final value and passed down sum
		}
		return max;
	}
	
	private int maxPick(int[] a, int len, int start, int end, int sum, Integer[][] dp){
		//manually shift start and end
		//only when accessing array we mod the index, otherwise, as if the start and end are shifted
		int si = start%len;
		int ei = end%len;
		if(dp[si][ei]!=null){
			return dp[si][ei];
		}
		if(start>end){
			return 0;
		}
		if(start==end){
			return sum;
		}
		int takeLeft = sum - maxPick(a, len, start+1, end, sum-a[si], dp);
		int takeRight = sum - maxPick(a, len, start, end-1, sum-a[ei], dp);
		int max = Math.max(takeLeft, takeRight);
		dp[si][ei] = max;
		return max;
	}
}
