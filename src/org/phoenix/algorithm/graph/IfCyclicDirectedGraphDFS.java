package org.phoenix.algorithm.graph;

import java.util.ArrayList;
import java.util.List;

/**
 * Check if a cycle is present in directed graph
 * @author surajitpaul
 *
 */
public class IfCyclicDirectedGraphDFS {

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
		
		System.out.println("Is cyclic graph >> " + g.isCyclicGraph(nodes));

	}
	
	private static class Graph{
		List<List<Integer>> graph;
		boolean[] visited, recursive;

		public Graph(int nodes) {
			graph = new ArrayList<>();
			visited = new boolean[nodes];
			recursive = new boolean[nodes];

			for(int i = 0; i < nodes; i++) {
				graph.add(i, new ArrayList<>());
			}
		}

		public void addEdge(int a, int b) {
			graph.get(a).add(b);
		}

		/**
		 * Check if Graph is cyclic
		 * @return if cyclic graph
		 */
		public boolean isCyclicGraph(int nodes) {
			for(int i = 0; i < nodes; i++) {
				if(isCyclic(i)) {
					return true;
				}
			}
			return false;
		}
		
		/**
		 * DFS to check if cyclic directed graph
		 * @param start index
		 */
		public boolean isCyclic(int start) {
			
			if(recursive[start]) {
				return true;
			}
			
			if(visited[start]) {
				return false;
			}
			
			recursive[start] = true;
			visited[start] = true;

			for(Integer neighbor: graph.get(start)) {
				if(isCyclic(neighbor)) {
					return true;
				}
			}
			
			recursive[start] = false;
			return false;
		}
	}
}



