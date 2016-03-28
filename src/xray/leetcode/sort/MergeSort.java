package xray.leetcode.sort;

public class MergeSort {

	public static void main(String[] args) {
		MergeSort s = new MergeSort();
		int[] num = {2,1};
		s.mergeSort(num);;
		return;
	}
	
	public void mergeSort(int[] num){
		if(num==null){
			return;
		}
		int len = num.length;
		if(len<2){
			return;
		}
		mergeSort(num, 0, len-1);
	}

	private void mergeSort(int[] num, int start, int end) {
		if(start>=end){ //invalid or single element, do nothing
			return;
		}
		int mid = (start + end) / 2;
		int leftStart = start;
		int leftEnd = mid;
		int rightStart = mid + 1;
		int rightEnd = end;
		
		mergeSort(num, leftStart, leftEnd);
		mergeSort(num, rightStart, rightEnd);
		merge(num, leftStart, leftEnd, rightStart, rightEnd);
	}

	private void merge(int[] num, 
			int leftStart, int leftEnd, 
			int rightStart,	int rightEnd) {
			
		int[] tmp = new int[num.length]; //array for merge
		int left = leftStart;
		int right = rightStart;
		int i = leftStart; //tmp index
		while(left<=leftEnd&&right<=rightEnd){
			if(num[left]<=num[right]){
				tmp[i] = num[left];
				i++;
				left++;
			}else{ //num right < num left
				tmp[i] = num[right];
				i++;
				right ++;
			}
		}
		//copy remaining ones from left part
		while(left<=leftEnd){
			tmp[i]=num[left];
			left++;
			i++;
		}
		//copy remaining ones from right part
		while(right<=rightEnd){
			tmp[i]=num[right];
			right++;
			i++;
		}
		
		//copy tmp to num
		for(int j=leftStart;j<=rightEnd;j++){
			num[j] = tmp[j];
		}
	}
}
