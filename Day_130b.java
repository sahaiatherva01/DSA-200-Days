/*
You are given an array of integers arr and an integer target.
You have to find two non-overlapping sub-arrays of arr each with a sum equal target. There can be multiple answers so you have to find an answer where the sum of the lengths of the two sub-arrays is minimum.
Return the minimum sum of the lengths of the two required sub-arrays, or return -1 if you cannot find such two sub-arrays.

Example 1:
Input: arr = [3,2,2,4,3], target = 3
Output: 2
Explanation: Only two sub-arrays have sum = 3 ([3] and [3]). The sum of their lengths is 2.

Example 2:
Input: arr = [7,3,4,7], target = 7
Output: 2
Explanation: Although we have three non-overlapping sub-arrays of sum = 7 ([7], [3,4] and [7]), but we will choose the first and third sub-arrays as the sum of their lengths is 2.

Example 3:
Input: arr = [4,3,2,6,2,3,4], target = 6
Output: -1
Explanation: We have only one sub-array of sum = 6.
*/
import java.util.*;
class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int INF = n + 1;
        // best[i] = minimum length of a valid subarray
        // completely inside arr[0...i]
        int[] best = new int[n];
        Arrays.fill(best, INF);
        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, -1);
        long prefix = 0;
        int answer = INF;
        int minLength = INF;
        for (int i = 0; i < n; i++) {
            prefix += arr[i];
            // Carry forward the best previous subarray
            if (i > 0) {
                best[i] = best[i - 1];
            }
            Long required = prefix - target;
            if (map.containsKey(required)) {
                int start = map.get(required);
                int length = i - start;
                // Previous subarray must end before 'start'
                if (start >= 0 && best[start] != INF) {
                    answer = Math.min(answer, length + best[start]);
                } else if (start == -1) {
                    // No previous subarray exists
                }
                minLength = Math.min(minLength, length);
                best[i] = Math.min(best[i], length);
            }
            map.put(prefix, i);
        }
        return answer == INF ? -1 : answer;
    }
}
