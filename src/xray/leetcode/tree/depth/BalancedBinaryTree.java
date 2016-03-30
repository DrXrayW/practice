package xray.leetcode.tree.depth;

import xray.leetcode.tree.TreeNode;
/*
 * calculate the max depth of left and right tree. 
 * but return negative or null to indicate the balance is broken
 * 
 * O(n) runtime, O(n) stack space (worst case link list), but generally, O(logn) space
 *
 * [python]
 *
 */
public class BalancedBinaryTree {
    public boolean isBalanced(TreeNode root) {
    	if(root == null){
    		return true;  //ask whether this is ok, This is acutally not useful
    	}
    	//Note that you need to create another one to calculate the depth recursively, and use this one to return true of false
    	Integer depth = depth(root); //or use negative number to indicate failure
    	if(depth == null){
    		return false;
    	}
    	return true;
    	
    }

	private Integer depth(TreeNode node) {
		if(node == null){
			return 0;
		}
		
		Integer leftDepth = depth(node.left);
		if(leftDepth==null){
			return null;
		}
			
		Integer rightDepth = depth(node.right);
		if(rightDepth == null){
			return null;
		}
		
    	if(Math.abs(leftDepth - rightDepth)>1){
    		return null;
    	}
		
		return Math.max(leftDepth, rightDepth) + 1;
	}
}
