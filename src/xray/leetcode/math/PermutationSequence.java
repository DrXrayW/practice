package xray.leetcode.math;

/*
 * IN SHORT:
 * 
 * get d = k / (n-1)!  for each number
 * d needs to be converted to the next one NOT used! 
 * use a stringbuilder to save all the unused in order
 * 
 * n! factorial 
 * 
 * IDEA
 * 
 * for k numbers, we have totally k! permutations. 
 * 
 * So suppose n = 6
 * 
 * if we look at the numbers after the first one, that is a n - 1 (5) long sequence
 * 
 * so the possibilities of permutations is (n - 1)!
 * 
 * we know that each number is put in the first digit for this 
 * 
 * So, 1 as first number, there are (n-1)! permutations 
 *     2 as first number, there are (n-1)! permutations
 *     
 * So it is like the radix calculation, 
 * 
 * we want the number 
 * k / (n-1)!
 * 
 * Here is one CATCH, k should start from 0, to match with the smallest permutation
 * 
 * For example: n - 1 = 3
 * 
 *  1 2 3
 *  1 3 2
 *  2 1 3
 *  2 3 1
 *  3 1 2
 *  3 2 1
 *  
 *  we use 6/6 = 1, then that would push the one to the next number, also 5/6 and 6/6 should have the same number
 *  
 *  So k should start from 0
 *  
 *  Another CATCH:
 *  
 *  When we get the d = k / (n-1)!
 *  
 *  what does d mean? d means we want the d-th smallest chars that is UNUSED yet!
 *  
 *  So we can use a StringBuilder for that purpose, remember to remove the char from the builder
 * 
 * 
 * 
 */

public class PermutationSequence {
    public String getPermutation(int n, int k) {
        StringBuilder b = new StringBuilder();
        if(n==1&&k==1){
            return "1";
        }
        int fact = 1;
        //find (n-1)!
        for(int i=1;i<=(n-1);i++){
            fact *= i;
        }
        int r = k-1; 
        
        StringBuilder unused = new StringBuilder();
        for(int i=0;i<n;i++){
            unused.append(i+1);
        }
        
        for(int i=n-1;i>=0;i--){ //0 1 2 3 4 5,  from n - 1 to 0,  n times, we need n chars
            int d = r / fact; //note that in the unused string, the char is index + 1 at the beginning.
            b.append(unused.charAt(d)); //so we can use d = r / fact; which is 0 based calculation
            if(i==0){ //no need to do anything for the next loop
                break;
            }
            unused.deleteCharAt(d);
            r=r%fact;
            fact=fact/i;
        }
        return b.toString();
    }
}
