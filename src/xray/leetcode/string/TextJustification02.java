package xray.leetcode.string;
import java.util.*;

/*
 * 
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words.

For example,
words: ["This", "is", "an", "example", "of", "text", "justification."]
L: 16.

Return the formatted lines as:
[
   "This    is    an",
   "example  of text",
   "justification.  "
]
Note: Each word is guaranteed not to exceed L in length.

click to show corner cases.

Corner Cases:
A line other than the last line might contain only one word. What should you do in this case?
In this case, that line should be left-justified.


 * IDEA:
 * get enough for the current line
 * 	process the current line
 * 		1. only one word
 * 		2. last line
 * 		3. not last line, more than one words
 * 
 * 
 */
public class TextJustification02 {
	public static void main(String[] args) {
		TextJustification02 s = new TextJustification02();
		List<String> x = s.fullJustify(new String[]{"a","b","c","d","e"}, 3);
		return;
	}
    public List<String> fullJustify(String[] words, int L) {
        List<String> res = new ArrayList<String>();
        if (words == null || words.length == 0 || L < 0){
        	return res;
        }
        List<StringBuilder> line = new ArrayList<StringBuilder>(); //words of the line with builder to append spaces to
        int remain = L;
        for (int i = 0; i < words.length; i++) {
        	String word = words[i];
        	int wordLen = word.length();
        	boolean firstWord = line.size() == 0;
            if (remain >= ((firstWord ? 0 : 1) + wordLen)) {
                if(!firstWord){
                	line.get(line.size()-1).append(" "); //add space to the last appended word
                	remain--;
                }
            }else {
                if (line.size() == 1) { // only 1 word in this line, left alignment
                	fillRight(line, remain);
                }else{
                	for(int j=0;j<remain;j++){
                		line.get(j % (line.size()-1)).append(" "); //excluding the last word
                	}
                }
                //reset
                addLine(line, res);
                line.clear();
                remain = L;
            }
            line.add(new StringBuilder(word));
            remain -= wordLen;
        }
        // last line
        fillRight(line, remain);
        addLine(line, res);
        return res;
    }
    
    private void fillRight(List<StringBuilder> line, int remain) {
        for (int i = 0; i< remain; i++){
        	line.get(line.size() - 1).append(" ");
        }
	}

	private void addLine(List<StringBuilder> line, List<String> res){
		StringBuilder buf = line.get(0); //reuse the first word as result
    	for(int i=1;i<line.size();i++){
    		buf.append(line.get(i).toString());
    	}
    	res.add(buf.toString());
    }
}
