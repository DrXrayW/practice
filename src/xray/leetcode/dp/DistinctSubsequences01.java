package xray.leetcode.dp;

/*
 * 
 * if not match, the current one should be 1
but how does different ways adds up?

Given a string S and a string T, count the number of distinct subsequences of T in S.

A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without
 disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

Here is an example:
S = "rabbbit", T = "rabbit"

Return 3.

 * IDEA:
 * 
 * NOTE that T cannot be deleted, i.e. it is either a match, or a delete in S, 
 * 
 * So it is looking at i-1, j-1 for match and i - 1, j for delete S!!!!   
 * 
 * DP MEANING: f[i][j] means the number of possible subsequences for S[0,i] and T[0,j]
 * 
 *  Therefore when we delete S, the count is 1 (Initialization), only when moving along T if non match, it will reduce to 0
 *  
 *  Similarly, when we delete S, all count are carried to the right, also , if there is a match, we add them up. 
 *  
 *  TIP in this edit distance cases, move along which string is to delete on which string, i.e. one char is skipped on the string where the move happens
 *  TIP so in this case, we do not move along T, so only taking from the above line
 *  
 *      T 0   a   1   b   2   c   3     
 *  S 0   1       0       0       0
 *            \   
 *    1   1     match? f[i-1][j-1]  : 0 + f[i-1][j]  
 *           
 *    2   1
 *    
 *    3   1
 *       
 *    4   1
 *    
 */

public class DistinctSubsequences01 {
	public static void main(String[] args) {
		DistinctSubsequences01 s = new DistinctSubsequences01();
		int x = s.numDistinct("ccc", "c");
		return;
	}
	public int numDistinct(String S, String T) { 
        if(S==null||T==null||S.length()==0||S.length()<T.length()){
            return 0;
        }
        if(T.length()==0){
            return 0; //discuss
        }
	
        int len1 = S.length();
        int len2 = T.length();
        
        //int[][] f = new int[len1+1][len2+1];  
        
        /* init is replaced by condition in loop
        for(int i=0;i<len1+1;i++){  //TIP: Initialize first, when  
        	f[i][0] = 1; //the rests are 0
        }
         */
        int[] pre = new int[len2+1];  //TIP: delete the first dimension, also prepare a pre for rolling
        for(int i=0;i<len1+1;i++){ //loop start from 0
            int[] cur = new int[len2+1];
        	for(int j=0;j<len2+1;j++){    //loop start from 0
        		if(j==0){
            		cur[j] = 1;
            	}else if(i==0){
            		cur[j] = 0;
            	}else{
                    //int match = S.charAt(i-1) == T.charAt(j-1) ?  f[i-1][j-1] : 0 ;
	                int match = S.charAt(i-1) == T.charAt(j-1) ?  pre[j-1] : 0 ;
	                //f[i][j] = f[i-1][j] + match;
	                cur[j] = pre[j] + match; //the right side is i - 1
            	}
            }
        	pre = cur; //it is rolling after each i!!! 
        }
        
        //return f[len1][len2]; 
        return pre[len2]; //TIP remove one dimension 
    }
}
