/*
You are given two arrays nums and andValues of length n and m respectively.
The value of an array is equal to the last element of that array.
You have to divide nums into m disjoint contiguous subarrays such that for the ith subarray [li, ri], the bitwise AND of the subarray elements is equal to andValues[i], in other words, nums[li] & nums[li + 1] & ... & nums[ri] == andValues[i] for all 1 <= i <= m, where & represents the bitwise AND operator.
Return the minimum possible sum of the values of the m subarrays nums is divided into. If it is not possible to divide nums into m subarrays satisfying these conditions, return -1.

Example 1:
Input: nums = [1,4,3,3,2], andValues = [0,3,3,2]
Output: 12
Explanation:
The only possible way to divide nums is:
[1,4] as 1 & 4 == 0.
[3] as the bitwise AND of a single element subarray is that element itself.
[3] as the bitwise AND of a single element subarray is that element itself.
[2] as the bitwise AND of a single element subarray is that element itself.
The sum of the values for these subarrays is 4 + 3 + 3 + 2 = 12.

Example 2:
Input: nums = [2,3,5,7,7,7,5], andValues = [0,7,5]
Output: 17
Explanation:
There are three ways to divide nums:
[[2,3,5],[7,7,7],[5]] with the sum of the values 5 + 7 + 5 == 17.
[[2,3,5,7],[7,7],[5]] with the sum of the values 7 + 7 + 5 == 19.
[[2,3,5,7,7],[7],[5]] with the sum of the values 7 + 7 + 5 == 19.
The minimum possible sum of the values is 17.

Example 3:
Input: nums = [1,2,3,4], andValues = [2]
Output: -1
Explanation:
The bitwise AND of the entire array nums is 0. As there is no possible way to divide nums into a single subarray to have the bitwise AND of elements 2, return -1.
*/
class Solution {
    private static final long INF = (long) 1e18;
    private int[] nums;
    private int[] andValues;
    private int n, m;
    private HashMap<Long, Long> memo = new HashMap<>();
    public int minimumValueSum(int[] nums, int[] andValues) {
        this.nums = nums;
        this.andValues = andValues;
        n = nums.length;
        m = andValues.length;
        long ans = dfs(0, 0, -1);
        return ans >= INF ? -1 : (int) ans;
    }
    private long dfs(int i, int j, int currAnd) {
        if (j == m) {
            return i == n ? 0 : INF;
        }
        if (i == n) {
            return INF;
        }
        if (n - i < m - j) {
            return INF;
        }
        int newAnd = (currAnd == -1) ? nums[i] : (currAnd & nums[i]);
        if (newAnd < andValues[j]) {
            return INF;
        }
        long key = (((long) i) << 40) | (((long) j) << 32) | (newAnd & 0xffffffffL);
        if (memo.containsKey(key)) {
            return memo.get(key);
        }
        long ans = dfs(i + 1, j, newAnd);
        if (newAnd == andValues[j]) {
            ans = Math.min(ans,
                    nums[i] + dfs(i + 1, j + 1, -1));
        }
        memo.put(key, ans);
        return ans;
    }
}
