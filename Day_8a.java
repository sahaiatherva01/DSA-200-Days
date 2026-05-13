/*
You are given an array nums consisting of positive integers where all integers have the same number of digits.
The digit difference between two integers is the count of different digits that are in the same position in the two integers.
Return the sum of the digit differences between all pairs of integers in nums.

Example 1:

Input: nums = [13,23,12]

Output: 4

Explanation:
We have the following:
- The digit difference between 13 and 23 is 1.
- The digit difference between 13 and 12 is 1.
- The digit difference between 23 and 12 is 2.
So the total sum of digit differences between all pairs of integers is 1 + 1 + 2 = 4.
*/
class Solution {
    public long sumDigitDifferences(int[] nums) {
        int n = nums.length;
        int digits = String.valueOf(nums[0]).length();
        long ans = 0;
        int place = 1;
        for (int pos = 0; pos < digits; pos++) {
            int[] freq = new int[10];
            for (int i = 0; i < n; i++) {
                int digit = (nums[i] / place) % 10;
                ans += i - freq[digit];
                freq[digit]++;
            }
            place *= 10;
        }
        return ans;
    }
}
