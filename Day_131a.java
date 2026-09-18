/*
Given an integer array nums and an integer k, return the number of good subarrays of nums.
A good array is an array where the number of different integers in that array is exactly k.
For example, [1,2,3,1,2] has 3 different integers: 1, 2, and 3.
A subarray is a contiguous part of an array.

Example 1:
Input: nums = [1,2,1,2,3], k = 2
Output: 7
Explanation: Subarrays formed with exactly 2 different integers: [1,2], [2,1], [1,2], [2,3], [1,2,1], [2,1,2], [1,2,1,2]

Example 2:
Input: nums = [1,2,1,3,4], k = 3
Output: 3
Explanation: Subarrays formed with exactly 3 different integers: [1,2,1,3], [2,1,3], [1,3,4].
*/
class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }
    private int atMost(int[] nums, int k) {
        if (k <= 0) return 0;
        int[] freq = new int[nums.length + 1];
        int left = 0;
        int distinct = 0;
        int ans = 0;
        for (int right = 0; right < nums.length; right++) {

            if (freq[nums[right]]++ == 0) {
                distinct++;
            }
            while (distinct > k) {
                if (--freq[nums[left]] == 0) {
                    distinct--;
                }
                left++;
            }
            ans += right - left + 1;
        }
        return ans;
    }
}
