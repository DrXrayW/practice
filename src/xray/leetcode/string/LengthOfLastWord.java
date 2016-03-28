package xray.leetcode.string;

/*
 * 
 */
public class LengthOfLastWord {
    public int lengthOfLastWord(String s) {
        if(s==null){
            return 0;
        }

        //scan from the last index, start adding when it is a word char
        //stop counting when it reaches a space
        int len = 0;
        for(int i=s.length()-1;i>=0;i--){
            if(s.charAt(i)!=' '){
                len++;                
            }else if(len!=0){
                return len;
            }
        }
        return len; //this is in case there is no space
    }
}
