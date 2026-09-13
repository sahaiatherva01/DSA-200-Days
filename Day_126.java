/*
You are given two images, img1 and img2, represented as binary, square matrices of size n x n. A binary matrix has only 0s and 1s as values.
We translate one image however we choose by sliding all the 1 bits left, right, up, and/or down any number of units. We then place it on top of the other image. We can then calculate the overlap by counting the number of positions that have a 1 in both images.
Note also that a translation does not include any kind of rotation. Any 1 bits that are translated outside of the matrix borders are erased.
Return the largest possible overlap.

Example 1:
Input: img1 = [[1,1,0],[0,1,0],[0,1,0]], img2 = [[0,0,0],[0,1,1],[0,0,1]]
Output: 3
Explanation: We translate img1 to right by 1 unit and down by 1 unit.
The number of positions that have a 1 in both images is 3 (shown in red).

Example 2:
Input: img1 = [[1]], img2 = [[1]]
Output: 1

Example 3:
Input: img1 = [[0]], img2 = [[0]]
Output: 0
*/
import java.util.*;
class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        List<int[]> ones1 = new ArrayList<>();
        List<int[]> ones2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (img1[i][j] == 1) {
                    ones1.add(new int[]{i, j});
                }
                if (img2[i][j] == 1) {
                    ones2.add(new int[]{i, j});
                }
            }
        }
        Map<String, Integer> map = new HashMap<>();
        int answer = 0;
        for (int[] a : ones1) {
            for (int[] b : ones2) {
                int dr = b[0] - a[0];
                int dc = b[1] - a[1];
                String key = dr + "," + dc;
                int count = map.getOrDefault(key, 0) + 1;
                map.put(key, count);
                answer = Math.max(answer, count);
            }
        }
        return answer;
    }
}
