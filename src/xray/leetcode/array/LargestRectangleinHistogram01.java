package xray.leetcode.array;

import java.util.Stack;

public class LargestRectangleinHistogram01 {
	/*
	 * Use a stack, if get a higher one than top, then push the higher one
	 * if get a lower one, then pop the stack
	 * 
	 * The area is : 
	 * 			when stack is empty after the pop, then i*height[
	 * 
	 * 
	 * When we want to calculate the area of a rectangle
	 *           []
	 *         [][] v
	 *   []  [][][][] 
	 * [][]  [][][][][]
	 * [][][][][][][][][]
	 * 
	 * what stops us is the last height that is lower than the current one, 
	 * that is the maximum we can go to the left. 
	 * 
	 * To handle this: 
	 * 
	 * Maintain a stack of heights that are possibly smaller than the current one, 
	 * 		So the stack should add any higher ones along the way
	 * 		But when we have a non-increasing one, the stack pops, 
	 *      until the stack top is lower than the current one. 
	 *      
	 *      Stack will be in increasing order, the top one will always be the last one that is lower than the current one, (if exists). 
	 *      
	 * Stack popping: 
	 *      when we pop a stack, we know that it is the first one that reaches that height, 
	 *      even if we do not remember the height of its left neighbor, it must be lower than the popped one, 
	 *      So for the popped one, we get the area from the popped index until i-1. 
	 *      
	 *      The first popped one is i-1. 
	 *      
	 *      The next popped ones, they are known to be shorter than the popped ones in this batch, so they can all go to i - 1;
	 *      
	 *      Note that the area is calculated only when one is popped. 
	 *      
	 *  while processing a popped one      
	 * 		but stack is empty
	 * 		that means no one is lower than the popped one, so the processed one is the lowest of all seen ones, it can go all the way to the beginning
	 * 
	 * 	while scanning, stack empty is not really a big deal, we just ignore the checking, just push the current one, 
	 * (because it is possibly a lower bound for the next ones)	
	 *       
	 */
    public int largestRectangleArea(int[] height) {
        if(height==null||height.length==0){
            return 0;
        }
        Stack<Integer> s = new Stack<Integer>();
        int maxArea = 0;
        for(int i=0;i<height.length;i++){
            while(  (!s.isEmpty()) &&(height[i]<=height[s.peek()])    ){   //TIP stack is for index, so do not confuse index and value!!!
                maxArea = maxAreaPop(s, height, i, maxArea);
            }
            s.push(i);
        }
        
        while(!s.isEmpty()){
            maxArea = maxAreaPop(s, height, height.length, maxArea);
        }
        
        return maxArea;
    }
    
    private int maxAreaPop(Stack<Integer> s, int[] height, int right, int maxArea){ //exclusion right
        int pop = s.pop();
        int popH = height[pop];
        // TIP
        //(before)     [right -1]   (right), so right - 1 - before, 
        // before is only an index boundary, so when stack is empty, our area is bounded at -1
        int before = s.isEmpty() ? - 1: s.peek(); 
        int curArea = popH * (right - 1 - before);
        return Math.max(maxArea, curArea);
    }
}
