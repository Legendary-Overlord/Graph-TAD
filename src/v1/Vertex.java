package v1;

public class Vertex<E> {
	E obj;
	Vertex<E> pre;
	double dist;
	public Vertex(E obj) {
		this.obj = obj;
		this.pre = null;
		this.dist = 99999999999999999999999999999.9;
	}
	
	

}
