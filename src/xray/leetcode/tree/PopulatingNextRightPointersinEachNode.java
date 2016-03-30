package xray.leetcode.tree;

import java.util.LinkedList;
import java.util.Queue;

/*
 * TIP: use the pre trick
 * TIP: understand the question!!!  it wants to end the chain at the end of each level
 * 
 * BFS level traverse
 * maintain two queues so that we know which layer. 
 * so that we know we are at the last, by looking at the queue emptiness.
 * remember to roll
 *
 * [python]
 *
 */
public class PopulatingNextRightPointersinEachNode {
    public void connect(TreeLinkNode root) {
        if(root==null){
            return;
        }
        /*
         * two queues for bfs, so that all nodes of the same layer/level are processed together.
         * need to roll, so current and next
         *
         * add root as current
         *
         * for each one in the current, pop and remember the last one (pre)
         * if the node is the first of the level then its pre is null, no link then.
         * link them up.
         * put its children to next according to the order.
         */
        Queue<TreeLinkNode> current = new LinkedList<TreeLinkNode>();
        Queue<TreeLinkNode> next = new LinkedList<TreeLinkNode>();
        current.add(root);
        TreeLinkNode pre = null;
        while(!current.isEmpty()){ // this loop is to make sure there is node to expand
            //this loop is to process nodes in the current one by one
            while(!current.isEmpty()){
                TreeLinkNode node = current.poll();
                if(pre!=null){
                    pre.next = node;
                }
                /*
                The following if else block is to only link up nodes in one level:
                If the current node is the last one, null out pre, or the next level will be linked up to it.
                 */
                if(!current.isEmpty()){ //not last one in the level, then continue
                    pre = node;
                }else{
                    pre = null; //last one in the level, null out
                }
                if(node.left!=null){
                    next.add(node.left);
                }
                
                if(node.right!=null){
                    next.add(node.right);
                }
            }
            current = next;
            next = new LinkedList<TreeLinkNode>();
        }
        
        return;
    }
}
