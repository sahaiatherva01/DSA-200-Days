/*
You are given a string s. You can convert s to a palindrome by adding characters in front of it.
Return the shortest palindrome you can find by performing this transformation.

Example 1:
Input: s = "aacecaaa"
Output: "aaacecaaa"

Example 2:
Input: s = "abcd"
Output: "dcbabcd"
*/
class Solution {
    public String shortestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        // Reverse the string
        String rev = new StringBuilder(s).reverse().toString();
        // Combine original + separator + reversed
        String combined = s + "#" + rev;
        // Build LPS array
        int[] lps = new int[combined.length()];
        for (int i = 1; i < combined.length(); i++) {
            int j = lps[i - 1];
            while (j > 0 &&
                   combined.charAt(i) != combined.charAt(j)) {
                j = lps[j - 1];
            }
            if (combined.charAt(i) == combined.charAt(j)) {
                j++;
            }
            lps[i] = j;
        }
        // Length of longest palindromic prefix
        int palindromeLength = lps[combined.length() - 1];
        // Remaining suffix
        String remaining = s.substring(palindromeLength);
        // Reverse remaining suffix
        String add = new StringBuilder(remaining).reverse().toString();
        return add + s;
    }
}
