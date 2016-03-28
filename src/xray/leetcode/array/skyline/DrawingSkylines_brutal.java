package xray.leetcode.array.skyline;

import java.util.ArrayList;
import java.util.List;

public class DrawingSkylines_brutal {
  public static void main(String[] args) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
    	  n = 3;
      }
      List<Skyline> A = new ArrayList<>();
      A.add(new Skyline(0, 3, 2));
      List<Skyline> ans = drawingSkylines(A);
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
   */
  public static List<Skyline> drawingSkylines(List<Skyline> skylines) {
	  int[] h = new int[10000]; //height for each x position, init to 0
	  
	  for(Skyline skyline : skylines){
		  for(int i=skyline.left;i<=skyline.right;i++){
			  h[i] = Math.max(h[i], skyline.height);
		  }
	  }
	  
	  List<Skyline> res = getSkylines(h);
	  return res;
  }

  	/*
  	 * 1. include all skylines within range
  	 */
	private static List<Skyline> getSkylines01(int[] h) {
		List<Skyline> res = new ArrayList<Skyline>();
		int left = 0;
		for(int i=0;i<=h.length;i++){ //trick include the last illegal index
			if(i==h.length||h[i]!=h[left]){ //trick: do this when starting a new one, or reaching the end
				res.add(new Skyline(left, i-1, h[left]));
				left = i;
			}
		}
		
		return res;
	}
	
  	/*
  	 * 2. exclude 0 heights
  	 */
	private static List<Skyline> getSkylines02(int[] h) {
		List<Skyline> res = new ArrayList<Skyline>();
		int left = 0;
		for(int i=0;i<=h.length;i++){ //trick include the last illegal index
			if(i==h.length||h[i]!=h[left]){ //trick: do this when starting a new one, or reaching the end
				if(h[left]!=0){
					res.add(new Skyline(left, i-1, h[left]));
				}
				left = i;
			}
		}
		return res;
	}
	
  	/*
  	 * 3. exclude the heading and tailing 0 heights
  	 */
	private static List<Skyline> getSkylines(int[] h) {
		List<Skyline> res = new ArrayList<Skyline>();
		int left = 0;
		for(int i=0;i<=h.length;i++){ //trick include the last illegal index
			if(i==h.length||h[i]!=h[left]){ //trick: do this when starting a new one, or reaching the end
				if(h[left]==0&&(left==0||i==h.length)){
					//ignore
				}else{
					res.add(new Skyline(left, i-1, h[left]));
				}
				left = i;
			}
		}
		return res;
	}

  
}