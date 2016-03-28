package xray.leetcode.tree.depth;

import xray.leetcode.tree.TreeNode;

public class MinimumDepthOfBinaryTree {
    public int minDepth(TreeNode root) {
        if(root==null){
            return 0;
        }
        
        if( (root.left==null)&&(root.right==null) ) {
            return 1;
        }
        
        //the trick is that we are taking min, but 0 shouldn't count
        
        int leftDepth = minDepth(root.left);
        int rightDepth = minDepth(root.right);
        
        if(leftDepth==0){
            leftDepth = Integer.MAX_VALUE;
        }        
        
        if(rightDepth==0){
            rightDepth = Integer.MAX_VALUE;
        }
        	
        return 1 + Math.min(leftDepth, rightDepth);
    }
}
