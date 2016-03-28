package xray.leetcode.tree.traversal;

import java.util.*;

import xray.leetcode.tree.TreeNode;

public class ConstructBinaryTreefromInorderAndPostorderTraversal {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if( (postorder==null)||(inorder==null)||(postorder.length==0)||(inorder.length==0) ){
            return null;
        }
        
        if(postorder.length!=inorder.length){
            return null; //discuss
        }
        Map<Integer, Integer> inorderPos = new HashMap<>();
        for(int i=0;i<inorder.length;i++){
        	inorderPos.put(inorder[i], i);
        }
        return buildTree(postorder, 0, postorder.length-1, inorder, 0, inorder.length - 1, inorderPos);
    }
    
    private TreeNode buildTree(int[] postorder, int post_s, int post_e, 
    		int[] inorder, int in_s, int in_e, Map<Integer, Integer> inorderPos){
        if((post_e<post_s)||(post_s>=postorder.length)||(post_e>=postorder.length)||in_s>=postorder.length||in_e>=postorder.length){
            return null;
        }
        int root = postorder[post_e];
        TreeNode rootNode = new TreeNode(root);
        if(post_s == post_e){
            return rootNode; //only one
        }
        int in_rootpos = inorderPos.get(root);
        //length = in_rootpos, 
        int length = in_rootpos - in_s;
        rootNode.left = buildTree(postorder, post_s, post_s + length - 1, inorder, in_s, in_s + length - 1, inorderPos);
        rootNode.right = buildTree(postorder, post_s + length, post_e - 1, inorder, in_s + length + 1, in_e, inorderPos);
        
        return rootNode;
    }
}
