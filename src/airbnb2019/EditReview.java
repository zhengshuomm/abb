package airbnb2019;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class EditReview {
	public static void main(String[] args) {
		String str = "I love airbnb, and, I live in Santa Clara, and airbnb";
		Map<String, String> map = new HashMap<>();
		map.put("airbnb", "business yiwenluo");
		map.put("Santa Clara", "city");
		map.put("Clara", "haha");
		map.put("business", "aa");
		String[] strs = str.split(" ");
//		String[] strs = str.split(",| ");
//		System.out.println(Arrays.toString(strs));
		EditReview e = new EditReview();
		System.out.println(str);
//		System.out.println(e.tagReplace(strs, map));
		System.out.println(e.tagReplace(str, map));
//		System.out.println(Character.isLetter(','));
	}
	
	// this solution could not handle the case like comma, dot
	public String tagReplace(String[] strs, Map<String, String> map) {
		// Solution 1, HashMap
		Map<String, TreeSet<Integer>> startMap = new HashMap<>();
		for (String key : map.keySet()) {
			String[] keys = key.split(" ");
			startMap.putIfAbsent(keys[0], new TreeSet<Integer>());
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
				char c = '\u0000';
				for (int pos : startMap.get(str)) {
					// get the string and check if it's in the map.
					for (int k = lastPos ; k < pos && i + k < strs.length; k ++) {
						sb.append(" ").append(strs[i + k]);
					}
					lastPos = pos ;
//					System.out.println(sb.toString());
					if (!Character.isLetter(sb.charAt(sb.length() - 1))) {
						c = sb.charAt(sb.length() - 1);
						if (map.containsKey(sb.deleteCharAt(sb.length() - 1).toString())) {
							result.append("[").append(map.get(sb.toString())).append("]");
							found = true;
						}
						break;
					}
					if (map.containsKey(sb.toString())) {
						result.append("[").append(map.get(sb.toString())).append("]");
						found = true;
						break;
					}
				}
				if (found) {
					result.append("{").append(sb.toString()).append("}").append(c).append(" ");
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
	
	// Seems to be a reasonable solution.
	public String tagReplace(String str, Map<String, String> map) {
		List<String> list = new ArrayList<>(map.keySet());
		Collections.sort(list, new Comparator<String>() {
			public int compare(String s1, String s2) {
				return s2.split(" ").length - s1.split(" ").length;
			}
		});
		
		for (String key : list) {
			str = replace(str, key, map.get(key));
		}
		return str;
	}
	
	private String replace(String str, String oldS, String newS) {
		StringBuilder sb = new StringBuilder();
		boolean inQuote = false;
		for (int i = 0 ; i < str.length();  i ++) {
			char c = str.charAt(i);
			if (inQuote) {
				if (c == ']') {
					inQuote = false;
				}
				sb.append(c);
			} else {
				if (c == '[') {
					inQuote = true;
				} 
				else if (i > 0 && str.charAt( i - 1) == ' ' && str.startsWith(oldS, i)) {
						sb.append("[").append(newS).append("]").append("{").append(oldS).append("}");
						i += oldS.length() - 1;
						continue;
				}
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
