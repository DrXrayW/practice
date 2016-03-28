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
 
 
 Another Recursion
 */
public class BinaryTreeUpsideDown02 {
    public TreeNode UpsideDownBinaryTree(TreeNode root) {
    	return dfsBottomUp(root, null);
    }
    
    private TreeNode dfsBottomUp(TreeNode p, TreeNode parent) {
	    if (p == null) return parent;
	    TreeNode root = dfsBottomUp(p.left, p);
	    p.left = (parent == null) ? null : parent.right;  //this doesn't need to clear root, as when parent is null, it will be set to parent/null
	    p.right = parent;
	    return root;
    }
}
