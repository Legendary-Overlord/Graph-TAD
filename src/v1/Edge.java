package v1;

public class Edge<E> {
	
	Vertex<E> a;
	Vertex<E> b;
	double weight;
	public Edge(Vertex<E> a, Vertex<E> b, double weight) {
		this.a = a;
		this.b = b;
		this.weight = weight;
	}
}
