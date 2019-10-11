package airbnb2019;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.TreeSet;

public class EditReview {
	public static void main(String[] args) {
		String str = "I love airbnb and, I live in Santa Clara, and airbnb";
		Map<String, String> map = new HashMap<>();
		map.put("airbnb", "business");
		map.put("Santa Clara", "city");
		map.put("Clara", "haha");
		String[] strs = str.split(" ");
//		String[] strs = str.split(",| ");
//		System.out.println(Arrays.toString(strs));
		EditReview e = new EditReview();
		System.out.println(e.tagReplace(strs, map));
	}
	
	// this solution could not handle the case like comma, dot
	public String tagReplace(String[] strs, Map<String, String> map) {
		// Solution 1, HashMap
		Map<String, TreeSet<Integer>> startMap = new HashMap<>();
		for (String key : map.keySet()) {
			String[] keys = key.split(" ");
			startMap.putIfAbsent(keys[0], new TreeSet<>());
			startMap.get(keys[0]).add(keys.length);
		}
		
		StringBuilder result = new StringBuilder();
		for (int i = 0 ; i < strs.length ;  i ++) {
			String str = strs[i];
			if (str.equals("")) continue;
			if (startMap.containsKey(str)) {
				boolean found = false;
				StringBuilder sb = new StringBuilder(str);
				int lastPos = 1;
				for (int pos : startMap.get(str)) {
					// get the string and check if it's in the map.
					for (int k = lastPos ; k < pos && i + k < strs.length; k ++) {
						sb.append(" ").append(strs[i + k]);
					}
					lastPos = pos ;
					if (map.containsKey(sb.toString())) {
						result.append("[").append(map.get(sb.toString())).append("]");
						found = true;
						break;
					}
				}
				System.out.println(found);
				if (found) {
					result.append("{").append(sb.toString()).append("}").append(" ");
					i = i + lastPos - 1 ;
				} else {
					result.append(str).append(" ");
				}
			} else {
				result.append(str).append(" ");
			}
		}
		return result.toString();
	}
}
