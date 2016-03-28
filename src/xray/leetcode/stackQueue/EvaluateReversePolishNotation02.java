package xray.leetcode.stackQueue;

import java.util.*;

/*
 * push operands to stack, and when we have an operator, calculate and push back
 */
public class EvaluateReversePolishNotation02 {
	
	interface Operator {
		int eval(int x, int y);
	}
	
	private static final Map<String, Operator> OPERATORS =
							new HashMap<String, Operator>() {{
									put("+", new Operator() {
										public int eval(int x, int y) { return x + y; }
									});
									put("-", new Operator() {
										public int eval(int x, int y) { return x - y; }
									});
									put("*", new Operator() {
										public int eval(int x, int y) { return x * y; }
									});
									put("/", new Operator() {
										public int eval(int x, int y) { return x / y; }
									});
								}};
								
	public int evalRPN(String[] tokens) {
		Stack<Integer> stack = new Stack<Integer>();
		for (String token : tokens) {
			if (OPERATORS.containsKey(token)) {
				int y = stack.pop();
				int x = stack.pop();
				stack.push(OPERATORS.get(token).eval(x, y)); //remember to reverse the two operands
			} else {
				stack.push(Integer.parseInt(token));
			}
		}
		return stack.pop();
	}
}
