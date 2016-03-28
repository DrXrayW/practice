package xray.leetcode.geometry;

public class InsidePolygon {
	public static void main(String[] args) {
		Point[] poly = new Point[]{
			new Point(2,3),
			new Point(4,4),
			new Point(6,3),
			new Point(5,1),
			new Point(3,1),
		};
		
		Point[] points = new Point[]{
			new Point(2,3), //true
			new Point(5.5, 1), //false
			new Point(6, 7), //false
			new Point(4, 3), //true
			new Point(4, 2) //true
		};
		
		InsidePolygon s = new InsidePolygon();
		for(Point p : points){
			System.out.println(""+s.insidePolygon(poly, p));
		}
		
	}

	private boolean insidePolygon(Point[] poly, Point p) {
		assert(poly.length>2);
		double rightMostX = getRightMostX(poly);
		Point rightMost = new Point(rightMostX, p.y);
		IntersectSegment is = new IntersectSegment();
		Point pre = poly[poly.length-1];
		int intersect = 0;
		for(int i=0;i<poly.length;i++){
			intersect += is.doIntersect(pre, poly[i], p, rightMost) ? 1 : 0; 
		}
		return (intersect&1)!=0; //odd means inside
	}

	private double getRightMostX(Point[] poly) {
		double rightMostX = poly[0].x;
		for(Point p : poly){
			rightMostX = Math.max(rightMostX, p.x);
		}
		return rightMostX;
	}
}
