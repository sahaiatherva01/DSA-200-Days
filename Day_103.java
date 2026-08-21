/*
You are given a sorted integer array arr containing 1 and prime numbers, where all the integers of arr are unique. You are also given an integer k.
For every i and j where 0 <= i < j < arr.length, we consider the fraction arr[i] / arr[j].
Return the kth smallest fraction considered. Return your answer as an array of integers of size 2, where answer[0] == arr[i] and answer[1] == arr[j].
 
Example 1:
Input: arr = [1,2,3,5], k = 3
Output: [2,5]
Explanation: The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, and 2/3.
The third fraction is 2/5.

Example 2:
Input: arr = [1,7], k = 1
Output: [1,7]
*/
class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int n = arr.length;
        double low = 0.0;
        double high = 1.0;
        while (low < high) {
            double mid = (low + high) / 2.0;
            int count = 0;
            int numerator = 0;
            int denominator = 1;
            int j = 1;
            for (int i = 0; i < n - 1; i++) {
                while (j < n && arr[i] > mid * arr[j]) {
                    j++;
                }
                if (j == n) {
                    break;
                }
                count += n - j;
                if (arr[i] * denominator > numerator * arr[j]) {
                    numerator = arr[i];
                    denominator = arr[j];
                }
            }
            if (count == k) {
                return new int[]{numerator, denominator};
            }
            if (count < k) {
                low = mid;
            } else {
                high = mid;
            }
        }
        return new int[]{0, 0};
    }
}
