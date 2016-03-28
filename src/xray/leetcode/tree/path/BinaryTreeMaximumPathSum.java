package xray.leetcode.tree.path;

import xray.leetcode.tree.TreeNode;

/*
 * 
 * IDEA: post order traverse, get the max to root path length for left and right, 
 * then our paths are: 
 * 1. connecting left and right (only select the positive ones), this is called arch, as it forms an arch  /\, or a cross root path
 * 2. the max for the current root, which our function returns:
 * 		a. left or right won't help, as they are <= 0, then root.val is the best path for this root taking it as an endpoint
 * 		b. summing up root.val and left (if >0) and right ( if >0 )
 * 
 * TIP: Track the max cross 
 * TIP: Use a structure node to track the maxArch so far
 * TIP: We may have an overflow catch, use long at first, or it would be very hairy.
 * 
 */

public class BinaryTreeMaximumPathSum {
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
        long toRoot = Math.max(root.val, Math.max(left, right) + root.val); //TIP: toRoot, either root itself, or plus the larger of left or right 
        long arch = root.val + left + right; //TIP: This is the only option left from toRoot, even if left or right is not contributing
        long path = Math.max(toRoot, arch);  //TIP: then we have max to fix it out, so path is the max path we get at the current node/root;
        maxPath.val = Math.max(maxPath.val, path); //update the tracked max path
        return toRoot;
    }
}

