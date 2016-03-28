package xray.leetcode.interview;

public class Node {
	public Node root = null;
	public Node parent = null;
	public int childCount = 0;
	public int val;
	public int sum;
	public int rank = 0;
	public Node(int val){
		this.val = val;
	}
}
