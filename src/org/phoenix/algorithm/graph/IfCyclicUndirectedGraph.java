package org.phoenix.algorithm.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Check if a cycle is present in undirected graph
 * @author surajitpaul
 *
 */
public class IfCyclicUndirectedGraph {

	public static void main(String[] args) {
		int nodes = 7;

		Graph g = new Graph(nodes);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 5);
		g.addEdge(4, 5);
		g.addEdge(4, 6);
		
		System.out.println();
		
		System.out.println("Is cyclic graph >> " + g.isCyclicGraph(nodes));

	}
	
	private static class Graph{
		List<List<Integer>> graph;
		boolean[] visited;

		public Graph(int nodes) {
			graph = new ArrayList<>();
			visited = new boolean[nodes];

			for(int i = 0; i < nodes; i++) {
				graph.add(i, new ArrayList<>());
			}
		}

		public void addEdge(int a, int b) {
			graph.get(a).add(b);
			graph.get(b).add(a);
		}

		/**
		 * Check if Graph is cyclic
		 * @return if cyclic graph
		 */
		public boolean isCyclicGraph(int nodes) {
			for(int i = 0; i < nodes; i++) {
				if(!visited[i]) {
					if(isCyclic(i, -1)) {
						return true;
					}
				}
			}
			return false;
		}
		
		/**
		 * Depth first search of undirected graph
		 * @param start index
		 */
		public boolean isCyclic(int start, int parent) {

			visited[start] = true;

			for(Integer neighbor: graph.get(start)) {
				if(!visited[neighbor]) {
					visited[neighbor] = true;
					isCyclic(neighbor, start);
				}
				else if(neighbor != parent){
					return true;
				}
			}
			return false;
		}
	}
}



