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
class Solution {
    public int numberOfSpecialChars(String word) {
        int lowerMask = 0;
        int upperMask = 0;
        for (char ch : word.toCharArray()) {
            if (Character.isLowerCase(ch)) {
                lowerMask |= (1 << (ch - 'a'));
            } else {
                upperMask |= (1 << (ch - 'A'));
            }
        }
        int common = lowerMask & upperMask;
        return Integer.bitCount(common);
    }
}
