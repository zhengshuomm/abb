package airbnb2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PalindromPairs {
	List<List<Integer>> res = new ArrayList<>();
	TrieNode root = new TrieNode();

	private class TrieNode {
		TrieNode[] children;
		int index;
		List<Integer> list;

		TrieNode() {
			children = new TrieNode[26];
			index = -1;
			list = new ArrayList<>();
		}
	}
	
	public static void main(String[] args) {
		PalindromPairs p = new PalindromPairs();
		String[] words = {"lls","s"};
		System.out.println(p.palindromePairs(words));
	}

	public List<List<Integer>> palindromePairs(String[] words) {

		for (int i = 0; i < words.length; i++) {
			insert(words[i], i);
		}

		for (int i = 0; i < words.length; i++) {
			search(words[i], i);
		}

		return res;
	}

	private void insert(String word, int index) {
		TrieNode trie = root;
		for (int i = word.length() - 1; i >= 0; i--) {
			int j = word.charAt(i) - 'a';

			if (trie.children[j] == null) {
				trie.children[j] = new TrieNode();
			}

			if (isPalindrome(word, 0, i)) {
				trie.list.add(index);
			}

			trie = trie.children[j];
		}

		trie.list.add(index); // 刚好有一个词跟这个词全对称
 		trie.index = index;  // used to determine words palindrom not equals to it self
	}

	private void search(String word, int i) {
		TrieNode trie = root;
		for (int j = 0; j < word.length(); j++) { // handles when other part is
													// shorter than words[i]
			if (trie.index >= 0 && trie.index != i
					&& isPalindrome(word, j, word.length() - 1)) {
				res.add(Arrays.asList(i, trie.index));
			}

			trie = trie.children[word.charAt(j) - 'a'];
			if (trie == null)
				return; // important!!!
		}

		for (int j : trie.list) { // handles when other part is longer than words[i]
//			System.out.println(trie.list);
			if (i == j)
				continue;
			res.add(Arrays.asList(i, j));
		}
	}

	private boolean isPalindrome(String word, int i, int j) {
		while (i < j) {
			if (word.charAt(i++) != word.charAt(j--))
				return false;
		}

		return true;
	}
}