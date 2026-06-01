/*
You are given a string s.
Consider performing the following operation until s becomes empty:
For every alphabet character from 'a' to 'z', remove the first occurrence of that character in s (if it exists).
For example, let initially s = "aabcbbca". We do the following operations:
Remove the underlined characters s = "aabcbbca". The resulting string is s = "abbca".
Remove the underlined characters s = "abbca". The resulting string is s = "ba".
Remove the underlined characters s = "ba". The resulting string is s = "".
Return the value of the string s right before applying the last operation. In the example above, answer is "ba".

Example 1:

Input: s = "aabcbbca"
Output: "ba"
Explanation: Explained in the statement.
*/
class Solution {
    public String lastNonEmptyString(String s) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        int maxFreq = 0;
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }
        int[] seen = new int[26];
        StringBuilder ans = new StringBuilder();
        for (char ch : s.toCharArray()) {
            int idx = ch - 'a';
            seen[idx]++;

            if (freq[idx] == maxFreq && seen[idx] == maxFreq) {
                ans.append(ch);
            }
        }
        return ans.toString();
    }
}
