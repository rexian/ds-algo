package org.phoenix.algorithm.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Find minimum distance between source and destination
 * @author surajitpaul
 *
 */
public class MinDistanceBetweenSourceDestination {

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
		
		System.out.println();
		
		System.out.println("Min. distance >> " + g.minimumDistance(0, 6));

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
		 * Find minimum distance between 2 vertices
		 * @return Minimum distance between 2 vertices
		 */
		public int minimumDistance(int source, int destination) {
			if(source == destination) {
				return 0;
			}
			
			Queue<Integer> q = new LinkedList<>();
			int minDistance = 0;
			visited[source] = true;
			q.add(source);
			
			while(!q.isEmpty()) {
				int size = q.size();
				
				for(int i = 0; i < size; i++) {
					for(Integer node: graph.get(q.poll())) {
						if(node == destination) {
							return ++minDistance;
						}
						else if(!visited[node]){
							q.add(node);
							visited[node] = true;
						}
					}
				}
				minDistance++;
			}
			return -1;
		}
	}
}



