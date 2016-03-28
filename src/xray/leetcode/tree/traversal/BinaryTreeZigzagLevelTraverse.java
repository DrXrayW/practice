package xray.leetcode.tree.traversal;

import java.util.*;
import xray.leetcode.tree.TreeNode;

/*
 * same as level order traverse, but use the depth to decide how to put the node in the level
 * 
 * use a Deque<Integer> addFirst for reversed lines, add or addLast is as normal depth%2==0 is a normal line
 * 
 * [python]
 *
 */
public class BinaryTreeZigzagLevelTraverse {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        List<Deque<Integer>> list = new ArrayList<Deque<Integer>>();
        levelOrderTop(list, root, 0);
        
        for(Deque<Integer> que : list){
            List<Integer> l = new ArrayList<Integer>(que);
            res.add(l);
        }
        return res;
    }
    
    public void levelOrderTop(List<Deque<Integer>> levelOrder, TreeNode node, int depth){
        if(node==null){
            return;
        }
        
        //put the current node in list
        Deque<Integer> level;
        if(levelOrder.size()<=depth){
            level = new LinkedList<Integer>();
            levelOrder.add(level);
    	}else{
    		level = levelOrder.get(depth);
    	}
        
        if(depth%2==0){
            level.addLast(node.val);
        }else{
            level.addFirst(node.val);
        }
        
        //go left and right
        levelOrderTop(levelOrder, node.left, depth + 1);
        levelOrderTop(levelOrder, node.right, depth + 1);
     }
}
