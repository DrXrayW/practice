package xray.leetcode.array.matrix;

import java.util.*;

import util.Ref;

public class SpiralMatrix02 {
	/*
	 */
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> elements = new ArrayList<Integer>();
		if (matrix.length == 0){
			return elements;
		}
		Ref<Integer> rowCount = new Ref<>(matrix.length);
		Ref<Integer> colCount = new Ref<>(matrix[0].length);
		
		Ref<Integer> row = new Ref<>(0);
		Ref<Integer> col = new Ref<>(-1);
		
		
		/*
		 * every time goes to the end
		 * every time the length of the next move -1
		 * every time needs to decide whether we have the next move or not
		 */
		while (true) {
			if(addLine(elements, matrix, colCount, row, col, col, 1)){
				break;
			}
			if(addLine(elements, matrix, rowCount, row, col, row, 1)){
				break;
			}
			if(addLine(elements, matrix, colCount, row, col, col, -1)){
				break;
			}
			if(addLine(elements, matrix, rowCount, row, col, row, -1)){
				break;
			}
		}
		return elements;
	}
	
	
	
	//return true if it is the end
	private boolean addLine(List<Integer> elements, int[][] matrix, Ref<Integer> bound, 
			Ref<Integer> row, Ref<Integer> col, Ref<Integer> moveIndex, int step){
		for (int i = 0; i < bound.value; i++) {
			moveIndex.value += step;
			elements.add(matrix[row.value][col.value]);
		}
		
		bound.value--;
		if (bound.value == 0){
			return true;
		}
		return false;
	}
}
