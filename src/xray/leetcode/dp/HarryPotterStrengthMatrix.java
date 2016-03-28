package xray.leetcode.dp;

/*
 * Dungeon Game 
 */
public class HarryPotterStrengthMatrix {
    public int calculateMinimumHP(int[][] dungeon) {
        int minHP = 1; 
        
        if(dungeon==null){
            return minHP;
        }
        int rowCount = dungeon.length;
        if(rowCount==0){
            return minHP;
        }
        int colCount = dungeon[0].length;
        if(colCount==0){
            return minHP;
        }
        //init the bottom right corner
        int[][] dp = new int[rowCount][colCount];
        //assuming 0 is the min hp we want, we will adjust by minHp later
        //dp[i][j] means the min hp we need from start to stay at least 0 at i,j, considering the dungeon[i][j]
        /*or dp[i][j] means the min hp we need to complete the dungeon starting from i,j, it is zero based. 
         * so it is either the - of the current cell when it is a threat, or it would be 0
         */
         
        /*
         * either we do initialization, or we do conditional judge, 
         * we can also save space by rolling on the array here
         */
        for(int i=rowCount-1;i>=0;i--){
            for(int j=colCount-1;j>=0;j--){
            	Integer down = null;
            	if(i<rowCount-1){
            		down = dp[i+1][j];
            	}
            	Integer right = null;
            	if(j<colCount-1){
            		right = dp[i][j+1];
            	}
            	
                dp[i][j] = Math.max(0, minExcludingNull(down, right) - dungeon[i][j]);
            }
        }

        return dp[0][0] + minHP;
    }
    
    static private int minExcludingNull(Integer num1, Integer num2){
        if(num1==null&&num2==null){ //only happens when it is init-ed
            return 0;
        }
        
        if(num1==null){ 
            return num2;
        }

        if(num2==null){ 
            return num1;
        }
        
        return Math.min(num1, num2);
    }
}
