package airbnb2019;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class CheapestFlightWithinKStops {
	public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
		Map<Integer, Map<Integer, Integer>> map = new HashMap<Integer, Map<Integer, Integer>>();
		for (int i = 0 ; i < n ; i ++) map.put(i, new HashMap<Integer, Integer>());
		Queue<int[]> queue = new PriorityQueue<int[]>(K, new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		
		for (int[] flight : flights) {
			map.get(flight[0]).put(flight[1], flight[2]);
		}
		Set<Integer> set = new HashSet<Integer>();
		queue.add(new int[]{0, src, -1});
		while (!queue.isEmpty()) {
			int[] temp = queue.poll();
			int cost = temp[0], vetex = temp[1], stop = temp[2]; 
			if (vetex == dst) return cost;
			set.add(vetex);
			if (stop < K) {
				Map<Integer, Integer> hs = map.get(vetex);
				for (int h : hs.keySet()) {
					if (!set.contains(h)) {
						queue.add(new int[]{cost + hs.get(h), h, stop + 1});
					}
				}
			}
		}
		return -1;
		
	}
}
