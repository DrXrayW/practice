package xray.leetcode.enumeration;
import java.util.*;
/*
 * this is a successful use of the dfs template. 
 * 
 * there should be a dp version though
 */
public class PalindromePartitioning {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<List<String>>();
        if(s==null){
            return res;
        }
        Map<String, List<List<String>>> cache = new HashMap<String, List<List<String>>>();
        Problem p = new Problem(s, cache); //find k valid ip section in string 
        return p.solve();
    }
    
    class Problem{
        String s;
        List<List<String>> sols = new ArrayList<List<String>>();
        Map<String, List<List<String>>> cache;
        Problem(String s, Map<String, List<List<String>>> cache){
            this.s = s;
            this.cache = cache;
        }
        boolean isExtendable(){
            return s.length()>0;
        }

        List<List<String>> solve(){
        	if(cache.containsKey(s)){
        		return cache.get(s);
        	}
            if(!isExtendable()){
            	//fail! not extendable and not having a solution
                return sols;
            }else{
                //first loop construct candidate
                for(String can : getValidCandidates()){
                    //decide whether this is the solution
                	if(solvesProblem(can)){
                		addToSolutions(can);
                	}else{
	                    List<List<String>> subSols = getSubSolutions(can);
	                    addToSolutionFromSub(subSols, can);
                	}
                }
            }
            cache.put(s, sols);
            return sols;
        }
		private Set<String> getValidCandidates() {
			Set<String> set = new HashSet<String>();
            for(int l=1;l<=s.length();l++){
                String candidate = s.substring(0,l);
                if(isValid(candidate)){
                	set.add(candidate);
                }
            }
			return set;
		}
		private boolean isValid(String candidate){
		    //isPalindrome
		    //0 1 2 3 4 : 5 /2 = 2 -1 = 1
		    //0 1 2 3 : 4 /2 = 2 -1 = 1;
		    //so i=0;i<=(len / 2 -1)
		    //
		    int len = candidate.length();
		    for(int i=0;i<=(len/2-1);i++){
		        if(candidate.charAt(i)!=candidate.charAt(len-1-i)){
		            return false;
		        }
		    }
		    return true;
		}
		private boolean solvesProblem(String validCan) {
			return validCan.length() == s.length();
		}
		private void addToSolutions(String can) {
		    List<String> sol = new ArrayList<String>();
		    sol.add(can);
			sols.add(sol);
		}
    	private void addToSolutionFromSub(List<List<String>> subSols, String can) {
            for(List<String> subSol : subSols){
                List<String> sol = new ArrayList<String>();
                sol.add(can);
                sol.addAll(subSol);
                sols.add(sol);
            }
		}
		private List<List<String>> getSubSolutions(String can) {
			Problem subp = new Problem(s.substring(can.length(), s.length()), this.cache);
			return subp.solve();
		}
    }
}
