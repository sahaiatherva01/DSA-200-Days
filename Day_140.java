/*
A parentheses string is a non-empty string consisting only of '(' and ')'. It is valid if any of the following conditions is true:
It is ().
It can be written as AB (A concatenated with B), where A and B are valid parentheses strings.
It can be written as (A), where A is a valid parentheses string.
You are given an m x n matrix of parentheses grid. A valid parentheses string path in the grid is a path satisfying all of the following conditions:
The path starts from the upper left cell (0, 0).
The path ends at the bottom-right cell (m - 1, n - 1).
The path only ever moves down or right.
The resulting parentheses string formed by the path is valid.
Return true if there exists a valid parentheses string path in the grid. Otherwise, return false.

Example 1:
Input: grid = [["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]
Output: true
Explanation: The above diagram shows two possible paths that form valid parentheses strings.
The first path shown results in the valid parentheses string "()(())".
The second path shown results in the valid parentheses string "((()))".
Note that there may be other valid parentheses string paths.

Example 2:
Input: grid = [[")",")"],["(","("]]
Output: false
Explanation: The two possible paths form the parentheses strings "))(" and ")((". Since neither of them are valid parentheses strings, we return false.
*/
import java.util.*;
class Solution {
    public boolean hasValidPath(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        // Valid parentheses string must have even length
        if ((m + n - 1) % 2 != 0) {
            return false;
        }
        // dp[i][j] = possible balances at (i, j)
        Set<Integer>[][] dp = new HashSet[m][n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = new HashSet<>();
            }
        }
        // Starting cell
        if (grid[0][0] == '(') {
            dp[0][0].add(1);
        } else {
            return false;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    continue;
                }
                int change = grid[i][j] == '(' ? 1 : -1;
                // From top
                if (i > 0) {
                    for (int balance : dp[i - 1][j]) {
                        int newBalance = balance + change;
                        if (newBalance >= 0) {
                            dp[i][j].add(newBalance);
                        }
                    }
                }
                // From left
                if (j > 0) {
                    for (int balance : dp[i][j - 1]) {
                        int newBalance = balance + change;
                        if (newBalance >= 0) {
                            dp[i][j].add(newBalance);
                        }
                    }
                }
            }
        }
        return dp[m - 1][n - 1].contains(0);
    }
}
