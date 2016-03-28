package xray.leetcode.dp;

public class CoinPicking {
	public static void main(String[] args) {
		CoinPicking s = new CoinPicking();
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
		Integer[][] dp = new Integer[len][len];
		return maxPick(a, 0, len-1, sum, dp);
	}
	
	private int maxPick(int[] a, int start, int end, int sum, Integer[][] dp){
		if(dp[start][end]!=null){
			return dp[start][end];
		}
		if(start>end){
			return 0;
		}
		if(start==end){
			return sum;
		}
		int takeLeft = sum - maxPick(a, start+1, end, sum-a[start], dp);
		int takeRight = sum - maxPick(a, start, end-1, sum-a[end], dp);
		int max = Math.max(takeLeft, takeRight);
		dp[start][end] = max;
		return max;
	}
}
