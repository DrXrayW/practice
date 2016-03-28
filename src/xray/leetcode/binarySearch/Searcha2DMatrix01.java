package xray.leetcode.binarySearch;
/*
 * IDEA: one way is to do the number trick so that this is a binary search, 
 * another way is to search row then search column
 * 
 * binary search
 * 
 * This is the number trick method
 * 
 * TIP: since we only want a hit, so we have equal covered, so the recursion are strict < or > 
 */
public class Searcha2DMatrix01 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix==null){
            return false;
        }
        int rowCount = matrix.length;
        if(rowCount==0){
            return false;
        }
        int colCount = matrix[0].length;
        if(colCount==0){
            return false;
        }
        
        int left = 0;
        int right = rowCount*colCount - 1;
        
        while(left<=right){
            int mid = (left + right) / 2;
            int midValue = getValue(matrix, colCount, mid);
            if(midValue==target){
                return true;
            }else if(target < midValue){
                right = mid - 1;
            }else{
                left = left + 1;
            }
        }
        return false;
    }
    
    private int getValue(int[][] matrix, int colCount, int index){
    	int i = index / colCount;  //TIP simple like that, because we are all 0 based index :)
    	int j = index % colCount;
    	return matrix[i][j];
    }
}
