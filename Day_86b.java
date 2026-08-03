/*
Given an integer n, return the least number of perfect square numbers that sum to n.
A perfect square is an integer that is the square of an integer; in other words, it is the product of some integer with itself. For example, 1, 4, 9, and 16 are perfect squares while 3 and 11 are not.

Example 1:
Input: n = 12
Output: 3
Explanation: 12 = 4 + 4 + 4.

Example 2:
Input: n = 13
Output: 2
Explanation: 13 = 4 + 9.
*/
class Solution {
    public int numSquares(int n) {
        if (isSquare(n)) return 1;
        while (n % 4 == 0) n /= 4;
        if (n % 8 == 7) return 4;
        for (int i = 1; i * i <= n; i++) {
            if (isSquare(n - i * i)) {
                return 2;
            }
        }
        return 3;
    }
    private boolean isSquare(int x) {
        int r = (int) Math.sqrt(x);
        return r * r == x;
    }
}
