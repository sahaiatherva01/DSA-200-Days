/*
You are given a 0-indexed integer array nums.
The distinct count of a subarray of nums is defined as:
Let nums[i..j] be a subarray of nums consisting of all the indices from i to j such that 0 <= i <= j < nums.length. Then the number of distinct values in nums[i..j] is called the distinct count of nums[i..j].
Return the sum of the squares of distinct counts of all subarrays of nums.
Since the answer may be very large, return it modulo 109 + 7.
A subarray is a contiguous non-empty sequence of elements within an array.

Example 1:
Input: nums = [1,2,1]
Output: 15
Explanation: Six possible subarrays are:
[1]: 1 distinct value
[2]: 1 distinct value
[1]: 1 distinct value
[1,2]: 2 distinct values
[2,1]: 2 distinct values
[1,2,1]: 2 distinct values
The sum of the squares of the distinct counts in all subarrays is equal to 12 + 12 + 12 + 22 + 22 + 22 = 15.

Example 2:
Input: nums = [2,2]
Output: 3
Explanation: Three possible subarrays are:
[2]: 1 distinct value
[2]: 1 distinct value
[2,2]: 1 distinct value
The sum of the squares of the distinct counts in all subarrays is equal to 12 + 12 + 12 = 3.
*/
import java.util.*;

class Solution {
    private static final long MOD = 1_000_000_007L;

    private long[] sum, sumSq, lazy;
    private int size;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        size = n;

        sum = new long[4 * n];
        sumSq = new long[4 * n];
        lazy = new long[4 * n];

        Map<Integer, Integer> last = new HashMap<>();
        long answer = 0;

        for (int r = 0; r < n; r++) {
            int prev = last.getOrDefault(nums[r], -1);

            // Distinct counts increase for starts in [prev + 1, r].
            update(1, 0, n - 1, prev + 1, r);

            // Sum of squared distinct counts for all subarrays ending at r.
            answer = (answer + sumSq[1]) % MOD;

            last.put(nums[r], r);
        }

        return (int) answer;
    }

    private void update(int node, int left, int right, int ql, int qr) {
        if (ql > right || qr < left) return;

        if (ql <= left && right <= qr) {
            apply(node, left, right);
            return;
        }

        push(node, left, right);

        int mid = left + (right - left) / 2;
        update(node * 2, left, mid, ql, qr);
        update(node * 2 + 1, mid + 1, right, ql, qr);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sumSq[node] = (sumSq[node * 2] + sumSq[node * 2 + 1]) % MOD;
    }

    private void apply(int node, int left, int right) {
        long len = right - left + 1L;
        long oldSum = sum[node];

        sumSq[node] = (sumSq[node] + 2L * oldSum + len) % MOD;
        sum[node] = (oldSum + len) % MOD;
        lazy[node]++;
    }

    private void push(int node, int left, int right) {
        if (lazy[node] == 0 || left == right) return;

        int mid = left + (right - left) / 2;
        applyTimes(node * 2, left, mid, lazy[node]);
        applyTimes(node * 2 + 1, mid + 1, right, lazy[node]);
        lazy[node] = 0;
    }

    private void applyTimes(int node, int left, int right, long times) {
        long len = right - left + 1L;
        long oldSum = sum[node];

        sumSq[node] = (sumSq[node] + 2L * times % MOD * oldSum
                + times % MOD * times % MOD * len) % MOD;
        sum[node] = (oldSum + times % MOD * len) % MOD;
        lazy[node] += times;
    }
}
