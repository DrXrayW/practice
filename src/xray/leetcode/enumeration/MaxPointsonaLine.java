package xray.leetcode.enumeration;
import java.util.*;
/*
 * IDEA 
 * 
 * for each point, scan OTHER points, create a map to count the slope k for each one, get the max count
 * 
 * NOTE: the first point count as one, also for duplicates, we need to count them for any lines found, 
 * so it is actually, 
 * dup + maxMapCount,  here dup = 1 for the original point
 * When max map count == 0, i.e. only dups or one point, then it is the number of dups. 
 * 
 * TIP: the slope is Double, two catches:
 * 1. when x1 = x2, it is infinity, we can use null to denote that, map can take null as a value
 * 2. when y1 = y2, it is 0, however, for double, it could be +0 or -0, so do the check, then set it to 0d
 * 
 * TIP: putting a number to map needs to check whether it is already in, build a helper function for that. 
 * 
 */
public class MaxPointsonaLine {
    public int maxPoints(Point[] points) {
        int max = 0;
        if(points==null||points.length==0){
            return max;    
        }
        boolean[] dup = new boolean[points.length];
        Map<Double, Integer> kmap = new HashMap<Double, Integer>();
        for(int i=0;i<points.length;i++){
            
            if(dup[i]){
                continue;
            }
            int dupnum = 1;
            kmap.clear();

            for(int j=i+1;j<points.length;j++){
                Double k = null;
                //same point
                if(points[i].x==points[j].x&&points[i].y==points[j].y){
                    dupnum++;
                    dup[i]=true;
                    continue;
                }
                
                if(points[i].x!=points[j].x){
                    if((double)points[j].y == (double)points[i].y){
                        k = 0d;
                    }else{
                        k = ((double)points[j].y - (double)points[i].y) / ((double)points[j].x - (double)points[i].x) ;
                    }
                }
                add(k, 1, kmap);
            }
            
            max = Math.max(max, getMaxCount(kmap, dupnum));
        }
        return max;
    }
    
    private int getMaxCount(Map<Double, Integer> kmap, int dupnum){
        int max = 0;
        for(Integer val : kmap.values()){
            max = Math.max(max, val);
        }
        return max + dupnum;
    }
    
    private void add(Double k, int count, Map<Double, Integer> kmap){
        Integer val = kmap.get(k);
        if(val==null){
            val = 0;
        }
        kmap.put(k, val+1);
    }
}
