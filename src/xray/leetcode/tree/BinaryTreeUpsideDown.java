package xray.leetcode.tree;

/*
 * IDEA
 *  1
   / \
  2   3
 / \
4   5


post order traverse, 
actually, it says that the right side won't need to traverse
so Left then Root

The function can recurse on its own, 

The function goes to the left most, 

turn left into root, right (if any ) into left, root into right.

then return the new root as left (only when left most).

 Consider we are at 1, then 2 returns its root 4, it is passed on now. 
 
 So the connection goes like 
 
 left.left = oldright
 left.right = root
 
 One catch is that we need to set root node's left and right to null, but save them first. 
 
 
 Recursion
 */
public class BinaryTreeUpsideDown {
    public TreeNode UpsideDownBinaryTree(TreeNode root) {
        if(root==null){
            return null;
        }
        if(root.left==null){ 
            //leaf
            return root;
        }else{ 
            TreeNode left = root.left;
            TreeNode right = root.right;
            root.left = null; //break for root
            root.right = null;
            TreeNode newRoot = UpsideDownBinaryTree(left); //root from the left tree is the new root
            left.left = right; //while the left node is the new parent
            left.right = root;
            return newRoot;
        }
    }
}
