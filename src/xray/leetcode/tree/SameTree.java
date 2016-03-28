package xray.leetcode.tree;

/*
[python]
 */
public class SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if((p==null)&&(q==null)){
            return true;
        }
        
        if( (q==null)||(p==null)){
            return false;
        }
        
        if(q.val!=p.val){
            return false;
        }
        
        if(!isSameTree(p.left, q.left)){
            return false;
        }
        
        if(!isSameTree(p.right, q.right)){
            return false;
        }
        return true;
    }
}
