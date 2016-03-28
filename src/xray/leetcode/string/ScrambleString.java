package xray.leetcode.string;
import java.util.*;
public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
        if(s1==null||s2==null||s1.length()!=s2.length()){
            return false;
        }
        char[] c1 = s1.toCharArray();
        char[] c2 = s2.toCharArray();
        Arrays.sort(c1);
        Arrays.sort(c2);
        if(!(new String(c1)).equals(new String(c2))){
            return false;
        }
        if(s1.length()==1){
            return true;
        }
        /*  i  break at i's left
        / 0 1 2 3 4 
                j>j
            j = cap - i = len - 1 - i, and we want the right of j, so the shifted j = len - i
            
            len = 5
            5 - 1 = 4
        */
        int len = s1.length();
        for(int i=1;i<len;i++){
            
            boolean res = isScramble(s1.substring(0, i), s2.substring(0, i)) &&
                           isScramble(s1.substring(i), s2.substring(i)) ||
                            isScramble(s1.substring(0, i), s2.substring(len-i)) &&
                           isScramble(s1.substring(i), s2.substring(0, len-i));
             if(res){
                 return true;
             }
            
        }    
        return false;
    }
}	
