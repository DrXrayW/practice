package xray.leetcode.binarySearch;
/*
 * IDEA: one way is to do the number trick so that this is a binary search, 
 * another way is to search row then search column
 * 
 * 
 * This is the row then column method
 * 
 * TIP: since we only want a hit, so we have equal covered, so the recursion are strick < or > 
 */
public class Searcha2DMatrix {
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
        int right = rowCount - 1;
        Integer theRow = null;
        while(left<=right){
            int mid = (left + right) / 2;
            if( (matrix[mid][0]<=target)&&(target<=matrix[mid][colCount-1]) ){  //TIP we want a hit, so equal is fine
                theRow = mid;
                break;
            }else if(target < matrix[mid][0]){ 
                right = mid - 1;
            }else{
                left = mid + 1;
            }
        }
        if(theRow==null){
            return false;
        }
        int[] row = matrix[theRow];
        
        left = 0;
        right = colCount - 1;
        while(left<=right){
            int mid = (left + right) / 2;
            if(row[mid]==target){
                return true;
            }else if(target < row[mid]){
                right = mid - 1;
            }else{
                left = left + 1;
            }
        }
        return false;
    }
}
