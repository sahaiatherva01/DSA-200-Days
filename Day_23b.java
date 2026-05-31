/*
Given a string s, return the number of unique palindromes of length three that are a subsequence of s.
Note that even if there are multiple ways to obtain the same subsequence, it is still only counted once.
A palindrome is a string that reads the same forwards and backwards.
A subsequence of a string is a new string generated from the original string with some characters (can be none) deleted without changing the relative order of the remaining characters.
For example, "ace" is a subsequence of "abcde".

Example 1:
Input: s = "aabca"
Output: 3
Explanation: The 3 palindromic subsequences of length 3 are:
- "aba" (subsequence of "aabca")
- "aaa" (subsequence of "aabca")
- "aca" (subsequence of "aabca")
Example 2:
Input: s = "adc"
Output: 0
Explanation: There are no palindromic subsequences of length 3 in "adc".
*/
class Solution {
    public int countPalindromicSubsequence(String s) {
        int a = 0;
        for (char ch = 'a'; ch <= 'z'; ch++) {
            int f = s.indexOf(ch);
            int l= s.lastIndexOf(ch);
            if (f == -1 || f == l) {
                continue;
            }
            boolean[] seen = new boolean[26];
            for (int i = f + 1; i < l; i++) {
                seen[s.charAt(i) - 'a'] = true;
            }
            for (boolean b : seen) {
                if (b) a++;
            }
        }
        return a;
    }
}
