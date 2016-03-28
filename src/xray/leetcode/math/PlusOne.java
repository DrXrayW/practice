package xray.leetcode.math;

/*
 * Given a non-negative number represented as an array of digits, plus one to the number.

The digits are stored such that the most significant digit is at the head of the list.
 * 
 * Example Questions Candidate Might Ask:
Q: Could the number be negative?
A: No. Assume it is a non-negative number.
Q: How are the digits ordered in the list? For example, is the number 12 represented by [1,2] or
[2,1]?
A: The digits are stored such that the most significant digit is at the head of the list.
Q: Could the number contain leading zeros, such as [0,0,1]?
A: No.

 */
public class PlusOne {
    public int[] plusOne(int[] digits) {
        if(digits==null){
            return null;
        }
        //mark a carry bit, init as true as plus one
        int carry = 1;
        //check digits[length-1] if leading 0 is not allowed
        for(int i=digits.length-1;i>=0;i--){
            int r = digits[i] + carry;
            digits[i] = r%10;
            carry = r/10;
        }
        
        if(carry==0){
           return digits;
        }
        //if the last digit carries, need to create a new array
        int[] newDigits = new int[digits.length+1];
        newDigits[0] = 1;
        for(int i=1;i<newDigits.length;i++){ 
            newDigits[i] = digits[i-1];  //mind the index here!
        }
        return newDigits;
    }
}
