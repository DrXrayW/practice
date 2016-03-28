package xray.leetcode.string;

/*
 * of k strinsg
 * 
 * IN SHORT
 * Comparing all others to the first string,
 * 
 *  if any one doesn't have the current char, or having a different one, then return the string before 
 *  (substring ends (exclusive) at this index)
 *  
 *  or return the first string
 * 
 */
public class LongestCommonPrefix01 {
    public String longestCommonPrefix(String[] strs) {
    	
    	//ask about null, empty array, also ask about null and empty string
        if( (strs==null)||(strs.length ==0) ){
            return "";
        }
        
        //it is important that empty strings and null has been taken care of
        for(String s : strs){
            if( (s==null) || (s.isEmpty()) ){
                return "";
            }           
        }
        /*compare each char from the first str to other, 
         * note that the substring i is one index ahead of the substring, 
         * so that is where it fails to match
         */
        for(int i=0;i<strs[0].length();i++){
            for(int k=1;k<strs.length;k++){
                if( ( strs[k].length() <= i ) ||
                    ( strs[k].charAt(i) != strs[0].charAt(i) ) ) {
                        return strs[0].substring(0, i);
                }
            }
        }
        
        return strs[0];
    }
}
