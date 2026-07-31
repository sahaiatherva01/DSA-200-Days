/*
You are given an integer n representing the dimensions of an n x n grid, with the origin at the bottom-left corner of the grid. You are also given a 2D array of coordinates rectangles, where rectangles[i] is in the form [startx, starty, endx, endy], representing a rectangle on the grid. Each rectangle is defined as follows:
(startx, starty): The bottom-left corner of the rectangle.
(endx, endy): The top-right corner of the rectangle.
Note that the rectangles do not overlap. Your task is to determine if it is possible to make either two horizontal or two vertical cuts on the grid such that:
Each of the three resulting sections formed by the cuts contains at least one rectangle.
Every rectangle belongs to exactly one section.
Return true if such cuts can be made; otherwise, return false.

Example 1:
Input: n = 5, rectangles = [[1,0,5,2],[0,2,2,4],[3,2,5,3],[0,4,4,5]]
Output: true
Explanation:
The grid is shown in the diagram. We can make horizontal cuts at y = 2 and y = 4. Hence, output is true.

Example 2:
Input: n = 4, rectangles = [[0,0,1,1],[2,0,3,4],[0,2,2,3],[3,0,4,3]]
Output: true
Explanation:
We can make vertical cuts at x = 2 and x = 3. Hence, output is true.

Example 3:
Input: n = 4, rectangles = [[0,2,2,4],[1,0,3,2],[2,2,3,4],[3,0,4,2],[3,2,4,4]]
Output: false
Explanation:
We cannot make two horizontal or two vertical cuts that satisfy the conditions. Hence, output is false
*/
import java.util.*;
class Solution {
    public boolean checkValidCuts(int n, int[][] rectangles) {
        return canCut(rectangles, 0, 2) || canCut(rectangles, 1, 3);
    }
    private boolean canCut(int[][] rectangles, int startIdx, int endIdx) {
        int[][] intervals = new int[rectangles.length][2];
        for (int i = 0; i < rectangles.length; i++) {
            intervals[i][0] = rectangles[i][startIdx];
            intervals[i][1] = rectangles[i][endIdx];
        }
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        int groups = 0;
        int end = -1;
        for (int[] interval : intervals) {
            if (groups == 0 || interval[0] >= end) {
                groups++;
                end = interval[1];
            } else {
                end = Math.max(end, interval[1]);
            }
        }
        return groups >= 3;
    }
}
