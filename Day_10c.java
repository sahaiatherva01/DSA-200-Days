/*
You are given an integer limit and a 2D array queries of size n x 2.
There are limit + 1 balls with distinct labels in the range [0, limit]. Initially, all balls are uncolored. For every query in queries that is of the form [x, y], you mark ball x with the color y. After each query, you need to find the number of colors among the balls.
Return an array result of length n, where result[i] denotes the number of colors after ith query.
Note that when answering a query, lack of a color will not be considered as a color.
*/
import java.util.*;
class Solution {
    public int[] queryResults(int limit, int[][] queries) {
        int n = queries.length;
        int[] result = new int[n];
        HashMap<Integer, Integer> ballColor = new HashMap<>();
        HashMap<Integer, Integer> colorFreq = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int ball = queries[i][0];
            int color = queries[i][1];
            if (ballColor.containsKey(ball)) {
                int oldColor = ballColor.get(ball);
                colorFreq.put(oldColor,
                    colorFreq.get(oldColor) - 1);
                if (colorFreq.get(oldColor) == 0) {
                    colorFreq.remove(oldColor);
                }
            }
            ballColor.put(ball, color);
            colorFreq.put(color,
                colorFreq.getOrDefault(color, 0) + 1);
            result[i] = colorFreq.size();
        }
        return result;
    }
}
