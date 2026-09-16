/*
Given a matrix and a target, return the number of non-empty submatrices that sum to target.
A submatrix x1, y1, x2, y2 is the set of all cells matrix[x][y] with x1 <= x <= x2 and y1 <= y <= y2.
Two submatrices (x1, y1, x2, y2) and (x1', y1', x2', y2') are different if they have some coordinate that is different: for example, if x1 != x1'.

Example 1:
Input: matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0
Output: 4
Explanation: The four 1x1 submatrices that only contain 0.

Example 2:
Input: matrix = [[1,-1],[-1,1]], target = 0
Output: 5
Explanation: The two 1x2 submatrices, plus the two 2x1 submatrices, plus the 2x2 submatrix.

Example 3:
Input: matrix = [[904]], target = 0
Output: 0
*/
import java.util.*;
class Solution {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        if (m > n) {
            int[][] transposed = new int[n][m];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    transposed[j][i] = matrix[i][j];
                }
            }
            matrix = transposed;
            m = matrix.length;
            n = matrix[0].length;
        }
        int ans = 0;
        for (int top = 0; top < m; top++) {
            int[] colSum = new int[n];
            for (int bottom = top; bottom < m; bottom++) {
                for (int col = 0; col < n; col++) {
                    colSum[col] += matrix[bottom][col];
                }
                HashMap<Integer, Integer> map = new HashMap<>();
                map.put(0, 1);
                int prefix = 0;
                for (int sum : colSum) {
                    prefix += sum;
                    ans += map.getOrDefault(prefix - target, 0);
                    map.put(prefix,
                            map.getOrDefault(prefix, 0) + 1);
                }
            }
        }
        return ans;
    }
}
