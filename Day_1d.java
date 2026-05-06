/*
You are given two arrays of equal length, nums1 and nums2.
Each element in nums1 has been increased (or decreased in the case of negative) by an integer, represented by the variable x.
As a result, nums1 becomes equal to nums2. Two arrays are considered equal when they contain the same integers with the same frequencies.
Return the integer x.
Example 1:
Input: nums1 = [2,6,4], nums2 = [9,7,5]
Output: 3
Explanation:
The integer added to each element of nums1 is 3.
Example 2:
Input: nums1 = [10], nums2 = [5]
Output: -5
Explanation:
The integer added to each element of nums1 is -5.
*/
class Solution {
    public int addedInteger(int[] nums1, int[] nums2) {
        int sum1 = 0, sum2 = 0;
        for (int i = 0; i < nums1.length; i++) {
            sum1 += nums1[i];
            sum2 += nums2[i];
        }
        return (sum2 - sum1) / nums1.length;
    }
}
