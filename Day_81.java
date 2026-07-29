/*
Given an integer array nums and an integer k, find three non-overlapping subarrays of length k with maximum sum and return them.
Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example 1:
Input: nums = [1,2,1,2,6,7,5,1], k = 2
Output: [0,3,5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.

Example 2:
Input: nums = [1,2,1,2,1,2,1,2,1], k = 2
Output: [0,2,4]
*/
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int m = n - k + 1;
        int[] sum = new int[m];
        int window = 0;
        for (int i = 0; i < n; i++) {
            window += nums[i];
            if (i >= k) {
                window -= nums[i - k];
            }
            if (i >= k - 1) {
                sum[i - k + 1] = window;
            }
        }
        int[] left = new int[m];
        int best = 0;
        for (int i = 0; i < m; i++) {
            if (sum[i] > sum[best]) {
                best = i;
            }
            left[i] = best;
        }
        int[] right = new int[m];
        best = m - 1;
        for (int i = m - 1; i >= 0; i--) {
            if (sum[i] >= sum[best]) {
                best = i;
            }
            right[i] = best;
        }
        int maxSum = 0;
        int[] ans = new int[3];
        for (int mid = k; mid < m - k; mid++) {
            int l = left[mid - k];
            int r = right[mid + k];
            int total = sum[l] + sum[mid] + sum[r];
            if (total > maxSum) {
                maxSum = total;
                ans[0] = l;
                ans[1] = mid;
                ans[2] = r;
            }
        }
        return ans;
    }
}
