/*
You are given a 0-indexed integer array nums of length n.
A split at an index i where 0 <= i <= n - 2 is called valid if the product of the first i + 1 elements and the product of the remaining elements are coprime.
For example, if nums = [2, 3, 3], then a split at the index i = 0 is valid because 2 and 9 are coprime, while a split at the index i = 1 is not valid because 6 and 3 are not coprime. A split at the index i = 2 is not valid because i == n - 1.
Return the smallest index i at which the array can be split validly or -1 if there is no such split.
Two values val1 and val2 are coprime if gcd(val1, val2) == 1 where gcd(val1, val2) is the greatest common divisor of val1 and val2.

Example 1:
Input: nums = [4,7,8,15,3,5]
Output: 2
Explanation: The table above shows the values of the product of the first i + 1 elements, the remaining elements, and their gcd at each index i.
The only valid split is at index 2.

Example 2:
Input: nums = [4,7,15,8,3,5]
Output: -1
Explanation: The table above shows the values of the product of the first i + 1 elements, the remaining elements, and their gcd at each index i.
There is no valid split.
*/
import java.util.*;
class Solution {
    public int findValidSplit(int[] nums) {
        int n = nums.length;
        // Last occurrence of every prime factor
        Map<Integer, Integer> last = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int x = nums[i];
            for (int p = 2; p * p <= x; p++) {
                if (x % p == 0) {
                    last.put(p, i);
                    while (x % p == 0) {
                        x /= p;
                    }
                }
            }

            if (x > 1) {
                last.put(x, i);
            }
        }
        int farthest = 0;
        for (int i = 0; i < n - 1; i++) {
            int x = nums[i];
            for (int p = 2; p * p <= x; p++) {
                if (x % p == 0) {
                    farthest = Math.max(farthest, last.get(p));

                    while (x % p == 0) {
                        x /= p;
                    }
                }
            }
            if (x > 1) {
                farthest = Math.max(farthest, last.get(x));
            }
            // All prime factors of the prefix
            // occur only inside the prefix.
            if (farthest == i) {
                return i;
            }
        }
        return -1;
    }
}
