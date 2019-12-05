import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class AFileSystemZookeeper {
	public static void main(String[] args) {
		AFileSystemZookeeper fileSystem = new AFileSystemZookeeper();
//		System.out.println(fileSystem.create("a", 1));
		System.out.println(fileSystem.create("/a", 1));
		System.out.println(fileSystem.create("/a/b", 2));
		System.out.println(fileSystem.create("/c/b", 3));
		fileSystem.watch("/a/b", new CallbackImpl());
		fileSystem.watch("/a", new CallbackImpl());
		fileSystem.watch("/", new CallbackImpl());
		System.out.println(fileSystem.set("/a/b", 1));
		System.out.println(fileSystem.create("/a/b/c", 2));
		System.out.println(fileSystem.get("/a/b/c"));
		System.out.println(fileSystem.get("/a/b/c/d"));
	}

	TrieNode root;

	interface Callback {
		public void apply(String path, int value);
	}
	
	static class CallbackImpl implements Callback {

		@Override
		public void apply(String path, int value) {
			System.out.println(path + "\t" + value);
		}
	}

	class TrieNode {
		Map<String, TrieNode> map;
		int value;
		Callback callback;

		public TrieNode() {
			map = new HashMap<>();
		}
	}

	public AFileSystemZookeeper() {
		root = new TrieNode();
	}

	public boolean set(String path, Integer value) {
		TrieNode node = root;
		String[] paths = path.split("/");
		for (int i = 1; i < paths.length ; i++) {
			if (!node.map.containsKey(paths[i])) {
				return false; 
			}
			node = node.map.get(paths[i]);
//			System.out.println(1);
			if (node.callback != null) {
				node.callback.apply(path, value);
			}
		}
		node.value = value;
		return true;
	}

	private Integer get(String path) {
		TrieNode node = root;
		String[] paths = path.split("/");
		for (int i = 1; i < paths.length ; i++) {
			if (!node.map.containsKey(paths[i])) {
				return null; 
			}
			node = node.map.get(paths[i]);
		}
		return node.value;
	}

	private boolean create(String path, int value) {
		TrieNode node = root;
		String[] paths = path.split("/");
		for (int i = 1; i < paths.length - 1; i++) {
			if (!node.map.containsKey(paths[i])) {
				return false; // no prefix path
			}
			node = node.map.get(paths[i]);
		}
//		node = node.map.get(paths[paths.length - 1]);
		if (node.map.get(paths[paths.length - 1]) != null) {
			return false; // path already exist;
		}
		TrieNode n = new TrieNode();
		n.value = value;
		node.map.put(paths[paths.length - 1], n);
		return true;

	}

	private void watch(String path, Callback callback) {
		TrieNode node = root;
		String[] paths = path.split("/");
		for (int i = 1; i < paths.length - 1; i++) {
			if (!node.map.containsKey(paths[i])) {
				return; // no prefix path
			}
			node = node.map.get(paths[i]);
		}
		node.callback = callback;
	}
}
