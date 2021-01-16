package org.phoenix.algorithm.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Get total number of connected components
 * @author surajitpaul
 *
 */
public class GetConnectedComponents {

	public static void main(String[] args) {
		int nodes = 11;

		Graph g = new Graph(nodes);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 5);
		g.addEdge(4, 5);
		g.addEdge(4, 6);
		g.addEdge(7, 8);
		g.addEdge(9, 10);
		
		System.out.println("Total connected components >> " + g.getConnectedComponentCount(nodes));

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
		 * Check if Graph is connected
		 * @return visited status
		 */
		public int getConnectedComponentCount(int nodes) {
			int totalComponents = 0;
			
			for(int i = 0; i < nodes; i++) {
				if(!visited[i]) {
					dfs(i);
					totalComponents++;
				}
			}
			return totalComponents;
		}
		
		/**
		 * Depth first search of undirected graph
		 * @param start index
		 */
		public void dfs(int start) {

			Stack<Integer> s = new Stack<>();
			s.push(start);
			visited[start] = true;

			while(!s.isEmpty()) {
				Integer current = s.pop();
				System.out.print(current + " ");
				
				for(Integer neighbor: graph.get(current)) {
					if(!visited[neighbor]) {
						s.push(neighbor);
						visited[neighbor] = true;
					}
				}
			}
		}
	}
}



