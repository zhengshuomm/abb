package airbnb2019;

import java.util.ArrayList;
import java.util.List;

public class KEditDistance {
	private TrieNode root;
	private String target;
	private int k;
	private int n;

	public List<String> kDistance(String[] words, String target, int k) {
		// write your code here
		List<String> result = new ArrayList<String>();
		for (String word : words) {
			insert(word);
		}
		root = new TrieNode();
		int[] dp = new int[target.length() + 1];
		for (int i = 0; i < dp.length; i++) {
			dp[i] = i;
		}
		search(root, dp, result);
		return result;

	}

	private void search(TrieNode node, int[] prevDp, List<String> result) {
		if (node.word != null && prevDp[n] <= k) {
			result.add(node.word);
		}
		for (int i = 0; i < 26; i++) {
			if (node.map[i] == null)
				continue;
			int[] curDp = new int[n + 1];
			curDp[0] = prevDp[0] + 1;
			for (int j = 1; j < prevDp.length; j++) {
				if (target.charAt(j - 1) == (char) (i + 'a')) {
					curDp[j] = prevDp[j - 1];
				} else {
					curDp[j] = Math.min(Math.min(prevDp[j - 1], prevDp[j]),
							curDp[j - 1]) + 1;
				}
			}
			search(node.map[i], curDp, result);
		}

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

}

class TrieNode {
	TrieNode[] map;
	String word;

	public TrieNode() {
		this.map = new TrieNode[26];
	}
}