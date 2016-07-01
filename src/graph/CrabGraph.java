package graph;

import java.util.BitSet;
import java.util.HashSet;
import java.util.Set;

public class CrabGraph {

	private final BitSet[] graph;
	
	public CrabGraph(int N, int[][] edges) {
		graph = new BitSet[N];
		buildGraph(edges);
	}
	
	public int maxCoveredNodes(int T, int nodeId, BitSet[] graph) {
		if (nodeId == graph.length) return 0;
		
		if (graph[nodeId].cardinality() < T) {
			return maxCoveredNodes(T, nodeId + 1, graph);
		}
		
		Set<BitSet> allCrabGraphs = getCrabGraph(graph[nodeId], T);
		int max = 0;
		for (BitSet crab : allCrabGraphs) {
			BitSet[] newGraph = copyGraphAndSetVisited(graph, nodeId, crab);
			int cnt = T + maxCoveredNodes(T, nodeId + 1, newGraph);
			max = Math.max(max, cnt);
		}
		return max;
	}
	
	private Set<BitSet> getCrabGraph(BitSet set, int T) {
		Set<BitSet> newSet = new HashSet<>();
		if (T == 0) {			
			newSet.add(new BitSet());
		} else {
			Set<BitSet> lastSets = getCrabGraph(set, T-1);
			for (BitSet lastSet : lastSets) {
				for (int v = set.nextSetBit(0); v >= 0; v = set.nextSetBit(v+1)) {
					if (!lastSet.get(v)) {
						BitSet bs = (BitSet) lastSet.clone();
						bs.set(v);
						newSet.add(bs);
					}
				}
			}
		}		
		return newSet;
	}
	
	private BitSet[] copyGraphAndSetVisited(BitSet[] graph, int nodeId, BitSet crab) {
		BitSet[] newGraph = new BitSet[graph.length];
		for (int i = 0; i < graph.length; i++) {
			newGraph[i] = (BitSet) graph[i].clone();
		}
		int v = crab.nextSetBit(0);
		while (v != -1) {
			newGraph[nodeId].clear(v);
			newGraph[v].clear(nodeId);
			v = crab.nextSetBit(v + 1);
		}
		return newGraph;
	}
 	
	private void buildGraph(int[][] edges) {
		for (int i = 0; i < edges.length; i++) {
			graph[edges[i][0] - 1].set(edges[i][1] - 1);
			graph[edges[i][1] - 1].set(edges[i][0] - 1);
		}
	}
}
