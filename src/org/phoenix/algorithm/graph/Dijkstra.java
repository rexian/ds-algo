package org.phoenix.algorithm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Check if a cycle is present in directed graph using InDegree/BFS
 * @author surajitpaul
 *
 */
public class Dijkstra {

	static class Edge{
		private int distanceFromNode;
		private int node;

		public Edge(int node, int distanceFromNode) {
			this.node = node;
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
		System.out.println("Min. distance between nodes >> " + g.minimumDistanceBetweenNodes(0, 5));

	}

	private static class Graph{
		List<List<Edge>> graph;
		boolean[] visited;
		int[] distance;

		public Graph(int nodes) {
			graph = new ArrayList<>();
			visited = new boolean[nodes];
			distance = new int[nodes];
			for(int i = 0; i < nodes; i++) {
				graph.add(i, new ArrayList<>());
				distance[i] = Integer.MAX_VALUE;
			}
		}

		public void addEdge(int source, int target, int distance) {
			graph.get(source).add(new Edge(target, distance));
		}

		/**
		 * Check if Graph is cyclic
		 * @return if cyclic graph
		 */
		public int minimumDistanceBetweenNodes(int source, int destination) {
			if(source == destination) {
				return 0;
			}

			PriorityQueue<Edge> minHeap = new PriorityQueue<>((e1, e2) -> e1.distanceFromNode - e2.distanceFromNode);
			distance[source] = 0;
			minHeap.add(new Edge(0,0));

			while(!minHeap.isEmpty()) {
				Edge e = minHeap.poll();
				int node = e.node;
				int nodeDistance = e.distanceFromNode;

				if(visited[node]) {
					continue;
				}

				visited[node] = true;
				for(Edge edge: graph.get(node)) {

					int nei = edge.node;
					int distanceFromNode = edge.distanceFromNode;

					if((!visited[nei]) && nodeDistance + distanceFromNode < distance[nei]) {
						distance[nei] = nodeDistance + distanceFromNode;
						edge.distanceFromNode = nodeDistance + distanceFromNode;
						minHeap.add(edge);
					}
				}
			}
			return distance[destination];
		}
	}
}



