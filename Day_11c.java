/*
You are given an array of positive integers nums.
You need to select a subset of nums which satisfies the following condition:
You can place the selected elements in a 0-indexed array such that it follows the pattern: [x, x2, x4, ..., xk/2, xk, xk/2, ..., x4, x2, x] (Note that k can be be any non-negative power of 2). For example, [2, 4, 16, 4, 2] and [3, 9, 3] follow the pattern while [2, 4, 8, 4, 2] does not.
Return the maximum number of elements in a subset that satisfies these conditions.

Example 1:

Input: nums = [5,4,1,2,2]
Output: 3
Explanation: We can select the subset {4,2,2}, which can be placed in the array as [2,4,2] which follows the pattern and 22 == 4. Hence the answer is 3.
*/
import java.util.*;
class Solution {
    public int maximumLength(int[] nums) {
        HashMap<Long, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put((long) num,
                freq.getOrDefault((long) num, 0) + 1);
        }
        int ans = 1;
        if (freq.containsKey(1L)) {
            int ones = freq.get(1L);
            if (ones % 2 == 0) {
                ones--;
            }
            ans = Math.max(ans, ones);
        }
        for (long x : freq.keySet()) {
            if (x == 1) continue;
            long curr = x;
            int length = 0;
            while (freq.getOrDefault(curr, 0) >= 2) {
                length += 2;
                curr = curr * curr;
                if (curr > 1e9) break;
            }
            if (freq.getOrDefault(curr, 0) >= 1) {
                length += 1;
            } else {
                length -= 1;
            }
            ans = Math.max(ans, length);
        }
        return ans;
    }
}
