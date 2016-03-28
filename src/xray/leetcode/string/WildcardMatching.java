package xray.leetcode.string;

/*
 * IDEA
 * note down the position of star and the char in the main string after the current match to *
 * moving forward, if about to fail, but still has a star then all move back, this time include the char in the main string (mark)
 * 
 * 
 * 
 * TIP in the end, if there are more * in pattern move forward
 * if we get to the end of j, then it is a match
 * 
 */
public class WildcardMatching {
	public boolean isMatch(String s, String p) {
        int i=0;
        int j=0;
        int mark=-1;
        int star=-1;
        int slen = s.length();
        int plen = p.length();
        while(i<slen){
            if( (j<plen)&&
                (p.charAt(j)=='?'||p.charAt(j)==s.charAt(i)) ){
                    i++;
                    j++;
            }else if(j<plen&&p.charAt(j)=='*'){
                star = j; //mark down the star position in j for a back track
                mark = i; //mark means the left bound of *, mark itself is not a match for the first time, as * could be empty
                j++; //only j move forward: skipping *, this is the same as j=star+1
            }else if(star!=-1){  //TIP this cannot be in the j<plen branch, because the next of a star in p may be nothing, but we still need to match the rest
                //i cannot match j, but we have star marked, that means we can try another approach, 
                mark++; //try to include one more char for *
                i=mark; //back track to mark, which is the left bound, note that i does not increase here. 
                j=star+1; //go back to the pattern after star, rematch, note that star is not updated, this is simply how to restart.
            }else{ //there is no star to save me
                return false;
            }
        }
        while(j<plen&&p.charAt(j)=='*'){ //TIP star can save anything in the previous loop, but j has to be finished to count as a match
            j++;
        }
        return j == plen;
	}
}
