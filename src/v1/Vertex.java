package v1;

public class Vertex<E> {
	
	static final double INFINITY=99999999999999999999999999999.9;
	
	E obj;
	Vertex<E> pre;
	double dist;
	
	public Vertex(E obj) {
		this.obj = obj;
		this.pre = null;
		this.dist = INFINITY;
	}
	
	

}
