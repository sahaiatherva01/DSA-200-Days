/*
You are given two string arrays words1 and words2.
A string b is a subset of string a if every letter in b occurs in a including multiplicity.
For example, "wrr" is a subset of "warrior" but is not a subset of "world".
A string a from words1 is universal if for every string b in words2, b is a subset of a.
Return an array of all the universal strings in words1. You may return the answer in any order.

Example 1:
Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["e","o"]
Output: ["facebook","google","leetcode"]

Example 2:
Input: words1 = ["amazon","apple","facebook","google","leetcode"], words2 = ["lc","eo"]
Output: ["leetcode"]

Example 3:
Input: words1 = ["acaac","cccbb","aacbb","caacc","bcbbb"], words2 = ["c","cc","b"]
Output: ["cccbb"]
*/
import java.util.*;
class Solution {
    public List<String> wordSubsets(String[] words1, String[] words2) {
        int[] required = new int[26];
        for (String word : words2) {
            int[] count = new int[26];
            for (char c : word.toCharArray()) {
                count[c - 'a']++;
            }
            for (int i = 0; i < 26; i++) {
                required[i] = Math.max(required[i], count[i]);
            }
        }
        List<String> answer = new ArrayList<>();
        for (String word : words1) {
            int[] count = new int[26];
            for (char c : word.toCharArray()) {
                count[c - 'a']++;
            }
            boolean universal = true;
            for (int i = 0; i < 26; i++) {
                if (count[i] < required[i]) {
                    universal = false;
                    break;
                }
            }
            if (universal) {
                answer.add(word);
            }
        }
        return answer;
    }
}
