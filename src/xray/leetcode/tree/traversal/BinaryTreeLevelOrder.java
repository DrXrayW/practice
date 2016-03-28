package xray.leetcode.tree.traversal;

import java.util.*;
import xray.leetcode.tree.TreeNode;

/*
 * Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

For example:
Given binary tree {3,9,20,#,#,15,7},
    3
   / \
  9  20
    /  \
   15   7
return its level order traversal as:
[
  [3],
  [9,20],
  [15,7]
]

IN SHORT: pass a depth in and put in the right array

or we may use a queue if the visiting order is require, see 01 

 */
public class BinaryTreeLevelOrder {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> levelOrder = new ArrayList<List<Integer>>();
        levelOrderTop(levelOrder, root, 0);
        return levelOrder;
    }
    
    public void levelOrderTop(List<List<Integer>> levelOrder, TreeNode node, int depth){
        if(node==null){
            return;
        }
        
        //put the current node in list
        List<Integer> level;
        if(levelOrder.size()<=depth){
            level = new ArrayList<Integer>();
            levelOrder.add(level);
    	}else{
    		level = levelOrder.get(depth);
    	}

        level.add(node.val);
        
        //go left and right
        levelOrderTop(levelOrder, node.left, depth + 1);
        levelOrderTop(levelOrder, node.right, depth + 1);
    }
}
