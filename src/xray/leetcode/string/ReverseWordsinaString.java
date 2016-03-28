package xray.leetcode.string;

/*
 * Given an input string, reverse the string word by word.

For example,
Given s = "the sky is blue",
return "blue is sky the".
 */
public class ReverseWordsinaString {
    public String reverseWords(String s) {
        //ask space, 
        //ask word
        //leading space, etc
        if(s==null){
            return s;
        }
        String t = s.trim();
        if(t.length()==0){
            return t;
        }
        
        StringBuilder buf = new StringBuilder();
        for(int i=t.length()-1;i>=0;i--){ //remove repeating spaces, reverse the string. 
            if( t.charAt(i)==' ' &&
                i<t.length()-2   &&
                t.charAt(i+1) == ' ' ){
                    //ignore
            }else{
                buf.append(t.charAt(i));
            }
        }
        
        
        int start = 0;
        while(start<buf.length()){
            while( start<buf.length() && buf.charAt(start)==' '){
                start++;
            }
            if(start==buf.length()){
                break;
            }
            int end = start;
            while( end<buf.length() && buf.charAt(end) != ' '  ){
                end++;
            }
            //end is out of range or end is space
            end --;
            
            //reverse everything between start and end
            int i=start;
            int j=end;
            while(i<j){
                //swap buf i and buf j
                char tmp = buf.charAt(i);
                buf.setCharAt(i, buf.charAt(j));
                buf.setCharAt(j, tmp);
                i++;
                j--;
            }
            
            //update start and end
            start = end + 1;
        }
        
        return buf.toString();
    }
}
