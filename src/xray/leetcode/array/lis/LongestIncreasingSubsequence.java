package xray.leetcode.array.lis;

public class LongestIncreasingSubsequence {
	public static void main(String[] args) {
		//int[] arr = new int[]{0, 8, 4, 12, 2, 10, 6, 14, 1, 9, 5, 13};
		int[] arr = new int[]{1,2,3};
		LongestIncreasingSubsequence s = new LongestIncreasingSubsequence();
		
		@SuppressWarnings("unused")
		int[] res = s.getLIS(arr);
		s.debugArr(res, "lis");
		return;
	}
	public int[] getLIS(int[] arr){
		int lisLen = 0; //length of lis
		int len = arr.length;
		int[] pre = new int[len];
		int[] minTail = new int[len+1];
		
		for(int i=0;i<len;i++){
			System.out.println("===================================");
			System.out.println("i="+i);
			debug(arr, pre, minTail);
			
			/*
			 * find the length position in the min tail array where the current arr[i] can be added 
			 * 
			 * starting from 1 to lislen, 
			 * 	 
			 * note this kind of binary search:
			 * 
			 * . while loop for left<=right
			 * . if value of mid < current
			 * 		left = mid +1;
			 * 	 else
			 * 		right = mid -1;
			 * 
			 * after this, if there is any element < left, then left is always the largest such element +1, 
			 * 				if there is none, then left is always its init value 
			 *  
			 *  We want to put any element as the tail of 1, so left is init to 1
			 *  
			 *  if the value is not 1, then the largest element at k that is smaller than current i represents the min tail of the subsequence of k
			 *  so the current element should replace the k+1 element (as the k+1 element is not smaller than current i, or the index should have been moved to right) 
			 *  so that it is the new min tail 
			 *  
			 *  also we want to update lisLen if we have a new one
			 *  
			 *  The largest element smaller than the current one is the new element we want to extend, 
			 *  so note down that for the current i, the LIS ending at the element, should point to the previous element's index 
			 *  
			 *  note that when left == 1, we update the previous element index to minTail[1 - 1] = minTail[0] = 0, which does NOT really mean the previous index is 0
			 *  However, the value will never be used, as when we reconstruct the lis, we will stop at length 1, and won't look into the previous one. 
			 *  
			 *  Q: why do we have minTail[0] ?? looks to me that just make it easier so that length is index : true!
			 *  
			 *  Q: One may wonder when a smaller one is updated to a even smaller one, won't the larger ones get updated for their pre?
			 *  We do NOT need to update those, as the newer smaller ones won't serve the previous larger ones anyways, 
			 *  as the longest increasing subsequence requires the index to be increasing.  
			 *  
			 *  Q: why when left = 1, we do not need to compare whether it is min
			 *  because if we can reach 1, means the current 1 is no smaller than the current one, so that we can replace 
			 *  
			 */
			int left = 1; //why 1
			int right = lisLen;
			while(left<=right){
				int mid = (left + right) /2;
				if(arr[minTail[mid]]<arr[i]){
					left = mid + 1;
				}else{
					right = mid -1;
				}
			}
			
			int newLen = left;
			
			pre[i] = minTail[newLen - 1];
			minTail[newLen] = i;
			
			if(newLen>lisLen){
				lisLen = newLen;
			}
		}
		System.out.println("===================================");
		debug(arr, pre, minTail);
		
		//reconstruct
		int[] lis = new int[lisLen];
		int k = minTail[lisLen];
		for(int i=lisLen-1;i>=0;i--){
			lis[i] = arr[k];
			k = pre[k];
		}
		return lis;
	}
	
	private void debug(int[] arr, int[] pre, int[] minTail){
		System.out.println("----------------------------------------");
		debugArr(arr, "arr");
		debugArr(pre, "pre");
		debugArr(minTail, "minTail");
		debugArrValue(minTail, arr, "minTail Value");
	}
	
	private void debugArrValue(int[] arr, int[] valueArr, String name) {
		System.out.println(name);
		for(int i=0;i<arr.length;i++){
			System.out.print(i);
			System.out.print("\t");
		}
		System.out.println();
		for(int i=0;i<arr.length;i++){
			System.out.print(valueArr[arr[i]]);
			System.out.print("\t");
		}
		System.out.println();
		
	}
	
	private void debugArr(int[] arr, String name) {
		System.out.println(name);
		for(int i=0;i<arr.length;i++){
			System.out.print(i);
			System.out.print("\t");
		}
		System.out.println();
		for(int i=0;i<arr.length;i++){
			System.out.print(arr[i]);
			System.out.print("\t");
		}
		System.out.println();
		
	}
}
