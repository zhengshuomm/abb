package airbnb2019;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class TenWizard {
	public static void main(String args[]) {
		List<List<Integer>> wizards = new ArrayList<>();
		for (int i = 0; i < 5; i++) {
			List<Integer> list = new ArrayList<>();
			if (i == 0) {
				list.add(1);
				list.add(2);
			} else if (i == 1) {
				list.add(3);
			} else if (i == 2) {
				list.add(3);
				list.add(4);
			} else if (i == 3) {
				list.add(4);
			}
			wizards.add(list);
		}
		List<Integer> path = getShortestPath(wizards, 0, 4);
		for (int i = 0; i < path.size(); i++) {
			System.out.println(path.get(i));
		}
	}

	public static List<Integer> getShortestPath(List<List<Integer>> wizards,
			int source, int target) {
		List<Integer> path = new ArrayList<>();
		if (wizards == null || wizards.size() == 0) {
			return path;
		}
		int n = wizards.size();
		PriorityQueue<Route> pq = new PriorityQueue<Route>(n,
				new Comparator<Route>() {
					public int compare(Route r1, Route r2) {
						return r1.cost - r2.cost;
					}
				});
		Route[] from = new Route[n];
		pq.add(new Route(source, source, 0));
		while (!pq.isEmpty()) {
			Route route = pq.poll();
			// if (from[route.wizard] != null) {
			// continue;
			// }
			from[route.wizard] = route;
			if (route.wizard == target) {
				getPath(from, source, target, path);
				return path;
			}
			for (int next : wizards.get(route.wizard)) {
				if (from[next] == null) {
					pq.offer(new Route(next, route.wizard, route.cost
							+ (route.wizard - next) * (route.wizard - next)));
				}
			}
		}
		return path;
	}

	private static void getPath(Route[] from, int source, int target,
			List<Integer> path) {
		int wizard = target;
		while (wizard != source) {
			path.add(wizard);
			wizard = from[wizard].fromWizard;
		}
		path.add(source);
		Collections.reverse(path);
	}
}

class Route {
	int wizard;
	int fromWizard;
	int cost;

	public Route(int wizard, int fromWizard, int cost) {
		this.wizard = wizard;
		this.fromWizard = fromWizard;
		this.cost = cost;
	}
}
