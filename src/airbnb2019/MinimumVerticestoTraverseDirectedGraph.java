package airbnb2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class MinimumVerticestoTraverseDirectedGraph {
	public static void main(String args[]) {
		int[][] edges = { { 2, 9 }, { 3, 3 }, { 3, 5 }, { 3, 7 }, { 4, 8 },
				{ 5, 8 }, { 6, 6 }, { 7, 4 }, { 8, 7 }, { 9, 3 }, { 9, 6 } };
		List<Integer> result = getMin(edges, 10);
		for (int num : result) {
			System.out.println(num);
		}
	}

//	public static List<Integer> getMin(int[][] edges, int n) {
//		List<Integer> result = new ArrayList<>();
//		boolean[] visited = new boolean[n];
//		Map<Integer, List<Integer>> map = new HashMap<>();
//		int[] inDegree = new int[n];
//		for (int[] edge : edges) {
//			if (!map.containsKey(edge[0])) {
//				map.put(edge[0], new ArrayList<Integer>());
//			}
//			map.get(edge[0]).add(edge[1]);
//			inDegree[edge[1]]++;
//		}
//		for (int i = 0; i < n; i++) {
//			if (inDegree[i] == 0) {
//				result.add(i);
//				dfs(i, map, visited);
//			}
//		}
//		for (int i = 0; i < n; i++) {
//			if (!visited[i]) {
//				result.add(i);
//				dfs(i, map, visited);
//			}
//		}
//		return result;
//	}
//
//	private static void dfs(int crt, Map<Integer, List<Integer>> map,
//			boolean[] visited) {
//		visited[crt] = true;
//		if (map.containsKey(crt)) {
//			for (int next : map.get(crt)) {
//				if (visited[next]) {
//					continue;
//				}
//				dfs(next, map, visited);
//			}
//		}
//	}

	private static List<Integer> getMin(int[][] edges, int n) {
		Map<Integer, Set<Integer>> nodes = new HashMap<>();
		for (int i = 0; i < n; i++) {
			nodes.put(i, new HashSet<Integer>());
		}
		for (int[] edge : edges) {
			nodes.get(edge[0]).add(edge[1]);
		}
		Set<Integer> visited = new HashSet<>();
		Set<Integer> res = new HashSet<>();
		for (int i = 0; i < n; i++) {
			if (!visited.contains(i)) {
				res.add(i);
				search(res, nodes, i, i, visited, new HashSet<Integer>());
			}
		}
		return new ArrayList<>(res);
	}

	private static void search(Set<Integer> res,
			Map<Integer, Set<Integer>> nodes, int cur, int start,
			Set<Integer> visited, Set<Integer> curVisited) {
		visited.add(cur);
		curVisited.add(cur);
		for (int next : nodes.get(cur)) {
			if (res.contains(next) && next != start) {
				res.remove(next);
				continue;
			}
			if (!curVisited.contains(next)) {
				search(res, nodes, next, start, visited, curVisited);
			}
		}

	}
}
