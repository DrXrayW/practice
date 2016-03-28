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
public class EditDistance01 {
	public int minDistance(String word1, String word2) {
        if(word1==null||word2==null){
            return -1;
        }
        int len1 = word1.length();
        int len2 = word2.length();
        /*
        for(int i=0;i<len1+1;i++){ //TIP: initialize the bounds!!
        	f[i][0] = i;
        }
        for(int j = 0;j<len2+1;j++){
        	f[0][j] = j;
        }
        */
        int[] pre = new int[len2+1]; 
        for(int i=0;i<len1+1;i++){
        	int[] cur = new int[len2+1];  
        	for(int j=0;j<len2+1;j++){  //TIP: start from one,
        		if(i==0){
        			cur[j] = j;
        		}else if(j==0){
        			cur[j] = i;
        		}else{
	                int delete1 = cur[j-1] + 1;   //IDEA delete from 1, delete from 2, or replace
	                int delete2 = pre[j] + 1;    
	                int match = (word1.charAt(i-1) == word2.charAt(j-1) ? 0 : 1) + pre[j-1];
	                cur[j] = Math.min(match, Math.min(delete1, delete2)); //TIP: we want edit distance, not match, go min!!!
        		}
            }
        	pre = cur;
        }

        return pre[len2]; //TIP return the last number 
    }
}
