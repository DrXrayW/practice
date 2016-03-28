package xray.leetcode.enumeration;
public class WordSearch01 {
	private int rowCount  = 0;
	private int colCount = 0;
    public boolean exist(char[][] board, String word) {
        if(board==null||word==null){
            return false;
        }
        if(word.length()==0){
            return false;
        }
        rowCount = board.length;
        if(rowCount==0){
            return false;
        }
        colCount = board[0].length;
        if(colCount==0){
            return false;
        }
        if(word.length()>rowCount*colCount){
            return false;
        }
        boolean[][] visited = new boolean[rowCount][colCount];
        for(int i=0;i<rowCount;i++){
            for(int j=0;j<colCount;j++){
                if(dfs(board, word, 0, i, j, visited)){
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
        if(row<0||col<0||row>=rowCount||col>=colCount){
        	return false;
        }
        if(visited[row][col]){
        	return false;
        }
        
        if(board[row][col]==word.charAt(index)){
            visited[row][col] = true;
            boolean res = dfs(board, word, index+1, row-1,col,visited) ||
            		dfs(board, word, index+1, row+1,col,visited) ||
            		dfs(board, word, index+1, row,col-1,visited) ||
            		dfs(board, word, index+1, row,col+1,visited); 
            if(res){
            	return true;
            }
            visited[row][col] = false;
        }
        
        return false;
    }
}
