package xray.leetcode.array;
import java.util.*;
public class MaximalRectangle {
	
	public static void main(String[] args) {
		MaximalRectangle s = new MaximalRectangle();
		
		//int x = s.maximalRectangle("", "b", "b");
		return;
	}
	
	/*
	 * this one uses the largest rectangle in histogram 
	 * 
	 * 1. construct height for each node (note that the matrix is char)
	 * 
	 * 2. loop through each row, find the max. 
	 */
	
   public int maximalRectangle(char[][] matrix) {
        if(matrix==null){
            return 0;
        }
        int rowCount = matrix.length;
        if(rowCount==0){
            return 0;
        }
        int colCount = matrix[0].length;
        if(colCount==0){
            return 0;
        }
        int[][] height = new int[rowCount][colCount];
        
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<colCount;j++){
                height[i][j]=1;
                if(matrix[i][j]=='0'){
                    height[i][j]=0;
                }else if(i>0){
                    height[i][j]+=height[i-1][j];
                }
            }
        }
        
        int max = 0;
        
        for(int i=0;i<rowCount;i++){
            max = Math.max(max, largestRectangleArea(height[i]));
        }

        return max;
    }
    
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
