package xray.leetcode.enumeration;

import java.util.*;
/*
 * tried to use an extraction of logic here.
 * the extraction works here 
 * but it doesn't work for all cases.
 * 
 * the idea is correct though
 * 
 */
public class RestoreIPAddresses {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<String>();
        if(s==null){
            return res;
        }
        int len = s.length();
        if(len<4||len>12){
            return res;
        }
        
        Problem p = new Problem(s, 4); //find k valid ip section in string 
        return p.solve();
    }
    
    class Problem{
        int k;
        String s;
        boolean failed = false;
        List<String> sols = new ArrayList<String>();
        Problem(String s, int k){
            this.s = s;
            this.k = k;
        }
        boolean isExtendable(){
            return (k>0)&&(s.length()>0);
        }

        List<String> solve(){
            if(!isExtendable()){
                failed = true;
                return sols;
            }else{
                //first loop construct candidate
                for(String can : getValidCandidates()){
                    //decide whether this is the solution
                	if(solvesProblem(can)){
                		addToSolutions(can);
                		//continue; //continue if we want more solutions,
                		break; //break if we can stop at any, or other candidates are not possible
                	}
                    
                	//reduce
                    List<String> subSols = getSubSolutions(can);
                    //connect solutions
                    addToSolutionFromSub(subSols, can);
                }
                
            }
            return sols;
        }
        
		private void addToSolutionFromSub(List<String> subSols, String can) {
            for(String subSol : subSols){
                StringBuilder b = new StringBuilder(can);
                b.append(".");
                b.append(subSol);
                sols.add(b.toString());
            }
		}
		private List<String> getSubSolutions(String can) {
			Problem subp = new Problem(s.substring(can.length(), s.length()), k - 1);
			return subp.solve();
		}
		private void addToSolutions(String can) {
			sols.add(s);
		}
		private boolean solvesProblem(String validCan) {
			return validCan.length() == s.length() && k == 1;
		}
		private Set<String> getValidCandidates() {
			Set<String> set = new HashSet<String>();
            for(int l=1;l<=3&&l<=s.length();l++){
                String candidate = s.substring(0,l);
                if(isValid(candidate)){
                	set.add(candidate);
                }
            }
			return set;
		}
		
		private boolean isValid(String candidate){
			if(candidate.length()>1&&candidate.charAt(0) == '0'){
				return false;
			}
			int num = Integer.parseInt(candidate);
			return 0<=num && num<=255;
		}
    }
}
