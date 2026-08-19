/*
You are given an array nums of non-negative integers and an integer k.
An array is called special if the bitwise OR of all of its elements is at least k.
Return the length of the shortest special non-empty subarray of nums, or return -1 if no special subarray exists.

Example 1:
Input: nums = [1,2,3], k = 2
Output: 1
Explanation:
The subarray [3] has OR value of 3. Hence, we return 1.

Example 2:
Input: nums = [2,1,8], k = 10
Output: 3
Explanation:
The subarray [2,1,8] has OR value of 11. Hence, we return 3.

Example 3:
Input: nums = [1,2], k = 0
Output: 1
Explanation:
The subarray [1] has OR value of 1. Hence, we return 1.
*/
class Solution {
    public int minimumSubarrayLength(int[] nums, int k) {
        int n = nums.length;
        int[] bits = new int[32];
        int left = 0;
        int currentOR = 0;
        int answer = Integer.MAX_VALUE;
        for (int right = 0; right < n; right++) {
            currentOR |= nums[right];
            for (int b = 0; b < 32; b++) {
                if ((nums[right] & (1 << b)) != 0) {
                    bits[b]++;
                }
            }
            while (left <= right && currentOR >= k) {
                answer = Math.min(answer, right - left + 1);
                for (int b = 0; b < 32; b++) {
                    if ((nums[left] & (1 << b)) != 0) {
                        bits[b]--;
                        if (bits[b] == 0) {
                            currentOR &= ~(1 << b);
                        }
                    }
                }
                left++;
            }
        }

        return answer == Integer.MAX_VALUE ? -1 : answer;
    }
}
