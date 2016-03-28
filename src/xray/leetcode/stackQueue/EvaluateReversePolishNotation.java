package xray.leetcode.stackQueue;

import java.util.Stack;

/*
 * push operands to stack, and when we have an operator, calculate and push back
 */
public class EvaluateReversePolishNotation {
    public int evalRPN(String[] tokens) {
        if((tokens==null)||(tokens.length==0)){
            //error handling
        }
        
        //ask for assumptions of tokens
        
        Stack<Integer> stack = new Stack<Integer>();
        for(String token : tokens){
            if(token.equals("+")){
                int n2 = stack.pop();
                stack.push(stack.pop() + n2);  //NOTE!! the one first popped out is n2!!!!
            }else if(token.equals("-")){
                int n2 = stack.pop();
                stack.push(stack.pop()- n2);
            }else if(token.equals("*")){
                int n2 = stack.pop();
                stack.push(stack.pop() * n2);
            }else if(token.equals("/")){
                int n2 = stack.pop();
                stack.push(stack.pop() / n2);
            }else{
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }
}
