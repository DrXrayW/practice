package xray.leetcode.array;

/*
 * child having higher rate than neighbor gets more candy than the neighbor
 * 
 * IN SHORT: 
left to right, if r[i] > r[i-1], then candy[i]=candi[i-1]+1, else, 1
right to left, if r[i] > [i + 1], candi[i]= max(candy[i], candy[i+1]+1),


 * 
 */
public class CandyNeighbor {
    public int candy(int[] ratings) {  
    	// we can call it DP
        if(ratings==null || ratings.length==0)  
        {  
            return 0;  
        }  
        int[] nums = new int[ratings.length];  
        int length = ratings.length;
        //at least 1 for each child
        for(int i=0;i<length;i++){
            nums[i] = 1;
        }
        
        //from left to right, satisfy the requirement
        for(int i=1;i<length;i++){
            if(ratings[i]>ratings[i-1]){
                nums[i] = nums[i-1] + 1;
            }
        }
        
        //from right to left, adjust the number if it is not good enough
        for(int i=length-2;i>=0;i--){
            if( (ratings[i]>ratings[i+1]) && (nums[i]<=nums[i+1]) )  {  //remember the not good enough part, do not always add 1
                nums[i] = nums[i+1] + 1;
            }
        }
        
        int total = 0;
        for(int i=0;i<length;i++){
            total+=nums[i];
        }
        
        return total;
        /**
         * question:
         * 1. why the right to left scan won't break the left result?
         * because the candy count can only be pushed higher by more consecutive increases, 
         * it will break when it drops, so the push won't go over a peak from the other side
         * it is only a matter of which side is higher, therefore, the shorter version of this by taking the max works.  
         */
    }  
}
