package xray.leetcode.math;

/*
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.

For example:

    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
 * 
 * Like a number, not a number
 */
public class ExcelSheetColumnNumber {
    public String convertToTitle(int n) {
        StringBuilder buf = new StringBuilder();
        if(n<=0){
            return buf.toString();
        }
        
        int r = n; 
        while(r>=0){
            int d = r % 26; 
            if(d==0){ //0 means 26, Z
                d=26;
            }
            char c = (char)(d + (int)'A' - 1); //From 1 to 26 map to A to Z
            buf.append(c);
            r-=d; //deduct from remainder
            r/=26;
            if(r==0){
                break;
            }
        }
        return buf.reverse().toString();
    }
}
