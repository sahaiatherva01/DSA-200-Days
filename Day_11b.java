/*
You are given an array nums of length n and a positive integer k.
A subarray of nums is called good if the absolute difference between its first and last element is exactly k, in other words, the subarray nums[i..j] is good if |nums[i] - nums[j]| == k.
Return the maximum sum of a good subarray of nums. If there are no good subarrays, return 0.
 
Example 1:

Input: nums = [1,2,3,4,5,6], k = 1
Output: 11
Explanation: The absolute difference between the first and last element must be 1 for a good subarray. All the good subarrays are: [1,2], [2,3], [3,4], [4,5], and [5,6]. The maximum subarray sum is 11 for the subarray [5,6].
*/
import java.util.HashMap;
class Solution {
    public long maximumSubarraySum(int[] nums, int k) {
        HashMap<Integer, Long> map = new HashMap<>();
        long prefix = 0;
        long ans = Long.MIN_VALUE;
        for (int num : nums) {
            if (map.containsKey(num - k)) {
                ans = Math.max(ans,
                        prefix + num - map.get(num - k));
            }
            if (map.containsKey(num + k)) {
                ans = Math.max(ans,
                        prefix + num - map.get(num + k));
            }
            map.put(num,
                Math.min(map.getOrDefault(num, Long.MAX_VALUE),
                         prefix));
            prefix += num;
        }
        return ans == Long.MIN_VALUE ? 0 : ans;
    }
}
