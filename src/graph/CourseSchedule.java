package graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class CourseSchedule {

	private Set<Integer> scheduled = new HashSet<>();
	private final Map<Integer, GNode> dependencyGraph = new HashMap<>();
	private int N;
	
	public CourseSchedule(int N, int[][] dependencies) {
		this.N = N;
		buildGraph(dependencies);
	}
	
	private void buildGraph(int[][] dependencies) {
		for (int i = 0; i < dependencies.length; i++) {
			GNode node1 = null;
			if (dependencyGraph.containsKey(dependencies[i][0])) {
				node1 = dependencyGraph.get(dependencies[i][0]);
			} else {
				node1 = new GNode(dependencies[i][0]);
				dependencyGraph.put(dependencies[i][0], node1);
			}
			GNode node2 = null;
			if (dependencyGraph.containsKey(dependencies[i][1])) {
				node2 = dependencyGraph.get(dependencies[i][1]);
			} else {
				node2 = new GNode(dependencies[i][1]);
				dependencyGraph.put(dependencies[i][1], node2);
			}
			node1.dependsOn.add(node2);
			node2.requiredFor.add(node1);
		}
	}
	
	public boolean canSchedule() {
		for (int i = 0; i < N; i++) {
			if (dependencyGraph.containsKey(i)) {
				dfsAllSchedulableClasses(dependencyGraph.get(i), new HashSet<Integer>());
			} else {
				scheduled.add(i);
			}			
		}
		return scheduled.size() == N;
	}
	
	// visited is not neccessary because isScheduleable will be false for nodes in a 
	// circular dependency
	private void dfsAllSchedulableClasses(GNode node, Set<Integer> visited) {
		if (!visited.contains(node.id)) {
			visited.add(node.id);
			if (isScheduleable(node)) {
				scheduled.add(node.id);
				for (GNode next : node.requiredFor) {
					dfsAllSchedulableClasses(next, visited);
				}
			}
		}		
	}
	
	private boolean isScheduleable(GNode node) {
//		if (node.dependsOn.isEmpty()) return true;
//		node.dependsOn.stream().allMatch(n -> scheduled.contains(n.id));
		for (GNode p : node.dependsOn) {
			if (!scheduled.contains(p.id)) return false;
		}
		return true;
	}
	
	private static class GNode {
		private int id;
		private List<GNode> dependsOn = new ArrayList<>();
		private List<GNode> requiredFor = new ArrayList<>();
		
		public GNode(int id) {
			this.id = id;
		}
		
		@Override
		public boolean equals(Object other) {
			if (other == null) return false;
			
			GNode otherGNode = (GNode) other;
			return this.id == otherGNode.id;
		}
	}
}
