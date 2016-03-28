package xray.leetcode.geometry;

import java.util.*;
public class ConvexHull {
	public static void main(String[] args) {
		Point[] points = new Point[]{
			new Point(2,3), //true
			new Point(5.5, 1), //false
			new Point(4,4),
			new Point(6,3),
			new Point(5,1),
			new Point(3,1),
			new Point(6, 7), //false
			new Point(4, 3), //true
			new Point(4, 2) //true
		};
		
		ConvexHull s = new ConvexHull();
		List<Point> ch = s.getConvexHull(points);
		//List<Point> ch = Arrays.asList(points);
		System.out.println(ch.toString());
		
		
	}

	private List<Point> getConvexHull(Point[] points) {
		Point bottomMost = points[0];
		for(Point point : points){
			if(point.y<bottomMost.y){
				bottomMost = point;
			}else if(point.y==bottomMost.y){
				if(point.x < bottomMost.x){
					bottomMost = point;
				}
			}
		}
		final Point b = bottomMost;
		System.out.println(b);
		/*
		SortedSet<Point> sort = new TreeSet<>(new Comparator<Point>(){
			@Override
			public int compare(Point o1, Point o2) {
				return new IntersectSegment().getOrientationValue(b, o1, o2);
			}
		});
		*/ 
		Arrays.sort(points, new Comparator<Point>(){
			@Override
			public int compare(Point p1, Point p2) {
				int o =  new IntersectSegment().getOrientationValue(b, p1, p2);
				if(o==0){
					double diff = p1.x - p2.x;
					if(diff==0d){
						return 0;
					}else if(diff>0d){
						return 1;
					}else{
						return -1;
					}
				}
				return o;
			}
		});
		//ignore p such that o pre, p, next >=0
		List<Point> ch = new ArrayList<Point>();
		ch.add(bottomMost);
		IntersectSegment is = new IntersectSegment();
		Point pre = bottomMost;
		for(int i=1;i<points.length;i++){
			Point next = points[i==points.length-1 ? 0 : i+1];
			if(is.orientation(pre, points[i], next)<0){ //good points
				ch.add(points[i]);
				pre = points[i];
			}
		}
		
		return ch;
	}
}
