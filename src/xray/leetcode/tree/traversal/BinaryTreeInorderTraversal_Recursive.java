package xray.leetcode.tree.traversal;

/*
 * [python]
 */
import java.util.ArrayList;
import java.util.List;

import xray.leetcode.tree.TreeNode;

public class BinaryTreeInorderTraversal_Recursive {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        return inorder(root, res);
    }
    
    private List<Integer> inorder(TreeNode root, List<Integer> res){
        if(root==null){
            return res;    
        }
        inorder(root.left, res);
        res.add(root.val);
        inorder(root.right, res);
        return res;
    }
}
