package data.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Graph<T> {
	
	Map<GNode<T>, List<GNode<T>>> graph = new HashMap<>();
	
	public Map<GNode<T>, List<GNode<T>>> getGraph() {
		return graph;
	}
	
	public void addNode(GNode<T> node) {
		graph.put(node, new ArrayList<GNode<T>>());
	}
	
	public void addEdge(GNode<T> from, GNode<T> to) {
		List<GNode<T>> neighbors = new ArrayList<>();
		neighbors.add(to);
		if (graph.containsKey(from)) {
			neighbors.addAll(graph.get(from));			
		}
		graph.put(from, neighbors);
	}
	
	public Graph<T> clone() {
		Graph<T> g = new Graph<T>();
		Map<GNode<T>, GNode<T>> map = new HashMap<>();
		for(GNode<T> node : graph.keySet()) {
			GNode<T> cnode = map.get(node);
			if (cnode == null) {
				cnode = new GNode<T>(node.value);
				map.put(node, cnode);
			}
			g.addNode(cnode);
//			List<GNode<T>> neighbors = new ArrayList<>();
			for(GNode<T> n : graph.get(node)) {
				GNode<T> cn = map.get(n);
				if (cn == null) {
					cn = new GNode<T>(n.value);
					map.put(n, cn);
				}
//				neighbors.add(cn);
				g.addEdge(cnode, cn);
			}
//			g.graph.put(cnode, neighbors);
		}
		return g;
	}
	
	public String serialize() {
		Map<GNode<T>, Integer> map = new HashMap<>();
		int label = 0;
		StringBuilder graphStr = new StringBuilder();
		StringBuilder dataStr = new StringBuilder();
		for (GNode<T> node : graph.keySet()) {
			if (map.containsKey(node)) {
				graphStr.append(map.get(node));
			} else {
				label++;
				map.put(node, label);
				graphStr.append(label);
				dataStr.append(node.value).append("#");
			}
			for (GNode<T> n : graph.get(node)) {
				graphStr.append(",");
				if (map.containsKey(n)) {
					graphStr.append(map.get(n));
				} else {
					label++;
					map.put(n, label);
					graphStr.append(label);
					dataStr.append(n.value).append("#");
				}
			}
			graphStr.append("#");
		}
		return dataStr.append("///").append(graphStr).toString();
	}
	
	private static class GNode<T> {
		T value;
		List<GNode<T>> neighbors = new ArrayList<>();
		
		public GNode(T t) {
			value = t;
		}
		
		public boolean equals(Object o) {
			GNode<T> obj = (GNode<T>) o;
			return this.value.equals(obj.value);
		}
	}

}
