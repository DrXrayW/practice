package xray.leetcode.string;

public class LongestCommonPrefix {
    public String longestCommonPrefix(String[] strs) {
        if( (strs==null)||(strs.length ==0) ){
            return "";
        }

        String oneStr = null;
        for(String s : strs){
            if(s==null){
                return "";
            }else{
                oneStr = s;
            }            
        }
        
        int i = 0;
        boolean stop = false;
        while(!stop){
            Character tmp = null;
            for(String s : strs){
                if(s.length()<=i){
                    stop = true;
                    break;
                }
                if(tmp==null){
                    tmp = s.charAt(i);
                }else if(tmp != s.charAt(i)){
                    stop = true;
                    break;
                }
            }
            i++;
        }
        i--; //with the numbering, i means the count of matching chars, but substring needs i to be one ahead, plus there is no way to go for empty string;
        if(i<0){
            return "";
        }
        return oneStr.substring(0, i);
    }
}
