/*
Given a 2D integer array nums, return all elements of nums in diagonal order as shown in the below images.

Example 1:
Input: nums = [[1,2,3],[4,5,6],[7,8,9]]
Output: [1,4,2,7,5,3,8,6,9]

Example 2:
Input: nums = [[1,2,3,4,5],[6,7],[8],[9,10,11],[12,13,14,15,16]]
Output: [1,6,2,8,7,3,9,4,12,10,5,13,11,14,15,16]
*/
class Solution {
    public int[] findDiagonalOrder(List<List<Integer>> nums) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        int total = 0;
        int maxDiagonal = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = 0; j < nums.get(i).size(); j++) {
                int d = i + j;
                map.computeIfAbsent(d, k -> new ArrayList<>()).add(nums.get(i).get(j));
                maxDiagonal = Math.max(maxDiagonal, d);
                total++;
            }
        }
        int[] ans = new int[total];
        int idx = 0;
        for (int d = 0; d <= maxDiagonal; d++) {
            if (!map.containsKey(d)) continue;
            List<Integer> list = map.get(d);
            for (int i = list.size() - 1; i >= 0; i--) {
                ans[idx++] = list.get(i);
            }
        }
        return ans;
    }
}
