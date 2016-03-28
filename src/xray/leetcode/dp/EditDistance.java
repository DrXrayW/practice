package xray.leetcode.dp;

/**
 * this is edit distance, 
 * @author xray
 * 
 *      \  word 1    A  B  C 
 * word2
 *      B
 *      S           
 *
 */
public class EditDistance {
	public int minDistance(String word1, String word2) {
        if(word1==null||word2==null){
            return -1;
        }
        int len1 = word1.length();
        int len2 = word2.length();
        
        int[][] f = new int[len1+1][len2+1];  //TIP: the array is +1 larger in each dimensions. 
        for(int i=0;i<len1+1;i++){ //TIP: initialize the bounds!!
        	f[i][0] = i;
        }
        for(int j = 0;j<len2+1;j++){
        	f[0][j] = j;
        }
        
        for(int j=1;j<len2+1;j++){  //TIP: start from one, 
            for(int i=1;i<len1+1;i++){ 
                int delete1 = f[i][j-1] + 1;   //IDEA delete from 1, delete from 2, or replace
                int delete2 = f[i-1][j] + 1;    
                int match = (word1.charAt(i-1) == word2.charAt(j-1) ? 0 : 1) + f[i-1][j-1];
                f[i][j] = Math.min(match, Math.min(delete1, delete2)); //TIP: we want edit distance, not match, go min!!!
            }
        }

        return f[len1][len2]; //TIP return the last number 
    }
}
