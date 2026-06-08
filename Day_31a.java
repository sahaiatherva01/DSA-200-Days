/*
Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure your result is the smallest in lexicographical order among all possible results.

Example 1:
Input: s = "bcabc"
Output: "abc"

Example 2:
Input: s = "cbacdcbc"
Output: "acdb"
*/
import java.util.*;
class Solution {
    public String removeDuplicateLetters(String s) {
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        boolean[] used = new boolean[26];
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (used[ch - 'a']) {
                continue;
            }
            while (!stack.isEmpty()
                    && stack.peek() > ch
                    && last[stack.peek() - 'a'] > i) {

                used[stack.pop() - 'a'] = false;
            }
            stack.push(ch);
            used[ch - 'a'] = true;
        }
        StringBuilder ans = new StringBuilder();
        for (char ch : stack) {
            ans.append(ch);
        }
        return ans.toString();
    }
}
