package xray.leetcode.enumeration;
import java.util.*;

/*
 * IDEA
 * 1. flood edge O, with some temp char #
 * 2. replace all O to X
 * 3. replace all # to O
 * 
 * CATCH recursion will cause stackoverflow, use queue, 
 * the board itself is visited, so we do not need to track that
 * 
 */

public class SurroundedRegions {
    private static char tc = '#'; //temp
    private static char fc = 'O'; //flood
    private static char rc = 'X';  //replace
    public void solve(char[][] board) {
        if(board==null){
            return;
        }
        int rowCount = board.length;
        if(rowCount<=1){
            return;
        }
        int colCount = board[0].length;
        if(colCount<=1){
            return;
        }
        
        for(int i=0;i<rowCount;i++){
            flood(board, rowCount, colCount, i, 0);
            flood(board, rowCount, colCount, i, colCount -1 );            
        }

        for(int j=1;j<colCount-1;j++){
            flood(board, rowCount, colCount, 0, j);
            flood(board, rowCount, colCount, rowCount-1, j);
        }
        
        //replace all not flooded
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<colCount;j++){
                if(board[i][j]==fc){
                    board[i][j]=rc;
                }
            }
        }
        //turn flood back to O
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<colCount;j++){
                if(board[i][j]==tc){
                    board[i][j]=fc;
                }
            }
        }
    }
    
    private void flood(char[][] board, int rowCount, int colCount, int posi, int posj){
        Queue<Pos> todo = new LinkedList<Pos>();
        todo.add(new Pos(posi,posj));
        
        while(!todo.isEmpty()){
            Pos pos = todo.remove();
            int i=pos.i;
            int j=pos.j;
            if(i<0||j<0||i>=rowCount||j>=colCount){
                continue;
            }
            if(board[i][j]!=fc){
                continue;
            }
            board[i][j]=tc;
            todo.add(new Pos(i-1,j));
            todo.add(new Pos(i+1,j));
            todo.add(new Pos(i,j-1));
            todo.add(new Pos(i,j+1));
        }
    }
    
    class Pos{
        int i;
        int j;
        Pos(int i,int j){
            this.i=i;
            this.j=j;
        }
    }
}
