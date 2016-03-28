package xray.leetcode.math;

import java.util.ArrayList;
import java.util.List;

public class PascalsTriangleI {
    public List<List<Integer>> generate(int numRows) {
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	for(int i=0;i<numRows;i++){
    		result.add(getRow(i));
    	}
    	return result;
    }
	
    public List<Integer> getRow(int rowIndex) {
        List<Integer> row = new ArrayList<Integer>();
        int n = rowIndex;
        int nCk = 1;
        for (int k = 0; k <= n; k++) {
        	row.add(nCk);
            nCk = (int)((long)nCk * (long)(n-k) / (long)(k+1));  //tip 1: math trick to avoid factorial, tip 2: need to consider overflow by casting 
        }
        return row;
   }
}
