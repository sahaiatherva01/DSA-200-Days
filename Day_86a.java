/*
You are given a string s, which is known to be a concatenation of anagrams of some string t.
Return the minimum possible length of the string t.
An anagram is formed by rearranging the letters of a string. For example, "aab", "aba", and, "baa" are anagrams of "aab".

Example 1:
Input: s = "abba"
Output: 2
Explanation:
One possible string t could be "ba".

Example 2:
Input: s = "cdef"
Output: 4
Explanation:
One possible string t could be "cdef", notice that t can be equal to s.

Example 2:
Input: s = "abcbcacabbaccba"
Output: 3
*/
class Solution {
    public int minAnagramLength(String s) {
        int n = s.length();
        for (int len = 1; len <= n; len++) {
            if (n % len != 0) continue;
            if (isValid(s, len)) {
                return len;
            }
        }
        return n;
    }
    private boolean isValid(String s, int len) {
        int[] base = new int[26];
        for (int i = 0; i < len; i++) {
            base[s.charAt(i) - 'a']++;
        }
        int n = s.length();
        for (int start = len; start < n; start += len) {
            int[] freq = new int[26];
            for (int i = start; i < start + len; i++) {
                freq[s.charAt(i) - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                if (freq[i] != base[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
