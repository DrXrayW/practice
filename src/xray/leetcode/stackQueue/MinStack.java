package xray.leetcode.stackQueue;

import java.util.Stack;

public class MinStack {
	private Stack<Integer> stack = new Stack<Integer>();
	private Stack<Integer> minStack = new Stack<Integer>();
	
    public void push(int x) {
    	stack.push(x);
    	if((minStack.isEmpty())||(minStack.peek() >= x)){ //if new is no greater (handling dup) than top then push to min 
    		minStack.push(x);
    	}
    }

    public void pop() {
        int pop = stack.pop();
        if(minStack.peek() == pop){ //if the same element is popped
        	minStack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() { //the top is always the min, peek()
        return minStack.peek();
    }
}
