package v2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;



public class GraphAM<T> implements Graph<T>{
	
	private Map<T, Integer> vertices;
	private List<VertexAM<T>> verticesLookUp;
	private int[][] adjMatrix;
	private int[][] distMatrix;
	private int index;
	private final boolean isDirected;
	private final boolean isWeighted;
	
	public GraphAM(int numVertices, boolean isDirected, boolean isWeighted) {
		adjMatrix = new int[numVertices][numVertices];
		distMatrix = new int[numVertices][numVertices];
		for(int i = 0; i < numVertices; i++) {
			for(int j = 0; j < numVertices; j++) {
				if(i == j) {
					distMatrix[i][i] = 0;
					adjMatrix[i][i] = 0;
				}else {
					adjMatrix[i][j] = INF;
					distMatrix[i][j] = INF;
				}
				
			}
			
		}
		index = 0;
		vertices = new HashMap<T, Integer>();
		verticesLookUp = new ArrayList<VertexAM<T>>();
		this.isDirected = isDirected;
		this.isWeighted = isWeighted;
	}

	
	public List<VertexAM<T>> getVerticesLookUp() {
		return verticesLookUp;
	}



	public void setVerticesLookUp(List<VertexAM<T>> verticesLookUp) {
		this.verticesLookUp = verticesLookUp;
	}



	public int[][] getAdjMatrix() {
		return adjMatrix;
	}



	public void setAdjMatrix(int[][] adjMatrix) {
		this.adjMatrix = adjMatrix;
	}



	public int getIndex() {
		return index;
	}



	public void setIndex(int index) {
		this.index = index;
	}



	public void setVertices(Map<T, Integer> vertices) {
		this.vertices = vertices;
	}
	@Override
	public void addVertex(T v) {
		VertexAM<T> vertex = new VertexAM<T>(v);
		addVertex(vertex);
	}

	@Override
	public void addVertex(VertexAM<T> v) throws IllegalArgumentException,IndexOutOfBoundsException {
		if(vertices.containsKey(v.getValue())) {
			throw new IllegalArgumentException("Vertex already exists in the graph");
		}else if(vertices.size() >= adjMatrix.length) {
			throw new IndexOutOfBoundsException();
		}
		vertices.put(v.getValue(), index);
		verticesLookUp.add(v);
		index++;
	}

	@Override
	public void addEdge(VertexAM<T> v1, VertexAM<T> v2) throws IllegalArgumentException {
		if(!vertices.containsKey(v1.getValue()) || !vertices.containsKey(v2.getValue())) {
			throw new IllegalArgumentException("Vertex not found");
		}
		int indexV1 = vertices.get(v1.getValue());
		int indexV2 = vertices.get(v2.getValue());
		adjMatrix[indexV1][indexV2] = 1;
		distMatrix[indexV1][indexV2] = 1;
		if(!isDirected) {
			distMatrix[indexV2][indexV1] = 1;
			adjMatrix[indexV2][indexV1] = 1;
			
		}
	}

	@Override
	public void addEdge(VertexAM<T> v1, VertexAM<T> v2, int w) throws IllegalArgumentException {
		if(!isWeighted) throw new IllegalArgumentException();
		if(!vertices.containsKey(v1.getValue()) || !vertices.containsKey(v2.getValue())) {
			throw new IllegalArgumentException("Vertex not found");
		}
		int indexV1 = vertices.get(v1.getValue());
		int indexV2 = vertices.get(v2.getValue());
		adjMatrix[indexV1][indexV2] = 1;
		distMatrix[indexV1][indexV2] = w;
		if(!isDirected) {
			distMatrix[indexV2][indexV1] = w;
			adjMatrix[indexV2][indexV1] = 1;
		}
	}

	@Override
	public void removeEdge(VertexAM<T> v1, VertexAM<T> v2) throws IllegalArgumentException {
		if(!vertices.containsKey(v1.getValue()) || !vertices.containsKey(v2.getValue())) {
			throw new IllegalArgumentException("Vertex not found");
		}
		int indexV1 = vertices.get(v1.getValue());
		int indexV2 = vertices.get(v2.getValue());
		adjMatrix[indexV1][indexV2] = INF;
		if(!isDirected) {
			adjMatrix[indexV2][indexV1] = INF;
		}
	}
	
	public int[][] getDistMatrix(){
		return distMatrix;
	}

	@Override
	public VertexAM<T> getVertex(T valueVertex) throws IllegalArgumentException {
		if(vertices.containsKey(valueVertex)) {
			return verticesLookUp.get(vertices.get(valueVertex));
		}
		throw new IllegalArgumentException("Vertex not found");
	}

	@Override
	public Iterable<VertexAM<T>> getVertices() {
		return verticesLookUp;
	}

	@Override
	public int getWeightEdge(VertexAM<T> v1, VertexAM<T> v2) throws IllegalArgumentException {
		int indexV1 = vertices.get(v1.getValue());
		int indexV2 = vertices.get(v2.getValue());
		return adjMatrix[indexV1][indexV2];
	}

	@Override
	public void setWeightEdge(VertexAM<T> v1, VertexAM<T> v2, int w) throws IllegalArgumentException {
		int indexV1 = vertices.get(v1.getValue());
		int indexV2 = vertices.get(v2.getValue());
		adjMatrix[indexV1][indexV2] = w;
		
	}
	@Override
	public void bfs(VertexAM<T> startVertex) {
		if(!vertices.containsKey(startVertex.getValue())) {
			throw new IllegalArgumentException("Vertex not found");
		}
		for(VertexAM<T> u: getVertices()) {
			if(!u.equals(startVertex)) {
				u.setColor(VertexAM.WHITE);
				u.setD(INF);
				u.setPred(null);
			}
		}
		startVertex.setColor(VertexAM.GRAY);
		startVertex.setD(0);
		startVertex.setPred(null);
		Queue<VertexAM<T>> queue = new LinkedList<VertexAM<T>>();
		queue.offer(startVertex);
		while(!queue.isEmpty()) {
			VertexAM<T> u = queue.poll();
			int index = vertices.get(u.getValue());
			for(int i = 0; i < vertices.size(); i++) {
				if(i != index) {
					if(adjMatrix[index][i] != INF) {
						VertexAM<T> v = verticesLookUp.get(i);
						if(v.getColor() == VertexAM.WHITE) {
							v.setColor(VertexAM.GRAY);
							v.setD(u.getD()+1);
							v.setPred(u);
							queue.offer(v);
						}
					}
				}
			}
			u.setColor(VertexAM.BLACK);
		}
		
	}
	
	@Override
	public boolean bellmanford(VertexAM<T> startVertex) {
		ShortestPath sp = new ShortestPath();
		int src = vertices.get(startVertex.getValue());
		return sp.bellmanford(distMatrix, src);
	}

	@Override
	public void dfs() {
		for(VertexAM<T> v : verticesLookUp) {
			v.setColor(VertexAM.WHITE);
		}
		for(VertexAM<T> u: verticesLookUp) {
			if(u.getColor() == VertexAM.WHITE) {
				dfsVisit(u);
			}
		}
	}
	
	public void dfsVisit(VertexAM<T> u) {
		u.setColor(VertexAM.BLACK);
		int indexU = vertices.get(u);
		for(int i = 0; i < vertices.size(); i++) {
			if(i != indexU) {
				VertexAM<T> v = verticesLookUp.get(i);
				if(adjMatrix[indexU][i] != INF && v.getColor() == VertexAM.WHITE) {
					v.setPred(u);
					dfsVisit(v);
				}
			}
		}
	}

	@Override
	public boolean areAdjacent(VertexAM<T> v1, VertexAM<T> v2) {
		int indexV1 = vertices.get(v1);
		int indexV2 = vertices.get(v2);
		
		return adjMatrix[indexV1][indexV2] == INF ? false:true;
	}
	
	@Override
	public ArrayList<EdgeAM<T>> getAdjacent(VertexAM<T> v){
		int index = vertices.get(v.getValue());
		ArrayList<EdgeAM<T>> result = new ArrayList<EdgeAM<T>>();
		for(int i = 0; i < adjMatrix[index].length; i++) {
			if(adjMatrix[index][i] != INF && adjMatrix[index][i] != 0) {
				result.add(new EdgeAM<T>(verticesLookUp.get(index),verticesLookUp.get(i),distMatrix[index][i]));
			}
		}
		return result;
	}
	
	public ArrayList<VertexAM<T>> getAdjacentVertices(VertexAM<T> v){
		int index = vertices.get(v.getValue());
		ArrayList<VertexAM<T>> result = new ArrayList<VertexAM<T>>();
		for(int i = 0; i < adjMatrix[index].length; i++) {
			if(adjMatrix[index][i] != INF && adjMatrix[index][i] != 0) {
				result.add(verticesLookUp.get(i));
			}
		}
		return result;
	}


	@Override
	public void dijkstra(VertexAM<T> s) {
		ShortestPath sp = new ShortestPath();
		int src = vertices.get(s.getValue());
		sp.dijkstra(distMatrix, src);
	}

	@Override
	public int getNumVertices() {
		return vertices.size();
	}

	@Override
	public int getNumEdges() {
		int numEdges = 0;
		for(int i = 0; i < vertices.size(); i++) {
			for(int j = 0; j < vertices.size(); j++) {
				if(j != i && adjMatrix[i][j] != INF) {
					numEdges++;
				}
			}
		}
		return numEdges;
	}

	static void floydWarshall(int graph[][]) 
    { 
        int dist[][] = new int[graph.length][graph.length]; 
        int i, j, k; 
  
        /* Initialize the solution matrix same as input graph matrix. 
           Or we can say the initial values of shortest distances 
           are based on shortest paths considering no intermediate 
           vertex. */
        for (i = 0; i < graph.length; i++) 
            for (j = 0; j < graph.length; j++) 
                dist[i][j] = graph[i][j]; 
  
        /* Add all vertices one by one to the set of intermediate 
           vertices. 
          ---> Before start of an iteration, we have shortest 
               distances between all pairs of vertices such that 
               the shortest distances consider only the vertices in 
               set {0, 1, 2, .. k-1} as intermediate vertices. 
          ----> After the end of an iteration, vertex no. k is added 
                to the set of intermediate vertices and the set 
                becomes {0, 1, 2, .. k} */
        for (k = 0; k < graph.length; k++) 
        { 
            // Pick all vertices as source one by one 
            for (i = 0; i < graph.length; i++) 
            { 
                // Pick all vertices as destination for the 
                // above picked source 
                for (j = 0; j < graph.length; j++) 
                { 
                    // If vertex k is on the shortest path from 
                    // i to j, then update the value of dist[i][j] 
                    if (dist[i][k] + dist[k][j] < dist[i][j]) 
                        dist[i][j] = dist[i][k] + dist[k][j]; 
                } 
            } 
        } 
  
        // Print the shortest distance matrix 
        printSolution(dist); 
    } 
  
    static void printSolution(int dist[][]) 
    { 
        System.out.println("The following matrix shows the shortest "+ 
                         "distances between every pair of vertices"); 
        for (int i=0; i<dist.length; ++i) 
        { 
            for (int j=0; j<dist.length; ++j) 
            { 
                if (dist[i][j]==10000000) 
                    System.out.print(-1 + "   "); 
                else
                    System.out.print(dist[i][j]+"   "); 
            } 
            System.out.println(); 
        } 
    } 

	
	@Override
	public void prim(VertexAM<T> r){
		for(VertexAM<T> v : verticesLookUp) {
			v.setD(INF);
			v.setPred(null);
			v.setColor(VertexAM.WHITE);
		}
		r.setD(0);
		r.setPred(null);
		PriorityQueue<VertexAM<T>> q = new PriorityQueue<>();
		q.offer(r);
		while(!q.isEmpty()) {
			VertexAM<T> u = q.poll();
			ArrayList<VertexAM<T>> adjVertices = getAdjacentVertices(u);
			for(VertexAM<T> v : adjVertices) {
				int w = getEdgeWeight(u,v);
				if(v.getColor() == VertexAM.WHITE && w < v.getD()) {
					v.setD(w);
					q.offer(v);
					v.setPred(u);
					
				}
				u.setColor(VertexAM.BLACK);
			}
		}
	}
	
	public int getEdgeWeight(VertexAM<T> u, VertexAM<T> v) {
		int posU = vertices.get(u.getValue());
		int posV = vertices.get(v.getValue());
		return distMatrix[posU][posV];
	}
	
	@Override
	public ArrayList<EdgeAM<T>> kruskal(){
		ArrayList<EdgeAM<T>> res = new ArrayList<>();
		int a = 0;
		int i = 0;
		ArrayList<EdgeAM<T>> edgesList = getEdges();
		Collections.sort(edgesList);
		UnionFindAM u = new UnionFindAM(verticesLookUp.size());
		
		while(a < verticesLookUp.size() -1 && i < edgesList.size()) {
			EdgeAM<T> edge = edgesList.get(i);
			i++;
			int x = u.find(verticesLookUp.indexOf(edge.initVertex()));
			int y = u.find(verticesLookUp.indexOf(edge.endVertex()));
			if(x != y) {
				res.add(edge);
				u.union(x, y);
			}
		}
		return res;
	}
	
	public ArrayList<EdgeAM<T>> getEdges(){
		ArrayList<EdgeAM<T>> edges = new ArrayList<>();
		for(int i = 0; i < adjMatrix.length; i++) {
			for(int j = 0; j < adjMatrix[0].length; j++) {
				if(adjMatrix[i][j] == 1) {
					edges.add(new EdgeAM<T>(verticesLookUp.get(i), verticesLookUp.get(j), distMatrix[i][j]));
				}
			}
		}
		return edges;
	}
	
	@Override
	public String toString() {
		String msg = "";
		for(int i = 0; i < vertices.size(); i++) {
			if(verticesLookUp.get(i) != null) {
				msg += verticesLookUp.get(i) + "-> ";
			}
			for(int j = 0; j < vertices.size(); j++) {
				if(j != i) {
					if(verticesLookUp.get(j) != null && adjMatrix[i][j] == 1) {
						msg += "(" + verticesLookUp.get(j)+ ", " + distMatrix[i][j] + ")";  
					}	
				}
			}
			msg+= "\n";
		}
		return msg;
		
		
	}
	
	private class ShortestPath{
		
		private int minDistance(int[] dist, boolean sptSet[]) {
			int min = INF;
			int min_index = -1;
			for(int i = 0; i < verticesLookUp.size(); i++) {
				if(sptSet[i] == false && dist[i] < min) {
					min = dist[i];
					min_index = i;
				}
			}
			
			return min_index;
		}
		
		private void dijkstra(int[][] graph, int src) {
			int[] dist = new int[verticesLookUp.size()];
			boolean[] sptSet = new boolean[verticesLookUp.size()];
			
			for(int i = 0; i < verticesLookUp.size(); i++) {
				dist[i] = INF;
				sptSet[i] = false;
			}
			
			dist[src] = 0;
			int u = -1;
			for(int count = 0; count < verticesLookUp.size() -1; count++) {
				u = minDistance(dist,sptSet);
				sptSet[u] = true;
				for(int v = 0; v < verticesLookUp.size(); v++) {
					if(!sptSet[v] && graph[u][v] != 0 && dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
						dist[v] = dist[u] + graph[u][v];
						verticesLookUp.get(v).setD(dist[u] + graph[u][v]);
						verticesLookUp.get(v).setPred(verticesLookUp.get(u));
					}
				}
			}
			distMatrix[src] = dist;
			
		}
		
		private boolean bellmanford(int[][] graph, int src) {
			int[] dist = new int[verticesLookUp.size()];
			boolean[] sptSet = new boolean[verticesLookUp.size()];
			for(int i = 0; i < verticesLookUp.size(); i++) {
				dist[i] = INF;
				sptSet[i] = false;
			}
			dist[src] = 0;
			int u = -1;
			for(int count = 0; count < verticesLookUp.size() -1; count++) {
				u = minDistance(dist,sptSet);
				sptSet[u] = true;
				for(int v = 0; v < verticesLookUp.size(); v++) {
					if(!sptSet[v] && graph[u][v] != 0 && dist[u] != INF && dist[u] + graph[u][v] < dist[v]) {
						dist[v] = dist[u] + graph[u][v];
						verticesLookUp.get(v).setD(dist[u] + graph[u][v]);
						verticesLookUp.get(v).setPred(verticesLookUp.get(u));
					}
				}
			}
			
			for(int e = 0; e < verticesLookUp.size(); e++) {
				for(int v = 0; v < verticesLookUp.size(); v++) {
					if(dist[e] != INF && dist[e] + graph[e][v] < dist[v]) {
						return false;
					}
				}
			}
			
			distMatrix[src] = dist;
			return true;
			
			
		}
	}

	@Override
	public int[][] floydWarshall() {
		// TODO Auto-generated method stub
		return null;
	}

}