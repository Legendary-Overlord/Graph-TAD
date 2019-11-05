package v1;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph<E> {
	
	private Map<E, List<E>> adjVertices;

	public Graph(Map<E, List<E>> adjVertices) {
		this.adjVertices = adjVertices;
	}
	
	void addVertex(E e) {
	    adjVertices.putIfAbsent(e, new ArrayList<>());
	}
	 
	void removeVertex(E e1) {
	    adjVertices.values().stream().forEach(e -> e.remove(e1));
	    adjVertices.remove(e1);
	}
	void addEdge(E e1,E e2) {
	    adjVertices.get(e1).add(e2);
	    adjVertices.get(e2).add(e1);
	}
	void removeEdge(E e1, E e2) {
	    List<E> eV1 = adjVertices.get(e1);
	    List<E> eV2 = adjVertices.get(e2);
	    if (eV1 != null)
	        eV1.remove(e2);
	    if (eV2 != null)
	        eV2.remove(e1);
	}
	List<E> getAdjVertices(E e) {
	    return adjVertices.get(e);
	}
	Set<E> depthFirstTraversal(Graph<E> graph, E root) {
	    Set<E> visited = new LinkedHashSet<E>();
	    Stack<E> stack = new Stack<E>();
	    stack.push(root);
	    while (!stack.isEmpty()) {
	        E vertex = stack.pop();
	        if (!visited.contains(vertex)) {
	            visited.add(vertex);
	            for (E v : graph.getAdjVertices(vertex)) {              
	                stack.push(v);
	            }
	        }
	    }
	    return visited;
	}
	Set<E> breadthFirstTraversal(Graph<E> graph, E root) {
	    Set<E> visited = new LinkedHashSet<E>();
	    Queue<E> queue = new LinkedList<E>();
	    queue.add(root);
	    visited.add(root);
	    while (!queue.isEmpty()) {
	        E vertex = queue.poll();
	        for (E v : graph.getAdjVertices(vertex)) {
	            if (!visited.contains(v)) {
	                visited.add(v);
	                queue.add(v);
	            }
	        }
	    }
	    return visited;
	}

}
