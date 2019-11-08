package v1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph<E> {

	private Map<Vertex<E>, List<Vertex<E>>> adjVertices;

	public Graph(Map<Vertex<E>, List<Vertex<E>>> adjVertices) {
		this.adjVertices = adjVertices;
	}

	public void addVertex(E e) {
	    adjVertices.putIfAbsent(new Vertex<E>(e), new ArrayList<>());
	}

	public void removeVertex(E obj) {
	    Vertex<E> v = new Vertex<E>(obj);
	    adjVertices.values().stream().forEach(e -> e.remove(v));
	    adjVertices.remove(new Vertex<E>(obj));
	}
	public void addEdge(E obj1, E obj2) {
	    Vertex<E> v1 = new Vertex<E>(obj1);
	    Vertex<E> v2 = new Vertex<E>(obj2);
	    adjVertices.get(v1).add(v2);
	    adjVertices.get(v2).add(v1);
	}
	public void removeEdge(E obj1, E obj2) {
	    Vertex<E> v1 = new Vertex<E>(obj1);
	    Vertex<E> v2 = new Vertex<E>(obj2);
	    List<Vertex<E>> eV1 = adjVertices.get(v1);
	    List<Vertex<E>> eV2 = adjVertices.get(v2);
	    if (eV1 != null)
	        eV1.remove(v2);
	    if (eV2 != null)
	        eV2.remove(v1);
	}
	public List<Vertex<E>> getAdjVertices(E e) {
	    return adjVertices.get(new Vertex<E>(e));
	}
	public Set<Vertex<E>> depthFirstSearch(Graph<E> graph, Vertex<E> root) {
		resetVertices();
	    Set<Vertex<E>> visited = new LinkedHashSet<Vertex<E>>();
	    Stack<Vertex<E>> stack = new Stack<Vertex<E>>();
	    stack.push(root);
	    while (!stack.isEmpty()) {
	    	Vertex<E> Vertex = stack.pop();
	        if (!visited.contains(Vertex)) {
	            visited.add(Vertex);
	            for (Vertex<E> v : graph.getAdjVertices(Vertex.obj)) {              
	                stack.push(v);
	            }
	        }
	    }
	    return visited;
	}
	public Set<Vertex<E>> breadthFirstSearch(Graph<E> graph, Vertex<E> root) {
		resetVertices();
	    Set<Vertex<E>> visited = new LinkedHashSet<Vertex<E>>();
	    Queue<Vertex<E>> queue = new LinkedList<Vertex<E>>();
	    queue.add(root);
	    visited.add(root);
	    while (!queue.isEmpty()) {
	    	Vertex<E> Vertex = queue.poll();
	        for (Vertex<E> v : graph.getAdjVertices(Vertex.obj)) {
	            if (!visited.contains(v)) {
	            	v.dist++;
	            	v.pre=Vertex;
	                visited.add(v);
	                queue.add(v);
	            }
	        }
	    }
	    return visited;
	}
	public Set<Vertex<E>> printPath(Graph<E> graph, Vertex<E>a,Vertex<E>b){
		LinkedHashSet<Vertex<E>> list = new LinkedHashSet<Vertex<E>>();
		printPath(graph,a,b,list);
		return list;
	}
	
	private void printPath(Graph<E> graph,Vertex<E>a,Vertex<E>b,LinkedHashSet<Vertex<E>> lhs) {
		if(a==b) {
			lhs.add(a);
		}else if(a.pre==null) {
		//do nothing	
		}else {
			printPath(graph,a,b.pre,lhs);
			lhs.add(a);
		}
	}
//	private void resetVertexCalculations(Graph<E> graph, Vertex<E> root) {
//		root.dist=0;
//		root.pre=null;
//		Set<Vertex<E>> visited = new LinkedHashSet<Vertex<E>>();
//	    Queue<Vertex<E>> queue = new LinkedList<Vertex<E>>();
//	    queue.add(root);
//	    visited.add(root);
//	    while (!queue.isEmpty()) {
//	    	Vertex<E> Vertex = queue.poll();
//	        for (Vertex<E> v : graph.getAdjVertices(Vertex.obj)) {
//	            if (!visited.contains(v)) {
//	            	v.dist=99999999;
//	            	v.pre=null;
//	                visited.add(v);
//	                queue.add(v);
//	            }
//	        }
//	    }
//	}
	private void resetVertices() {
		for(Map.Entry<Vertex<E>, List<Vertex<E>>> v : adjVertices.entrySet()) {
			Vertex<E> vertex = v.getKey();
			vertex.dist=Vertex.INFINITY;
			vertex.pre=null;
		}
	}
	public void dijkstra(Graph<E> graph, Vertex<E> a, Vertex<E> b) {
		a.dist=0;
		PriorityQueue<Vertex<E>> Q = new PriorityQueue<>(new PQComparator());
		for(Map.Entry<Vertex<E>, List<Vertex<E>>> v: adjVertices.entrySet()) {
			Vertex<E> temp=null;
			if((temp=v.getKey())!=a) {
				temp.dist=Vertex.INFINITY;
			}
			temp.pre=null;
			Q.add(temp);
		}
		while(!Q.isEmpty()) {
			Vertex<E> u = Q.poll();
			 for (Vertex<E> v : graph.getAdjVertices(u.obj)) {
		           double alt = v.dist; //+weight(u,v)
		           if(alt<v.dist) {
		        	   v.dist=alt;
		        	   v.pre=u;
		        	   Q.remove(v);
		        	   Q.add(v);
		           }
		        }
		}
	}

}
class PQComparator implements Comparator<Vertex>{
	@Override
	public int compare(Vertex o1, Vertex o2) {
		Double a1=o1.dist;
		Double a2 = o2.dist;
		return a2.compareTo(a1);
	}
}
