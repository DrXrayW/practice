package xray.leetcode.tree.traversal;

import xray.leetcode.tree.TreeNode;

public class ConstructBinaryTreefromPreorderandInorderTraversal {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if( (preorder==null)||(inorder==null)||(preorder.length==0)||(inorder.length==0) ){
            return null;
        }
        
        if(preorder.length!=inorder.length){
            return null; //discuss
        }

        return buildTree(preorder, 0, preorder.length-1, inorder, 0, inorder.length - 1);
    }
    
    private TreeNode buildTree(int[] preorder, int pre_s, int pre_e, int[] inorder, int in_s, int in_e){
        if((pre_s>pre_e)||(pre_s>=preorder.length)||(pre_e>=preorder.length)||in_s>=preorder.length||in_e>=preorder.length){
            return null;
        }
        int root = preorder[pre_s];
        TreeNode rootNode = new TreeNode(root);
        if(pre_s == pre_e){
            return rootNode; //only one
        }
        int in_rootpos = in_s;
        for(int i=in_s;i<=in_e;i++){
            if(inorder[i]==root){
                in_rootpos = i;
                break; //todo use a hash map to get the position
            }
        }
        //length = in_rootpos, /!!! the in_rootpos is not length!  but the position!
        int length = in_rootpos - in_s;
        rootNode.left = buildTree(preorder, pre_s + 1, pre_s + length, inorder, in_s, in_s + length - 1);
        rootNode.right = buildTree(preorder, pre_s + length + 1, pre_e, inorder, in_s + length + 1, in_e);
        
        return rootNode;
    }
}
