package hackerrank.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class CutTree {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int N = in.nextInt();
			int[] vals = new int[N];
			for (int i = 0; i < N; i++) {
				vals[i] = in.nextInt();
			}
			int[][] edges = new int[N-1][2];
			for (int i = 0; i < edges.length; i++) {
				for (int j = 0; j < 2; j++) {
					edges[i][j] = in.nextInt();
				}
			}
			
			CutTree solution = new CutTree(vals, edges);
			System.out.println(solution.minDiff());
		}
	}
	
	private final Node root;
	
	public CutTree(int[] vals, int[][] edges) {
		root = buildTree(vals, edges);
	}
	
	private Node buildTree(int[] vals, int[][] edges) {
		Node[] nodes = new Node[vals.length];
		for (int i = 0; i < vals.length; i++) {
			nodes[i] = new Node(i, vals[i]);
		}
		
		Map<Integer, List<Integer>> edgeMap = new HashMap<>();
		for (int i = 0; i < edges.length; i++) {
			addEdge(edges[i][0] - 1, edges[i][1] - 1, edgeMap);
			addEdge(edges[i][1] - 1, edges[i][0] - 1, edgeMap);
		}
		
		Queue<Node> queue = new LinkedList<>();
		queue.add(nodes[0]);
		boolean[] visited = new boolean[nodes.length];
		while (!queue.isEmpty()) {
			Queue<Node> currentQueue = new LinkedList<>(queue);
			queue.clear();
			for (Node node : currentQueue) {
				visited[node.id] = true;
				for (int neighbor : edgeMap.get(node.id)) {
					if (!visited[neighbor]) {
						node.children.add(nodes[neighbor]);
						queue.add(nodes[neighbor]);
					}
				}
			}			
		}
		return nodes[0];
	}
	
	private void addEdge(int n1, int n2, Map<Integer, List<Integer>> map) {
		List<Integer> list = map.get(n1);
		if (list == null) {
			list = new ArrayList<Integer>();
			map.put(n1, list);
		}
		list.add(n2);
	}
	
	public int minDiff() {
		int total = sumOfTree(root);
		int minDiff = total;
		Queue<Node> queue = new LinkedList<>();
		queue.add(root);
		while (!queue.isEmpty()) {
			Queue<Node> thisQueue = new LinkedList<>(queue);
			queue.clear();
			for (Node node : thisQueue) {
				for (Node child : node.children) {
					int diff = Math.abs(child.sum - (total - child.sum));
					minDiff = Math.min(minDiff, diff);
					queue.add(child);
				}
			}			
		}
		return minDiff;
	}
	
	private int sumOfTree(Node node) {
		if (node.children.isEmpty()) {
			node.sum = node.val;
			return node.sum;
		}
		
		int sum = node.val;
		for (Node child : node.children) {
			sum += sumOfTree(child);
		}
		node.sum = sum;
		return sum;
	}

	private static class Node {
		int id;
		int val;
		List<Node> children = new ArrayList<>();
		int sum;
		
		Node(int id, int val) {
			this.id = id;
			this.val = val;
		}
	}
}
