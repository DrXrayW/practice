package xray.leetcode.range;
import java.util.*;
/*
 * 
 * NOTE that the range given is lower and upper bound, so it is not possible that the range starts within 
 * (if that assumption is not true, then we need to find the correct position first)
 * 
 * IDEA, generally, if there is a gap between the two numbers, i.e. cur - pre > 1, then we need to output the range [pre+1, cur+1]
 * TIP pre+1 and cur+1 could be the same one, create a function to handle this
 * 
 * However, this won't handle the case where the lower and/or upper bounds not in the array
 * This is dealt with this trick: insert a lower bound -1 to the left, and a upper bound +1 to the right. 
 * Now it is generally getting all the gaps in an array.
 *
 *    (-1) 3,  5,  6,  7,  10 (11)
 *  
 * NOTE that we do not need to actually insert to the array, 
 * Just set the pre = start - 1 for the left, and actually loop into i<=vals.length for an extra digit,
 * when it is the added bit, assume cur = end + 1
 * 

 * 
 * 
 */
public class MissingRanges {
    public List<String> findMissingRanges(int[] vals, int start, int end) {
        List<String> res = new ArrayList<String>();
        if(vals==null){
            return res;
        }
        if(vals.length==0){
            res.add(getRange(start, end));
            return res;
        }
        // setting boundary    (-1) 3,  5,  6,  7,  10 (11)
        int pre = start-1; //handle the first one, boundary for first
        for(int i=0;i<vals.length;i++){ //include the last
            int cur = i==vals.length ? end+1 : vals[i];  //handle the last, boundary for last
            
            if( (cur-pre)>1 ){     //if gap > 1, then add range
                res.add(getRange(pre+1, cur-1));
            }
            pre = cur;
        }
        return res;
    }
    
    private String getRange(int start, int end){
        if(start==end){
            return ""+start;
        }else{
            return start + "->" + end;
        }
    }
}	
