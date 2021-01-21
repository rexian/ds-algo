package org.phoenix.algorithm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Find minimum spanning tree from un-directed graph
 * @author surajitpaul
 *
 */
public class PrimsMinimumSpanningTree {

	static class Edge{
		private int targetNode;
		private int distanceFromNode;
		
		public Edge(int targetNode, int distanceFromNode) {
			this.targetNode = targetNode;
			this.distanceFromNode = distanceFromNode;
		}
	}

	public static void main(String[] args) {
		int nodes = 7;

		Graph g = new Graph(nodes);

		g.addEdge(0, 1, 3);
		g.addEdge(0, 2, 8);
		g.addEdge(1, 3, 6);
		g.addEdge(1, 4, 2);
		g.addEdge(2, 4, 9);
		g.addEdge(3, 5, 2);
		g.addEdge(4, 5, 4);
		g.addEdge(5, 6, 1);
		System.out.println();
		System.out.println("Min. cost of traversal of all nodes >> " + g.minimumCost());

	}

	private static class Graph{
		List<List<Edge>> graph;
		boolean[] visited;
		int nodes;

		public Graph(int nodes) {
			graph = new ArrayList<>();
			visited = new boolean[nodes];
			this.nodes = nodes;
			for(int i = 0; i < nodes; i++) {
				graph.add(i, new ArrayList<>());
			}
		}

		public void addEdge(int source, int target, int distance) {
			graph.get(source).add(new Edge(target, distance));
			graph.get(target).add(new Edge(source, distance));
		}
		
		/**
		 * 
		 * @return minimum cost of traversal
		 */
		private int minimumCost() {
			int minCost = 0;
			
			for(int i = 0; i < nodes; i++) {
				if(!visited[i]) {
					minCost += minimumSpanningTree(i);
				}
			}
			return minCost;
		}

		/**
		 * Check if Graph is cyclic
		 * @return if cyclic graph
		 */
		public int minimumSpanningTree(int source) {
			
			PriorityQueue<Edge> minHeap = new PriorityQueue<>((e1, e2) -> e1.distanceFromNode - e2.distanceFromNode);
			visited[source] = true;
			List<Edge> neighbors = graph.get(source);
			for(Edge e: neighbors) {
				minHeap.add(e);
			}
			int minCost = 0;
			
			while(!minHeap.isEmpty()) {
				Edge e = minHeap.poll();
				if(visited[e.targetNode]) {
					continue;
				}
				visited[e.targetNode] = true;
				minCost += e.distanceFromNode;
				
				for(Edge edge: graph.get(e.targetNode)) {
					if(!visited[edge.targetNode]) {
						minHeap.add(edge);
					}
				}
			}
			return minCost;
		}
	}
}



