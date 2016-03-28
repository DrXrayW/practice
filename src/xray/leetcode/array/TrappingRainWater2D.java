package xray.leetcode.array;
/*

 */
public class TrappingRainWater2D {
    public int trap(int[][] A) {
        
        int total = 0;
        
        /*
         * my idea:
         * edge then 0
         * 
         * start from non-edge
         * flow down, 
         * if flow down to edge, then ignore
         * 
         * (remember to note visited)
         * 
         * or flood until done, then all the flooded node get the min edge, that is not in the flooding area
         * that is our water level, 
         * 
         * repeat until done
         * 
         */
        
        return total;
    }
}
