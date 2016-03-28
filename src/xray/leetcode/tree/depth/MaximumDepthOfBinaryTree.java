package xray.leetcode.tree.depth;

import xray.leetcode.tree.TreeNode;

public class MaximumDepthOfBinaryTree {
    public int maxDepth(TreeNode root) {
        if(root==null){ //null is 0
            return 0;
        }
        
        return 1 + Math.max(maxDepth(root.left) , maxDepth(root.right)); //or 1 plus max left and right
    }
}
