/*
Given two strings s and t of lengths m and n respectively, return the minimum window substring of s such that every character in t (including duplicates) is included in the window. If there is no such substring, return the empty string "".
The testcases will be generated such that the answer is unique.

Example 1:
Input: s = "ADOBECODEBANC", t = "ABC"
Output: "BANC"
Explanation: The minimum window substring "BANC" includes 'A', 'B', and 'C' from string t.

Example 2:
Input: s = "a", t = "a"
Output: "a"
Explanation: The entire string s is the minimum window.

Example 3:
Input: s = "a", t = "aa"
Output: ""
Explanation: Both 'a's from t must be included in the window.
Since the largest window of s only has one 'a', return empty string.
*/
class Solution {
    public String minWindow(String s, String t) {
        if (s.length() < t.length())
            return "";
        int[] need = new int[128];
        int[] window = new int[128];
        for (char ch : t.toCharArray()) {
            need[ch]++;
        }
        int required = 0;
        for (int i = 0; i < 128; i++) {
            if (need[i] > 0)
                required++;
        }
        int formed = 0;
        int left = 0;
        int minLength = Integer.MAX_VALUE;
        int start = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            window[ch]++;
            if (need[ch] > 0 && window[ch] == need[ch]) {
                formed++;
            }
            while (formed == required) {
                if (right - left + 1 < minLength) {
                    minLength = right - left + 1;
                    start = left;
                }
                char leftChar = s.charAt(left);
                window[leftChar]--;
                if (need[leftChar] > 0 && window[leftChar] < need[leftChar]) {
                    formed--;
                }
                left++;
            }
        }
        if (minLength == Integer.MAX_VALUE)
            return "";
        return s.substring(start, start + minLength);
    }
}
