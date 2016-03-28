package xray.leetcode.tree.traversal;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import xray.leetcode.tree.TreeNode;

/*
 * STACK version
 * 
 * node needs to go with left, 
 * so parent is pushed to access right side. 
 * 
 * the while condition:
 * 
 * left not null (we can go left), or no parent stacked.
 * 
 * noticed that the else in while loop: it implies the stack is not empty, combined with the while loop
 * 
 * 
 * IN SHORT:
 * push走左  pop取值 走右，所以就是不断的push 最左树到底  然后pop，再走右，再不断push左到底...
 */
		
public class BinaryTreeInorderTraversal_Stack {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode node = root; //we cannot go push root first as that would be pre order
        /*
         * !(stack.isEmpty()): I am the left child of my parent, my parent is in the stack
         * node!=null: 
         */
        while((node!=null)||(!stack.isEmpty())){ 
            if(node!=null){
                stack.push(node); //always push the current node and go left
                node = node.left;
            }else{
                TreeNode parent = stack.pop();   //stack starts to pop when there is no left, so it is either a left most, or left has been processed
                res.add(parent.val);
                node = parent.right;
            }
        }

        return res;
    }
}
