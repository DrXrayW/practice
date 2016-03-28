package xray.leetcode.bits;

public class SingleNumber {
    public int singleNumber(int[] A) {
        if(A==null){
            return -1; //discuss
        }
        
        //xor so that double numbers will cancel themselves, leaving the only one
        int result = 0;
        for(Integer i : A){
            result^=i;
        }
        return result;
    }
}
