package airbnb2019;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LCA {
	Node root;
	Map<String, Node> map ;
	public static void main(String[] args) {
		// [earth, america]
		// [america, south america, north acmerica]
		// [north america, canada, us]
		// [canada, ont
		List<List<String>> input = new ArrayList<>();
		input.add(Arrays.asList("earth", "america"));
		input.add(Arrays.asList("america", "south america", "north america"));
		input.add(Arrays.asList("north america", "canada", "us"));
		
		LCA lca = new LCA();
		lca.buildTree(input);
		System.out.println(lca.findLCA("canada", "us"));
		System.out.println(lca.findLCA("south america", "us"));
		System.out.println(lca.findLCA("north america", "us"));
	}
	
	class Node {
		String value;
		List<Node> child = new ArrayList<>();
		
		public Node(String value) {
			this.value = value;
		}
		
		public void addChild(Node node) {
			child.add(node);
		}
	}
	
	public LCA() {
		this.map = new HashMap<>();
	}
	
	private String findLCA( String a, String b) {
		Node n = lca(this.root, a, b);
		return n.value;
	}
	
	private Node lca(Node node, String a, String b)
	
	private Node findRootNode() {
		// suppose the parent already told you
		return null;
	}

	private  void buildTree(List<List<String>> input) {
		this.root = new Node(input.get(0).get(0));
		for (List<String> entry : input) {
			String parent = entry.get(0);
			map.putIfAbsent(parent, new Node(parent));
			Node parentNode  = map.get(parent);
			for (int i = 1 ; i < entry.size() ; i ++) {
				String child = entry.get(i);
				map.putIfAbsent(child, new Node(child));
				parentNode.addChild(map.get(child));
			}
		}
	}

	
	

}
