package airbnb2019;

import java.util.LinkedList;
import java.util.Queue;

public class FindOcean {
	public static void main(String args[]) {
		char[][] graph = { { 'W', 'W', 'W', 'L', 'L', 'L', 'W' },
				{ 'W', 'W', 'L', 'L', 'L', 'W', 'W' },
				{ 'W', 'L', 'L', 'L', 'L', 'W', 'W' }, };
		markOcean(graph, 0, 1, 'W', 'O');
		for (int i = 0; i < graph.length; i++) {
			System.out.println();
			for (int j = 0; j < graph[0].length; j++) {
				System.out.print(graph[i][j]);
				System.out.print(",");
			}
		}
	}

	static int[][] dir = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
	private static void markOcean(char[][] graph, int i, int j, char water,
			char ocean) {
		if (graph == null || graph.length == 0 || graph[0].length == 0) {
			return;
		}
		int m = graph.length;
		int n = graph[0].length;
		Queue<Integer> queue = new LinkedList<Integer>();
		queue.add(i * n + j);
		while (!queue.isEmpty()) {
			int cur = queue.poll();
			for (int k = 0 ; k < 4 ; k ++) {
				int x = cur / n + dir[k][0];
				int y = cur % n + dir[k][1];
				if (!inBound(x, y, m, n) || graph[x][y] != water) continue;
				graph[x][y] = ocean;
				queue.add(x * n + y);
			}
		}

	}
	
	private static boolean inBound(int x, int y, int m, int n) {
		return x >= 0 && x < m && y >= 0 && y < n;
		}
}
