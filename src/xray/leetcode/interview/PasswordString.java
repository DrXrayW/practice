package xray.leetcode.interview;
/*
 * de bruijin sequence
 */
import java.util.*;
import java.util.Map.Entry;
import xray.leetcode.graph.Edge;
public class PasswordString {
	public static void main(String[] args) {
		PasswordString s = new PasswordString();
		String str = s.getShortestPasswordString(new char[]{'0', '1'}, 4);
		System.out.println(str);
		return;
	}

	private String getShortestPasswordString(char[] cs, int len) {
		if(cs==null||cs.length==0||len==0){
			return "";
		}
		if(len==1){
			return new String(cs);
		}
		Queue<String> todo = new LinkedList<String>();
		StringBuilder buf = new StringBuilder();
		for(int i=0;i<len-1;i++){ //build -1 map
			buf.append(cs[0]);
		}
		String start = buf.toString();
		todo.add(start);
		Map<String, Set<Edge>> graph = new HashMap<>();
		buildGraph(cs, len, todo, graph);
		//print(graph);
		List<Edge> edges = getEulerCircleString(graph, start);
		buf = new StringBuilder(start);
		for(Edge e : edges){
			buf.append(e.c);
		}
		return buf.toString();
	}

	private void print(Map<String, Set<Edge>> graph) {
		for(Entry<String, Set<Edge>> entry : graph.entrySet()){
			System.out.print("From: " + entry.getKey());
			System.out.println();
			for(Edge e : entry.getValue()){
				System.out.println(e.c + " -> " + e.toVal);
			}
		}
	}

	private void buildGraph(char[] cs, int len, 
			Queue<String> todo, Map<String, Set<Edge>> graph) {
		while(!todo.isEmpty()){
			String val = todo.poll();
			if(graph.containsKey(val)){
				continue;
			}
			StringBuilder next = new StringBuilder(val);
			next.deleteCharAt(0);
			Set<Edge> edges = new HashSet<Edge>();
			for(char c : cs){
				next.append(c);
				String nextVal = next.toString();
				edges.add(new Edge(c, nextVal)); //allow self loop
				todo.add(nextVal);
				next.deleteCharAt(next.length()-1);
			}
			graph.put(val, edges);
		}
	}
	
	private List<Edge> getEulerCircleString(Map<String, Set<Edge>> graph, String start) {
		List<Edge> edges = new ArrayList<>();
		Map<String, List<Edge>> circle = new HashMap<>(); //dp to remember circle of each node 
		
		
		
		return edges;
	}

}

