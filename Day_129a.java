/*
The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.

Example 1:
Input: n = 4
Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
Explanation: There exist two distinct solutions to the 4-queens puzzle as shown above

Example 2:
Input: n = 1
Output: [["Q"]]
*/
import java.util.*;
class Solution {
    List<List<String>> ans = new ArrayList<>();
    int[] queens;
    boolean[] col;
    boolean[] diag1;
    boolean[] diag2;
    public List<List<String>> solveNQueens(int n) {
        queens = new int[n];
        col = new boolean[n];
        diag1 = new boolean[2 * n - 1];
        diag2 = new boolean[2 * n - 1];
        backtrack(0, n);
        return ans;
    }
    private void backtrack(int row, int n) {
        if (row == n) {
            ans.add(buildBoard(n));
            return;
        }
        for (int c = 0; c < n; c++) {

            int d1 = row - c + n - 1;
            int d2 = row + c;

            if (col[c] || diag1[d1] || diag2[d2]) {
                continue;
            }
            queens[row] = c;
            col[c] = true;
            diag1[d1] = true;
            diag2[d2] = true;
            backtrack(row + 1, n);
            col[c] = false;
            diag1[d1] = false;
            diag2[d2] = false;
        }
    }
    private List<String> buildBoard(int n) {
        List<String> board = new ArrayList<>();
        for (int r = 0; r < n; r++) {
            char[] row = new char[n];
            Arrays.fill(row, '.');
            row[queens[r]] = 'Q';
            board.add(new String(row));
        }
        return board;
    }
}
