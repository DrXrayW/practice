package xray.leetcode.interview;
import java.util.*;

/*
 * . use similar idea of disjoint set
 * . we can still use the balance trick 
 */
public class ParentChildPair {
	public static void main(String[] args) {
		ParentChildPair s = new ParentChildPair();
		List<Pair> pairs = new ArrayList<>();
		
		//Integer x = s.getRoot(pairs);
		//boolean x=s.isTree(pairs);
		int x;// = s.getSum(pairs);
		//System.out.println(x);
		
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n7 = new Node(7);
		Node n8 = new Node(8);
		
		Node n9 = new Node(9);
		Node n10 = new Node(10);

		pairs.add(new Pair(n1, n2));
		//x = s.getRoot(pairs);
		//x=s.getSum(pairs);
		//System.out.println(x);
		
		pairs.add(new Pair(n2, n3));
		pairs.add(new Pair(n8, n1));
		pairs.add(new Pair(n1, n7));
		//pairs.add(new Pair(4, 5));
		//x = s.getRoot(pairs);
		//x=s.getSum(pairs);
		//System.out.println(x);
		
		pairs.add(new Pair(n10, n9));
		//x = s.getRoot(pairs);
		x=s.getSum(pairs);
		System.out.println(x);
	}
	
	private Set<Node> buildMap(List<Pair> pairs){
		Set<Node> nodes = new HashSet<Node>();
		for(Pair pair : pairs){
			Node parent = prepNode(pair.parent, nodes);
			Node child = prepNode(pair.child, nodes);
			
			//sanity check before connecting:
			if(child.parent!=null){ //existing non root node 
				if(child.parent!=parent){
					System.out.println("Conflict input");
					return null; //conflict
				}
				//dup, next pair
				continue;
			}else if(child.root==child){ //root
				Node parentRoot = findRoot(parent);
				if(parentRoot == child){ //same tree
					System.out.println("Loop");
					return null;
				}
			}
			
			//check link
			int cap = 2;
			if(parent.childCount>=cap){
				System.out.println("too many children");
				return null;
			}
			
			//connect
			mergeRoot(parent, child);
			child.parent = parent;
			parent.childCount++;
		}
		return nodes;
	}
	
	private Node prepNode(Node node, Set<Node> nodes) {
		if(node.root==null){
			node.root = node;
			node.sum = node.val;
			nodes.add(node);
		}
		return node;
	}

	private void mergeRoot(Node node1, Node node2){
		Node root1 = findRoot(node1);
		Node root2 = findRoot(node2);
		if(root1.rank>root2.rank){
			root2.root = root1;
			root1.sum += root2.sum;
		}else if(root1.rank<root2.rank){
			root1.root = root2;
			root2.sum += root1.sum;
		}else{
			root2.root = root1;
			root1.sum += root2.sum;
			root1.rank++;
		}
	}
	
	private Node findRoot(Node node){
		if(node.root==node){
			return node; //root itself
		}
		Node root = findRoot(node.root);
		node.root = root;
		return root;
	}
	
	public boolean isTree(List<Pair> pairs){
		if(pairs==null||pairs.isEmpty()){
			return false;
		}
		Set<Node> roots = new HashSet<>();
		Set<Node> nodes = buildMap(pairs);
		if(nodes==null){
			return false;
		}
		for(Node node : nodes){
			while(node.root!=node){
				node = node.root;
			}
			roots.add(node);
		}
		if(roots.size()>1){
			System.out.println("More than one root.");
		}
		return roots.size()==1; //only one root
	}
	
	public Integer getRoot(List<Pair> pairs){
		if(pairs==null||pairs.isEmpty()){
			return null;
		}
		Set<Node> nodes = buildMap(pairs);
		for(Node node : nodes){
			while(node.root!=node){
				node = node.root;
			}
			return node.val;
		}
		return null;
	}
	
	public Integer getSum(List<Pair> pairs){
		if(pairs==null||pairs.isEmpty()){
			return 0;
		}
		Set<Node> nodes = buildMap(pairs);
		if(nodes==null){
			return 0;
		}
		Integer sum = null;
		for(Node node : nodes){
			Node root = findRoot(node);
			if(sum==null){
				sum = root.sum;
			}else{
				return 0;
			}
		}
		return 0;
	}
}
