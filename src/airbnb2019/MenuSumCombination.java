package airbnb2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MenuSumCombination {
	private static final double eps = 1.0E-1;

	public static void main(String args[]) {
		double[] prices = { 2.40, 0.01, 6.00, 2.58 };
		List<List<Double>> result = getCombos(prices, 2.50);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(i + "th result:");
			for (int j = 0; j < result.get(i).size(); j++) {
				System.out.println(result.get(i).get(j));
			}
		}
	}

	private static List<List<Double>> getCombos(double[] prices, double target) {
		List<List<Double>> result = new ArrayList<List<Double>>();
		if (prices == null || prices.length == 0 || target <= 0) {
			return result;
		}
		Arrays.sort(prices);
		dfs(prices, 0, target, new ArrayList<Double>(), result);
		return result;
	}

	private static void dfs(double[] prices, int index, double target,
			List<Double> combo, List<List<Double>> result) {
		if (Math.abs(target) < eps) {
			result.add(new ArrayList<Double>(combo));
			return;
		}

		for (int i = index; i < prices.length; i++) {
			if (i != index && prices[i] == prices[i - 1])
				continue;
			if (prices[i] - target > eps)
				break;
			combo.add(prices[i]);
			dfs(prices, i + 1, target - prices[i], combo, result);
			combo.remove(combo.size() - 1);
		}
	}
}
