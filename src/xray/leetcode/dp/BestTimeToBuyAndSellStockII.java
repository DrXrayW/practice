package xray.leetcode.dp;

public class BestTimeToBuyAndSellStockII {
	/*
	 * IDEA: This one is easy: you gain any profit by all the increases between days, and ignore the drops 
	 * or looking at the delta array from i=1 to len-1, prices[i] - prices[i-1], get all positive p
	 * 
	 * if we can short, then get all abs :)
	 * 
	 */
    public int maxProfit(int[] prices) {
        if( (prices==null)||(prices.length<2) ) {
            return 0;
        }
        
        int profit = 0;
        
        for(int i=1;i<prices.length;i++){
            if(prices[i]>prices[i-1]){
                profit+=prices[i]-prices[i-1]; // or it could be Math.max(0, prices[i]-prices[i-1])
            }
        }
        return profit;
    }
}
