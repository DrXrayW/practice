package xray.leetcode.array;

/*
 * 
 * 
 * NOTE that this is about coordinate!! not the block like the other water problem!! 
 * here the heights are just lines, so that end - start is actually the width!
 * 
 * IDEA, calculate the area, and move the shorter one to the mid one step more
 *  * 
 * TIP:
 * It really doesn't matter which one we move to the center if there is a tie.
 *  
 * 
 */
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
        int max = 0;
        if(height==null){
            return max;
        }
        if(height.length<2){
            return max;
        }
        int start = 0;
        int end = height.length - 1;
        while(start<end){
            int h = Math.min(height[start], height[end]);
            int area = h * (end - start);
            max = Math.max(max, area);
            if(height[start]<=height[end]){
                start++;
            }else{
                end --;
            }
        }
        return max;
    }
}
