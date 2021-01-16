package org.phoenix.algorithm.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

/**
 * Graph traversal (BFS/DFS) for undirected graph
 * @author surajitpaul
 *
 */
public class GraphTraversal {

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

		g.bfs(0);
		System.out.println();
		
		g = new Graph(nodes);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 4);
		g.addEdge(3, 5);
		g.addEdge(4, 5);
		g.addEdge(4, 6);
		
		g.dfs(0);

	}
}


class Graph{
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
	 * Breadth first search of undirected graph
	 * @param start index
	 */
	public void bfs(int start) {

		Queue<Integer> s = new LinkedList<>();
		s.add(start);
		visited[start] = true;

		while(!s.isEmpty()) {
			Integer current = s.poll();
			System.out.print(current + " ");
			
			for(Integer neighbor: graph.get(current)) {
				if(!visited[neighbor]) {
					s.add(neighbor);
					visited[neighbor] = true;
				}
			}
		}
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
