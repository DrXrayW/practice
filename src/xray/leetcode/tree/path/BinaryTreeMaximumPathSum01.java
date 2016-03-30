package xray.leetcode.tree.path;

import xray.leetcode.tree.TreeNode;

/*
 * 
 * IDEA: post order traverse, get the max to root path length for left and right, 

 * tracking two numbers:
 * 1. the max path sum of the tree
 * 2. the max none negative sum path with one end being the root
 *
 * The Node can be something else like a list or any structure that can hold reference.
 */

public class BinaryTreeMaximumPathSum01 {
    class Node {
        long val;
        Node(long value){
            this.val = value;
        }
    }
    public int maxPathSum(TreeNode root) {
        if(root==null){
            return 0;
        }
        Node maxPath = new Node((long)Integer.MIN_VALUE - 1);
        maxPathToRoot(root, maxPath);
        long max = maxPath.val;
        
        if(max<=Integer.MAX_VALUE){
            return (int)max;
        }else{
            return (int)max; //exception, discuss
        }
    }
    
    private long maxPathToRoot(TreeNode root, Node maxPath){
        if(root==null){
            return 0;
        }
        long left = maxPathToRoot(root.left, maxPath);
        long right = maxPathToRoot(root.right, maxPath);
        
        //update max arch
        long toRoot = root.val + Math.max(left, right); //TIP: toRoot, either root itself, or plus the larger of left or right 
        long sum = root.val + left + right; //TIP: This is the only option left from toRoot, even if left or right is not contributing
        maxPath.val = Math.max(maxPath.val, sum); //update the tracked max path
        return toRoot > 0 ? toRoot : 0;  //this means the subtree will never return a negative, so we can assume adding it won't be as bad
    }
}

