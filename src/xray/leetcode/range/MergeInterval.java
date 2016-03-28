package xray.leetcode.range;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * Given a collection of intervals, merge all overlapping intervals.

For example,
Given [1,3],[2,6],[8,10],[15,18],
return [1,6],[8,10],[15,18].

IN SHORT
sort by start
The first insert is the first one
since it is sorted, we only consider the case where the interval start is after the end, or overlapping case, 
as current start must be the left bound of everything we are process (excluding those we have passed)

 
 */
public class MergeInterval {
    public List<Interval> merge(List<Interval> intervals) {
        if((intervals==null)||(intervals.isEmpty())){  //TIP: include empty here, or it could be hard to handle in generic case
            return intervals;
        }
        //TIP: needs to sort first, remember how to write this
        Collections.sort(intervals, new Comparator<Interval>() {  
            @Override  
            public int compare(Interval o1, Interval o2) {  
                return o1.start - o2.start;     
            }  
        });  
        
        List<Interval> res = new ArrayList<Interval>(); 
        Interval insert = intervals.get(0);  //TIP: unlike the insert method, here we should always pick up the next one to insert
        for(Interval interval : intervals){ //since sorted, the current insert.start must be at the left bound of anything we see now
            if(insert.end < interval.start){ 
                res.add(insert);          //TIP: unlike the insert method, here we should always pick up the next one to insert
                insert = interval;
            }else{ //overlap cases
                //do not really care about the details, give me the merged range
                insert.start = Math.min(insert.start, interval.start);
                insert.end = Math.max(insert.end, interval.end);
            }
        }
         //TIP: this is still needed to lay down insert if it never get a chance to be put down. insert is never null
        res.add(insert);
        return res;
    }
}
