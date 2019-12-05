import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class ShutBox2 {

	Node root;
	
	public static void main(String[] args) {
		ShutBox2 s = new ShutBox2();
		s.startGame();
//		System.out.println(s.root.score);
//		for (Entry<Integer, Map<Integer, Node>> m : s.root.map.entrySet()) {
//			System.out.println(m.getKey());
//			for (Map.Entry<Integer, Node> n : m.getValue().entrySet()) {
//				System.out.println(n.getKey() + "\t" + n.getValue().score);
//			}
//		}
	}
	
	public void startGame() {
		this.root = new Node(Arrays.asList(1,2,3,4,5,6,7));	
//		System.out.println(root.available);
		dfs(root, 0);
		System.out.println(root.score);
//		List<List<Integer>> res = getPossibleSolutions(Arrays.asList(), 9);
//		if (res.isEmpty()) {
//			System.out.println("yes");
//		}
//		System.out.println(res);
	} 
	
	private void dfs(Node node, int count) {
		for (int i = 2 ; i <= 12 ; i ++) {
			List<List<Integer>> availables = getPossibleSolutions(node.available, i);
//			System.out.println(count);
			
			if (availables.isEmpty()) {
				// compute score
				Node n = new Node(node.available);
				n.score = getScore(node.available);
				node.map.get(i).put(node.available, n);
//				System.out.println(n.score);
				continue;
			}
			for (List<Integer> available : availables) {
				node.map.get(i).put(available, new Node(available));
			}
			for (Node n : node.map.get(i).values()) {
				dfs(n, count + 1);
			}
		}
		
		double score = 0;
		for (int i = 2 ; i <= 12 ; i ++) {
			Map<List<Integer>, Node> nMap = node.map.get(i);
			double min = Integer.MAX_VALUE;
			List<Integer> minNum = null;
			Node minNode = null;
			for (List<Integer>  n: nMap.keySet()) {
				if (nMap.get(n).score < min) {
					min = nMap.get(n).score;
					minNum = n;
					minNode = nMap.get(n);
				}
			}
//			System.out.println(min);
			nMap.clear();
			nMap.put(minNum, minNode);
			node.map.put(i, nMap);
			
			score += node.prob.get(i) * min;
		}
		node.score = score;
	}
	
	private double getScore(List<Integer> available) {
		int sum = 0;
		for (int n : available) {
			sum += n;
		}
		return sum;
	}

	private List<List<Integer>> getPossibleSolutions(List<Integer> available, int target) {
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> cur = new ArrayList<>();
//		System.out.println(nums);
		getPossibleSolutionsHelper(result, cur, available, target, 0);
//		System.out.println(result);
		return result;
	}
	
	private void getPossibleSolutionsHelper(List<List<Integer>> result, List<Integer> cur, List<Integer> nums, int target,
			int start) {
		if (target == 0) {
			List<Integer> numsCopy = new ArrayList<>(nums);
			numsCopy.removeAll(cur);
			result.add(numsCopy);
			return;
		}
		
		for (int i = start ; i < nums.size() ; i ++) {
			if (nums.get(i) > target) break;
			cur.add(nums.get(i));
			getPossibleSolutionsHelper(result, cur, nums, target - nums.get(i), i + 1);
			cur.remove(cur.size() - 1);
		}
	}
	
	class Node {
//		boolean[] available;
		List<Integer> available;
		double score = 45;
		Map<Integer, Map<List<Integer>, Node>> map;
		Map<Integer, Double> prob;
		public Node(List<Integer> available) {
//			this.available = new boolean[9];
			this.available = available;
			this.prob = new HashMap<>();
			this.map =  new HashMap<>();
			for (int i = 2 ; i <= 12 ; i ++) {
				map.put(i, new HashMap<List<Integer>, Node>());
			}
			init();
		}
		
		private void init() {
			prob.put(2, 1.0/36);
			prob.put(3, 2.0/36);
			prob.put(4, 3.0/36);
			prob.put(5, 4.0/36);
			prob.put(6, 5.0/36);
			prob.put(7, 6.0/36);
			prob.put(8, 5.0/36);
			prob.put(9, 4.0/36);
			prob.put(10, 3.0/36);
			prob.put(11, 2.0/36);
			prob.put(12, 1.0/36);
		}
		
	}
}
