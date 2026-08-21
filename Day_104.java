/*
You are given an integer array coins representing coins of different denominations and an integer k.
You have an infinite number of coins of each denomination. However, you are not allowed to combine coins of different denominations.
Return the kth smallest amount that can be made using these coins.

Example 1:
Input: coins = [3,6,9], k = 3
Output: 9
Explanation: The given coins can make the following amounts:
Coin 3 produces multiples of 3: 3, 6, 9, 12, 15, etc.
Coin 6 produces multiples of 6: 6, 12, 18, 24, etc.
Coin 9 produces multiples of 9: 9, 18, 27, 36, etc.
All of the coins combined produce: 3, 6, 9, 12, 15, etc.

Example 2:
Input: coins = [5,2], k = 7
Output: 12
Explanation: The given coins can make the following amounts:
Coin 5 produces multiples of 5: 5, 10, 15, 20, etc.
Coin 2 produces multiples of 2: 2, 4, 6, 8, 10, 12, etc.
All of the coins combined produce: 2, 4, 5, 6, 8, 10, 12, 14, 15, etc.
*/
import java.util.*;
class Solution {
    long limit;
    int[] coins;
    int n;
    public long findKthSmallest(int[] coins, int k) {
        Arrays.sort(coins);
        ArrayList<Integer> list = new ArrayList<>();
        for (int coin : coins) {
            boolean redundant = false;
            for (int x : list) {
                if (coin % x == 0) {
                    redundant = true;
                    break;
                }
            }
            if (!redundant) {
                list.add(coin);
            }
        }
        this.coins = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            this.coins[i] = list.get(i);
        }
        n = this.coins.length;
        long low = 1;
        long high = (long) this.coins[0] * k;
        while (low < high) {
            long mid = low + (high - low) / 2;
            if (count(mid) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
    private long count(long x) {
        return dfs(0, 1, x, 0);
    }
    private long dfs(int index, long lcm, long x, int used) {
        long result = 0;
        for (int i = index; i < n; i++) {
            long newLcm = lcm(lcm, coins[i]);
            if (newLcm > x) {
                continue;
            }
            long contribution = x / newLcm;
            if ((used & 1) == 0) {
                result += contribution;
            } else {
                result -= contribution;
            }
            result += dfs(i + 1, newLcm, x, used + 1);
        }
        return result;
    }
    private long gcd(long a, long b) {
        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
    private long lcm(long a, long b) {
        long g = gcd(a, b);

        if (a > Long.MAX_VALUE / (b / g)) {
            return Long.MAX_VALUE;
        }
        return a / g * b;
    }
}
