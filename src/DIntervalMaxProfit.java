import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class DIntervalMaxProfit {
	public static void main(String[] args) {
		List<List<String>> input = new ArrayList<>();
		List<String> i1 = Arrays.asList("0-2", "10");
		List<String> i2 = Arrays.asList("1-3", "20");
		List<String> i3 = Arrays.asList("2-4", "5");
		input.add(i1);
		input.add(i2);
		input.add(i3);
		DIntervalMaxProfit i = new DIntervalMaxProfit();
		System.out.println(i.findMaxProfit(input));
	}

	public int findMaxProfit(List<List<String>> input) {
		if (input == null || input.size() == 0)
			return 0;
		List<Interval> orders = new ArrayList<>();
		for (List<String> list : input) {
			String[] time = list.get(0).split("-");
			int start = Integer.parseInt(time[0]);
			int end = Integer.parseInt(time[1]);
			int profit = Integer.parseInt(list.get(1));
			orders.add(new Interval(start, end, profit));
		}
		Collections.sort(orders, new Comparator<Interval>() {
			public int compare(Interval o1, Interval o2) {
				return Integer.compare(o1.start, o2.start);
			}
		});

		System.out.println(orders);
		// DP => look back all results
		int[] M = new int[orders.size()];
		M[0] = orders.get(0).profit;
		int global = M[0];
		for (int i = 1; i < orders.size(); i++) {
			int max = orders.get(i).profit;
			for (int j = 0; j < i; j++) {
				if (orders.get(j).end <= orders.get(i).start) { // if no overlap
					max = Math.max(max, M[j] + orders.get(i).profit);
				}
			}
			M[i] = max;
			global = Math.max(global, max);
		}
		return global;
	}

	class Interval {
		int start;
		int end;
		int profit;

		public Interval(int start, int end, int profit) {
			this.start = start;
			this.end = end;
			this.profit = profit;
		}
	}
}
