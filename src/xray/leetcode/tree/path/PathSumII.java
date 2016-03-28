package xray.leetcode.tree.path;

import java.util.ArrayList;
import java.util.List;

import xray.leetcode.tree.TreeNode;

/*
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

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
 */
public class PathSumII {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if(root==null){
            return result;
        }
        
        pathSum(root, new ArrayList<Integer>(), result, sum);
        return result;
    }
    
    private void pathSum(TreeNode root, List<Integer> current, List<List<Integer>> result, int sum){
        if(root==null){
            return;
        }
        
        current.add(root.val); //pre-recursion
        
        /*
         * note that we are detecting leaf node here.  If we are going to stop at sum == 0 and current = null, then we will have dup for left and right.
         */
        if( (root.left==null)&&(root.right==null) ) { 
           if(root.val == sum){
               result.add(new ArrayList<Integer>(current)); //here is the trick to clone the current list
           }
           current.remove(current.size() - 1);   //remember to remove the last one whenever we return after add
           return;
        }
        
        pathSum(root.left, current, result, sum - root.val);
        pathSum(root.right, current, result, sum - root.val);
        
        
        current.remove(current.size() - 1); //remember to remove the last one whenever we return after add //post recursion
    }
}
