package xray.leetcode.tree.depth;

import xray.leetcode.tree.TreeNode;
/*
 * O(n) runtime, O(log n) space
 * 
 * The trick here is that null sub tree counts for no existing
 */
public class MinimumDepthOfBinaryTree01 {
    public int minDepth(TreeNode root) {
    	if(root==null){
    		return 0;
    	}
    	if(root.left==null){ //we must ignore the null tree, otherwise, it will always be 0 + 1
    		return 1 + minDepth(root.right);
    	}
    	if(root.right==null){
    		return 1 + minDepth(root.left);
    	}
    	return 1 + Math.min(minDepth(root.left), minDepth(root.right));
    }
}
