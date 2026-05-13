/*
You are given a string word. A letter c is called special if it appears both in lowercase and uppercase in word, and every lowercase occurrence of c appears before the first uppercase occurrence of c.
Return the number of special letters in word.

Example 1:
Input: word = "aaAbcBC"
Output: 3
Explanation:
The special characters are 'a', 'b', and 'c'.

Example 2:
Input: word = "abc"
Output: 0
Explanation:
There are no special characters in word.
*/
class Solution {
    public int numberOfSpecialChars(String word) {
        int[] lastLower = new int[26];
        int[] firstUpper = new int[26];
        for (int i = 0; i < 26; i++) {
            lastLower[i] = -1;
            firstUpper[i] = -1;
        }
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (Character.isLowerCase(ch)) {
                lastLower[ch - 'a'] = i;
            } else {
                int idx = ch - 'A';
                if (firstUpper[idx] == -1) {
                    firstUpper[idx] = i;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < 26; i++) {
            if (lastLower[i] != -1 &&
                firstUpper[i] != -1 &&
                lastLower[i] < firstUpper[i]) {
                count++;
            }
        }
        return count;
    }
}
