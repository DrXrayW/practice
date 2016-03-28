package xray.leetcode.string;

public class OneEditDistance01 {
    public boolean isOneEditDistance(String s, String t) {
        if(s==null||t==null){
            return false;
        }
        int len1 = s.length();
        int len2 = t.length();
        if(len1 > len2){ //making sure s is shorter than t
        	return isOneEditDistance(t, s);
        }
        //len1 <= len2
        int shift = len2 - len1;
        if(shift>1){ //length diff too much
        	return false;
        }
        
        //shift 0 or 1
        int i=0;
        while(i<len1&&s.charAt(i)==t.charAt(i)){ //move along if they match
        	i++;
        }
        if(i==len1){ //match till end of first string
        	return shift == 1; //s is the shorter one, if all match but t is one longer than yes, otherwise, shift==0, no distance
        }
        //the char at i doesn't match
       /*both jump ahead, e.g. ignoring one char in both, 
        * i.e. one replace, only available when shift == 0
        */
        if(shift == 0){ 
        	i++;
        }
        /* shift == 0, ignore one missing, already one edit distance, all needs to match
         * 
         * if shift == 1, only t needs to jump ahead
         */
        while(i<len1&&s.charAt(i)==t.charAt(i+shift)){
        	i++;
        }
        //both case, they all needs to match until the end
        return i == len1;
    }
}
