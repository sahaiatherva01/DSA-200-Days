/*
Given a string s that contains parentheses and letters, remove the minimum number of invalid parentheses to make the input string valid.
Return a list of unique strings that are valid with the minimum number of removals. You may return the answer in any order.

Example 1:
Input: s = "()())()"
Output: ["(())()","()()()"]

Example 2:
Input: s = "(a)())()"
Output: ["(a())()","(a)()()"]

Example 3:
Output: [""]
*/
import java.util.*;

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> ans = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();

        queue.offer(s);
        visited.add(s);

        boolean found = false;

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                String cur = queue.poll();

                if (isValid(cur)) {
                    ans.add(cur);
                    found = true;
                }

                // Don't generate next level once a valid level is found
                if (found) continue;

                for (int i = 0; i < cur.length(); i++) {
                    // Only remove parentheses
                    if (cur.charAt(i) != '(' && cur.charAt(i) != ')')
                        continue;

                    // Avoid generating duplicate strings
                    if (i > 0 && cur.charAt(i) == cur.charAt(i - 1))
                        continue;

                    String next = cur.substring(0, i) + cur.substring(i + 1);

                    if (visited.add(next)) {
                        queue.offer(next);
                    }
                }
            }

            if (found) break;
        }

        return ans;
    }

    private boolean isValid(String s) {
        int balance = 0;

        for (char c : s.toCharArray()) {
            if (c == '(') {
                balance++;
            } else if (c == ')') {
                if (--balance < 0) return false;
            }
        }

        return balance == 0;
    }
}
