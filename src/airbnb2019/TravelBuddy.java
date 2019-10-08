package airbnb2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TravelBuddy {
	public static void main(String args[]) {
		Set<String> myList = new HashSet<String>(Arrays.asList("A", "B", "C",
				"D"));
		Set<String> peter = new HashSet<String>(Arrays.asList("A", "B", "E",
				"F"));
		Set<String> john = new HashSet<String>(
				Arrays.asList("A", "B", "D", "G"));
		Set<String> casy = new HashSet<String>(Arrays.asList("X", "B", "A",
				"D", "Q"));
		Set<String> jason = new HashSet<String>(Arrays.asList("A", "B", "C",
				"D", "P", "Q"));
		Set<String> ken = new HashSet<String>(Arrays.asList("A", "X", "Y", "Z"));
		Map<String, Set<String>> friendLists = new HashMap<String, Set<String>>();
		friendLists.put("peter", peter);
		friendLists.put("john", john);
		friendLists.put("casy", casy);
		friendLists.put("jason", jason);
		friendLists.put("ken", ken);
		TravelBuddy solution = new TravelBuddy(myList, friendLists);
		List<Buddy> buddies = solution.findBuddies();
		for (int i = 0; i < buddies.size(); i++) {
			Buddy b = buddies.get(i);
			System.out.println("Name: " + b.name + " sim: " + b.sim);
		}
		Set<String> cities = solution.recommend(5);
		for (String city : cities) {
			System.out.println(city);
		}
	}

	List<Buddy> buddies;
	Set<String> myList;
	Map<String, Set<String>> friendLists;

	public TravelBuddy(Set<String> myList, Map<String, Set<String>> friendLists) {
		this.myList = myList;
		this.friendLists = friendLists;
	}

	public List<Buddy> findBuddies() {
		List<Buddy> buddies = new ArrayList<Buddy>();
		for (Map.Entry<String, Set<String>> entry : friendLists.entrySet()) {
			Set<String> common = new HashSet<String>(myList);
			String name = entry.getKey();
			Set<String> wishList = entry.getValue();
			common.retainAll(wishList);
			if (common.size() >= myList.size() / 2) {
				buddies.add(new Buddy(name, common.size(), wishList));
			}
		}
		Collections.sort(buddies);
		this.buddies = buddies;
		return buddies;
	}

	public Set<String> recommend(int k) {
		Set<String> result = new LinkedHashSet<String>();
		for (Buddy buddy : buddies) {
			Set<String> diff = new HashSet<String>(buddy.list);
			diff.removeAll(myList);
			for (String city : diff) {
				if (result.size() < k) {
					result.add(city);
				} else {
					return result;
				}
			}
		}
		return result;
	}

	class Buddy implements Comparable<Buddy> {
		String name;
		int sim;
		Set<String> list;

		public Buddy(String name, int sim, Set<String> list) {
			this.name = name;
			this.sim = sim;
			this.list = list;
		}

		@Override
		public int compareTo(Buddy o) {
			return o.sim - this.sim;
		}
	}
}
