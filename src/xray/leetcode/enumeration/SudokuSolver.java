package xray.leetcode.enumeration;
/*
 * based on nqueen
 * 
 * TIPS: 
 * 
 * 1. Must impose an order on the missing information, must prevent situations like filling in the first then second, or second then first, that will be a long run!
 * 2. Understand the different situations on moving ahead:
 * 		a. missing: 
 * 			then we need to fill in , if we cannot find one then fail
 * 			if we can fill in
 * 				 try the sub, if sub win then all win
 * 				if sub not win, then we still have next fill in to try!
 * 		b. not missing
 * 			then we need to try the sub
 * 
 * 		if we tried everything then fail
 * 
 * 3. Mind the use of next pos, since the main call is about to fill in the char, but not filled in yet, 
 * we cannot fail because the next is null, we can only fail if the current pos is null
 * think about this: leaving the validate check to the next one. 
 * 
 * 
*/
public class SudokuSolver {
	//main
	public static void main(String[] args) {
		//String[] strs  = {"..9748...","7........",".2.1.9...","..7...24.",".64.1.59.",".98...3..","...8.3.2.","........6","...2759.."};
		String[] strs  = {"..9748...","7........",".2.1.9...","..7...24.",".64.1.59.",".98...3..","...8.3.2.","........6","...2759.."};
		char[][] board = new char[9][9];
		for(int i=0;i<9;i++){
			board[i] = strs[i].toCharArray();
		}
		SudokuSolver s = new SudokuSolver();
		s.solveSudoku(board);
		return;
	}
	//main
    private void print(char[][] board, int i1, int j1) {
    	System.out.println("======");
    	System.out.println("i:" + i1 + ", j:" + j1);
		System.out.println("======");
    	for(int i=0;i<9;i++){
    		for(int j=0;j<9;j++){
    			System.out.print(board[i][j]);
    			System.out.print(" ");
    		}
    		System.out.println();
    	}
	}
    //debug end

    private static char mc = '.'; //missing char
    public void solveSudoku(char[][] board) {
        if(board==null){
            return;
        }

        solveSudoku(board, new Pos(0,0));
    }
    
    private boolean solveSudoku(char[][] board, Pos pos){
    	if(pos==null){
    		return true;//filled everything
    	}
    	Pos newPos = pos.getNext();
    	
    	/*
    	 * TIP if missing then fill in
    	 */
    	if(board[pos.i][pos.j]==mc){
	        for(char c='1';c<='9';c++){
	            if(pass(board, pos.i,pos.j, c)){
	            	board[pos.i][pos.j] = c;
	            	if(solveSudoku(board, newPos)){
	            		return true;
	            	}
	            	board[pos.i][pos.j] = mc;
	            }
	        }
	        return false; //tried everything cannot fill in anything, this is a dead end
    	}else{
    		//already filled in, move ahead
            if(solveSudoku(board, newPos)){
                return true;
            }
    	}
    	
        return false;
    }
    
	static private boolean pass(char[][] board, int row, int col, char c){
        if(board[row][col]!=mc){
            return false;
        }
        
        for(int i=0;i<9;i++){
            if(board[i][col]==c){
                return false;
            }
        }

        for(int i=0;i<9;i++){
            if(board[row][i]==c){
                return false;
            }
        }
        
        int rowbase = row / 3;
        int colbase = col / 3;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[rowbase*3+i][colbase*3+j]==c){  //TIP note how you use the base
                    return false;
                }                
            }
        }
        return true;
    }
    
    class Pos{
        int i;
        int j;
        Pos(int i, int j){
            this.i=i;
            this.j=j;
        }
        Pos getNext(){
            if(i==8&&j==8){
                return null;
            }
            int newi=i;
            int newj=j+1;
            if(newj==9){
                newi++;
                newj=0;
            }
            return new Pos(newi, newj);
        }
    }
}
