package xray.leetcode.math;
/*
 * IN SHORT:
 * 1 reverse strings so that we can start from the beginning (the index would be more convenient)
 * 2. multiply each digits, and put the result in the corresponding position, i + j
 * 3. deal with the carry result from left to right (low to high)
 * 4. use a stringbuilder for result also use its insert(0, digit)to always append to its first char
 * 5. delete any heading 0 using deleteCharAt(0) 
 * 
 */
public class MultiplyString {
    public String multiply(String num1, String num2) {
        if( num1==null||num1.length()==0||num2==null||num2.length()==0){
            return ""; //discuss
        }
        
        //TIP reverse two strings
        String n1 = new StringBuilder(num1).reverse().toString();
        String n2 = new StringBuilder(num2).reverse().toString();
        
        //TIP array of digit length sum
        int[] d = new int[num1.length()+num2.length()];
        
        //TIP put in the right place, which is i+j
        for(int i=0;i<num1.length();i++){
            int a = n1.charAt(i) - '0';
            for(int j=0;j<num2.length();j++){
                int b = n2.charAt(j) - '0';
                d[i+j] += a*b;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<d.length;i++){
            int carry = d[i] / 10;
            if(i<d.length-1){ //TIP carry should never happen at the top digits, but we need to protect index overflow
                d[i+1] += carry;
            }
            int digit = d[i] % 10;
            sb.insert(0, digit);  //TIP remember to insert the DIGIT!! at 0
        }
        
        while((sb.charAt(0) == '0')&&(sb.length()>1)){  //TIP remember the length needs to be > 1 before removing!!
            sb.deleteCharAt(0); //TIP remember this
        }
        
        return sb.toString();
    }
}
