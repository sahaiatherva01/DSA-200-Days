/*
You are given a string s.
You can perform the following process on s any number of times:
Choose an index i in the string such that there is at least one character to the left of index i that is equal to s[i], and at least one character to the right that is also equal to s[i].
Delete the closest occurrence of s[i] located to the left of i.
Delete the closest occurrence of s[i] located to the right of i.
Return the minimum length of the final string s that you can achieve.
*/
class Solution {
    public int minimumLength(String s) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        int ans = 0;
        for (int count : freq) {
            if (count == 0) continue;
            if (count % 2 == 1) {
                ans += 1;
            }
            else {
                ans += 2;
            }
        }
        return ans;
    }
}
