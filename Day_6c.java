/*
Given an array of positive integers nums, return the maximum possible sum of an strictly increasing subarray in nums.
A subarray is defined as a contiguous sequence of numbers in an array.

Example 1:

Input: nums = [10,20,30,5,10,50]
Output: 65
Explanation: [5,10,50] is the ascending subarray with the maximum sum of 65.
Example 2:

Input: nums = [10,20,30,40,50]
Output: 150
Explanation: [10,20,30,40,50] is the ascending subarray with the maximum sum of 150.
*/
class Solution {
    public int maxAscendingSum(int[] nums) {
        int currentSum = nums[0];
        int maxSum = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                currentSum += nums[i];
            } else {
                currentSum = nums[i];
            }
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}
