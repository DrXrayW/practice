package xray.leetcode.string;

/*
 * IN SHORT, iterate n-1 times, with s starts with "1"
note that != doesn't work on null


 */
public class CountAndSay {
    public String countAndSay(int n) {
        String s = "1";
        for(int i=1;i<n;i++){
            s = getNext(s);
        }
        return s;
    }
    private String getNext(String s){
        StringBuilder buf = new StringBuilder();
        Character c = null;
        Integer count = null;
        for(int i=0;i<s.length();i++){
            if(c==null){
                c = s.charAt(i);
                count = 1;
            }else if(c!=s.charAt(i) ){
                buf.append(""+count);
                buf.append(c);
                
                c = s.charAt(i);
                count = 1;
            }else{ //c == s.charAt(i)
                count++;
            }
        }
        if(c!=null){
            buf.append(""+count);
            buf.append(c);
        }
        return buf.toString();
    }
}
