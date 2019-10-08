package airbnb2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListIterator implements Iterator<Integer> {

	public static void main(String args[]) {
		List<Integer> list1 = new ArrayList<Integer>(Arrays.asList(1, 2));
		List<Integer> list2 = new ArrayList<Integer>(Arrays.asList(3));
		List<List<Integer>> vec2d = new ArrayList<List<Integer>>();
		vec2d.add(list1);
		vec2d.add(list2);
		// 1,2
		// 3
		ListIterator myIter = new ListIterator(vec2d);
		System.out.println(myIter.hasNext());
		System.out.println(myIter.next());
		System.out.println(myIter.hasNext());
		myIter.remove();
		System.out.println(myIter.next());
		for (int i = 0; i < vec2d.size(); i++) {
			if (vec2d.get(i) == null)
				continue;
			for (int j = 0; j < vec2d.get(i).size(); j++) {
				System.out.println("List" + i + ":" + vec2d.get(i).get(j));
			}
		}
		System.out.println(myIter.hasNext());
		System.out.println(myIter.next());
	}
	
	private Iterator<List<Integer>> all;
	private Iterator<Integer> cur;
	
	public ListIterator(List<List<Integer>> vec2d) {
		this.all = vec2d.iterator();
		this.cur =  null;
	}

	@Override
	public boolean hasNext() {
		while ((cur == null || !cur.hasNext()) && all.hasNext()) {
			cur = all.next().iterator();
		}
		return cur != null && cur.hasNext();
	}

	@Override
	public Integer next() {
		if (hasNext()) {
			return cur.next();
		}
		return null;
	}

	@Override
	public void remove() {
		if (cur == null && all.hasNext()) {
			cur  = all.next().iterator();
		}
		if (cur != null) {
			cur.remove();
		}

	}
}
