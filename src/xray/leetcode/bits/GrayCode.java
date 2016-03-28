package xray.leetcode.bits;

import java.util.ArrayList;
import java.util.List;
/*
 * 00 - 0
01 - 1
11 - 3
10 - 2

IN SHORT:
long gray = i^(i>>1) ;  


 */
public class GrayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<Integer>();
        int N = 32; 
        if((n<=0)||(n>N)){ 
            result.add(0); //OJ needs this, for n=0, want a 0 in the list
            return result;
        }
        
        long size = 1 << n;
        for(long i=0;i<size;i++){
            long gray = i^(i>>1) ;  //This is the trick: gray code = n ^ (n/2)
            result.add((int)gray);
        }
        
        return result;
    }
}
