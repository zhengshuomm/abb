package airbnb2019;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class RoundPrice {
	public static void main(String args[]) {
		double[] input = new double[] {1.2, 2.3, 3.4};
		System.out.println(Arrays.toString(roundUp(input)));
	}

	static class NumWithDiff {
		int num;
		double diffWithCeil;

		public NumWithDiff(int n, double c) {
			this.num = n;
			this.diffWithCeil = c;
		}
	}

	public static int[] roundUp(double[] input) {
		int n = input.length;
		NumWithDiff[] arr = new NumWithDiff[n];
		double sum = 0.0;
		int floorSum = 0;
		int i = 0;
		for (double num : input) {
			int floor = (int) num;
			floorSum += floor;
			sum += num;
			int ceil = floor;
			if (ceil < num)
				ceil++;
			arr[i] = new NumWithDiff(ceil, ceil - num);
			i++;
		}
		
		Arrays.sort(arr, new Comparator<NumWithDiff>() {
			public int compare(NumWithDiff n1, NumWithDiff n2) {
				if (n1.diffWithCeil <= n2.diffWithCeil) return -1;
				else return 1;
			}
		});
		
		int[] result = new int[n];
		int diff = (int )Math.round(sum) - floorSum;
		i = 0;
		for (; i < diff ; i ++) {
			result[i] = arr[i].num;
		}
		for (; i < n ;i  ++) {
			result[i] = arr[i].num - 1;
		}
		return result;
	}
}
