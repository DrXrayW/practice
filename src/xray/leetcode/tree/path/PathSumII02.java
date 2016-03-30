package xray.leetcode.tree.path;

import xray.leetcode.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/*
Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

For example:
Given the below binary tree and sum = 22,
              5
             / \
            4   8
           /   / \
          11  13  4
         /  \    / \
        7    2  5   1
return
[
   [5,4,11,2],
   [5,8,4,5]
]

[python]
 */

public class PathSumII02 {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root==null){
            return result;
        }
        
        pathSum(root, new Stack<Integer>(), result, sum);
        return result;
    }
    
    private void pathSum(TreeNode root, Stack<Integer> current, List<List<Integer>> result, int sum){
        if(root==null){
            return;
        }
        
        current.push(root.val); //pre-recursion
        
        /*
         * note that we are detecting leaf node here.  If we are going to stop at sum == 0 and current = null, then we will have dup for left and right.
         */
        if( (root.left==null)&&(root.right==null)&&(root.val == sum)){
               result.add(new ArrayList<Integer>(current)); //here is the trick to clone the current list
        } else {
            pathSum(root.left, current, result, sum - root.val);
            pathSum(root.right, current, result, sum - root.val);
        }
        current.pop(); //remember to remove the last one whenever we return after add //post recursion
    }
}
