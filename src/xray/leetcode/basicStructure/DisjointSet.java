package xray.leetcode.basicStructure;

/*
 * Note that the tree here is a backward tree only, 
 * i.e. only parent, no child
 * as the point is to find the representative
 *
 */
public class DisjointSet<T> {
	private class Node{
		Node parent;
		T val;
		int rank;
		Node(T val){
			this.val = val;
		}
	}
	public void makeSet(Node x){
		x.parent = x;
		
		//rank improvement
		x.rank = 0;
	}
	
	public Node find(Node x){
		if(x.parent==x){
			return x;
		}
		Node root = find(x.parent);
		x.parent = root;
		return root;
	}
	
	public boolean sameSet(Node x, Node y){
		return find(x) == find(y);
	}
	
	public void union(Node x, Node y){
		Node xRoot = find(x);
		Node yRoot = find(y);
		merge(xRoot, yRoot);
	}
	
	//naive approach
    private void merge01(Node xRoot, Node yRoot) {
    	xRoot.parent = yRoot;
    }
    private void merge(Node xRoot, Node yRoot) {
    	if(xRoot==yRoot){
    		return;
    	}
    	if(xRoot.rank < yRoot.rank){
    		xRoot.parent = yRoot;
    	}else if(xRoot.rank > yRoot.rank){
    		yRoot.parent = xRoot;
    	}else{
    		yRoot.parent = xRoot;
    		xRoot.rank = xRoot.rank + 1;
    	}
    	
    	/*
    	 * Q: why when equal, use  + 1?
    	 * Note that we are taking xRoot as the new Root, 
    	 * we know that xRoot and yRoot has the same rank, 
    	 * so that means that the max length in y finding xRoot would add 1, 
    	 * that is the new max nodes in the longest path of the new tree 
    	 * 
    	 * When the ranks are different, we always connect the shorter one to the longer one, 
    	 * so that the longest path starting from the shorter one would +1
    	 * the longest path starting from the longer one would stay the same but which is already at least +1
    	 * So we are NOT increasing the rank. 
    	 * 
    	 * Note that this is not a real tree, it could be multiple children pointing to the same root
    	 * 
    	 * Q: the find will flatten the tree, should we also adjust rank?
    	 * It is true that the new longest path of the flattened tree can be reduced to 2. 
    	 * However, it is possible that we flattened one part of the tree but there is another part of the tree is not flattened.
    	 * It will cost a lot if we want to do the proper update. 
    	 * 
    	 * On the other hand, the point of rank is to avoid the worst case, where we can consider no find has happened yet. 
    	 * Where the rank would mean the longest path to root 
    	 * 
    	 */
    }

	public static void main(String[] args){
    	
    }
    

}
