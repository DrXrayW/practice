package xray.leetcode.array;
/*
 * IN SHORT:

because the water is determined by the two highest bars around the position, (the min of the two - ai)

scan twice, 

the dp matrix is the waterlevel for each point

left to right, the highest bar seen so far for each i.
right to left, the highest bar seen so far for each i. 

while scanning, get the min of left and right for each position

finally, get water level - bar height at i


 */
public class TrappingRainWater {
    public int trap(int[] A) {
        if( (A==null)||(A.length<3)){
            return 0; //discuss
        }
        int length = A.length;        
        //DP
        //highest height for this point
        //t[i] the 
        int[] t = new int[length];
        //left to right
        int max = t[0];
        for(int i=0;i<length;i++){
        	max = Math.max(max, A[i]);
            t[i] = max;
        }
        
        //right to left
        max = A[length - 1];
        for(int i=length-1;i>=0;i--){
        	max = Math.max(max, A[i]);
            t[i] = Math.min(t[i], max);
        }
        
        //take the total of (min threshold - height) 
        /*
         * we can also 
         */
        int total = 0;
        for(int i=0;i<length;i++){
            total += (t[i] - A[i]); //this cannot go below 0, as t[i] is the max from both side, which is at least A[i], we 
        }
        
        return total;
    }
}
