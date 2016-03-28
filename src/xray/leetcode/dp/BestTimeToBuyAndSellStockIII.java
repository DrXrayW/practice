package xray.leetcode.dp;

public class BestTimeToBuyAndSellStockIII {
	/*
	 * IDEA:
	 * 
	 *  TIP: taking the diff between i and i +1, this becomes the max sub array problem
	 *  
	 *  Suppose this is the diff array
	 *  
	 *  Consider buying once, at each point we have the choice of continue or start new
	 *                    1,         -2,                  3,               -4,                 4,                    -5,            6,                   -7
	 *  sum1              1,    max(1-2, -2)=-1     max(-1+3, 3)=3    max(3-4, -4)=-1     max(-1, 4) = 4      max(4-5, -5)=-1    max(-1+6, 6)=6    max(6-7, -7)=-1
	 *  maxsum1           1,          1                    3                3                  4                      4               6                    6
	 *  
	 *  sum2              1                       
	 *  					  			          sum1+3=2, still the first subarray
	 *                                            or
	 *                                            3, but start 2nd subarray 
	 *  
	 *  local0  0          
	 *  local1  0    g0+1,L1+1=1  g0-2,L1-2=-1     g0+3,L1+3=3,2=3  g0-4,L1-4=-4,-1=-1     g0+4,L1+4=4,3=4         -1               6                -1                  
	 *  local2  0    g1+1,L2+1=1  g1-2,L2-2=-1     g1+3,L2+3=4,2=4  g1-4,L2-4=-1,0=0       g1+4,L2+4=7,4=7       
	 *  
	 *  global0 0                        
	 *  global1 0    g1,L1=1         1                  3                3                        4                4                  6                  6
	 *  global2 0    g2,L2=1         1                  4                4                        7 
	 *  
	 *  
	 *  
	 *  global j, is the max sum of j subarrays.
	 *  
	 *  local j, is the sum of max sum of j -1 subarrays and the extending one
	 *  
	 *  so for local j, it either takes the maximum of sum of j -1 subarrys, and start a new one;  or extend on its own
	 *  
	 *  Question: when going global[j-1] + diff, it is also extending, why use up one time?
	 *  
	 *  Consider the j = 1 case:
	 *  
	 *  global[j-1]= global[0] = 0 always
	 *  local[j]=local[1] = max(global[0] + (diff>0? diff:0), local[1]+diff))
	 *  = max(0 + diff>0? diff : 0, local[1]+diff)
	 *  
	 *  TIP: the diff>0? is redundant, for if diff < 0, the next guy will go direct with global[j-1],
	 *  
	 *   = max(0 + diff, local[1]+diff) : meaning, the sum of starting a new one here, or continue with the last one, 
	 *   
	 *  also the negative diff won't help the global.
	 *  
	 *  So it is ACTUALLY:
	 *  
	 *  max(global[j-1] + diff, local[j]+diff);
	 *  
	 *  means, either build on top of global[j-1], or extending the current
	 *  
	 *  NOTE that global[j-1] is the best j - 1 can do already, so adding on top is definitely for one more subarray j. 
	 *  
	 *  
	 *  
	 *  we were using:
	 *  max(diff, local[j] + diff)
	 *  
	 */
	
    public int maxProfit02(int[] prices) {
        if(prices==null||prices.length==0){
            return 0;
        }
        int k = 3;
        int[] local = new int[k];
        int[] global = new int[k];
        /*this part is just for clearance, as Java initialized 0 for us
        for(int i=0;i<k;i++){
            local[i]=0;
            global[i]=0;
        }
        */
        for(int i=0;i<prices.length-1;i++){
            int diff = prices[i+1] - prices[i];
            for(int j=k-1;j>=1;j--){
                local[j] = Math.max(global[j-1]+diff, local[j]+diff);
                global[j] = Math.max(global[j], local[j]);
            }
        }
        return global[k-1];
    }
    
    public int maxProfit(int[] prices) {
        if(prices==null || prices.length==0){
            return 0;
        }
        int local1 = 0;
        int global1 = 0;
        int local2 = 0;
        int global2 = 0;
        for(int i=0;i<prices.length-1;i++){
            int diff = prices[i+1] - prices[i];
            int preLocal1 = local1;
            int preGlobal1 = global1;
            local1 = Math.max(preLocal1+diff, diff);
            global1 = Math.max(preGlobal1, local1);
            
            //local2 = Math.max(local2 + diff, preGlobal1 + (diff>0? diff:0) ); 
            local2 = Math.max(local2 + diff, preGlobal1 + diff); //TIP: either extend (must add), or start a new one using one less step sum
            global2 = Math.max(local2, global2);
        }
        return global2;
    }
    

    
    public int maxProfit01(int[] prices) {
        if(prices==null || prices.length==0)
            return 0;
        int[] local = new int[3];
        int[] global = new int[3];
        for(int i=0;i<prices.length-1;i++)
        {
            int diff = prices[i+1]-prices[i];
            for(int j=2;j>=1;j--)
            {
                local[j] = Math.max(global[j-1]+(diff>0?diff:0), local[j]+diff);
                global[j] = Math.max(local[j],global[j]);
            }
        }
        return global[2];
    }
}
