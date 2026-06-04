/*
You are given a 0-indexed string array words having length n and containing 0-indexed strings.
You are allowed to perform the following operation any number of times (including zero):
Choose integers i, j, x, and y such that 0 <= i, j < n, 0 <= x < words[i].length, 0 <= y < words[j].length, and swap the characters words[i][x] and words[j][y].
Return an integer denoting the maximum number of palindromes words can contain, after performing some operations.
Note: i and j may be equal during an operation.

Example 1:
Input: words = ["abbb","ba","aa"]
Output: 3
Explanation: In this example, one way to get the maximum number of palindromes is:
Choose i = 0, j = 1, x = 0, y = 0, so we swap words[0][0] and words[1][0]. words becomes ["bbbb","aa","aa"].
All strings in words are now palindromes.
Hence, the maximum number of palindromes achievable is 3.
*/
import java.util.*;
class Solution {
    public int maxPalindromesAfterOperations(String[] words) {
        int freq[] = new int[26];
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                freq[ch - 'a']++;
            }
        }
        int pairs = 0;
        for (int f : freq) {
            pairs += f / 2;
        }
        int lengths[] = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            lengths[i] = words[i].length();
        }
        Arrays.sort(lengths);
        int ans = 0;
        for (int len : lengths) {
            int need = len / 2;
            if (pairs < need) {
                break;
            }
            pairs -= need;
            ans++;
        }
        return ans;
    }
}
