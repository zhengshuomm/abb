package airbnb2019;

import java.util.ArrayList;
import java.util.List;

public class BoggleGame {
	private int n;
	private int m;
	private final int[] dirX = { 0, 0, 1, -1 };
	private final int[] dirY = { 1, -1, 0, 0 };
	private TrieNode root;
	private int max = 0;

	public int boggleGame(char[][] board, String[] words) {
		// write your code here
		if (board == null || board.length == 0 || board[0].length == 0
				|| words == null || words.length == 0) {
			return this.max;
		}
		this.n = board.length;
		this.m = board[0].length;
		this.root = new TrieNode();
		for (String word : words) {
			insert(word);
		}

		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				search(board, i, j, this.root, new ArrayList<String>(),
						new boolean[this.n][this.m]);
			}
		}
		return this.max;
	}

	private void search(char[][] board, int i, int j, TrieNode node,
			List<String> list, boolean[][] visited) {
		if (!inBound(i, j) || visited[i][j] || node.map[board[i][j] - 'a'] == null ) return;
		node = node.map[board[i][j] - 'a'];
		visited[i][j] = true;
		if (node.word != null) {
			list.add(node.word);
			this.max = Math.max(max, list.size());
			
			for (int x = 0; i < n; i++) {
				for (int y = 0; j < m; j++) {
					search(board, x, y, this.root, new ArrayList<String>(),
							new boolean[this.n][this.m]);
				}
			}
			// 没有必要继续搜索其他单词了
			visited[i][j] = false;
			return;
		}
		for (int k = 0 ; k < 4 ; k ++) {
			int nx = i + dirX[k];
			int ny = j + dirY[k];
			search(board, nx, ny, node, list, visited);
		}
		visited[i][j] = false;
	}
	
	private boolean inBound(int x, int y) {
		return x >= 0 && x < this.n && y >= 0 && y < this.m;
		}

	private void insert(String word) {
		TrieNode node = this.root;
		for (char c : word.toCharArray()) {
			if (node.map[c - 'a'] == null) {
				node.map[c - 'a'] = new TrieNode();
			}
			node = node.map[c - 'a'];
		}
		node.word = word;
	}

	class TrieNode {
		TrieNode[] map;
		String word;

		public TrieNode() {
			this.map = new TrieNode[26];
		}
	}
}
