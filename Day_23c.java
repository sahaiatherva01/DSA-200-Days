/*
You are given a string s of lowercase English letters and a 2D integer array shifts where shifts[i] = [starti, endi, directioni]. For every i, shift the characters in s from the index starti to the index endi (inclusive) forward if directioni = 1, or shift the characters backward if directioni = 0.
Shifting a character forward means replacing it with the next letter in the alphabet (wrapping around so that 'z' becomes 'a'). Similarly, shifting a character backward means replacing it with the previous letter in the alphabet (wrapping around so that 'a' becomes 'z').
Return the final string after all such shifts to s are applied.

Example 1:

Input: s = "abc", shifts = [[0,1,0],[1,2,1],[0,2,1]]
Output: "ace"
Explanation: Firstly, shift the characters from index 0 to index 1 backward. Now s = "zac".
Secondly, shift the characters from index 1 to index 2 forward. Now s = "zbd".
Finally, shift the characters from index 0 to index 2 forward. Now s = "ace".
*/
class Solution {
    public String shiftingLetters(String s, int[][] shifts) {
        int n = s.length();
        int[] diff = new int[n + 1];
        for (int[] shift : shifts) {
            int l = shift[0];
            int r = shift[1];
            int dir = shift[2];
            if (dir == 1) { 
                diff[l]++;
                if (r + 1 < diff.length) {
                    diff[r + 1]--;
                }
            } else {
                diff[l]--;
                if (r + 1 < diff.length) {
                    diff[r + 1]++;
                }
            }
        }
        StringBuilder ans = new StringBuilder();
        int currShift = 0;
        for (int i = 0; i < n; i++) {
            currShift += diff[i];
            int pos = s.charAt(i) - 'a';
            pos = (pos + currShift) % 26;
            if (pos < 0) {
                pos += 26;
            }
            ans.append((char)(pos + 'a'));
        }
        return ans.toString();
    }
}
