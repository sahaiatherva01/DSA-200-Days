/*
You are given a binary array nums.
We call a subarray alternating if no two adjacent elements in the subarray have the same value.
Return the number of alternating subarrays in nums.

Example 1:

Input: nums = [0,1,1,1]
Output: 5
Explanation:
The following subarrays are alternating: [0], [1], [1], [1], and [0,1].
*/
class Solution {
    public long countAlternatingSubarrays(int[] nums) {
        long ans = 1;
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                len++;
            } else {
                len = 1;
            }
            ans += len;
        }
        return ans;
    }
}
