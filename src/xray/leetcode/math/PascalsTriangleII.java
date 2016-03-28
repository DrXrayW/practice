package xray.leetcode.math;

import java.util.ArrayList;
import java.util.List;
/*
 * [
     [1],
    [1,1],
   [1,2,1],
  [1,3,3,1],
 [1,4,6,4,1]
]

output the row of rowIndex

tip 1: math trick to avoid factorial

nCk = (int)((long)nCk * (long)(n-k) / (long)(k+1))

tip 2: need to consider overflow by casting to long

 */
public class PascalsTriangleII {
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
