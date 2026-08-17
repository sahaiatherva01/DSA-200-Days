/*
You are given an array arr of size n consisting of non-empty strings.
Find a string array answer of size n such that:
answer[i] is the shortest substring of arr[i] that does not occur as a substring in any other string in arr. If multiple such substrings exist, answer[i] should be the lexicographically smallest. And if no such substring exists, answer[i] should be an empty string.
Return the array answer.

Example 1:
Input: arr = ["cab","ad","bad","c"]
Output: ["ab","","ba",""]
Explanation: We have the following:
- For the string "cab", the shortest substring that does not occur in any other string is either "ca" or "ab", we choose the lexicographically smaller substring, which is "ab".
- For the string "ad", there is no substring that does not occur in any other string.
- For the string "bad", the shortest substring that does not occur in any other string is "ba".
- For the string "c", there is no substring that does not occur in any other string.

Example 2:
Input: arr = ["abc","bcd","abcd"]
Output: ["","","abcd"]
Explanation: We have the following:
- For the string "abc", there is no substring that does not occur in any other string.
- For the string "bcd", there is no substring that does not occur in any other string.
- For the string "abcd", the shortest substring that does not occur in any other string is "abcd".
*/
import java.util.*;
class Solution {
    public String[] shortestSubstrings(String[] arr) {
        int n = arr.length;
        String[] answer = new String[n];
        for (int i = 0; i < n; i++) {
            String best = "";
            for (int len = 1; len <= arr[i].length(); len++) {
                String currentBest = null;
                for (int start = 0; start + len <= arr[i].length(); start++) {
                    String sub = arr[i].substring(start, start + len);
                    boolean unique = true;
                    for (int j = 0; j < n; j++) {
                        if (i != j && arr[j].contains(sub)) {
                            unique = false;
                            break;
                        }
                    }
                    if (unique && (currentBest == null || sub.compareTo(currentBest) < 0)) {
                        currentBest = sub;
                    }
                }
                if (currentBest != null) {
                    best = currentBest;
                    break;
                }
            }
            answer[i] = best;
        }
        return answer;
    }
}
