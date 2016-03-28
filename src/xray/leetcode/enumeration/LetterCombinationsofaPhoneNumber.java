package xray.leetcode.enumeration;

import java.util.*;

public class LetterCombinationsofaPhoneNumber {
	//TIP use a string array for conversion
    static String[] letters = new String[]{" ", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        res.add(""); //TIP start with an empty string
        if(digits==null||digits.length()==0){
            return res;
        }

        for(int i=0;i<digits.length();i++){
            List<String> newres = new ArrayList<String>();
            for(String s : res){
                char c = digits.charAt(i);
                int num = c - '0';
                String chars = letters[num];
                for(int j=0;j<chars.length();j++){
                    StringBuilder buf = new StringBuilder(s);
                    buf.append(chars.charAt(j));
                    newres.add(buf.toString());
                }
            }
            res = newres;
        }
        return res;
    }
}
