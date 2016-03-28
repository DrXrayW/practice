package xray.leetcode.tree;

import util.Ref;

public class FlattenBinaryTreetoLinkedList {
	/*
	 * Given a binary tree, flatten it to a linked list in-place.

For example,
Given

         1
        / \
       2   5
      / \   \
     3   4   6
The flattened tree should look like:
   1
    \
     2
      \
       3
        \
         4
          \
           5
            \
             6
click to show hints.

Hints:
If you look carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.
	 * 
	 * TIP using list to pass an object by reference
	 * TIP because we are doing preorder, so the object is always the pre:
	 * root for the left tree root, 
	 * right most node in the left tree for the right tree
	 * 
	 * IN SHORT: pre-order traverse, passing current link list tail allow the way, which is always the current root
	 * it is null for the very root, 
	 * when it is not null, then link the current root to its right, do not forget to null the left. 
	 *   
	 */
    public void flatten(TreeNode root) {
    	
        Ref<TreeNode> pre = new Ref<TreeNode>(null);  
        flatten(root, pre);
    }
    
    private void flatten(TreeNode root, Ref<TreeNode> pre){

        if(root==null){
            return;
        }
        TreeNode right = root.right;
        TreeNode preNode = pre.value;
        if(preNode!=null){ 
            preNode.left = null;
            preNode.right = root;
        }
        pre.value = root;
        flatten(root.left, pre);
        flatten(right, pre);
    }
}
