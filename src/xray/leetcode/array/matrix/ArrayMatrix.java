package xray.leetcode.array.matrix;

public class ArrayMatrix {
    public static void main(String[] args) {
    	int[] arr = new int[]{1, 2, 3, 4, 5, 6};
    	ArrayMatrix am = new ArrayMatrix(3, 2, arr);
    	am.transposeInPlace();
    	am.output();
    	am.outputSequence();
    }
    
	private int[] arr;
	private int rowCount;
	private int colCount;
	private boolean transpose = false;
	public ArrayMatrix(int rowCount, int colCount, int[] initArr){
		this.rowCount = rowCount;
		this.colCount = colCount;
		arr = new int[rowCount*colCount];
		for(int i=0;i<rowCount;i++){
			for(int j=0;j<colCount;j++){
				int index = getIndex(i,j);
				arr[index] = initArr[index];
			}
		}
	}
	
	public int getRowCount(){
		if(transpose){
			return colCount;
		}
		return rowCount;
	}
	
	public int getColCount(){
		if(transpose){
			return rowCount;
		}
		return colCount;
	}
	
	public void outputSequence(){
		System.out.println("==Matrix Sequence==");
		for(int i=0;i<getRowCount();i++){
			for(int j=0;j<getColCount();j++){
				System.out.print(arr[getIndex(i,j)] + ",");
			}
		}
		System.out.println();
	}
	
	public void output(){
		System.out.println("==Matrix==");
		for(int i=0;i<getRowCount();i++){
			for(int j=0;j<getColCount();j++){
				System.out.print(arr[getIndex(i,j)] + ",");
			}
			System.out.println();
		}
	}
	
	int getIndexFromTranspose(int transIndex) // 求前驱 //where should I grab the value to put in the current index 
	{
		int colCountT = rowCount;
		int colT = transIndex % colCountT;
		int rowT = transIndex / colCountT;
		
		int row = colT;
		int col = rowT;
	    return row * colCount + col;
	}
	 
	int getTransposeIndex(int index) // 求后继 //where my current node/value should be put in the transpose index
	{
		int row = index / colCount;
		int col = index % colCount;
		//swap row and col for transpose index
		//int colCountT = rowCount;
		//int rowT = col;
		//int colT = row;
		return getIndex(col, row, rowCount);
	}
	
	private int getIndex(int row, int col, int colCount){
		return row * colCount + col;
	}
	 
	void moveLoop(int index) // 处理环
	{
	    int holder =  arr[index];
	    int cur = index;
	    int pre = getIndexFromTranspose(index); //pre is that the value of which index should go to the current index, i.e. where to grab to put in the current index
	    while(index != pre) //until where to grab is the init value, where we already saved it in holder
	    {
	    	arr[cur] =  arr[pre];
	        cur = pre;
	        pre = getIndexFromTranspose(cur);
	    }
	    arr[cur] = holder;
	}
	 
	void transposeInPlace()  // 转置
	{
		
	    for(int i = 0; i < rowCount; i++)
	    {
	    	for(int j=0 ; j < colCount ; j++){
	    		int index = getIndex(i,j);
		        int next = getIndexFromTranspose(index); //where my current node should be put in the transpose index
		        /*
		         * 若大于说明已处理过, only process when there is any next > index, 
		         * if all next is less than or equal to the current one, 
		         * then some previous node in the same loop must have processed the current node, 
		         * as it must have some at some point has currentIndex as next > some previous index 
		         * 
		         * 
		         */
		        
		        /*
		         * after this while, only two possibilities: index == next, or index > next
		         * 
		         * So the logic here is that, 
		         * Ignoring any next in the loop that is greater than the current one. 
		         * If there is at lease one next < current index, that means this loop has been processed, so go next. 
		         * 
		         * KEY OBSERVATION: if any of the loop contains a index that is smaller than this one then ignore this loop, as it must have been processed
		         * 
		         * Or, it should reach back, i.e. index == next, so process the loop.
		         * 
		         */
		        while(index < next)   
		        {
		            next = getIndexFromTranspose(next);
		        }
		        if(next == index)
		        {
		            moveLoop(index);
		        }
	    	}
	    }
	    swapRowCountAndColCount();
	}	

	private void swapRowCountAndColCount() {
		int tmp = rowCount;
		rowCount = colCount;
		colCount = tmp;
	}

	private void swap(int i, int j) {
		int index1 = getIndex(i,j);
		int index2 = getIndex(j,i);
		int tmp = arr[index1];
		arr[index1] = arr[index2];
		arr[index2] = tmp;
	}

	public void transposeBySetting(){
		this.transpose  = !this.transpose;
	}
	
	public int getAt(int row, int col){
		return arr[getIndex(row, col)];
	}
	
	public void setAt(int row, int col, int value){
		arr[getIndex(row, col)] = value;
	}
	
	
	/*
	 * if we do it by setting, remember that swapping i and j is enough
	 * as it is already converted to the old way, 
	 * do not touch the transposed count, but we need to use the old count 
	 */

	private int getIndex(int row, int col){
		if(transpose){
			int tmp = row;
			row = col;
			col = tmp;
		}
		int index = row * colCount + col;
		return index;
	}
}
