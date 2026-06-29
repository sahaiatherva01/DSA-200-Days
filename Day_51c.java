/*
Bob is stuck in a dungeon and must break n locks, each requiring some amount of energy to break. The required energy for each lock is stored in an array called strength where strength[i] indicates the energy needed to break the ith lock.
To break a lock, Bob uses a sword with the following characteristics:
The initial energy of the sword is 0.
The initial factor x by which the energy of the sword increases is 1.
Every minute, the energy of the sword increases by the current factor x.
To break the ith lock, the energy of the sword must reach at least strength[i].
After breaking a lock, the energy of the sword resets to 0, and the factor x increases by a given value k.
Your task is to determine the minimum time in minutes required for Bob to break all n locks and escape the dungeon.
Return the minimum time required for Bob to break all n locks.

Example 1:
Input: strength = [3,4,1], k = 1
Output: 4
Explanation:
Time	Energy	x	Action	Updated x
0	0	1	Nothing	1
1	1	1	Break 3rd Lock	2
2	2	2	Nothing	2
3	4	2	Break 2nd Lock	3
4	3	3	Break 1st Lock	3
The locks cannot be broken in less than 4 minutes; thus, the answer is 4.

Example 2:
Input: strength = [2,5,4], k = 2
Output: 5
Explanation:
Time	Energy	x	Action	Updated x
0	0	1	Nothing	1
1	1	1	Nothing	1
2	2	1	Break 1st Lock	3
3	3	3	Nothing	3
4	6	3	Break 2nd Lock	5
5	5	5	Break 3rd Lock	7
The locks cannot be broken in less than 5 minutes; thus, the answer is 5.
*/
class Solution {
    public int findMinimumTime(List<Integer> strength, int k) {
        int n = strength.size();
        int[] memo = new int[1 << n];
        Arrays.fill(memo, -1);
        return dfs(0, strength, k, memo);
    }
    private int dfs(int mask, List<Integer> strength, int k, int[] memo) {
        int n = strength.size();
        if (mask == (1 << n) - 1) {
            return 0;
        }
        if (memo[mask] != -1) {
            return memo[mask];
        }
        int broken = Integer.bitCount(mask);
        int x = 1 + broken * k;
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if ((mask & (1 << i)) == 0) {
                int time = (strength.get(i) + x - 1) / x;
                ans = Math.min(ans,
                        time + dfs(mask | (1 << i), strength, k, memo));
            }
        }
        return memo[mask] = ans;
    }
}
