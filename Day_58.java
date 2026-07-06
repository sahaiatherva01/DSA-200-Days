/*
You are given a string s. It may contain any number of '*' characters. Your task is to remove all '*' characters.
While there is a '*', do the following operation:
Delete the leftmost '*' and the smallest non-'*' character to its left. If there are several smallest characters, you can delete any of them.
Return the lexicographically smallest resulting string after removing all '*' characters.

Example 1:
Input: s = "aaba*"
Output: "aab"
Explanation:
We should delete one of the 'a' characters with '*'. If we choose s[3], s becomes the lexicographically smallest.

Example 2:
Input: s = "abc"
Output: "abc"
Explanation:
There is no '*' in the string.
*/
class Solution {
    public String clearStars(String s) {
        int n = s.length();
        Deque<Integer>[] pos = new ArrayDeque[26];
        for (int i = 0; i < 26; i++) {
            pos[i] = new ArrayDeque<>();
        }
        boolean[] removed = new boolean[n];
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            if (ch == '*') {
                removed[i] = true;
                for (int c = 0; c < 26; c++) {
                    if (!pos[c].isEmpty()) {
                        removed[pos[c].pop()] = true;
                        break;
                    }
                }
            } else {
                pos[ch - 'a'].push(i);
            }
        }
        StringBuilder ans = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (!removed[i]) {
                ans.append(s.charAt(i));
            }
        }
        return ans.toString();
    }
}
