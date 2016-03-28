package xray.leetcode.range;
/*
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

You may assume that the intervals were initially sorted according to their start times.

Example 1:
Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].

Example 2:
Given [1,2],[3,5],[6,7],[8,10],[12,16], insert and merge [4,9] in as [1,2],[3,10],[12,16].

This is because the new interval [4,9] overlaps with [3,5],[6,7],[8,10].

IN SHORT:

create a new list to insert.
create one to insert, if not allowed to use the input one
use a flag to indicate whether it has been inserted, to be used at the end of loop
1. interva.end < insert.start : already passed the range, leave the current one alone
2. insert.end < interval.start: we should go before the current one, here is where we put the insert to the new list, also set the flag 
3. overlapping, just merge with the min start and the max end to the insert
use the flag for the pending insert.

 */
import java.util.ArrayList;
import java.util.List;

public class InsertInterval {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if(newInterval==null){
            return intervals;
        }
        /*
         * TIP create a new interval to be inserted, as it would be easier to change the values, unless I can change the input one
         * 
         */
        Interval insert = new Interval(newInterval.start, newInterval.end);  
        List<Interval> res = new ArrayList<Interval>(); //TIP: use a res, as shifting in list is not fun; TIP stick to res for the rest of the code
        if(intervals==null){
            res.add(insert);
            return res;
        }
        
        boolean inserted = false; //use a flag to see whether it has been used, to be used at loop end
        for(Interval interval : intervals){
            if(interval.end < insert.start){
                res.add(interval);
            }else if(insert.end < interval.start){
                if(!inserted){
                    res.add(insert);    
                    inserted = true;
                }
                res.add(interval); //we need to add the interval after the inserted, even if it has been inserted. 
                
            }else{ //overlap cases
                //do not really care about the details, give me the merged range
                insert.start = Math.min(insert.start, interval.start);
                insert.end = Math.max(insert.end, interval.end);
            }
        }
        if(!inserted){
            res.add(insert);
        }

        return res;
    }
}
