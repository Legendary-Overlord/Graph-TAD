package v2;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class GraphAM<E> {

    //Maps vertex with its adjacency matrix index. O(1) to retrieve index of a vertex
    private Map<E, Integer> vertex;
    //To get vertex using index at O(1) time
    private List<E> vertexLookup;
    private List<EdgeAM<E>> weightEdge;
    //adjacency matrix
    private int[][] adj;
    private int index;
    private boolean directed;
    
    public GraphAM(int numVertex, boolean directed) {
        adj = new int[numVertex][numVertex];
        index = 0;
        vertex = new HashMap<>();
        vertexLookup = new ArrayList<>();
        this.directed=directed;
    }

     void addEdge(E from, E to) {
        addVertex(from);
        addVertex(to);

        int fromIndex = vertex.get(from);
        int toIndex = vertex.get(to);
        if(!directed) {
        adj[fromIndex][toIndex] = 1;
        }
    }

     void addVertex(E e) {
        if(!vertex.containsKey(e)) {
        	vertex.put(e, index);
            vertexLookup.add(index, e);
            index++;
        }
    }

     void bfs(E start) {
        Queue<E> queue = new LinkedList<>();
        boolean[] visited = new boolean[vertex.size()]; 

        queue.add(start);
        int index = vertex.get(start);
        visited[index] = true;

        while(!queue.isEmpty()) {
            E e = queue.poll();
            System.out.print(e + " ");

            List<E> adjacentVertex = getAdjacentVertices(e);
            for(E a : adjacentVertex) {
                int adjInd = vertex.get(a);
                if(!visited[adjInd]) {
                    queue.add(a);
                    visited[adjInd] = true;
                }
            }

        }

    }

     void dfs(E start) {
        boolean[] visited = new boolean[vertex.size()];
        dfs(start, visited);
    }

    private void dfs(E e, boolean[] visited) {
        System.out.print(e + " ");
        int index = vertex.get(e);
        visited[index] = true;

        List<E> adjacentVertex = getAdjacentVertices(e);
        for(E a : adjacentVertex) {
            int aIndex = vertex.get(a);
            if(!visited[aIndex]) {
                dfs(a, visited);
            }
        }
    }
    public List<VertexAM<E>> dijkstra(GraphAM<E> graph, E r) {
    	VertexAM<E> root = new VertexAM<>(r);
    	root.dist=0;
    	List<VertexAM<E>> theVertices = new ArrayList<>();
    	theVertices.add(root);
    	PriorityQueue<VertexAM<E>> q = new PriorityQueue<>(new PQComparator());
    	vertexLookup.forEach(e->{
    		if(theVertices.contains(new VertexAM<E>(e)))
    		theVertices.add(new VertexAM<E>(e));
    	});
    	theVertices.forEach(e->{
    		if(e!=root)
    			e.dist=VertexAM.INFINITY;
    		q.add(e);
    	});
    	while(!q.isEmpty()) {
    		VertexAM<E> u = q.poll();
    		for(E v:getAdjacentVertices(u.obj)) {
    			VertexAM<E> tmp = getVertexFromList(theVertices,v);
    			double alt =(double)tmp.dist+adj[vertex.get(u.obj)][vertex.get(v)];
    			if(tmp.dist>alt) {
    				tmp.dist=alt;
		        	tmp.pre=u;
		        	q.remove(tmp);
		        	q.add(tmp);
    			}
    		}
    	}
    	return theVertices;
    }
    private VertexAM<E> getVertexFromList(List<VertexAM<E>> ls, E obj){
    	VertexAM<E> tmp = null;
    	for(VertexAM<E> v:ls) {
    		tmp=(v.obj.equals(obj))?v:null;
    	}
    	return tmp;
    }
    public void FloydWarshallSolver() {
    	double[][] dp;
    	double[][] next;
        index = adj.length;
        dp = new double[index][index];
        next = new double[index][index];

        // Copy input matrix and setup 'next' matrix for path reconstruction.
        for (int i = 0; i < index; i++) {
          for (int j = 0; j < index; j++) {
            if (adj[i][j] != VertexAM.INFINITY) next[i][j] = j;
            dp[i][j] = adj[i][j];
          }
        }
      }
  
    private List<E> getAdjacentVertices(E e) {
        int index = vertex.get(e);
        List<E> result = new ArrayList<>();
        for(int i=0; i<adj[index].length; i++) {
            if(adj[index][i] == 1) {
                result.add(vertexLookup.get(i));
            }
        }
        return result;
    }   	


	private List<EdgeAM<E>> prim(List<EdgeAM<E>> weightEdge) {
		List<EdgeAM<E>> finalList = null;
		for(int i = 1; i <= 2; i++) {
			finalList.add(weightEdge.get(i));  	
		}
		return finalList;
	}
	
	private double kruskal(List<EdgeAM<E>> weightEdge) {
		double t = 0;
		for(int i = 1; i <= weightEdge.size()-1; i++) {
			  	t += weightEdge.get(i).weight;
		}
		return t;
	}
	
	
}


@SuppressWarnings("rawtypes")
class PQComparator implements Comparator<VertexAM>{
	@Override
	public int compare(VertexAM o1, VertexAM o2) {
		Double a1=o1.dist;
		Double a2 = o2.dist;
		return a2.compareTo(a1);
	}
}