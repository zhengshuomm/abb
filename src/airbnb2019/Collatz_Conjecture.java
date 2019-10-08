package airbnb2019;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Collatz_Conjecture {
	public static void main(String[] args) {
		System.out.println(findLongestSteps2(7));
	}
	
	public static int findLongestSteps(int limit) {
		if (limit < 1) return 0;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		int longest = 0;
		for (int i  = 1; i <= limit ; i ++) {
			longest = Math.max(longest, findSteps(i, map));
		}
		return longest;
	}
	
	public static int findSteps(int num, Map<Integer, Integer> map) {
		if ( num <= 1 ) return 1;
		if (map.containsKey(num)) return map.get(num);
		int result = 0;
		if (num % 2 == 0) {
			result = 1 + findSteps(num / 2, map);
		} else {
			result = 1 + findSteps(3 * num + 1, map);
		}
		map.put(num, result);
		return result;
	}
	
	public static int findLongestSteps2(int limit) {
		if (limit <= 1) return 0;
		int[] dp = new int[limit + 1];
		Arrays.fill(dp, -1);
		dp[1] = 1;
		int longest = 0;
		for (int i = 1 ; i <= limit ; i ++) {
			int num = i;
			int count = 0;
//			System.out.println(i);
			while (num > 1 || dp[num] == -1) {
				count ++;
				if (num % 2 == 0) {
					num = num /2;
				} else {
					num = 3 * num + 1;
				}
			}
			count += dp[num];
			dp[i] = count;
			longest = Math.max(dp[i], longest);
		}
		System.out.println(Arrays.toString(dp));
		return longest;
	}
}
