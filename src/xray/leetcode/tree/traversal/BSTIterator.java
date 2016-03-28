package xray.leetcode.tree.traversal;

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 * 
 * use a stack to save all nodes from root to the left most children
 * 
 * when popping, pushAllLeft of the right node if not null
 * 
 */

import xray.leetcode.tree.*;
import java.util.*;
public class BSTIterator {
    private Stack<TreeNode> stack = new Stack<TreeNode>();
    boolean empty = true;
    public BSTIterator(TreeNode root) {
        if(root==null){
            return;
        }
        empty = false;
        pushAllLeft(root);
    }
    
    private void pushAllLeft(TreeNode node){
        while(node!=null){
            stack.push(node);
            node = node.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        if(empty){
            return false;
        }
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        if(empty){
            //throw no such element exception
        }
        
        TreeNode node = stack.pop();
        int result = node.val;
        
        if(node.right!=null){
            pushAllLeft(node.right);
        }

        return result;
    }
}
