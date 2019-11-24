package v2;



public class EdgeAM<T> implements Comparable<EdgeAM<T>>{
	private VertexAM<T> v1;
	private VertexAM<T> v2;
	private int w;

	
	public EdgeAM(VertexAM<T> v1, VertexAM<T> v2, int w) {
		this.v1 = v1; 
		this.v2 = v2;
		this.w = w;
	}
	
	
	public VertexAM<T> initVertex(){
		return v1;
	}
	
	public VertexAM<T> endVertex(){
		return v2;
	}
	
	public int getWeight() {
		return w;
	}
	
	public void setWeight(int w) {
		this.w = w;
	}


	@Override
	public String toString() {
		return "(" + v2 + "," + w + ")";
	}

	@Override
	public int compareTo(EdgeAM<T> o) {
		if(w > o.getWeight()) {
			return 1;
		}else if(w == o.getWeight()) {
			return 0;
		}else {
			return -1;
		}
	}
	
}