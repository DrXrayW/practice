package xray.leetcode.tree.path;

import xray.leetcode.tree.TreeNode;

public class PathSum {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root==null){
            return false;
        }
        
        int subSum = sum - root.val;
        
        //leaf, ending recursion
        if( (root.left==null)&&(root.right==null) )  {
            if(subSum == 0){
                return true;
            }
        }
        
        //note that we cannot go return hasPathSum, as if it doesn't work in the left, we need to pass it to the right side
        if(root.left!=null){
            if(hasPathSum(root.left, subSum)){
                return true;
            }
        }
        
        if(root.right!=null){
            if(hasPathSum(root.right, subSum)){
                return true;
            }
        }
        
        return false;
    }
}
