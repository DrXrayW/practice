package xray.leetcode.array.matrix;

import java.util.*;

public class SpiralMatrix01 {
	/*
	 */
	public List<Integer> spiralOrder(int[][] matrix) {
		List<Integer> elements = new ArrayList<Integer>();
		if (matrix.length == 0){
			return elements;
		}
		int m = matrix.length;
		int n = matrix[0].length;
		int row = 0, col = -1; //col = -1 because first move is right
		/*
		 * every time goes to the end
		 * every time the length of the next move -1
		 * every time needs to decide whether we have the next move or not
		 */
		while (true) {
			for (int i = 0; i < n; i++) { 
				col++;
				elements.add(matrix[row][col]);
			}
			m--;
			if (m == 0){
				break;
			}
			for (int i = 0; i < m; i++) {
				row ++;
				elements.add(matrix[row][col]);
			}
			n--;
			if (n == 0){
				break;
			}
			for (int i = 0; i < n; i++) {
				col--;
				elements.add(matrix[row][col]);
			}
			m--;
			if (m == 0){
				break;
			}
			for (int i = 0; i < m; i++) {
				row--;
				elements.add(matrix[row][col]);
			}
			n--;
			if (n == 0){
				break;
			}
		}
		return elements;
	}
}
