/*
Given an array of integers nums and an integer k, return the number of subarrays of nums where the bitwise AND of the elements of the subarray equals k.

Example 1:
Input: nums = [1,1,1], k = 1
Output: 6
Explanation:
All subarrays contain only 1's.

Example 2:
Input: nums = [1,1,2], k = 1
Output: 3
Explanation:
Subarrays having an AND value of 1 are: [1,1,2], [1,1,2], [1,1,2].

Example 3:
Input: nums = [1,2,3], k = 2
Output: 2
Explanation:
Subarrays having an AND value of 2 are: [1,2,3], [1,2,3].
*/
import java.util.*;
class Solution {
    public long countSubarrays(int[] nums, int k) {
        long ans = 0;
        Map<Integer, Integer> prev = new HashMap<>();
        for (int num : nums) {
            Map<Integer, Integer> curr = new HashMap<>();
            curr.put(num, curr.getOrDefault(num, 0) + 1);
            for (Map.Entry<Integer, Integer> entry : prev.entrySet()) {
                int andValue = entry.getKey() & num;
                int count = entry.getValue();
                curr.put(
                    andValue,
                    curr.getOrDefault(andValue, 0) + count
                );
            }
            ans += curr.getOrDefault(k, 0);
            prev = curr;
        }
        return ans;
    }
}
