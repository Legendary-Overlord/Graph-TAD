package v2;

public class EdgeAM<E> {
	VertexAM<E> a;
	VertexAM<E> b;
	double weight;
	public EdgeAM(VertexAM<E> a, VertexAM<E> b, double weight) {
		this.a = a;
		this.b = b;
		this.weight = weight;
	}
}


