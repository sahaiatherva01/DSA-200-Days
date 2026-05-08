/*
Given a string array words, return an array of all characters that show up in all strings within the words (including duplicates). You may return the answer in any order.
Example 1:
Input: words = ["bella","label","roller"]
Output: ["e","l","l"]
Example 2:
Input: words = ["cool","lock","cook"]
Output: ["c","o"]
*/
import java.util.*;
class Solution {
    public List<String> commonChars(String[] words) {
        int[] common = new int[26];
        for (char ch : words[0].toCharArray()) {
            common[ch - 'a']++;
        }
        for (int i = 1; i < words.length; i++) {
            int[] freq = new int[26];
            for (char ch : words[i].toCharArray()) {
                freq[ch - 'a']++;
            }
            for (int j = 0; j < 26; j++) {
                common[j] = Math.min(common[j], freq[j]);
            }
        }
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            while (common[i] > 0) {
                ans.add("" + (char)(i + 'a'));
                common[i]--;
            }
        }
        return ans;
    }
}
