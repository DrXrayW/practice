package xray.leetcode.sort;

public class MaximumGap {
    public int maximumGap(int[] num) {
        int maxGap = 0;
        if(num==null){
            return maxGap;
        }
        int len = num.length;
        if(len<2){
            return maxGap;
        }
        
        //bucket sort
        int k = len - 1;
        int max = num[0];
        int min = num[0];
        for(int i=1;i<len;i++){
        	max = Math.max(max, num[i]);
        	min = Math.min(min, num[i]);
        }
        int extra = (max - min)%k>0 ? 1 : 0;
        int bucketWidth = (max - min) / k + extra; //in case max - min has remainders dividing k, we are aiming for empty bucket so the size doesn't matter
        int[] bucketMax = new int[k]; 
        int[] bucketMin = new int[k];
        for(int i=0;i<k;i++){
        	bucketMax[i] = -1; //-1 for no value
        	bucketMin[i] = Integer.MAX_VALUE;
        }
        
        //bucket sort, remember we are excluding min and max
        for(int i=0;i<len;i++){
            int value = num[i];
            if(value>min && value<max){
            	int bi = (value - min) / bucketWidth; //[, ) if num[i] == bucketWidth, then belongs to the 2nd, i.e. index 1
            	bucketMax[bi] = Math.max(bucketMax[bi], value);
            	bucketMin[bi] = Math.min(bucketMin[bi], value);
            }
        }
        
        //now the gap
        int curMin = min;
        for(int i=0;i<k;i++){
        	if(bucketMax[i]!=-1){
        		maxGap = Math.max(maxGap, bucketMin[i] - curMin);
        		curMin = bucketMax[i];
        	}
        }
        maxGap = Math.max(maxGap, max - curMin);
        
        return maxGap;
    }
}
