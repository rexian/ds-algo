package org.phoenix.algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Check if undirected graph is connected or not using DFS
 * @author surajitpaul
 *
 */
public class CheckIfDirectedGraphStronglyConnected {

	public static void main(String[] args) {
		int nodes = 5;

		DirectedGraph g = new DirectedGraph(nodes);

		g.addEdge(0, 1);
		g.addEdge(0, 2);
		g.addEdge(0, 3);
		g.addEdge(1, 0);
		g.addEdge(1, 2);
		g.addEdge(1, 3);
		g.addEdge(2, 0);
		g.addEdge(2, 1);
		g.addEdge(2, 3);
		g.addEdge(3, 0);
		g.addEdge(3, 1);
		g.addEdge(3, 2);
		
		System.out.println();
		
		System.out.println("Is connected graph >> " + g.isStronglyConnectedGraph(nodes));

	}
}


class DirectedGraph{
	List<List<Integer>> graph;
	boolean[] visited;

	public DirectedGraph(int nodes) {
		graph = new ArrayList<>();
		visited = new boolean[nodes];

		for(int i = 0; i < nodes; i++) {
			graph.add(i, new ArrayList<>());
		}
	}

	public void addEdge(int a, int b) {
		graph.get(a).add(b);
	}

	/**
	 * Check if Graph is connected
	 * @return visited status
	 */
	public boolean isStronglyConnectedGraph(int nodes) {
		
		for(int i = 0; i < nodes; i++) {
			dfs(i);
			
			for(boolean visitStatus: visited) {
				if(!visitStatus) {
					return false;
				}
			}
			Arrays.fill(visited, false);
		}
		return true;
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
