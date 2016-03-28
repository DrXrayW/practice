package xray.leetcode.math;

/*
 * IN SHORT
 * right to left, find the first one that drops, (if no, then reverse)
 * note it the droping one
 * again, right to left, find first that is larger than the dropping one (must have)
 * swap the dropping one and the larger one
 * reverse the decreasing part 
 * 
 * IDEA
 * 
 * big idea is that:
 * to extract a larger number to a left position to make the permutation larger
 * 
 * we know that a permutation with no ascending order e.g. 5 5 3 3 1 is already at its maximum
 * 
 * So the steps:
 * 1. identify the maximum group in the right, if the whole permutation is, then the next is the min, reverse array
 *   
 *             [       ]  
 *   7 8 8 2 2 5 5 3 3 1
 * 
 * 2. select the one number left to the maximum group as the one to be switched in to the group
 * 
 *           Lead
 *           v  
 *   7 8 8 2 2 5 5 3 3 1
 *  
 * 3. next step is to find the first one (from right) that is larger than the selected one
 *  
 *           Lead    newLead
 *           v       v
 *   7 8 8 2 2 5 5 3 3 1
 * 
 * 4. swap the two numbers
 * 
 *           Lead    newLead
 *           v       v
 *   7 8 8 2 3 5 5 3 2 1
 * 
 * 5. now the one after lead is still a maximum, we should start from minimum, reverse it
 * 
 *           Lead    
 *           v [       ]
 *   7 8 8 2 3 5 5 3 2 1
 *   7 8 8 2 3 1 2 3 5 5
 */
public class NextPermutation {
    public void nextPermutation(int[] num) {
        if(num==null||num.length<2){
            return;
        }
        
        //find first decreasing
        int i=num.length - 1;
        while(i>0&&(num[i-1]>=num[i])){ //find num[i-1] < num[i], so lead should be i - 1
            i--;
        }
        if(i==0){
            reverse(num, 0, num.length-1);
            return;
        }
        
        int lead = i-1;
        //find the first one that is larger than i-1
        i=num.length - 1;
        while(i>lead&&num[i]<=num[lead]){
            i--;
        }
        int nextlead = i;
        swap(num, lead, nextlead);
        reverse(num, lead+1, num.length-1);
    }
    private void swap(int[] num, int lead, int nextlead){
        int tmp = num[lead];
        num[lead] = num[nextlead];
        num[nextlead] = tmp;
    }
    private void reverse(int[] num, int left, int right){
        while(left<right){
            swap(num, left, right);
            left++;
            right--;
        }
    }
}
