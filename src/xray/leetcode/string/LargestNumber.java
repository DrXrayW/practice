package xray.leetcode.string;
import java.util.*;
public class LargestNumber {
	/*
	 * IDEA
	 * actually, every string has a key, by repeating itself to the end of the max length.
	 * This proved the correctness of the method. 
	 * 
	 * for comparator, we can still use s+t compareTo t+s
	 * 
	 * see the idea to use a+b comparing to b+a as comparator
	 
	public String largestNumber(int[] num) {
	    if(num == null || num.length == 0)
	        return "";

	    if(Arrays.stream(num).allMatch(n -> n == 0)){
	        return "0";
	    }
	    StringBuffer sb = new StringBuffer();
	    Arrays.stream(num)
	        .mapToObj(n -> String.valueOf(n))
	        .sorted((a, b) -> -((a + b).compareTo(b + a)))
	        .forEachOrdered(n -> sb.append(n));

	    return sb.toString();
	}
	*/
	/*
	if asked how to prove that the comparator would work, 
	we need to prove that comparator is transitive. 
	
	this can be illustrated by another way of comparator:
	
	OBSERVATION:
	say s is the shorter of the two 
	when s is a prefix of t, then the comparing of the result is to take s out of the head of t 
	recursively, then compare in lexical  order of what is not a repeating of s in the remaining t
	This guarantees the order and therefore transitive
	*/
	public String largestNumber(int[] numInput) {
		Integer[] num = new Integer[numInput.length];
		for(int i=0;i<numInput.length;i++){
			num[i] = numInput[i];
		}
		Arrays.sort(num, new Comparator<Integer>() {
	        @Override
	        public int compare(Integer i1, Integer i2) {
	        	String s = i1.toString();
	        	String t = i2.toString();
	            int i = 0;
	            while (i < s.length() && i < t.length()) {
	                if (s.charAt(i) < t.charAt(i)) {
	                    return -1;
	                } else if (s.charAt(i) > t.charAt(i)) {
	                    return 1;
	                } else {
	                    i++;
	                }
	            }
	
	            if (i == s.length() && i == t.length()) {
	                return 0;
	            } else if (i == s.length()) {
	                // s is shorter
	                return compare(Integer.valueOf(s), Integer.valueOf(t.substring(i)));
	            } else {
	                // t is shorter
	                return compare(Integer.valueOf(s.substring(i)), Integer.valueOf(t));
	            }
	        }
	    });
		StringBuilder buf = new StringBuilder();
		for(int i=0;i<num.length;i++){
			buf.append(num[i].toString());
		}
		return buf.toString();
	}
}
    
	
	
	

