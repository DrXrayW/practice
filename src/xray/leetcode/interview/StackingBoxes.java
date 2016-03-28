package xray.leetcode.interview;

import java.util.*;

public class StackingBoxes {
	public static void main(String[] args) {
		StackingBoxes s = new StackingBoxes();
		List<Box> boxes = new ArrayList<>();
		boxes.add(new Box(4,5,1));
		//boxes.add(new Box(1,5,1));
		boxes.add(new Box(2,2,1));
		boxes.add(new Box(2,5,1));
		boxes.add(new Box(3,3,1));
		int x = s.getMinStackingArea(boxes);
		System.out.println(x);
	}
	
	public int getMinStackingArea(List<Box> boxes){
		int area = 0;
		for(Box box : boxes){
			area+= box.area;
		}
		
		boxes.sort(new Comparator<Box>(){
			@Override
			public int compare(Box b1, Box b2){
				return b2.area - b1.area;
			}
		});
		
		boolean[] used = new boolean[boxes.size()];
		for(int i=1;i<boxes.size();i++){
			Box cur = boxes.get(i);
			for(int j=0;j<i;j++){
				if(used[j]){
					continue;
				}
				Box pre = boxes.get(j);
				if(pre.depth>=cur.depth&&pre.width>=cur.width){ //already sorted, so the first stackable is the max area
					System.out.println("Stacking box " + cur + " on " + pre + "Saving:" + cur.area );
					area -= cur.area;
					used[j] = true;
					break;
				}
			}
		}
		
		return area;
	}
	
}
