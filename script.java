class script{
    
        // 1. HackerRank - Sherlock and Valid String
    class SherlockValidString {
        static String isValid(String s) {
            // Count frequency of each character
            int[] freq = new int[26];
            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }
            
            // Count frequency of frequencies
            Map<Integer, Integer> freqCount = new HashMap<>();
            for (int f : freq) {
                if (f > 0) {
                    freqCount.put(f, freqCount.getOrDefault(f, 0) + 1);
                }
            }
            
            // If all characters have same frequency
            if (freqCount.size() == 1) return "YES";
            
            // If we have more than 2 different frequencies
            if (freqCount.size() > 2) return "NO";
            
            // Check if we can remove one character to make all frequencies same
            int[] frequencies = freqCount.keySet().stream().mapToInt(Integer::intValue).toArray();
            int[] counts = freqCount.values().stream().mapToInt(Integer::intValue).toArray();
            
            return (counts[0] == 1 && (frequencies[0] == 1 || frequencies[0] - frequencies[1] == 1)) ||
                (counts[1] == 1 && (frequencies[1] == 1 || frequencies[1] - frequencies[0] == 1)) 
                ? "YES" : "NO";
        }
    }

    /*
    Analysis for Sherlock and Valid String:
    1. Time Complexity: O(n)
    - First loop to count character frequencies: O(n)
    - Creating frequency of frequencies map: O(1) as we have max 26 characters
    - Final checks are O(1)

    2. Space Complexity: O(1)
    - freq array is fixed size 26: O(1)
    - freqCount map has at most 26 entries: O(1)
    - All other variables are constant space

    Key Points:
    - Uses counting strategy with fixed-size arrays
    - Handles edge cases efficiently
    - Constant space despite using HashMap due to limited character set
    */

    // 2. LeetCode - String to Integer (atoi)
    class StringToInteger {
        public int myAtoi(String s) {
            if (s == null || s.length() == 0) return 0;
            
            int index = 0;
            int sign = 1;
            long result = 0;
            
            // Skip leading whitespace
            while (index < s.length() && s.charAt(index) == ' ') {
                index++;
            }
            
            // Handle sign
            if (index < s.length() && (s.charAt(index) == '+' || s.charAt(index) == '-')) {
                sign = s.charAt(index) == '+' ? 1 : -1;
                index++;
            }
            
            // Process digits
            while (index < s.length() && Character.isDigit(s.charAt(index))) {
                result = result * 10 + (s.charAt(index) - '0');
                if (result * sign > Integer.MAX_VALUE) return Integer.MAX_VALUE;
                if (result * sign < Integer.MIN_VALUE) return Integer.MIN_VALUE;
                index++;
            }
            
            return (int) (sign * result);
        }
    }

    /*
    Analysis for String to Integer:
    1. Time Complexity: O(n)
    - Single pass through the string
    - Each character is processed at most once
    - All operations within the loop are O(1)

    2. Space Complexity: O(1)
    - Only using a few primitive variables
    - No additional data structures

    Key Points:
    - Handles overflow cases
    - Processes string character by character
    - Early termination for invalid inputs
    */

    // 3. LeetCode - Partition Equal Subset Sum
    class PartitionEqualSubsetSum {
        public boolean canPartition(int[] nums) {
            int sum = 0;
            for (int num : nums) {
                sum += num;
            }
            
            if (sum % 2 != 0) return false;
            int target = sum / 2;
            
            boolean[] dp = new boolean[target + 1];
            dp[0] = true;
            
            for (int num : nums) {
                for (int j = target; j >= num; j--) {
                    dp[j] = dp[j] || dp[j - num];
                }
            }
            
            return dp[target];
        }
    }

    /*
    Analysis for Partition Equal Subset Sum:
    1. Time Complexity: O(n * target)
    - n is length of array
    - target is sum/2
    - Nested loops: outer loop runs n times, inner loop runs target times

    2. Space Complexity: O(target)
    - Single boolean array of size target + 1
    - No recursion or additional space needed

    Key Points:
    - Dynamic programming solution
    - Uses 1D DP array instead of 2D to optimize space
    - Early termination for odd sums
    */

    // 4. HackerRank - Jumping on the Clouds
    class JumpingOnClouds {
        static int jumpingOnClouds(int[] c) {
            int jumps = 0;
            int i = 0;
            
            while (i < c.length - 1) {
                if (i + 2 < c.length && c[i + 2] == 0) {
                    i += 2;
                } else {
                    i++;
                }
                jumps++;
            }
            
            return jumps;
        }
    }

    /*
    Analysis for Jumping on the Clouds:
    1. Time Complexity: O(n)
    - Single pass through the array
    - Each position is visited at most once
    - Greedy approach allows for optimal solution

    2. Space Complexity: O(1)
    - Only using counter variables
    - No additional data structures

    Key Points:
    - Greedy algorithm
    - Always takes 2-step jump when possible
    - Handles edge cases implicitly
    */

    // 5. HackerRank - Count Triplets
    class CountTriplets {
        static long countTriplets(List<Long> arr, long r) {
            Map<Long, Long> v2 = new HashMap<>();
            Map<Long, Long> v3 = new HashMap<>();
            long count = 0;
            
            for (long k : arr) {
                if (v3.containsKey(k)) {
                    count += v3.get(k);
                }
                
                if (v2.containsKey(k)) {
                    v3.put(k * r, v3.getOrDefault(k * r, 0L) + v2.get(k));
                }
                
                v2.put(k * r, v2.getOrDefault(k * r, 0L) + 1);
            }
            
            return count;
        }
    }

    /*
    Analysis for Count Triplets:
    1. Time Complexity: O(n)
    - Single pass through the array
    - HashMap operations are O(1)
    - Each element is processed exactly once

    2. Space Complexity: O(n)
    - Two HashMaps storing potential pairs and triplets
    - In worst case, might store all elements

    Key Points:
    - Uses two maps to track potential pairs and triplets
    - Handles geometric progression efficiently
    - Avoids nested loops for better performance

    Additional Optimization Considerations:
    1. Sherlock and Valid String:
    - Could use array instead of HashMap for frequency counting
    - Could optimize by early termination on invalid cases

    2. String to Integer:
    - Could use StringBuilder for very long strings
    - Could add more edge case handling

    3. Partition Equal Subset Sum:
    - Could add memoization for recursive solution
    - Could optimize by sorting array first in some cases

    4. Jumping on the Clouds:
    - Could use recursive solution for more complex patterns
    - Could add path tracking if needed

    5. Count Triplets:
    - Could use array instead of HashMap for small r values
    - Could optimize memory usage for sparse data
    */
}