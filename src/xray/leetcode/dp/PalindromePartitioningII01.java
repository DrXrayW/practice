package xray.leetcode.dp;


public class PalindromePartitioningII01 {
	public static void main(String[] args) {
		PalindromePartitioningII01 s = new PalindromePartitioningII01();
		
		int x = s.minCut("abba");
		return;
	}

	/*
	 * double dp
	 *  
	 *  dp matrix, for whether sub string i to j inclusive is a palindrome 
	 *  
	 *  only the upper half and the diagonal are used. 
	 *  
	 *  basically, the char at position i and j should match  
	 *             then either (an auto match when i == j) or j - 1 ==1 (aa case)
	 *             or the unit at left and down is True  
	 * 
	 *    
	 *    
	 *    c   a  a   c
	 *    0   1  2   3  j
	 * 0  T          T
	 * 1  -   T  T
	 * 2  -   -  T    
	 * 3  -   -   -  T
	 * i
	 * 
	 * 
	 * we also need a cut array to note down the min count of cut from i to end needs. 
	 * 
	 * the recursion formulat is
	 * cut[i] =  min(cut[i], cut[j+1]+1), when we have a new palindrome identified (a true set to the dp matrix).
	 * 
	 * the idea is, we know that i to j is a palindrome, so the position from i to j must be the same cut, so the cut of i can be the min of what it has now, and cut[j+1]+1
	 * 
	 *  Although it is true that cut[i] = min(cut[i], cut[j]) would take the same result from cut j, but this formula cannot take when cut from i-j is not in the same palindrome
	 *  so the j+1 is needed to take what is rest.
	 *  
	 *  example
	 *        
	 *  a b b  
	 *  2 0 0  
	 *      
	 * suppose we are at the a      
	 * a itself is a palindrome, but it needs to take what is after it, to use the result we have from bb  
	 * 
	 * 
	 * 
	 */
    public int minCut(String s) {
        int min=0;
        if(s==null||s.length()==0){
            return min;
        }
        int len = s.length();
        
        int[] cut=new int[len+1];
        for(int i=0;i<len+1;i++){
            cut[i] = len - 1 - i; //cap - i, reverse
        }
        
        boolean[] pre = new boolean[len];
        for(int i=len-1;i>=0;i--){
        	boolean[] cur = new boolean[len];
            for(int j=i;j<len;j++){
                if(s.charAt(i) == s.charAt(j)){ //char match
                    if( (j-i<=1)|| //for even case, aa, and the same char, just pass, no need to check i +1 in bound as j would control that
                        (pre[j-1]) ){
                            //palindrome from i to j
                    		cur[j] = true;
                            cut[i] = Math.min(cut[i], cut[j+1]+1); //the meaning of +1 +1
                    }
                }
            }
            pre = cur;
        }
        return cut[0];
    }
}
