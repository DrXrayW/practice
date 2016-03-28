package xray.leetcode.stackQueue;
import java.util.*;
/*
 * The one before is a boundary (of a valid region so far), not part of the max local  
 * Consider this case:
 * v                j  i
 * (()()()()()()()()(())
 * 
 * 
 * 
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        if(s==null||s.length()==0) {
            return 0;
        }
        int start     = -1; //here start means the one BEFORE start
        int max = 0;
        Stack<Integer> stack   = new Stack<Integer>();
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i)=='(') {
                stack.push(i);
            } else { //get ')'
                if(!stack.empty()) { // have a matching (
                    stack.pop();   //pop the matching (
                    if(stack.empty()) {  //if that is the last one
                        max = Math.max(i - start , max); //then this is the max of the local, including i, excluding start, i - start
                    } else { //this is not the last one
                        max = Math.max(i - stack.peek() , max); 
                    }
                } else { //invalid, the right most ) cannot find a matching (, have to discard all
                    start = i;
                }
            }
        }
    
        return max;
    }
}
