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

	@Override
	public String toString() {
		return "Vertex [obj=" + obj.toString() + ", pre=" + pre + ", dist=" + dist + "]";
	}

	public E getObj() {
		return obj;
	}

	public Vertex<E> getPre() {
		return pre;
	}

	public double getDist() {
		return dist;
	}
	
	public void setDist(int dist) {
		this.dist = dist;
	}
	
	public void setPre(Vertex<E> pre){
		this.pre = pre;
		
	}
	
	

}
