package xray.leetcode.dp;

import java.util.List;
/*
 * Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

For example, given the following triangle
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
 */
public class Triangle {
    public int minimumTotal(List<List<Integer>> triangle) {
        if(triangle==null){
            return 0;
        }
        
        //using the input parameter to same space
        //bottom up so that the min number is saved at the top
        
        for(int row=triangle.size()-2;row>=0;row--){
            List<Integer> curRow = triangle.get(row);
            List<Integer> lastRow = triangle.get(row+1);
            for(int col=0;col<=row;col++){
                int minPath = Math.min(lastRow.get(col), lastRow.get(col+1));
                curRow.set(col, curRow.get(col) + minPath);
            }
        }
        
        return triangle.get(0).get(0);
    }
}
