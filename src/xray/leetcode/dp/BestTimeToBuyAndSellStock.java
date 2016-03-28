package xray.leetcode.dp;

public class BestTimeToBuyAndSellStock {
    //note that you can only buy one sell one, so find the maximum difference between a previous low price and a later high one
    public int maxProfit(int[] prices) {
        if( (prices==null)||(prices.length<2) ) {
            return 0;
        }
        
        int profit = 0;
        int curmin = prices[0];
        for(int i=1;i<prices.length;i++){
            profit = Math.max(profit, prices[i] - curmin);
            curmin = Math.min(curmin, prices[i]);
        }
        return profit;
    }
}
