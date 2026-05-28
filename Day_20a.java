/*
You are given a string word. A letter is called special if it appears both in lowercase and uppercase in word.
Return the number of special letters in word.

Example 1:
Input: word = "aaAbcBC"
Output: 3
Explanation:
The special characters in word are 'a', 'b', and 'c'.

Example 2:
Input: word = "abc"
Output: 0
Explanation:
No character in word appears in uppercase.
*/
import java.util.*;
class Solution {
    public int numberOfSpecialChars(String word) {
        HashSet<Character> lower = new HashSet<>();
        HashSet<Character> upper = new HashSet<>();
        for (char ch : word.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                lower.add(ch);
            } else {
                upper.add(Character.toLowerCase(ch));
            }
        }
        int count = 0;
        for (char ch : lower) {
            if (upper.contains(ch)) {
                count++;
            }
        }
        return count;
    }
}
