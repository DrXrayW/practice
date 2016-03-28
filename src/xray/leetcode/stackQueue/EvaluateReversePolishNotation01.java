package xray.leetcode.stackQueue;

import java.util.*;

/*
 * push operands to stack, and when we have an operator, calculate and push back
 */
public class EvaluateReversePolishNotation01 {
	private static final Set<Character> OPERATORS =
			new HashSet<Character>(Arrays.asList('+', '-', '*', '/'));
	
	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<Integer>();
		for (String token : tokens) {
			if(token.length()==1){
				char c = token.charAt(0);
				if (OPERATORS.contains(c)) {
					int y = stack.pop();
					int x = stack.pop();
					stack.push(eval(x, y, c));
				}
			}
		}
		return stack.pop();
	}
	/*
	 * 
	 */
	private int eval(int x, int y, char operator) {
		switch (operator) {
			case '+': return x + y;
			case '-': return x - y;
			case '*': return x * y;
			default: return x / y;
		}
	}
}
