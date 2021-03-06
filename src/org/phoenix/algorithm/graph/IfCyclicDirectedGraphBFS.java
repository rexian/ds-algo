package org.phoenix.algorithm.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/**
 * Check if a cycle is present in directed graph using InDegree/BFS
 * @author surajitpaul
 *
 */
public class IfCyclicDirectedGraphBFS {

	public static void main(String[] args) {
		int nodes = 7;

		Graph g = new Graph(nodes);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 5);
		g.addEdge(4, 5);
		g.addEdge(5, 6);
		g.addEdge(6, 4);
		
		System.out.println();
		
		System.out.println("Is cyclic graph >> " + g.isCyclicGraph());

	}
	
	private static class Graph{
		List<List<Integer>> graph;
		Map<Integer, Integer> incomingDegree;
		int nodes;
		int visitedNodes;
		Queue<Integer> sourceQueue;
		

		public Graph(int nodes) {
			graph = new ArrayList<>();
			incomingDegree = new HashMap<>();
			this.nodes = nodes;
			this.visitedNodes = 0;
			sourceQueue = new LinkedList<>();

			for(int i = 0; i < nodes; i++) {
				graph.add(i, new ArrayList<>());
				incomingDegree.put(i, 0);
			}
		}

		public void addEdge(int a, int b) {
			graph.get(a).add(b);
			incomingDegree.put(b, incomingDegree.get(b) + 1);
		}

		/**
		 * Check if Graph is cyclic
		 * @return if cyclic graph
		 */
		public boolean isCyclicGraph() {
			for(Map.Entry<Integer, Integer> entry: incomingDegree.entrySet()) {
				if(entry.getValue() == 0) {
					sourceQueue.add(entry.getKey());
				}
			}
			
			while(!sourceQueue.isEmpty()) {
				visitedNodes++;
				Integer node = sourceQueue.remove();
				
				for(Integer neighbor: graph.get(node)) {
					incomingDegree.put(neighbor, incomingDegree.get(neighbor) - 1);
					if(incomingDegree.get(neighbor) == 0) {
						sourceQueue.add(neighbor);
					}
				}
			}
			return nodes != visitedNodes;
		}
	}
}



