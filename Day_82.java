/*
You are given an integer array nums of 2 * n integers. You need to partition nums into two arrays of length n to minimize the absolute difference of the sums of the arrays. To partition nums, put each element of nums into one of the two arrays.
Return the minimum possible absolute difference.

Example 1:
example-1
Input: nums = [3,9,7,3]
Output: 2
Explanation: One optimal partition is: [3,9] and [7,3].
The absolute difference between the sums of the arrays is abs((3 + 9) - (7 + 3)) = 2.

Example 2:
Input: nums = [-36,36]
Output: 72
Explanation: One optimal partition is: [-36] and [36].
The absolute difference between the sums of the arrays is abs((-36) - (36)) = 72.

Example 3:
example-3
Input: nums = [2,-1,0,4,-2,-9]
Output: 0
Explanation: One optimal partition is: [2,4,-9] and [-1,0,-2].
The absolute difference between the sums of the arrays is abs((2 + 4 + -9) - (-1 + 0 + -2)) = 0.
*/
import java.util.*;
class Solution {
    public int minimumDifference(int[] nums) {
        int n = nums.length / 2;
        int[] left = Arrays.copyOfRange(nums, 0, n);
        int[] right = Arrays.copyOfRange(nums, n, 2 * n);
        List<Integer>[] leftSum = new ArrayList[n + 1];
        List<Integer>[] rightSum = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            leftSum[i] = new ArrayList<>();
            rightSum[i] = new ArrayList<>();
        }
        int total = 0;
        for (int x : nums) total += x;
        int limit = 1 << n;
        for (int mask = 0; mask < limit; mask++) {
            int cnt = 0;
            int s1 = 0;
            int s2 = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    cnt++;
                    s1 += left[i];
                    s2 += right[i];
                }
            }
            leftSum[cnt].add(s1);
            rightSum[cnt].add(s2);
        }
        for (int i = 0; i <= n; i++) {
            Collections.sort(rightSum[i]);
        }
        int ans = Integer.MAX_VALUE;
        for (int k = 0; k <= n; k++) {
            List<Integer> r = rightSum[n - k];
            for (int s1 : leftSum[k]) {
                int target = total / 2 - s1;
                int idx = Collections.binarySearch(r, target);
                if (idx < 0) idx = -idx - 1;
                if (idx < r.size()) {
                    int chosen = s1 + r.get(idx);
                    ans = Math.min(ans,
                            Math.abs(total - 2 * chosen));
                }
                if (idx > 0) {
                    int chosen = s1 + r.get(idx - 1);
                    ans = Math.min(ans,
                            Math.abs(total - 2 * chosen));
                }
            }
        }
        return ans;
    }
}
