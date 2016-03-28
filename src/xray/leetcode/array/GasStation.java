package xray.leetcode.array;

public class GasStation {
	/*
	 * There is a very important observation for this one:
	 * 
	 * If A as a start can reach B, then A must be better than B.
	 * That would mean that all station reachable by A is no better than A. 
	 * 
	 * Therefore:
	 * 
	 * 1. We can safely discard the current start to the current station upon sum going below 0;
	 * 2. Due to the uniqueness of the solution, we know that all the nodes before the final start are bad ones, 
	 * we also know that the start we have reaches to the end of the path, which means it is the best.  
	 * 
	 *	last question: why is total enough to determine whether we have a solution?
	 *
	 *  we know that the start is the best, we know when total < 0 , there is no solution
	 *  so why total >=0 then start has a solution?
	 *  
	 *  because for the start to fail, we have to have a sequence continuing with the final start but reaches fun < 0 before reaching to the final start. 
	 *  
	 *  that sequence must contribute a negative sum to the path.  
	 *  
	 *  we know that the total >=0, that means the sequence after the negative sequence and sum, must be positive. 
	 *  
	 *  The best start in the sequence must be able to reach the final start then (or it must be blocked by a negative sequence, which is contradict with the sum is positive ).
	 *  
	 *  This is contradict to the fact that all nodes before start, after 0 are eliminated. 
	 *  
	 *  Therefore, when total >=0, final start can reach itself
	 *  
	 *  ---
	 *  IN SHORT: from the begining adding up gas - cost, if reach negative, start from the next, meaning the current one is a show stopper for all the previous ones. return the beginning if it can reach the end.  

(We have the fact that: if A can reach B, then A is no worse than any station after A to B, as the prefix always contributes at least 0). 

Note that we cannot have this: 
-100 1 2 -6 1000 1000

      *  we know that the start is the best, we know when total < 0 , there is no solution
     *  so why total >=0 then start has a solution?

We know that the current one can reach the first one, and also if there is any, the path from first to the selected one must contains sequences of negative ones (that is how they fail), if the selected one cannot make it, it means that some of these sequences have failed the guy, with or without all negative sequences counted.  That suggests that total < 0, which is wrong. 


	 * 
	 */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        if( (gas==null)||(gas.length==0) ||
            (cost==null)||(cost.length==0) ||
            (gas.length!=cost.length) ){
                return -1;
            }
            
        int start = 0;
        int sum = 0;
        int total = 0;
        for(int i=0;i<gas.length;i++){
            int gain = gas[i] - cost[i];
            sum += gain;
            total += gain;
            if(sum<0){
                start = i + 1;  //not even the current, note that when i+1 go over gas.length, that means all failed test, we must have total <0. 
                sum = 0;
            }
        }
        return total >=0 ? start : -1;
    }
}

