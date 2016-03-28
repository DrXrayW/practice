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
public class TextJustification01 {
    public List<String> fullJustify(String[] words, int L) {
        List<String> res = new ArrayList<String>();
        if (words == null || words.length == 0 || L < 0){
        	return res;
        }
        List<String> line = new ArrayList<String>();
        StringBuilder buf = new StringBuilder();
        int len = 0;
        for (int i = 0; i < words.length; i++) {
            if (len + line.size() + words[i].length() <= L) { //line size is word count and therefore space count
                line.add(words[i]); 
                len += words[i].length();
            } else {
                if (line.size() == 1) { // only 1 word in this line
                    buf = new StringBuilder(line.get(0));
                    for (int j = L - buf.length(); j > 0; j--){
                    	buf.append(" ");
                    }
                } else if (line.size() > 1) {
                    int div = (L - len) / (line.size() - 1);
                    int mod = (L - len) % (line.size() - 1);
                    buf = new StringBuilder(line.get(0)); // append first word
                    for (int j = 1; j < line.size(); j++) { // append remaining words
                        for (int k = 0; k < div; k++){
                        	buf.append(" ");
                        }
                        if (j <= mod){
                        	buf.append(" "); // append 1 more space
                        }
                        buf.append(line.get(j));
                    }
                }
                res.add(buf.toString());
                line.clear();
                line.add(words[i]); // next line
                len = words[i].length();
            }
        }
        // last line
        buf = new StringBuilder(line.get(0));
        for (int i = 1; i < line.size(); i++){
        	buf.append(" ");
        	buf.append(line.get(i));
        }
        for (int i = L - buf.length(); i > 0; i--){
        	buf.append(" ");
        }
        res.add(buf.toString());
        return res;
    }
}
