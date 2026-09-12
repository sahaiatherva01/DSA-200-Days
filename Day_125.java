/*
You are given a 2D integer array intervals, where intervals[i] = [li, ri, weighti]. Interval i starts at position li and ends at ri, and has a weight of weighti. You can choose up to 4 non-overlapping intervals. The score of the chosen intervals is defined as the total sum of their weights.
Return the lexicographically smallest array of at most 4 indices from intervals with maximum score, representing your choice of non-overlapping intervals.
Two intervals are said to be non-overlapping if they do not share any points. In particular, intervals sharing a left or right boundary are considered overlapping.
 
Example 1:
Input: intervals = [[1,3,2],[4,5,2],[1,5,5],[6,9,3],[6,7,1],[8,9,1]]
Output: [2,3]
Explanation:
You can choose the intervals with indices 2, and 3 with respective weights of 5, and 3.

Example 2
Input: intervals = [[5,8,1],[6,7,7],[4,7,3],[9,10,6],[7,8,2],[11,14,3],[3,5,5]]
Output: [1,3,5,6]
Explanation:
You can choose the intervals with indices 1, 3, 5, and 6 with respective weights of 7, 6, 3, and 5.
*/
import java.util.*;
class Solution {
    static class Node {
        long score;
        int[] ids;
        Node(long score, int[] ids) {
            this.score = score;
            this.ids = ids;
        }
    }
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();
        int[][] a = new int[n][4];
        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }
        Arrays.sort(a, (x, y) -> {
            if (x[0] != y[0])
                return Integer.compare(x[0], y[0]);
            return Integer.compare(x[1], y[1]);
        });
        int[] next = new int[n];
        for (int i = 0; i < n; i++) {
            int lo = i + 1;
            int hi = n;
            while (lo < hi) {
                int mid = (lo + hi) >>> 1;

                if (a[mid][0] > a[i][1])
                    hi = mid;
                else
                    lo = mid + 1;
            }
            next[i] = lo;
        }
        Node[][] dp = new Node[n + 1][5];
        for (int k = 0; k <= 4; k++) {
            dp[n][k] = new Node(0, new int[0]);
        }
        for (int i = n - 1; i >= 0; i--) {
            dp[i][0] = new Node(0, new int[0]);
            for (int k = 1; k <= 4; k++) {
                Node skip = dp[i + 1][k];
                Node nxt = dp[next[i]][k - 1];
                int[] ids = new int[nxt.ids.length + 1];
                for (int j = 0; j < nxt.ids.length; j++) {
                    ids[j] = nxt.ids[j];
                }
                ids[ids.length - 1] = a[i][3];
                Arrays.sort(ids);
                Node take = new Node(
                    a[i][2] + nxt.score,
                    ids
                );
                dp[i][k] = better(skip, take);
            }
        }
        return dp[0][4].ids;
    }
    static Node better(Node x, Node y) {
        if (x.score != y.score) {
            return x.score > y.score ? x : y;
        }
        return lexicographicallySmaller(x.ids, y.ids) ? x : y;
    }
    static boolean lexicographicallySmaller(int[] a, int[] b) {
        int n = Math.min(a.length, b.length);
        for (int i = 0; i < n; i++) {
            if (a[i] != b[i])
                return a[i] < b[i];
        }
        return a.length < b.length;
    }
}
