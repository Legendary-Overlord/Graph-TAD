package v2;

import java.util.ArrayList;

public interface Graph<T> {
	
	public static final int INF = 10000000;
	public void addVertex(T id) throws IllegalArgumentException;
	public void addVertex(VertexAM<T> v)throws IllegalArgumentException;
	public void addEdge(VertexAM<T> v1, VertexAM<T> v2) throws IllegalArgumentException;
	public void addEdge(VertexAM<T> v1, VertexAM<T> v2, int w)throws IllegalArgumentException;
	public void removeEdge(VertexAM<T> v1, VertexAM<T> v2)throws IllegalArgumentException;
	public VertexAM<T> getVertex(T valueVertex) throws IllegalArgumentException;
	public Iterable<VertexAM<T>> getVertices();
	public int getWeightEdge(VertexAM<T> v1, VertexAM<T> v2) throws IllegalArgumentException;
	public void setWeightEdge(VertexAM<T> v1, VertexAM<T> v2, int w) throws IllegalArgumentException;
	public boolean areAdjacent(VertexAM<T> v1, VertexAM<T> v2);
	public void bfs(VertexAM<T> startVertex);
	public void dfs();
	public void dijkstra(VertexAM<T> s);
	public boolean bellmanford(VertexAM<T> startVertex);
	public int[][] floydWarshall();
	public int getNumVertices();
	public int getNumEdges();
	public ArrayList<EdgeAM<T>> kruskal();
	public void prim(VertexAM<T> s);
	public ArrayList<EdgeAM<T>> getAdjacent(VertexAM<T> s); 
}