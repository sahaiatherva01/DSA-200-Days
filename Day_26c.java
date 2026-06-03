/*
You are given two strings s and t, both of which are anagrams of each other, and an integer k.
Your task is to determine whether it is possible to split the string s into k equal-sized substrings, rearrange the substrings, and concatenate them in any order to create a new string that matches the given string t.
Return true if this is possible, otherwise, return false.
An anagram is a word or phrase formed by rearranging the letters of a different word or phrase, using all the original letters exactly once.
A substring is a contiguous non-empty sequence of characters within a string.

Example 1:
Input: s = "abcd", t = "cdab", k = 2
Output: true
Explanation:
Split s into 2 substrings of length 2: ["ab", "cd"].
Rearranging these substrings as ["cd", "ab"], and then concatenating them results in "cdab", which matches t.

Example 2:
Input: s = "aabbcc", t = "bbaacc", k = 3
Output: true
Explanation:
Split s into 3 substrings of length 2: ["aa", "bb", "cc"].
Rearranging these substrings as ["bb", "aa", "cc"], and then concatenating them results in "bbaacc", which matches t.
*/
import java.util.*;
class Solution {
    public boolean isPossibleToRearrange(String s, String t, int k) {
        int n = s.length();
        int len = n / k;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i += len) {
            String part = s.substring(i, i + len);
            map.put(part, map.getOrDefault(part, 0) + 1);
        }
        for (int i = 0; i < n; i += len) {
            String part = t.substring(i, i + len);
            int f = map.getOrDefault(part, 0);
            if (f == 0) {
                return false;
            }
            map.put(part, f - 1);
        }
        return true;
    }
}
