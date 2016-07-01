package graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class JourneyToTheMoon {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int N = in.nextInt();
			int I = in.nextInt();
			int[][] edges = new int[I][2];
			for (int i = 0; i < I; i++) {
				for (int j = 0; j < 2; j++) {
					edges[i][j] = in.nextInt();
				}
			}
			JourneyToTheMoon solution = new JourneyToTheMoon(N, edges);
			System.out.println(solution.pairs());
		}
	}

	private final Node[] A;
	
	public JourneyToTheMoon(int N, int[][] edges) {
		A = new Node[N];
		for (int i = 0; i < N; i++) {
			A[i] = new Node();
		}
		buildGraph(edges);
	}
	
	private void buildGraph(int[][] edges) {
		for (int i = 0; i < edges.length; i++) {
			addEdge(edges[i][0], edges[i][1]);
			addEdge(edges[i][1], edges[i][0]);
		}
	}
	
	private void addEdge(int n1, int n2) {
		List<Integer> neighbors = A[n1].sameCountry;
		if (neighbors == null) {
			neighbors = new ArrayList<Integer>();
			A[n1].sameCountry = neighbors;
		}
		neighbors.add(n2);
	}
	
	public long pairs() {
		long total = 0;
		boolean[] visited = new boolean[A.length];
		for (int i = 0; i < A.length; i++) {
			if ( !visited[i] ) {
				long c = Long.valueOf(findAllFromSameCountry(i, visited));
				total += (Long.valueOf(A.length) - c) * (c);
			}
			
		}
		return total/2;
	}
	
	private int findAllFromSameCountry(int nodeId, boolean[] visited) {
		Queue<Integer> queue = new LinkedList<>();
		queue.add(nodeId);
		int cnt = 0;
		while (!queue.isEmpty()) {
			Queue<Integer> currentQueue = new LinkedList<>(queue);
			queue.clear();
			for (int node : currentQueue) {
				if (!visited[node]) {
					cnt++;
					visited[node] = true;
					if (A[node].sameCountry != null) {
						for (int neighbor : A[node].sameCountry) {
							if (!visited[neighbor]) {
								queue.add(neighbor);
							}
						}
					}
				}								
			}
		}
		return cnt;
	}
	
	private static class Node {
		List<Integer> sameCountry;
	}
}
