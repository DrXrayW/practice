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
 */

public class DistinctSubsequences {
	public int numDistinct(String S, String T) { 
        if(S==null||T==null||S.length()==0||S.length()<T.length()){
            return 0;
        }
        if(T.length()==0){
            return 0; //discuss
        }
	
        int len1 = S.length();
        int len2 = T.length();
        
        int[][] f = new int[len1+1][len2+1];  //TIP: the array is +1 larger in each dimensions.
        for(int i=0;i<len1+1;i++){  //TIP: Initialize first,
        	f[i][0] = 1; //the rests are 0, because deleting all is a way to match empty, that is 1
        }
        
        for(int i=0;i<len1;i++){ 
        	for(int j=0;j<len2;j++){   
                int match = S.charAt(i) == T.charAt(j) ?  f[i][j] : 0 ;
                f[i+1][j+1] = f[i][j+1] + match;
            }
        }

        return f[len1][len2]; //TIP return the last number 
    }
}
