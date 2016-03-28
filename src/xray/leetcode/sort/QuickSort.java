package xray.leetcode.sort;

public class QuickSort {

	public static void main(String[] args) {
		QuickSort s = new QuickSort();
		int[] num = {1,2,3,2,1};
		//s.quickSort(num, 0, num.length-1);
		int k =2;
		int value = s.quickSelect(num, k-1, 0, num.length-1);
		
		return;
	}
	
	public int quickSelect(int[] num, int k, int start, int end){
		int i = partition(num, start, end);
		if(i==k){
			return num[i];
		}
		
		if(i>k){
			return quickSelect(num, k, start, i-1);
		}
		
		//i<k
		return quickSelect(num, k-i-1, i+1, end);
	}
	
	public void quickSort(int[] num){
		if(num==null){
			return;
		}
		int len = num.length;
		if(len<2){
			return;
		}
		quickSort(num, 0, len-1);
	}
	
	private void quickSort(int[] num, int left, int right) {
		if(left>=right){ //invalid or single, no op
			return; 
		}
		
		int mid = partition(num, left, right);
        quickSort(num, left, mid - 1);
		quickSort(num, mid, right);
	}
	
	int partition(int[] num, int left, int right)
	{
	      int i = left, j = right;

	      int pivot = num[(left + right) / 2];
	     
	      while (i <= j) {
	            while (num[i] < pivot){
	                  i++;
	            }
	            while (num[j] > pivot){
	                  j--;
	            }
	            if (i <= j) {
	            	//swap num i and j
	      	      	int tmp = num[i];
	                num[i] = num[j];
	                num[j] = tmp;
	                i++;
	                j--;
	            }
	      };
	     
	      return i; //return i for quick select to use
	}
	 
}
