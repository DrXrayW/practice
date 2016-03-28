package xray.leetcode.tree.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import xray.leetcode.tree.TreeNode;

/*
 * STACK version
 * 
 * root get accessed first, 
 * TIP: so push right then left, so that it will deep first into left
 * 
 * it is totally different pushing of nodes into stack for the 3 traversal!
 * 
 * IN SHORT: push root, pop 时， 先右后左
 */
public class BinaryTreePreorderTraversal_Stack {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        if(root!=null){
            stack.push(root);
        }
        
        while(!stack.isEmpty()){
            TreeNode node = stack.pop();
            res.add(node.val);
            if(node.right!=null){
                stack.push(node.right);
            }
            if(node.left!=null){
                stack.push(node.left);
            }
        }        

        return res;
    }
}
