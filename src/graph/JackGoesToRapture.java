package graph;

import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

public class JackGoesToRapture {
	
//	10 45
//	1 2 6337
//	1 3 1594
//	1 4 3766
//	1 5 3645
//	1 6 75
//	1 7 5877
//	1 8 8561
//	1 9 242
//	1 10 6386
//	2 3 3331
//	2 4 4194
//	2 5 8069
//	2 6 3934
//	2 7 101
//	2 8 8536
//	2 9 6963
//	2 10 9303
//	3 4 7639
//	3 5 8512
//	3 6 1330
//	3 7 6458
//	3 8 1180
//	3 9 3913
//	3 10 1565
//	4 5 9488
//	4 6 1369
//	4 7 8066
//	4 8 9439
//	4 9 7510
//	4 10 6833
//	5 6 4215
//	5 7 194
//	5 8 4774
//	5 9 4328
//	5 10 187
//	6 7 1196
//	6 8 200
//	6 9 8743
//	6 10 1433
//	7 8 2933
//	7 9 2069
//	7 10 1974
//	8 9 7349
//	8 10 2351
//	9 10 8423
	// output: 1196

//	public static void main(String[] args) {
//		int N = 5;
//		int[][] roads = new int[][]{{1,2,60},{3,5,70},{1,4,120},{4,5,150},{2,3,80},{1,3,40}};
//		JackGoesToRapture solution = new JackGoesToRapture(N, roads);
//		System.out.println(solution.lowestCost(0, N-1));
//	}
	
	public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int N = in.nextInt();
            int E = in.nextInt();
            int[][] edges = new int[E][3];
            for (int i = 0; i < E; i++) {
                for (int j = 0; j < 3; j++) {
                    edges[i][j] = in.nextInt();
                }
            }
            JackGoesToRapture solution = new JackGoesToRapture(N, edges);
            int cost = solution.lowestCost(0, N-1);
            if (cost == -1) {
                System.out.println("NO PATH EXISTS");
            } else {
                System.out.println(cost);
            }
        }
    }
	
	private final Node[] graph;
	
	public JackGoesToRapture(int N, int[][] roads) {
		graph = new Node[N];
		buildGraph(roads);
	}
	
	public int lowestCost(int start, int end) {
		PriorityQueue<PathNode> queue = new PriorityQueue<>(new Comparator<PathNode>(){

			@Override
			public int compare(PathNode o1, PathNode o2) {
				return o1.costAccum - o2.costAccum;
			}
			
		});
		queue.offer(new PathNode(start, 0));
		BitSet visited = new BitSet();
		while (!queue.isEmpty()) {
			PathNode current = queue.poll();
			visited.set(current.id);
			if (current.id == end) {
				return current.costAccum;
			}
			Node node = graph[current.id];
			if (node != null) {
				for (int next : graph[current.id].roads.keySet()) {
					if (!visited.get(next)) {
						int fare = graph[current.id].roads.get(next);
						int cost = fare < current.costAccum ? current.costAccum : fare;
						queue.offer(new PathNode(next, cost));
					}
				}
			}
			
		}
		return -1;
	}
	
	private void buildGraph(int[][] edges) {
		for (int i = 0; i < edges.length; i++) {
			int from = edges[i][0] - 1;
			int to = edges[i][1] - 1;
			int fare = edges[i][2];
			Node node = graph[from];
			if (node == null) {
				node = new Node();
				graph[from] = node;
			}
			Map<Integer, Integer> roads = node.roads;
			roads.put(to, fare);
		}
	}
	
	private static class Node {
		Map<Integer, Integer> roads = new HashMap<>();
	}
	
	private static class PathNode {
		int id;
		int costAccum;
		
		public PathNode(int id, int costAccum) {
			this.id = id;
			this.costAccum = costAccum;
		}
	}
}
