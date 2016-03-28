package xray.leetcode.array.matrix;

/*
 * IN SHORT: remember the transitions
 * 
 *       j  0 1 2
 *    i 0   1 2 3
 *      1   4 5 6
 *      2   7 8 9
 *      
 *      [clockwise 90 degree]
 *      
 *      first:
 *      
 *      flip i and j: swap(matrix, i, j, j, i), i=0;i<len, j=0;j<i, 
 *      
 *       j  0 1 2
 *    i 0   1 4 7
 *      1   2 5 8
 *      2   3 6 9
 *      
 *      then exchange j from left to right : swap(matrix, i, j, i, len-1-j), i=0;i<len, j=0;j<len/2, 
 *      
 *       j  0 1 2
 *    i 0   7 4 1
 *      1   8 5 2
 *      2   9 6 3
 *
 *      TIP: up and down: i (first index);  left and right: j (second index);
 *      
 *      [anti-clockwise 90 degree]
 *      
 *      flip i and j : swap(matrix, i, j, j, i), i=0;i<len, j=0;j<i, 
 *      
 *       j  0 1 2
 *    i 0   1 4 7
 *      1   2 5 8
 *      2   3 6 9
 *      
 *      then exchange i from up to down: swap(matrix, i, j, len-1-i, j) , i=0;i<len/2, j=0;j<len, 
 *      
 *       j  0 1 2
 *    i 0   3 6 9
 *      1   2 5 8
 *      2   1 4 7
 *      
 *      [180 degree]
 *      
 *      flip i and j around:swap(matrix, i, j, len-1-i, len-1-j), i=0;i<len, j=0;j<i / k=0;k<len/2 swap(matrix, k, k, len-1-k, len-1-k)
 *      There is one catch, if one node is swapped twice, then it is wrong. 
 *      So what is left after the diag mirror? half of the diag itself. so 
 *      
 *      
 *      
 *      
 */      
public class RotateImage {
    public void rotate(int[][] matrix) {
        if((matrix==null)||(matrix.length<=1)){
            return;
        }
        int len = matrix.length;
        int cap = len - 1;
        //swap i, j
        for(int i=0;i<len;i++){  
            for(int j=0;j<i;j++){ //TIP: switching i and j, the inner loop is j<i
                swap(matrix, i, j, j, i);
            }
        }
        for(int i=0;i<len;i++){
            for(int j=0;j<len/2;j++){    //TIP: Flapping one side around, the inner loop condition is j<len/2 
                swap(matrix, i, j, i, cap - j);
            }
        }
    }
    
    private void swap(int[][] matrix, int i1, int j1, int i2, int j2){
        int tmp = matrix[i1][j1];
        matrix[i1][j1] = matrix[i2][j2];
        matrix[i2][j2] = tmp;
    }
}
