package airbnb2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DisplayPage {

	public static void main(String[] args) {
		List<String> input = new ArrayList<String>();
		input.add("1,28,310.6,SF");
		input.add("4,5,204.1,SF");
		input.add("20,7,203.2,Oakland");
		input.add("6,8,202.2,SF");
		input.add("6,10,199.1,SF");
		input.add("1,16,190.4,SF");
		input.add("6,29,185.2,SF");
		input.add("7,20,180.1,SF");
		input.add("6,21,162.1,SF");
		input.add("2,18,161.2,SF");
		input.add("2,30,149.1,SF");
		input.add("3,76,146.2,SF");
		input.add("2,14,141.1,San Jose");
		List<String> result = displayPages(input, 5);
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i));
		}
	}

	public static List<String> displayPages(List<String> input, int pageSize) {
		List<String> result = new ArrayList<String>();
		Map<String, Set<String>> map = new HashMap<String, Set<String>>();
		Set<String> set = new HashSet<String>();
		for (String record : input) {
			set.add(record);

			String hostId = record.split(",")[0];
			if (!map.containsKey(hostId)) {
				map.put(hostId, new LinkedHashSet<String>());
			}
			map.get(hostId).add(record);
		}

		int count = 0;
		while (!set.isEmpty()) {
//			if (result.isEmpty()) {
//				result.add("");
//			}

			for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
				if (entry.getValue().size() == 0)
					continue;
				if (count < pageSize) {
					String record = entry.getValue().iterator().next();
					result.add(record);
					count++;
					sync(set, map, record);
				} else {
					break;
				}
			}
			Iterator<String> it = set.iterator();
			while (count < pageSize && !set.isEmpty()) {
				String record = it.next();
				result.add(record);
				sync(set, map, record);
				count++;
			}
			count = 0;
		}
		return result;

	}

	private static void sync(Set<String> set, Map<String, Set<String>> map,
			String del) {
		String hostId = del.split(",")[0];
		set.remove(del);
		map.get(hostId).remove(del);
	}
}
