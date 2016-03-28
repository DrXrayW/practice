package xray.leetcode.geometry;

public class IntersectSegment {
	public static void main(String[] args) {
		IntersectSegment s = new IntersectSegment();
		System.out.println(""+s.doIntersect(new Point(1,1), new Point(6,3), new Point(5,2), new Point(0, 7)));
		System.out.println(""+s.doIntersect(new Point(1,1), new Point(3,3), new Point(2,2), new Point(7, 7)));
		System.out.println(""+s.doIntersect(new Point(1,1), new Point(3,3), new Point(5,5), new Point(7, 7)));
		System.out.println(""+s.doIntersect(new Point(1,1), new Point(3,3), new Point(2,2), new Point(1, 7)));
		System.out.println(""+s.doIntersect(new Point(1,1), new Point(3,3), new Point(4,4), new Point(1, 7)));
		/*
		System.out.println(""+s.orientation(new Point(1,1), new Point(2,2), new Point(3,3)));
		System.out.println(""+s.orientation(new Point(1,1), new Point(2,2), new Point(2,3)));
		System.out.println(""+s.orientation(new Point(1,1), new Point(2,2), new Point(5,3)));
		*/
	}

	
	public boolean doIntersect(Point s1, Point e1, 
			Point s2, Point e2) {
		//
		int o1 = orientation(s1, e1, s2); 
		int o2 = orientation(s1, e1, e2);
		
		int o3 = orientation(s2, e2, s1);
		int o4 = orientation(s2, e2, e1);
		
		if(o1!=o2&&o3!=o4){
			/*not equal, meaning one of them maybe 0, but at least one is not zero.
			 * so one point on one side, the other one either on the line or the other side. 
			 * 
			*/
			return true;
		}
		
		/*
		 * just check to make sure the co-linears are within range 
		 */
		if(o1==0&&onSegment(s1, s2, e1)){return true;}
		if(o2==0&&onSegment(s1, e2, e1)){return true;}
		if(o3==0&&onSegment(s2, s1, e2)){return true;}
		if(o4==0&&onSegment(s2, e1, e2)){return true;}
		
		return false;
	}
	
	private boolean onSegment(Point p1, Point p2, Point p3){
		return Math.min(p1.x, p3.x)<=p2.x && Math.max(p1.x, p3.x)>=p2.x &&
				Math.min(p1.y, p3.y)<=p2.y && Math.max(p1.y, p3.y)>=p2.y ; 
	}
	
	/*
	 * Property of orientation:
	 * <0, : p3 is making a left turn from the line of p1 to p2
	 * >0, : p3 is making a right turn from the line of p1 to p2
	 * =0, : p3 is on the line of p1 and p2
	 * 
	 */
	public int orientation(Point p1, Point p2, Point p3){
		int d = getOrientationValue(p1, p2, p3);
		if(d<0){return -1;}
		if(d>0){return 1;}
		return 0;
	}
	
	public int getOrientationValue(Point p1, Point p2, Point p3){
		/* two points equation: (x - x2)*(y2 - y1) - (y - y2)*(x2 - x1) = 0
		 * say we have x0 on the line of x1,y1, x2,y2, and y0 = y3
		 * then we have (x0 - x2)*(y2 - y1) - (y0 - y2)*(x2 - x1) = 0
		 * now if we have (x3 - x2)*(y2 - y1) - (y3 - y2)*(x2 - x1) > 0
		 * (x3 - x0)*(y2 - y1) >0
		 * 
		 *  suppose y2 > y1
		 *  then x3 must be > x0, meaning x3 at right side of the x0 the line
		 *  
		 *  suppose y2 < y1, x3 at left side of x0, and the line, however, since y is backwards, 
		 *  in terms of turnning left or right, the result is the same
		 *  
		 *  y2 = y1, we can do the same trick with x0 = x3, etc. 
		 * 
		 * 
		 */
		Double value = (p3.x - p2.x)*(p2.y - p1.y) -  (p3.y - p2.y) * (p2.x - p1.x); 
		return value.intValue();
	}
	
}
