package graph;

import java.util.BitSet;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class ShortestReach {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)){
			int T = in.nextInt();
			for (int i = 0; i < T; i++) {
				int N = in.nextInt();
				int M = in.nextInt();
				int[][] edges = new int[M][2];
				for (int j = 0; j < M; j++) {
					for (int k = 0; k < 2; k++) {
						edges[j][k] = in.nextInt();
					}
				}
				int S = in.nextInt();
				ShortestReach solution = new ShortestReach(N, edges);
				int[] shortest = solution.shortestReach(S-1);
				for (int p = 0; p < N; p++) {
					if (p != S - 1) {
						System.out.print(shortest[p] + " ");
					}					
				}
				System.out.println();
			}
		}
	}
	
	private final BitSet[] graph;
	
	public ShortestReach(int N, int[][] edges) {
		graph = new BitSet[N];
		buildGraph(edges);
	}
	
	public int[] shortestReach(int S) {
		PriorityQueue<PathNode> queue = new PriorityQueue<>(new Comparator<PathNode>() {

			@Override
			public int compare(PathNode o1, PathNode o2) {
				return o1.distAccum - o2.distAccum;
			}
			
		});
		int[] shortest = new int[graph.length];
		for (int i = 0; i < graph.length; i++) {
			shortest[i] = -1;
		}
		queue.offer(new PathNode(S, 0));
		while (!queue.isEmpty()) {
			PathNode current = queue.poll();
			if (shortest[current.nodeId] == -1) {
				// found the shortest from S -> current
				shortest[current.nodeId] = current.distAccum;
				
				BitSet edges = graph[current.nodeId];
				if (edges != null) {
					for (int to = graph[current.nodeId].nextSetBit(0); to >= 0; to = graph[current.nodeId].nextSetBit(to + 1)) {
						if (shortest[to] == -1) {
							queue.offer(new PathNode(to, current.distAccum + 6));
						}
						
					}
				}				
			}
		}
		return shortest;
	}
	
	private void buildGraph(int[][] edges) {
		for (int i = 0; i < edges.length; i++) {
			addEdge(edges[i][0] - 1, edges[i][1] - 1);
			addEdge(edges[i][1] - 1, edges[i][0] - 1);
		}
	}
	
	private void addEdge(int from, int to) {
		BitSet edges = graph[from];
		if (edges == null) {
			edges = new BitSet();
			graph[from] = edges;
		}
		edges.set(to);
	}
	
	private static class PathNode {
		int nodeId;
		int distAccum;
		
		PathNode(int nodeId, int distAccm) {
			this.nodeId = nodeId;
			this.distAccum = distAccm;
		}
	}

}
