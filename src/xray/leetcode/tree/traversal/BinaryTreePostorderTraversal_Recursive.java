package xray.leetcode.tree.traversal;

/*
 * [python]
 */
import java.util.ArrayList;
import java.util.List;

import xray.leetcode.tree.TreeNode;

public class BinaryTreePostorderTraversal_Recursive {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<Integer>();
        return postorder(root, res);
    }
    
    private List<Integer> postorder(TreeNode root, List<Integer> res){
        if(root==null){
            return res;    
        }
        postorder(root.left, res);
        postorder(root.right, res);
        res.add(root.val);
        return res;
    }
}
