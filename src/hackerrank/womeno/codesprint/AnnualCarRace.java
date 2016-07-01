package hackerrank.womeno.codesprint;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;

import org.junit.Test;

public class AnnualCarRace {
	
	@Test
	public void test() {
//		List<String> list = Arrays.asList(
//		"0 1 1",
//		"0 2 1",
//		"0 3 2",
//		"0 4 3",
//		"1 5 2",
//		"2 6 4",
//		"3 6 2",
//		"4 6 4",
//		"5 6 1");
		List<String> list = Arrays.asList(
				"0 1 1",
				"0 2 1",
				"1 2 3",
				"2 3 1",
				"1 3 3");
		buildGraph(list);
//		printGraph();
//		System.out.println(nthShortestDist(3,5, 6));
		System.out.println(thirdShortestDist(1, 0));
	}
	
//	private void printGraph() {
//		for (int i : stopsMap.keySet()) {
//			System.out.print(i + ": ");
//			for (Road r : stopsMap.get(i).roads) {
//				System.out.print(r.to + " " + r.distance + ", ");
//			}
//			System.out.println();
//		}
//	}
	
	public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            int sampleNumber = in.nextInt();
            for (int i = 0; i < sampleNumber; i++) {
                in.nextInt();
                int D = in.nextInt();
                int sId = in.nextInt();
                int eId = in.nextInt();
                in.nextLine();
                List<String> list = new ArrayList<>();
                for(int r = 0; r < D; r++) {
                    list.add(in.nextLine());
                }
                AnnualCarRace solution = new AnnualCarRace();
                solution.buildGraph(list);
                System.out.println(solution.thirdShortestDist(sId, eId));
            }
        }
    }
	
	Map<Integer, Map<Integer, Integer>> stopsMap = new HashMap<>();
	
	public void buildGraph(List<String> list) {
		for (String r : list) {
			String[] info = r.split(" ");
			int u = Integer.parseInt(info[0]);
			int v = Integer.parseInt(info[1]);
			int dist = Integer.parseInt(info[2]);
			addToGraph(u, v, dist);
			addToGraph(v, u, dist);
		}
	}
	
	private void addToGraph(int uId, int vId, int dist) {
		Map<Integer, Integer> roads = stopsMap.get(uId);
		if (roads == null) {
			roads = new HashMap<Integer, Integer>();
			stopsMap.put(uId, roads);
		}
		roads.put(vId, dist);
	}
	
//	public int nthShortestDist(int nth, int startId, int endId) {
//		int dist = -1;
//		int cnt = 0;
//		while (cnt < nth) {
//			int nextDist = shortestPath(startId, endId);
//			if (nextDist == -1) return nextDist;
//			if (nextDist > dist) {
//				dist = nextDist;
//				cnt++;
//			}
//		}
//		return dist;
//	}
//	
//	private int shortestPath(int startId, int endId) {
//		PriorityQueue<PathNode> queue = new PriorityQueue<>(new Comparator<PathNode>(){
//
//			@Override
//			public int compare(PathNode o1, PathNode o2) {
//				return o1.distTraveled - o2.distTraveled;
//			}
//			
//		});
//		
//		PathNode current = new PathNode(startId, null, 0);
//		queue.add(current);
//		while(!queue.isEmpty()) {
//			current = queue.poll();
//			if (current.id == endId) {
//				int rlt = current.distTraveled;				
//				while(current.from.id != startId) {
//					removeFromGraph(current.from.id);
//					current = current.from;
//				}
//				return rlt;
//			}
//			for (int to : stopsMap.get(current.id).keySet()) {
//				queue.offer(new PathNode(to, current, current.distTraveled + stopsMap.get(current.id).get(to)));
//			}
//		}
//		return -1;
//	}
//	
//	private void removeFromGraph(int idToRemove) {
//		for (int neighbor : stopsMap.get(idToRemove).keySet()) {
//			stopsMap.get(neighbor).remove(idToRemove);
//		}
//		stopsMap.remove(idToRemove);
//	}
	
	private static class PathNode {
		int id;
		PathNode from;
		int distTraveled;
		
		public PathNode(int id, PathNode from, int distTraveled) {			
			this.id = id;
			this.from = from;
			if (from == null) this.from = this;
			this.distTraveled = distTraveled;
		}
	}
	
	public int thirdShortestDist(int startId, int endId) {
		PriorityQueue<PathNode> queue = new PriorityQueue<>(new Comparator<PathNode>() {

			@Override
			public int compare(PathNode o1, PathNode o2) {
				return o1.distTraveled - o2.distTraveled;
			}
			
		});
		
		int cnt = 0;
		int shortestDistSoFar = 0; 
		queue.add(new PathNode(startId, null, 0));
		BitSet visited = new BitSet();
		BitSet usedByPath = new BitSet();
		while (!queue.isEmpty()) {
			PathNode current = queue.poll();
			visited.set(current.id);
			if (current.id == endId) {
				if (current.distTraveled > shortestDistSoFar) {
					shortestDistSoFar = current.distTraveled;
					cnt++;
				}
				if (!isPathUsed(current, startId, usedByPath) && cnt == 3) {
					return current.distTraveled;					
				}
			} else {
				for (int to : stopsMap.get(current.id).keySet()) {
					if (to == endId || !visited.get(to)) {
						queue.offer(new PathNode(to, current, current.distTraveled + stopsMap.get(current.id).get(to)));
					}				
				}
			}
			
		}
		return -1;
	}
	
	private boolean isPathUsed(PathNode node, int startId, BitSet used) {
		PathNode current = node;
		while (current.from.id != startId) {
			if (!used.get(current.from.id)) {
				used.set(current.from.id);
				current = current.from;
			} else {
				return true;
			}
		}
		return false;
	}
//	
//	
//	private static class StopTraveled {
//		int stopId;
//		int distTraveled;
//		
//		public StopTraveled(int id, int distTraveled) {
//			this.stopId = id;
//			this.distTraveled = distTraveled;
//		}
//	}
//	
////	private static class Stop {
////		int id;
////		List<Road> roads = new ArrayList<>();
////		
////		public Stop(int id) {
////			this.id = id;
////		}
////	}
//	
//	private static class Road {
//		int distance;
//		int to;
//		
//		public Road(int distance, int to) {
//			this.distance = distance;
//			this.to= to;
//		}
//	}

}
