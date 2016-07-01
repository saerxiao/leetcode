package hackerrank.graph;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MSTSpecialSubtree {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int N = in.nextInt();
			int M = in.nextInt();
			int[][] edges = new int[M][3];
			for (int i = 0; i < M; i++) {
				for (int j = 0; j < 3; j++) {
					edges[i][j] = in.nextInt();
				}
			}
			int start = in.nextInt() - 1;
			MSTSpecialSubtree solution = new MSTSpecialSubtree(N, edges);
			System.out.println(solution.totalWeightOfMST(start));
		}
	}
	
	private final Node[] nodes;
	
	public MSTSpecialSubtree (int N, int[][] edges) {
		nodes = new Node[N];
		for (int i = 0; i < N; i++) {
			nodes[i] = new Node();
		}
		buildGraph(edges);
	}
	
	private void buildGraph(int[][] edges) {
		for (int i = 0; i < edges.length; i++) {
			addEdge(edges[i][0] - 1, edges[i][1] - 1, edges[i][2]);
			addEdge(edges[i][1] - 1, edges[i][0] - 1, edges[i][2]);
		}
	}
	
	private void addEdge(int n1, int n2, int w) {
		List<Edge> edges = nodes[n1].edges;
		if (edges == null) {
			edges = new ArrayList<Edge>();
			nodes[n1].edges = edges;
		}
		edges.add(new Edge(n2, w));
	}
	
	public long totalWeightOfMST(int start) {
		PriorityQueue<Edge> queue = new PriorityQueue<>(new Comparator<Edge>() {

			@Override
			public int compare(Edge o1, Edge o2) {
				return o1.weight - o2.weight;
			}
			
		});
		for (Edge edge : nodes[start].edges) {
			queue.offer(edge);
		}		
		BitSet visited = new BitSet(nodes.length);
		visited.set(start);
		long totalWeight = 0;
		while (!queue.isEmpty() && visited.cardinality() < nodes.length) {
			Edge next = queue.poll();
			if (!visited.get(next.to)) {
				visited.set(next.to);
				totalWeight += Long.valueOf(next.weight);
				for (Edge edge : nodes[next.to].edges) {
					if (!visited.get(edge.to)) {
						queue.offer(edge);
					}
				}
			}
		}
		return totalWeight;
	}

	private static class Node {
		List<Edge> edges;
	}
	
	private static class Edge {
		int to;
		int weight;
		
		Edge(int to, int weight) {
			this.to = to;
			this.weight = weight;
		}
	}
}
