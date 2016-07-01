package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EvenTree {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int N = in.nextInt();
			int M = in.nextInt();
			int[][] edges = new int[M][2];
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < 2; j++) {
					edges[i][j] = in.nextInt();
				}
			}
			EvenTree solution = new EvenTree(N, edges);
			System.out.println(solution.maxCuts());
		}
	}
	
	private final Node[] nodes;
	
	public EvenTree(int N, int[][] edges) {
		this.nodes = new Node[N];
		buildTree(N, edges);
	}
	
	private void buildTree(int N, int[][] edges) {
		for (int i = 0; i < N; i++) {
			nodes[i] = new Node(i);
		}
		
		Map<Integer, List<Integer>> connections = new HashMap<>();
		for (int i = 0; i < edges.length; i++) {
			addConnections(connections, edges[i][0] - 1, edges[i][1] - 1);
			addConnections(connections, edges[i][1] - 1, edges[i][0] - 1);
		}
		
		boolean[] visited = new boolean[N];
		buildTree(0, connections, visited);
	}
	
	private Node buildTree(int nid, Map<Integer, List<Integer>> map, boolean[] visited) {
		visited[nid] = true;
		nodes[nid].cnt = 1;
		for (int neighbor : map.get(nid)) {
			if (!visited[neighbor]) {
				nodes[nid].addChild(neighbor);
				nodes[nid].cnt += buildTree(neighbor, map, visited).cnt;
			}
		}
		return nodes[nid];
	}
	
	private void addConnections(Map<Integer, List<Integer>> map, int n1, int n2) {
		List<Integer> list = map.get(n1);
		if ( list == null) {
			list = new ArrayList<Integer>();
			map.put(n1, list);
		}
		list.add(n2);
	}
	
	public int maxCuts() {
		return numberOfCuts(nodes[0]);
	}

	private int numberOfCuts(Node node) {
		if (node.cnt == 2 || node.cnt == 1) return 0;
		
		int cnt = 0;
		for (int child : node.children) {
			if (nodes[child].cnt % 2 == 0) {
				cnt++;
			} 
			cnt += numberOfCuts(nodes[child]);
		}
		return cnt;
	}
	
	private static class Node {
		int id;
		List<Integer> children = new ArrayList<>();
		int cnt;
		
		Node(int id) {
			this.id = id;
		}
		
		void addChild(int child) {
			children.add(child);
		}
	}
}
