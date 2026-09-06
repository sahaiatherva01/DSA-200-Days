/*
Given two strings s and t, return the number of distinct subsequences of s which equals t.
The test cases are generated so that the answer fits on a 32-bit signed integer.

Example 1:
Input: s = "rabbbit", t = "rabbit"
Output: 3
Explanation:
As shown below, there are 3 ways you can generate "rabbit" from s.
rabbbit
rabbbit
rabbbit

Example 2:
Input: s = "babgbag", t = "bag"
Output: 5
Explanation:
As shown below, there are 5 ways you can generate "bag" from s.
babgbag
babgbag
babgbag
babgbag
babgbag
*/
class Solution {
    public int numDistinct(String s, String t) {
        int m = t.length();
        int[] dp = new int[m + 1];
        dp[0] = 1;
        for (char c : s.toCharArray()) {
            for (int j = m - 1; j >= 0; j--) {
                if (c == t.charAt(j)) {
                    dp[j + 1] += dp[j];
                }
            }
        }
        return dp[m];
    }
}
