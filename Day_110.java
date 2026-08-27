/*
You are given two strings s and target, both having length n, consisting of lowercase English letters.
Return the lexicographically smallest permutation of s that is strictly greater than target. If no permutation of s is lexicographically strictly greater than target, return an empty string.
A string a is lexicographically strictly greater than a string b (of the same length) if in the first position where a and b differ, string a has a letter that appears later in the alphabet than the corresponding letter in b.

Example 1:
Input: s = "abc", target = "bba"
Output: "bca"
Explanation:
The permutations of s (in lexicographical order) are "abc", "acb", "bac", "bca", "cab", and "cba".
The lexicographically smallest permutation that is strictly greater than target is "bca".

Example 2:
Input: s = "leet", target = "code"
Output: "eelt"
Explanation:
The permutations of s (in lexicographical order) are "eelt", "eetl", "elet", "elte", "etel", "etle", "leet", "lete", "ltee", "teel", "tele", and "tlee".
The lexicographically smallest permutation that is strictly greater than target is "eelt".

Example 3:
Input: s = "baba", target = "bbaa"
Output: ""
Explanation:
The permutations of s (in lexicographical order) are "aabb", "abab", "abba", "baab", "baba", and "bbaa".
None of them is lexicographically strictly greater than target. Therefore, the answer is "".
*/
class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        StringBuilder prefix = new StringBuilder();
        for (int i = 0; i < n; i++) {
            int cur = target.charAt(i) - 'a';
            if (freq[cur] == 0) {
                for (int c = cur + 1; c < 26; c++) {
                    if (freq[c] > 0) {
                        StringBuilder ans = new StringBuilder(prefix);
                        ans.append((char) ('a' + c));
                        freq[c]--;
                        for (int x = 0; x < 26; x++) {
                            while (freq[x] > 0) {
                                ans.append((char) ('a' + x));
                                freq[x]--;
                            }
                        }
                        return ans.toString();
                    }
                }
                break;
            }
            prefix.append((char) ('a' + cur));
            freq[cur]--;
        }
        for (int i = prefix.length() - 1; i >= 0; i--) {
            int cur = target.charAt(i) - 'a';
            freq[cur]++;
            for (int c = cur + 1; c < 26; c++) {
                if (freq[c] > 0) {
                    StringBuilder ans = new StringBuilder();
                    for (int j = 0; j < i; j++) {
                        ans.append(target.charAt(j));
                    }
                    ans.append((char) ('a' + c));
                    freq[c]--;
                    for (int x = 0; x < 26; x++) {
                        while (freq[x] > 0) {
                            ans.append((char) ('a' + x));
                            freq[x]--;
                        }
                    }
                    return ans.toString();
                }
            }
        }
        return "";
    }
}
