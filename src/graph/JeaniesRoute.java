package graph;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class JeaniesRoute {
	
//	5 4
//	1 3 4 5
//	1 2 1
//	2 3 2
//	2 4 2
//	3 5 3
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int N = in.nextInt();
			int K = in.nextInt();
			BitSet kBitSet = new BitSet();
			for (int i = 0; i < K; i++) {
				kBitSet.set(in.nextInt() - 1);
			}
			int[][] edges = new int[N-1][3];
			for (int i = 0; i < N - 1; i++) {
				for (int j = 0; j < 3; j++) {
					edges[i][j] = in.nextInt();
				}
			}
			JeaniesRoute solution = new JeaniesRoute(N, edges);
			System.out.println(solution.minDistance(kBitSet));
		}
		
	}
	
	private final Edges[] graph;
	
	public JeaniesRoute(int N, int[][] edges) {
		graph = new Edges[N];
		buildGraph(edges);
	}
	
	public int minDistance(BitSet K) {
		BitSet kSetVisited = new BitSet();
		Result rlt = new Result();
		for (int i = K.nextSetBit(0); i >= 0; i = K.nextSetBit(i+1)) {
			if (!kSetVisited.get(i)) {
				List<Node> path = new ArrayList<Node>();
				path.add(new Node(i, 0));
				traverse(i, kSetVisited, rlt, K, path, new BitSet(), new BitSet());
			}
		}
		return rlt.totalDist == rlt.maxSingleDist ? rlt.totalDist : rlt.totalDist - rlt.maxSingleDist;
	}
	
	private void traverse(int nodeId, BitSet kSetVisited, Result rlt, BitSet K, List<Node> path, BitSet visited, BitSet printed) {
		visited.set(nodeId);
		for (int to : graph[nodeId].edges.keySet()) {
			if (!visited.get(to)) {
				int d = graph[nodeId].edges.get(to) + path.get(path.size()-1).distAccum;
				path.add(new Node(to, d));
				traverse(to, kSetVisited, rlt, K, path, visited, printed);
				path.remove(path.size() - 1);
			}
		}
		int i = path.size() - 1;
		while (i >= 0) {
			if (K.get(path.get(i).nodeId) && path.get(i).nodeId > path.get(0).nodeId 
					&& !printed.get(path.get(i).nodeId) && !kSetVisited.get(path.get(i).nodeId)) {
				rlt.totalDist += path.get(i).distAccum;
				rlt.maxSingleDist = Math.max(rlt.maxSingleDist, path.get(i).distAccum);
				printed.set(path.get(i).nodeId);
				while (i > 0) {
					i--;
					if (K.get(path.get(i).nodeId)) {
						printed.set(path.get(i).nodeId);
						kSetVisited.set(path.get(i).nodeId);
					}
				}
				return;
			} else {
				i--;
			}
		}
		
	}

	private void buildGraph(int[][] edges) {
		for (int i = 0; i< edges.length; i++) {
			int node1 = edges[i][0];
			int node2 = edges[i][1];
			int d = edges[i][2];
			addEdge(node1-1, node2-1, d);
			addEdge(node2-1, node1-1, d);
		}
	}
	
	private void addEdge(int node1, int node2, int d) {
		Edges edges = graph[node1];
		if (edges == null) {
			edges = new Edges();
			graph[node1] = edges;
		}
		edges.edges.put(node2, d);
	}
	
	private static class Node {
		int nodeId;
		int distAccum;
		
		public Node(int nodeId, int distAccum) {
			this.nodeId = nodeId;
			this.distAccum = distAccum;
		}
	}
	
	private static class Edges {
		Map<Integer, Integer> edges = new HashMap<Integer, Integer>();
	}
	
	private static class Result {
		int totalDist;
		int maxSingleDist;
	}
}
