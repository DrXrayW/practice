package xray.leetcode.binarySearch;

/*
 * IN SHORT:
naive sort and count nlogn, but that would be bad, as we are searching.

Idea, if we compare the k/2 heads of the two arrays, we can always include all heads of the winning array, and the ones after the losing array. 

tips:
impose the length limit so that the first is always shorter. 

while doing k/2, cap it with min(a.length, k/2)

when a.length == 0, then pick the k-th from the 2nd. 

When k==1, return the first that is smaller

remember the median calculation as well. 


 * 
 * IDEA
 * generalized to getting k-th element in two sorted arrays
 * 
 * compare first k/2 elements of two arrays, 
 * 
 * if one is smaller one than the other one, 
 * that means the max count less than the smaller one is: what is in front of it k/2 -1, and the leading ones in the other half, k/2 - 1
 * that is k - 2, that means the smaller one is definitely in the first kth list
 * 
 * Also what is in front of this smaller one are in the first kth too.
 * 
 * Similarly the ones after the greater one are out of the game, as they all have k numbers smaller than them. 
 * 
 *  So that k/2 ones are selected!
 *  
 *  now, excluding that heading k/2, now the job is to find the remaining k/2 ones in the remaining two arrays with the selected ones cut. 
 * 
 *  Note that this conclusion also applies to this situation: when not using k/2 but any length that sum up to k
 *  
 *  A 0 ...  ia, length = ia+1
 *           v
 *  B 0 .... ib, length = ib+1
 *  
 *  we have k = ia + ib + 2
 *  
 *  assume A[ia] > B[ib]
 *  for A[ia+1], we know all the selected numbers are smaller than it, so exclude all after
 *  for B[ib], we know that it is among the top k-th because in the worst case, only 0 ... ia -1, are smaller than ib, but plus 0 ... ib -1, that is k - 2 numbers, so ib is in.
 *    
 *  
 *  
 *  
 *  DETAILs:
 *  
 *  1. Median: how to convert it to k
 *  
 *      0 1 2 3 4 5 6 7 8, len = 9
 *              ^                 9/2
 *                         len = 8, 
 *            ^ ^                    
 *  	if odd, then the (m + n ) / 2 index
 *      if even, then the and (m + n ) /2 - 1, (m + n ) / 2, 
 *
 *      if k is the count, then 
 *      odd (m + n) / 2 + 1
 *      even (m + n) /2, (m + n) / 2 + 1
 * 
 * 2. When one remaining array is empty, then the next kth elements are the kth element in the remaining one
 * 
 *  
 * 
 * 
 */
public class MedianofTwoSortedArrays {
	public static void main(String[] args) {
		MedianofTwoSortedArrays s = new MedianofTwoSortedArrays();
		int[] A={1, 2, 3};
		int[] B={2, 4, 5};
		double x = s.findMedianSortedArrays(A, B);
		return;
	}
	
	public static double findMedianSortedArrays(int A[], int B[]) {
		int m = A.length;
		int n = B.length;
		
		if ((m + n) % 2 != 0) // odd
			return (double) findKth(A, B, (m + n) / 2 + 1, 0, m - 1, 0, n - 1);
		else { // even
			return (findKth(A, B, (m + n) / 2 + 1, 0, m - 1, 0, n - 1) 
				+ findKth(A, B, (m + n) / 2 , 0, m - 1, 0, n - 1)) * 0.5;
		}
	}
	 
	public static int findKth(int A[], int B[], int k, 
		int aStart, int aEnd, int bStart, int bEnd) {
		
		//k is k-th so start at 0, if we need to implement for k starts at 1, then we need to reduce the k by 1, like this impl
		int aLen = aEnd - aStart + 1;
		int bLen = bEnd - bStart + 1;
		
	    if(aLen>bLen){ //TIP boundary protection: making sure A is always the shorter array left!!
	        return findKth(B, A, k, bStart, bEnd, aStart, aEnd);
	    }
		// Handle special cases
		if (aLen == 0){ //a is always shorter so A must be the first zero
			return B[bStart + k - 1];
		}
		if (k == 1){ 
			return A[aStart] < B[bStart] ? A[aStart] : B[bStart];
		}
		    			
		/*
		 * This is to prevent the k/2 need to be
		 */
		//int aMid = aLen * k / (aLen + bLen); // a's middle count
		int aCount = Math.min(aLen, k/2);	   //TIP get the end of comparing subarray, do not go over A's limit
    	int bCount = k - aCount; // b's middle count

		// make aMid and bMid to be array index
		int aMid = aStart + aCount - 1;
		int bMid = bStart + bCount - 1;
	 
		if (A[aMid] > B[bMid]) {
			k = k - bCount;
			aEnd = aMid;
			bStart = bMid + 1;
		} else {
			k = k - aCount;
			bEnd = bMid;
			aStart = aMid + 1;
		}
	 
		return findKth(A, B, k, aStart, aEnd, bStart, bEnd);
	}
}
