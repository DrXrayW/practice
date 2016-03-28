package xray.leetcode.interview;

public class Pair {
	public Node parent;
	public Node child;
	public Pair(Node parent, Node child){
		this.parent = parent;
		this.child = child;
	}
	public Pair(int parent, int child){
		this.parent = new Node(parent);
		this.child = new Node(child);
	}
}
