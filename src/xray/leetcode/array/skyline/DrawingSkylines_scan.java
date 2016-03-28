package xray.leetcode.array.skyline;

import java.util.*;

public class DrawingSkylines_scan {
  public static void main(String[] args) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
    	  n = 3;
      }
      List<Skyline> A = new ArrayList<>();
      List<Skyline> ans;
      
      A.add(new Skyline(0, 3, 2));
      ans = drawingSkylines(A);
      output(A, ans);
      
      A.clear();
      A.add(new Skyline(0, 3, 2));
      A.add(new Skyline(1, 2, 3));
      ans = drawingSkylines(A);
      output(A, ans);
      
      A.clear();
      A.add(new Skyline(0, 3, 2));
      A.add(new Skyline(1, 2, 1));
      ans = drawingSkylines(A);
      output(A, ans);

      A.clear();
      A.add(new Skyline(0, 1, 2));
      A.add(new Skyline(3, 4, 1));
      ans = drawingSkylines(A);
      output(A, ans);
      
      A.clear();
      A.add(new Skyline(0, 1, 2));
      A.add(new Skyline(2, 3, 2));
      ans = drawingSkylines(A);
      output(A, ans);
	
      A.clear();
      A.add(new Skyline(1, 2, 2));
      A.add(new Skyline(2, 3, 2));
      ans = drawingSkylines(A);
      output(A, ans);
      
      A.clear();
      ans = drawingSkylines(A);
      output(A, ans);
		
      return;
  }

  private static void output(List<Skyline> a, List<Skyline> ans) {
	System.out.println("====");
	System.out.println("Input:");
	for(Skyline skyline : a){
		System.out.println(skyline);
	}
	
	System.out.println("Output:");
	for(Skyline skyline : ans){
		System.out.println(skyline);
	}
	
  }
  
/*
   * given a list of skylines: with left, right and height (not coordinates, but pixel positions)
   * find the top of everything
   * 
   * TIP:
   * make a change: so that the right endpoint is actually the real right + 1
   * therefore, on each endpoint pos, we can safely just add the left, and remove the right at the same time!
   */
  public static List<Skyline> drawingSkylines(List<Skyline> skylines) {
	  //deal with null or empty
	  List<Skyline> res = new ArrayList<Skyline>();
	  PriorityQueue<Endpoint> queue = new PriorityQueue<>();
	  for(Skyline skyline : skylines){
		  queue.add(new Endpoint(skyline, true));
		  queue.add(new Endpoint(skyline, false));
	  }
	  SortedMap<Integer, Set<Skyline>> top = new TreeMap<>(); //use map as treeset won't allow dup keys
	  //sorted by endpoints position from left to right
	  int preheight = 0; //init to 0 height
	  int start = -1; //init to -1 position as left
	  while(!queue.isEmpty()){
		  Endpoint endpoint = queue.poll();
		  int pos = endpoint.getPos();
		  addLeftRemoveRight(top, endpoint);
		  while(!queue.isEmpty()){
			  Endpoint topEndpoint = queue.peek();
			  if(topEndpoint.getPos()!=pos){
				  break;
			  }
			  queue.poll();
			  addLeftRemoveRight(top, topEndpoint);
		  }
		  int height = top.isEmpty()? 0 : top.lastKey(); //use last key as we want the largest 
		  if(queue.isEmpty()||height!=preheight){ //queue is empty : over the end
			  if(start!=-1){ //skipping the range starting from -1, it would be (-1, -1) or (-1, i), i is the last 0
				  res.add(new Skyline(start, pos - 1, preheight));
			  }
			  start = pos;
			  preheight = height;
		  }
	  }
	  
	  return res;
  }

  	private static void addLeftRemoveRight(SortedMap<Integer, Set<Skyline>> top, Endpoint endpoint) {
  		int height = endpoint.skyline.height;
  		if(endpoint.isLeft){
  			Set<Skyline> set = top.get(height);
  			if(set==null){
  				set = new HashSet<Skyline>();
  				top.put(height, set);
  			}
  			set.add(endpoint.skyline);
  		}else{
  			Set<Skyline> set = top.get(height);
  			set.remove(endpoint.skyline);
  			if(set.isEmpty()){
  				top.remove(height);
  			}
  		}
  	}
  
}