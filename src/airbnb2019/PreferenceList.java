package airbnb2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class PreferenceList {
	public static void main(String args[]) {
		List<Integer> list1 = new ArrayList<>(Arrays.asList(3, 5, 7, 9));
		List<Integer> list2 = new ArrayList<>(Arrays.asList(2, 3, 8));
		List<Integer> list3 = new ArrayList<>(Arrays.asList(5, 8));
		List<List<Integer>> input = new ArrayList<>();
		input.add(list1);
		input.add(list2);
		input.add(list3);
		List<Integer> result = getPreference(input);
		for (int i = 0; i < result.size(); i++) {
			System.out.print(result.get(i) + ",");
		}
	}

	private static List<Integer> getPreference(List<List<Integer>> prefers) {
		List<Integer> result = new ArrayList<Integer>();
		Map<Integer, Set<Integer>> map = new HashMap<>();
		Map<Integer, Integer> inDegree = new HashMap<>();
		for (List<Integer> prefer : prefers) {
			for (int i = 0; i < prefer.size() - 1; i++) {
				int from = prefer.get(i);
				int to = prefer.get(i + 1);
				if (!inDegree.containsKey(from)) {
					inDegree.put(from, 0);
				}
				int num = inDegree.get(to) == null ? 1 : inDegree.get(to) + 1;
				inDegree.put(to, num);
				if (!map.containsKey(from)) {
					map.put(from, new HashSet<Integer>());
				}
				map.get(from).add(to);
			}
		}
		
		Queue<Integer> queue = new LinkedList<>();
		for (int n : inDegree.keySet()) {
			if (inDegree.get(n) == 0) {
				queue.add(n);
			}
		}
		
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			result.add(cur);
			if (!map.containsKey(cur)) continue;
			for (int neighbor : map.get(cur)) {
				inDegree.put(neighbor, inDegree.get(neighbor) - 1);
				if (inDegree.get(neighbor) == 0) {
					queue.add(neighbor);
				}
			}
		}
		return result;
	}
}
