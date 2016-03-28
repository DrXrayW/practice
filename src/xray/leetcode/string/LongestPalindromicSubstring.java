package xray.leetcode.string;

/*
 * Given a string S, find the longest palindromic substring in S. 
 * You may assume that the maximum length of S is 1000, 
 * and there exists one unique longest palindromic substring.
 * 
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        if(s==null||s.length()==0){
            return "";
        }
        /* IDEA test all the centers of palindrome
          0 1 2 : len 3
          a b c
          01234 : len 5=3*2 : i
          
          note that all i on char is even, all i between char is odd
          
          so left = i/2, either the one char i at , or the char before
          right = i/2 + i%2, either the one char i at, or the char after. 
        */
        int max = 0;
        String longestStr = "";
        for(int i=0;i<s.length()*2 - 1; i++){
            int left = i/2;
            int right = i/2 + i%2;
            String str = getLongestPalindrome(s, left, right);
            if(str.length()>max){
                max = str.length();
                longestStr = str;
            }
        }
        return longestStr;
    }
    
    //TIP get the maximum expansion at this center
    private String getLongestPalindrome(String s, int left, int right){
        while(left>=0&&right<s.length()&&s.charAt(left)==s.charAt(right)){
            left--;
            right++;
        }
        /* 0 1 2 3 4 
           l       r
           
           note that left and right are one greater than the longest one
        */
        return s.substring(left+1, right);
    }
}
