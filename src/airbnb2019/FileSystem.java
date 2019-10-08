package airbnb2019;

import java.util.HashMap;
import java.util.Map;

public class FileSystem {
	public static void main(String args[]) {
		FileSystem solution = new FileSystem();
		solution.create("/a", 1);
		System.out.println(solution.get("/a"));
		solution.create("/a/b", 2);
		System.out.println(solution.get("/a/b"));
		solution.create("/c/d", 3);
		System.out.println(solution.get("/c"));
		solution.set("/a/b", 4);
		System.out.println(solution.get("/a/b"));
		solution.watch("/a", "/a call back triggerred");
		solution.watch("/a/b", "/a/b call back triggerred");
		solution.set("/d", 5);
		solution.create("/a/b/c", 10);
		solution.set("/a/b/c", 11);
	}

	private Map<String, Integer> pathMap;
	private Map<String, Runnable> callbackMap;
	
	public FileSystem() {
		this.pathMap = new HashMap<String, Integer>();
		// this is important
		pathMap.put("", 0);
		this.callbackMap = new HashMap<String, Runnable>();
	}

	public boolean create(String key, Integer value) {
		if (pathMap.containsKey(key))
			return false;
		String prefix = key.substring(0, key.lastIndexOf("/"));
		if (!pathMap.containsKey(prefix))
			return false;
		pathMap.put(key, value);
		return true;
	}

	public Integer get(String key) {
		return pathMap.get(key);
	}

	public boolean set(String key, Integer value) {
		if (!pathMap.containsKey(key))
			return false;
		pathMap.put(key, value);
		String cur = key;
		while (cur.length() > 0) {
			if (callbackMap.containsKey(cur)) {
				callbackMap.get(cur).run();
			}
			cur = cur.substring(0, cur.lastIndexOf("/"));
		}
		return true;
	}

	public void watch(String path, final String alert) {
		Runnable runnable = new Runnable() {
			public void run() {
				System.out.println(alert);
			}
		};
		callbackMap.put(path, runnable);
	}

}
