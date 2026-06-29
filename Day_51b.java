/*
You are given an integer array nums.
Return an integer that is the maximum distance between the indices of two (not necessarily different) prime numbers in nums.

Example 1:
Input: nums = [4,2,9,5,3]
Output: 3
Explanation: nums[1], nums[3], and nums[4] are prime. So the answer is |4 - 1| = 3.

Example 2:
Input: nums = [4,8,2,8]
Output: 0
Explanation: nums[2] is prime. Because there is just one prime number, the answer is |2 - 2| = 0.
*/
class Solution {
    public int maximumPrimeDifference(int[] nums) {
        int first = -1;
        int last = -1;
        for (int i = 0; i < nums.length; i++) {
            if (isPrime(nums[i])) {
                if (first == -1) {
                    first = i;
                }
                last = i;
            }
        }
        return last - first;
    }
    private boolean isPrime(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
