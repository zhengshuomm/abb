package airbnb2019;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class SlidingPuzzle {
	public static void main(String args[]) {
		int[][] board = { { 4, 1, 2 }, { 5, 0, 3 } };
		int[][] board2 = { { 1, 2, 3 }, { 4, 5, 0 } };
		System.out.println(slidingGame(board, board2));
	}

	private static int[][] dir = { { 0, 1 }, { 0, -1 }, { 1, 0 }, { -1, 0 } };

	public static int slidingGame(int[][] board, int[][] board2) {
		if (board == null || board.length == 0 || board[0].length == 0) {
			return -1;
		}
		int n = board.length;
		int m = board[0].length;
		String start = "";
		String target = "";
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				start += board[i][j];
				target += board2[i][j];
			}
		}

		Set<String> set = new HashSet<String>();
		Queue<String> queue = new LinkedList<String>();
		set.add(start);
		queue.offer(start);
		int steps = -1;
		while (!queue.isEmpty()) {
			steps++;
			int size = queue.size();
			for (int i = 0; i < size; i++) {
				String cur = queue.poll();
				if (cur.equals(target)) {
					return steps;
				}

				int index = cur.indexOf('0');
				int x = index / m;
				int y = index % m;
				for (int j = 0; j < 4; j++) {
					int nx = x + dir[j][0];
					int ny = y + dir[j][1];
					if (!isValid(nx, ny, n, m))
						continue;
					String next = swap(cur, index, nx * m + ny);
					if (set.add(next)) {
						queue.offer(next);
					}
				}
			}
		}
		return -1;
	}

	private static String swap(String s, int i, int j) {
		char[] arr = s.toCharArray();
		char temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
		return new String(arr);
	}

	private static boolean isValid(int x, int y, int n, int m) {
		return x >= 0 && x < n && y >= 0 && y < m;
	}

}
