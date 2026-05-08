/* 
You are given a 0-indexed integer array nums.
The concatenation of two numbers is the number formed by concatenating their numerals.
For example, the concatenation of 15, 49 is 1549.
The concatenation value of nums is initially equal to 0. Perform this operation until nums becomes empty:
If nums has a size greater than one, add the value of the concatenation of the first and the last element to the concatenation value of nums, and remove those two elements from nums. For example, if the nums was [1, 2, 4, 5, 6], add 16 to the concatenation value.
If only one element exists in nums, add its value to the concatenation value of nums, then remove it.
Return the concatenation value of nums.

Example 1:
Input: nums = [7,52,2,4]
Output: 596
Explanation: Before performing any operation, nums is [7,52,2,4] and concatenation value is 0.
 - In the first operation:
We pick the first element, 7, and the last element, 4.
Their concatenation is 74, and we add it to the concatenation value, so it becomes equal to 74.
Then we delete them from nums, so nums becomes equal to [52,2].
 - In the second operation:
We pick the first element, 52, and the last element, 2.
Their concatenation is 522, and we add it to the concatenation value, so it becomes equal to 596.
Then we delete them from the nums, so nums becomes empty.
Since the concatenation value is 596 so the answer is 596.
*/
class Solution {
    public long findTheArrayConcVal(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        long ans = 0;
        while (left <= right) {
            if (left == right) {
                ans += nums[left];
            } else {
                int a = nums[left];
                int b = nums[right];
                int temp = b;
                int power = 1;
                while (temp > 0) {
                    power *= 10;
                    temp /= 10;
                }
                ans += (long)a * power + b;
            }
            left++;
            right--;
        }
        return ans;
    }
}
