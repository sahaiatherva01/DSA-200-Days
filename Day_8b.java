/*
You are given two integer arrays nums1 and nums2.
From nums1 two elements have been removed, and all other elements have been increased (or decreased in the case of negative) by an integer, represented by the variable x.
As a result, nums1 becomes equal to nums2. Two arrays are considered equal when they contain the same integers with the same frequencies.
Return the minimum possible integer x that achieves this equivalence.

Example 1:
Input: nums1 = [4,20,16,12,8], nums2 = [14,18,10]
Output: -2
Explanation:
After removing elements at indices [0,4] and adding -2, nums1 becomes [18,14,10].
*/
import java.util.Arrays;
class Solution {
    public int minimumAddedInteger(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < 3; i++) {
            int x = nums2[0] - nums1[i];
            if (isValid(nums1, nums2, x)) {
                ans = Math.min(ans, x);
            }
        }
        return ans;
    }
    private boolean isValid(int[] nums1, int[] nums2, int x) {
        int i = 0, j = 0;
        int removed = 0;
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] + x == nums2[j]) {
                i++;
                j++;
            } else {
                removed++;
                i++;
            }
        }
        removed += (nums1.length - i);
        return removed == 2;
    }
}
