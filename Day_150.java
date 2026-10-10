/*
You are given two positive 0-indexed integer arrays nums1 and nums2, both of length n.
The sum of squared difference of arrays nums1 and nums2 is defined as the sum of (nums1[i] - nums2[i])2 for each 0 <= i < n.
You are also given two positive integers k1 and k2. You can modify any of the elements of nums1 by +1 or -1 at most k1 times. Similarly, you can modify any of the elements of nums2 by +1 or -1 at most k2 times.
Return the minimum sum of squared difference after modifying array nums1 at most k1 times and modifying array nums2 at most k2 times.
Note: You are allowed to modify the array elements to become negative integers.

Example 1:
Input: nums1 = [1,2,3,4], nums2 = [2,10,20,19], k1 = 0, k2 = 0
Output: 579
Explanation: The elements in nums1 and nums2 cannot be modified because k1 = 0 and k2 = 0. 
The sum of square difference will be: (1 - 2)2 + (2 - 10)2 + (3 - 20)2 + (4 - 19)2 = 579.

Example 2:
Input: nums1 = [1,4,10,12], nums2 = [5,8,6,9], k1 = 1, k2 = 1
Output: 43
Explanation: One way to obtain the minimum sum of square difference is: 
- Increase nums1[0] once.
- Increase nums2[2] once.
The minimum of the sum of square difference will be: 
(2 - 5)2 + (4 - 8)2 + (10 - 7)2 + (12 - 9)2 = 43.
Note that, there are other ways to obtain the minimum of the sum of square difference, but there is no way to obtain a sum smaller than 43.
*/
class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        int max = 0;

        int[] diff = new int[n];

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            max = Math.max(max, diff[i]);
        }

        if (k >= java.util.Arrays.stream(diff).asLongStream().sum()) {
            return 0;
        }

        int left = 0, right = max;

        while (left < right) {
            int mid = left + (right - left) / 2;
            long needed = 0;

            for (int d : diff) {
                if (d > mid) {
                    needed += d - mid;
                }
            }

            if (needed <= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        int limit = left;
        long remaining = k;
        long ans = 0;

        for (int d : diff) {
            if (d > limit) {
                remaining -= d - limit;
                d = limit;
            }
            ans += (long) d * d;
        }

        // Distribute remaining operations to reduce differences at the limit.
        // This binary-search threshold approach is easier to implement
        // directly with a frequency array instead.
        return calculate(diff, k, max);
    }

    private long calculate(int[] diff, long k, int max) {
        long[] count = new long[max + 1];

        for (int d : diff) count[d]++;

        for (int d = max; d > 0 && k > 0; d--) {
            if (count[d] == 0) continue;

            long moves = Math.min(k, count[d]);
            count[d] -= moves;
            count[d - 1] += moves;
            k -= moves;
        }

        long ans = 0;
        for (int d = 1; d <= max; d++) {
            ans += count[d] * d * d;
        }

        return ans;
    }
}
