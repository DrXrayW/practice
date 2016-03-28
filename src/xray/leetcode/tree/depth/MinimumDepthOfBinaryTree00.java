package xray.leetcode.tree.depth;

import xray.leetcode.tree.TreeNode;

public class MinimumDepthOfBinaryTree00 {
    public int minDepth(TreeNode root) {
    	return minDepth(root, false);
    }

	private int minDepth(TreeNode root, boolean hasBrother) {
		if(root==null){
			return hasBrother ? Integer.MAX_VALUE : 0;  //this helps protecting a real brother, as well as identifying a true leaf
		}
        
        return 1 + Math.min(minDepth(root.left, root.right != null), minDepth(root.right, root.left != null));
	}
}
