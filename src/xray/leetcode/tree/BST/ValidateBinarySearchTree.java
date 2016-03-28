package xray.leetcode.tree.BST;

import xray.leetcode.tree.TreeNode;

/*
 * 
 * IDEA: use an upper bound and a lower bound, while going through root, set the upper bound and lower bound. 
 * TIP: binary tree should not allow equal (or it should be at the node that has the number)
 * So if we are using the failing method, make sure = is a fail, 
 * if we are using valid method, make sure do not include = 
 * 
 * TIP: the int overflow thing, it is not safe to assume the set upper bound and lower bound of integer are enough: we need to go 1 further and cast them to long
 *
 */
public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null); //using null for infinities
    }
    
    private boolean isValidBST(TreeNode root, Integer lower, Integer upper){
        if(root==null){
            return true;
        }
        //use && or || evaluation sequence to save if statements
        return (lower==null||root.val>lower) && //infinity got an auto pass   
        	   (upper == null||root.val<upper) && 
        	   isValidBST(root.left, lower, root.val) &&
        	   isValidBST(root.right, root.val, upper);
    }
}
