package xray.leetcode.tree.traversal;

/*
 * [python]
 *  
 */
import java.util.ArrayList;
import java.util.List;

import xray.leetcode.tree.TreeNode;

public class BinaryTreePreorderTraversal_Recursive {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        return preorder(root, res);
    }
    
    private List<Integer> preorder(TreeNode root, List<Integer> res){
        if(root==null){
            return res;    
        }
        res.add(root.val);
        preorder(root.left, res);
        preorder(root.right, res);
        return res;
    }
}
