package graph;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.junit.Test;

public class Clique {
	
	public static void main(String[] args) {
		try (Scanner in = new Scanner(System.in)) {
			int T = in.nextInt();
			for (int i = 0; i < T; i++) {
				System.out.println(minSetForLargestClique(in.nextInt(), in.nextInt()));
			}
		}
	}

	// strategy: pick the next edge to connect the sparsest nodes - the nodes that 
	// have fewest edges.
	// why this is correct? prove by contradiction
	// if after adding a new edge to a node with the fewest connections, it has k connections, so
	// k-1 was the smallest connections a node has.
	// If there is another clique of k+1,that means we add an edge to a node with k connections before 
	// adding an edge to a node with k-1 connections.
	// therefore, the node that we connect with the latest edge must be part of one of the largest cliques
	// so far
	// the above prove was wrong because not all the connections of a node need to belong to the clique
	public static int minSetForLargestClique(int N, int M) {
		if (M == N * (N -1) / 2) return N;
		
		PriorityQueue<Node> queue = new PriorityQueue<>(new Comparator<Node>(){

			@Override
			public int compare(Node o1, Node o2) {
				return o1.edges - o2.edges;
			}
			
		});
		
		for (int i =0; i < N; i++) {
			queue.offer(new Node(i));
		}
		boolean[][] connections = new boolean[N][N];
		int edges = 0;
		int cliqueSize = 0; 
		while (edges < M) {
			Node n1 = queue.remove();
			if (n1.edges < 2) {
				cliqueSize = 2;
			} else {
				cliqueSize = n1.edges + 1;
			}
			Node n2 = queue.remove();
			List<Node> tos = new ArrayList<>();
			while (connections[n1.id][n2.id]) {
				tos.add(n2);
				n2 = queue.remove();
			}
			connections[n1.id][n2.id] = true;
			n1.incEdges();
			n2.incEdges();
			for (Node n: tos) {
				queue.offer(n);
			}
			queue.offer(n1);
			queue.offer(n2);
			edges++;
		}
		return cliqueSize;
	}
	
	private static class Node {
		int id;
		int edges;
		
		Node(int id) {
			this.id = id;
		}
		
		void incEdges() {
			edges++;
		}
	}
	
	@Test
	public void test() {
		int N = 35;
		int M = 152; // should be 2
		System.out.println(minSetForLargestClique(N,M));
	}
}
