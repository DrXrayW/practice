package xray.leetcode.tree;

/*
 * same as Same Tree, just mirror left to right
 */
public class SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if(root==null){
            return true;
        }
        
        return isMirror(root.left, root.right);
    }
    
    private boolean isMirror(TreeNode left, TreeNode right){
        if((left==null)&&(right==null)){
            return true;
        }
        
        if( (left==null)||(right==null)){
            return false;
        }
        
        if(left.val!=right.val){
            return false;
        }
        
        if(!isMirror(left.left, right.right)){
            return false;
        }
        
        if(!isMirror(left.right, right.left)){
            return false;
        }
        return true;
    }
}
