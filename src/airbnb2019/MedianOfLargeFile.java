package airbnb2019;

import java.util.Arrays;
import java.util.Random;

public class MedianOfLargeFile {
	public static void main(String args[]) {
		int[] nums = new int[100];
		Random rand = new Random();
		for (int i = 0; i < 100; i++) {
			int num = rand.nextInt(100);
			nums[i] = num;
		}
		Arrays.sort(nums);
		for (int i = 0; i < 100; i++) {
			System.out.print(nums[i] + ",");
		}
		System.out.println();
		System.out.println(nums[49]);
		System.out.println(nums[50]);
		System.out.println(findMedian(nums));
	}
	
	public static double findMedian(int[] nums) {
		int len = 0;
		// 模拟scan file
		for (int num : nums) {
			len ++;
		}
		
		if (len % 2 == 0) {
			return (double) (search(nums, len/2, Integer.MIN_VALUE, Integer.MAX_VALUE) + search(nums, len/2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE) ) /2; 
		}
		else {
			return (double) search(nums, len/2 + 1, Integer.MIN_VALUE, Integer.MAX_VALUE);
		}
	}

	private static long search(int[] nums, int k, long left, long right) {
		if (left >= right) return left; // key
		long largestLessThanGuess = left;
		long guess = left + (right - left) / 2;
		int count = 0;
		for (int num : nums) {
			if (num <= guess) {
				count ++;
				largestLessThanGuess = Math.max(num, largestLessThanGuess);
			}
		}
		if (count == k) {
			return largestLessThanGuess;
		}else if (count < k) {
			return search(nums, k, largestLessThanGuess + 1, right);
		} else {
			return search(nums, k, left, largestLessThanGuess);
		}
	}
}
