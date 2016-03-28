package xray.leetcode.enumeration;

import java.util.*;
/*
 * use a set to calculate dup, donot go pairwise comparing
 */
public class ValidSudoku01 {
    public boolean isValidSudoku(char[][] board) {
        
        //check row
        for(int i=0;i<9;i++){
            Set<Character> set = new HashSet<Character>();
           for(int j=0;j<9;j++){
                if( conflict(board[i][j], set) ){
                    return false;
                }
            }
            set.clear();
        }

        //check col
        for(int j=0;j<9;j++){
            Set<Character> set = new HashSet<Character>();
           for(int i=0;i<9;i++){
                if( conflict(board[i][j], set) ){
                    return false;
                }
            }
            set.clear();
        }
        
        //check current area
        for(int m=0;m<3;m++){
            for(int n=0;n<3;n++){
                Set<Character> set = new HashSet<Character>();
                for(int i=(m*3);i<(m*3+3);i++){
                    for(int j=(n*3);j<(n*3+3);j++){
                        if( conflict(board[i][j], set) ){
                            return false;
                        }
                    }
                }
                set.clear();
            }
        }
        return true;
    }
    
    private boolean conflict(char c, Set<Character> set){
        if(c!='.'){
            if(!set.add(c)){
                return true;
            }
        }
        return false;
    }
}
