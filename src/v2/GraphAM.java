package v2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

public class GraphAM<E> {

    //Maps vertex with its adjacency matrix index. O(1) to retrieve index of a vertex
    private Map<E, Integer> vertex;
    //To get vertex using index at O(1) time
    private List<E> vertexLookup;

    //adjacency matrix
    private int[][] adj;

    private int index;

    public GraphAM(int numVertex) {
        adj = new int[numVertex][numVertex];
        index = 0;
        vertex = new HashMap<>();
        vertexLookup = new ArrayList<>();
    }

    public void addEdge(E from, E to) {
        addVertex(from);
        addVertex(to);

        int fromIndex = vertex.get(from);
        int toIndex = vertex.get(to);
        adj[fromIndex][toIndex] = 1;
    }

    private void addVertex(E e) {
        if(!vertex.containsKey(e)) {
        	vertex.put(e, index);
            vertexLookup.add(index, e);
            index++;
        }
    }

    public void bfs(E start) {
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

    public void dfs(E start) {
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

}