package v2;

public class VertexAM<E> {
	

	static final double INFINITY=99999999999999999999999999999.9;
	E obj;
	VertexAM<E> pre;
	double dist;
	
	public VertexAM(E obj) {
		this.obj = obj;
		this.pre = null;
		this.dist = INFINITY;
	}
}
		
