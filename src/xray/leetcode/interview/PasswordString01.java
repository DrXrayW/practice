package xray.leetcode.interview;
/*
 * de bruijin sequence
 */
import java.util.*;
public class PasswordString01 {
	public static void main(String[] args) {
		PasswordString01 s = new PasswordString01();
		String str = s.getShortestPasswordString(new char[]{'0', '1'}, 5);
		System.out.println(str);
		return;
	}
	
	/*
	 * FKM algorithm
	 * 
	 * connecting all aperiodic prefixes in lexical order
	 */
	private String getShortestPasswordString(char[] cs, int n) {
		if(cs==null||cs.length==0||n==0){
			return "";
		}
		if(n==1){
			return new String(cs);
		}
		Set<String> visited = new HashSet<String>();
		SortedSet<String> prefix = new TreeSet<String>();
		PrefixReader reader = new PrefixReader(cs, n);
		while(reader.hasNext()){
			String pre = reader.read();
			if(!visited.contains(pre)){
				prefix.add(pre);
				StringBuilder built = new StringBuilder(pre);
				while(built.length()<=n){
					visited.add(built.toString());
					built.append(pre);
				}
			}
		}
		for(String x : prefix){
			System.out.println(x);
		}
		
		StringBuilder s = new StringBuilder();
		return s.toString();
	}
}

