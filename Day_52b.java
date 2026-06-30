/*
An integer has sequential digits if and only if each digit in the number is one more than the previous digit.
Return a sorted list of all the integers in the range [low, high] inclusive that have sequential digits.

Example 1:
Input: low = 100, high = 300
Output: [123,234]

Example 2:
Input: low = 1000, high = 13000
Output: [1234,2345,3456,4567,5678,6789,12345]
*/
class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> ans = new ArrayList<>();
        for (int start = 1; start <= 9; start++) {
            int num = start;
            for (int next = start + 1; next <= 9; next++) {
                num = num * 10 + next;
                if (num >= low && num <= high) {
                    ans.add(num);
                }
            }
        }
        Collections.sort(ans);
        return ans;
    }
}
