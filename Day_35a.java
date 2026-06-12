/*
You are given an integer array nums of length n and a 2D array queries where queries[i] = [li, ri, vali].
Each queries[i] represents the following action on nums:
Decrement the value at each index in the range [li, ri] in nums by at most vali.
The amount by which each value is decremented can be chosen independently for each index.
A Zero Array is an array with all its elements equal to 0.
Return the minimum possible non-negative value of k, such that after processing the first k queries in sequence, nums becomes a Zero Array. If no such k exists, return -1.

Example 1:
Input: nums = [2,0,2], queries = [[0,2,1],[0,2,1],[1,1,3]]
Output: 2
Explanation:
For i = 0 (l = 0, r = 2, val = 1):
Decrement values at indices [0, 1, 2] by [1, 0, 1] respectively.
The array will become [1, 0, 1].
For i = 1 (l = 0, r = 2, val = 1):
Decrement values at indices [0, 1, 2] by [1, 0, 1] respectively.
The array will become [0, 0, 0], which is a Zero Array. Therefore, the minimum value of k is 2.
*/
class Solution {
    public int minZeroArray(int[] nums, int[][] queries) {
        int m = queries.length;
        if (canMakeZero(nums, queries, 0)) {
            return 0;
        }
        int left = 1;
        int right = m;
        int ans = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canMakeZero(nums, queries, mid)) {
                ans = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return ans;
    }
    private boolean canMakeZero(int[] nums, int[][] queries, int k) {
        int n = nums.length;
        long[] diff = new long[n + 1];
        for (int i = 0; i < k; i++) {
            int l = queries[i][0];
            int r = queries[i][1];
            int val = queries[i][2];
            diff[l] += val;
            if (r + 1 < diff.length) {
                diff[r + 1] -= val;
            }
        }
        long available = 0;
        for (int i = 0; i < n; i++) {
            available += diff[i];
            if (available < nums[i]) {
                return false;
            }
        }
        return true;
    }
}
