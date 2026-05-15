/*
You are given an integer array nums. This array contains n elements, where exactly n - 2 elements are special numbers. One of the remaining two elements is the sum of these special numbers, and the other is an outlier.
An outlier is defined as a number that is neither one of the original special numbers nor the element representing the sum of those numbers.
Note that special numbers, the sum element, and the outlier must have distinct indices, but may share the same value.
Return the largest potential outlier in nums.

Example 1:
Input: nums = [2,3,5,10]
Output: 10
Explanation:
The special numbers could be 2 and 3, thus making their sum 5 and the outlier 10.
*/
import java.util.HashMap;
class Solution {
    public int getLargestOutlier(int[] nums) {
        HashMap<Integer, Integer> freq = new HashMap<>();
        int total = 0;
        for (int num : nums) {
            total += num;
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }
        int ans = Integer.MIN_VALUE;
        for (int x : nums) {
            int remaining = total - x;
            if (remaining % 2 != 0) continue;
            int sumElement = remaining / 2;
            if (!freq.containsKey(sumElement)) continue;
            if (sumElement == x && freq.get(sumElement) < 2) {
                continue;
            }
            ans = Math.max(ans, x);
        }
        return ans;
    }
}
