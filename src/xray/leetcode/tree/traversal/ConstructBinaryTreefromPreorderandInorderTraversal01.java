package xray.leetcode.tree.traversal;

import java.util.*;
import xray.leetcode.tree.TreeNode;

/*
Given preorder and inorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

If there is dup, then it is hard! So no dup is essential!

We know that the preorder starts with the root.
So we can find its position in inorder, then break the two parts into left and right.

That is why a map of the inorder position is created to avoid scanning multiple times.
[python]
 */

public class ConstructBinaryTreefromPreorderandInorderTraversal01 {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if( (preorder==null)||(inorder==null)||(preorder.length==0)||(inorder.length==0) ){
            return null;
        }
        
        if(preorder.length!=inorder.length){
            return null; //discuss
        }
        Map<Integer, Integer> inorderPos = new HashMap<>();
        for(int i=0;i<inorder.length;i++){
        	inorderPos.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length - 1, inorderPos);
    }
    
    private TreeNode buildTree(int[] preorder, int pre_s, int pre_e, int[] inorder, int in_s, int in_e, Map<Integer, Integer> inorderPos){
        //the conditions on the ends can be removed
        if((pre_s>pre_e)||(pre_s>=preorder.length)||(pre_e>=preorder.length)||in_s>=preorder.length||in_e>=preorder.length){
            return null;
        }
        int root = preorder[pre_s];
        TreeNode rootNode = new TreeNode(root);
        if(pre_s == pre_e){
            return rootNode; //only one
        }
        int in_rootpos = inorderPos.get(root);
        //length = in_rootpos, /!!! the in_rootpos is not length!  but the position!
        int length = in_rootpos - in_s;
        rootNode.left = buildTree(preorder, pre_s + 1, pre_s + length, inorder, in_s, in_s + length - 1, inorderPos);
        rootNode.right = buildTree(preorder, pre_s + length + 1, pre_e, inorder, in_s + length + 1, in_e, inorderPos);
        
        return rootNode;
    }
}
