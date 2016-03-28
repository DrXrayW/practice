package xray.leetcode.enumeration;

public class ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                char c = board[i][j];
                if( (c>='1')&&(c<='9') ){
                    if(conflict(i,j,board,c)){
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    private boolean conflict(int row, int col, char[][] board, char curChar){
        //check row
        for(int i=0;i<9;i++){
            if(i!=col){
                if(curChar==board[row][i]){
                    return true;
                }
            }
        }
        //check col
        for(int i=0;i<9;i++){
            if(i!=row){
                if(curChar==board[i][col]){
                    return true;
                }
            }
        }
        
        //check current area
        int rowStart = row - (row % 3);
        int colStart = col - (col % 3);
        for(int i=rowStart;i<rowStart+3;i++){
            for(int j=colStart;j<colStart+3;j++){
                if((i!=row)||(j!=col)) {
                   if(curChar == board[i][j]) {
                       return true;
                   }
                }
            }
        }
        
        return false;
    }
}
