package xray.leetcode.enumeration;
import java.util.*;
public class WordSearch {
    public boolean exist(char[][] board, String word) {
        if(board==null||word==null){
            return false;
        }
        if(word.length()==0){
            return false;
        }
        int rowCount = board.length;
        if(rowCount==0){
            return false;
        }
        int colCount = board[0].length;
        if(colCount==0){
            return false;
        }
        if(word.length()>rowCount*colCount){
            return false;
        }
        boolean[][] visited = new boolean[rowCount][colCount];  
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<colCount;j++){
                if(dfs(board, word, 0, i, j, visited)){ //TRAP 1: do not create the array each time!!  
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, String word, int index, int row, int col, boolean[][] visited){
        if(index==word.length()){  //next index reaches end of word
            return true; 
        }
        if(board[row][col]==word.charAt(index)){
            if(index==word.length()-1){  //TRAP 2: if you are judging on extension, need a right stop here, or it will be one step further
            	
                return true;  
            }
            /*
             * TIP!!!
             * The difference between without the above fix and the other working way:
             * 
             * In this approach (without the above fix) when the last char matches, we still need to look at 4 directions; 
             * but in the other approach, after last char match, it is forwarded to the next recursion, which will return true directly on the first length check.
             * 
             * In other words, termination judge should happen before everything else, or you are running the risk of doing one extra step.
             * 
             * So try to do the extension judge in the next recursion if possible, and make sure those happens after the termintation judge.
             * 
             * 
             */

            visited[row][col] = true;

            int rowCount = visited.length;
            int colCount = visited[0].length;
            
            List<Pos> positions = new ArrayList<Pos>();
            positions.add(new Pos(row-1, col));
            positions.add(new Pos(row+1, col));
            positions.add(new Pos(row, col-1));
            positions.add(new Pos(row, col+1));
            for(Pos pos : positions){
            	if(pass(pos.row, pos.col, rowCount, colCount, visited)){
            		if(dfs(board, word, index+1, pos.row, pos.col, visited)){
            			return true;
            		}
            	}
            }
            
            visited[row][col] = false;
        }
        
        return false;
    }

	private boolean pass(int i, int j, int rowCount, int colCount,
			boolean[][] visited) {
		return i>=0&&j>=0&&i<rowCount&&j<colCount&&(!visited[i][j]);

	}
	
	class Pos{
		int row;
		int col;
		Pos(int row, int col){
			this.row = row; 
			this.col = col;
		}
	}
}
