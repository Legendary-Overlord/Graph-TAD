package v1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class Graph<E> {

	private Map<Vertex<E>, List<Vertex<E>>> adjVertices;
	private Map<Vertex<E>,Integer> vertexNum;  
	private List<Edge<E>> edgeWeight;
	private int gSize;
	private boolean directed;

	public Graph(boolean directed) {
		this.adjVertices = new HashMap<>();
		vertexNum = new HashMap<>();
		edgeWeight = new ArrayList<>();
		gSize=0;
		this.directed=directed;
	}

	public void addVertex(E e) {
			Vertex<E> v= new Vertex<>(e);
			adjVertices.put(v, new ArrayList<>());
			vertexNum.putIfAbsent(v, gSize);
			gSize++;
	}

	public void removeVertex(E obj) {
	    Vertex<E> v = new Vertex<E>(obj);
	    adjVertices.values().stream().forEach(e -> e.remove(v));
	    adjVertices.remove(v);
	    int n = vertexNum.get(v);
	    for(Map.Entry<Vertex<E>, Integer> x:vertexNum.entrySet()) {
	    	if(x.getValue()>n)
	    		x.setValue(x.getValue()-1);
	    }
	}
	public Vertex<E> findVertex(E a){
		Vertex<E>x=null;
		for(Map.Entry<Vertex<E>, List<Vertex<E>>> v : adjVertices.entrySet()) {
			
			E vertex = v.getKey().obj;
			if(vertex==a) {
				x=v.getKey();
				break;
			}
		}
		return x;
	}
	public void addEdge(E obj1, E obj2) {
		Vertex<E> v1 = findVertex(obj1);
	    Vertex<E> v2 = findVertex(obj2);
	    adjVertices.get(v1).add(v2);
	    adjVertices.get(v2).add(v1);
	    Edge<E> x = new Edge<>(v1,v2,1.0);
	    if(!edgeWeight.contains(x))
	    	edgeWeight.add(x);
	    if(!directed) {
	    	Edge<E> r = new Edge<>(v2,v1,1.0);
	    	if(!edgeWeight.contains(r))
		    	edgeWeight.add(r);
	    }
	}
	public void addEdge(E obj1, E obj2, double w) {
		Vertex<E> v1 = findVertex(obj1);
	    Vertex<E> v2 = findVertex(obj2);
	    adjVertices.get(v1).add(v2);
	    adjVertices.get(v2).add(v1);
	    Edge<E> x = new Edge<>(v1,v2,w);
	    edgeWeight.add(x);
	    if(!directed) {
	    	Edge<E> r = new Edge<>(v2,v1,w);
		    edgeWeight.add(r);
	    }
	}
	public Edge<E> getEdge(Vertex<E>a,Vertex<E>b){
		Edge<E> ans=null;
		for(int i=0;i<edgeWeight.size();i++) {
			Edge<E> temp=edgeWeight.get(i);
			if(temp.a.equals(a)&&temp.b.equals(b)) {
				ans=temp;
				break;
			}
		}
		return ans;
	}
	public void removeEdge(E obj1, E obj2) {
	    Vertex<E> v1 = new Vertex<E>(obj1);
	    Vertex<E> v2 = new Vertex<E>(obj2);
	    List<Vertex<E>> eV1 = adjVertices.get(v1);
	    List<Vertex<E>> eV2 = adjVertices.get(v2);
	    if (eV1 != null)
	        eV1.remove(v2);
	    if (eV2 != null)
	        eV2.remove(v1);
	    Edge<E> rm = getEdge(v1,v2);
	    if(rm!=null)
	    	edgeWeight.remove(rm);
	}
	public List<Vertex<E>> getAdjVertices(E e) {
	    return adjVertices.get(findVertex(e));
	}
	public Set<Vertex<E>> depthFirstSearch(Graph<E> graph, Vertex<E> root) {
		resetVertices();
	    Set<Vertex<E>> visited = new LinkedHashSet<Vertex<E>>();
	    Stack<Vertex<E>> stack = new Stack<Vertex<E>>();
	    stack.push(root);
	    while (!stack.isEmpty()) {
	    	Vertex<E> Vertex = stack.pop();
	        if (!visited.contains(Vertex)) {
	            visited.add(Vertex);
	            for (Vertex<E> v : graph.getAdjVertices(Vertex.obj)) {              
	                stack.push(v);
	            }
	        }
	    }
	    return visited;
	}
	public Set<Vertex<E>> breadthFirstSearch(Graph<E> graph, Vertex<E> root) {
		resetVertices();
	    Set<Vertex<E>> visited = new LinkedHashSet<Vertex<E>>();
	    Queue<Vertex<E>> queue = new LinkedList<Vertex<E>>();
	    queue.add(root);
	    visited.add(root);
	    while (!queue.isEmpty()) {
	    	Vertex<E> Vertex = queue.poll();
	        for (Vertex<E> v : graph.getAdjVertices(Vertex.obj)) {
	            if (!visited.contains(v)) {
	            	v.dist++;
	            	v.pre=Vertex;
	                visited.add(v);
	                queue.add(v);
	            }
	        }
	    }
	    return visited;
	}
	public Set<Vertex<E>> printPath(Graph<E> graph, Vertex<E>a,Vertex<E>b){
		Set<Vertex<E>> list = new LinkedHashSet<Vertex<E>>();
		printPath(graph,a,b,list);
		return list;
	}
	
	private void printPath(Graph<E> graph,Vertex<E>a,Vertex<E>b,Set<Vertex<E>> lhs) {
		if(a==b) {
			lhs.add(a);
		}else if(a.pre==null) {
			System.out.println(b.pre.toString());
			printPath(graph,a,b.pre,lhs);
			lhs.add(a);
		}
	}
	private void resetVertices() {
		for(Map.Entry<Vertex<E>, List<Vertex<E>>> v : adjVertices.entrySet()) {
			Vertex<E> vertex = v.getKey();
			vertex.dist=Vertex.INFINITY;
			vertex.pre=null;
		}
	}
	private void initGraph(Graph<E> g) {
		g.adjVertices=adjVertices;
		g.edgeWeight=edgeWeight;
		g.vertexNum=vertexNum;
		g.gSize=gSize;
	}
	public void dijkstra(Graph<E> graph, Vertex<E> a) {
		a.dist=0;
		initGraph(graph);
		PriorityQueue<Vertex<E>> Q = new PriorityQueue<>(new PQComparator());
		for(Map.Entry<Vertex<E>, List<Vertex<E>>> v: graph.adjVertices.entrySet()) {
			Vertex<E> temp=null;
			if((temp=v.getKey())!=a) {
				temp.dist=Vertex.INFINITY;
			}
			temp.pre=null;
			Q.add(temp);
		}
		while(!Q.isEmpty()) {
			Vertex<E> u = Q.poll();
			 for (Vertex<E> v : graph.getAdjVertices(u.obj)) {
				 Edge<E> x = graph.getEdge(u,v);
				 double w=0;
				 if(x!=null)
					 w=x.weight;
		           double alt = v.dist+w;
		           if(alt<v.dist) {
		        	   v.dist=alt;
		        	   v.pre=u;
		        	   Q.remove(v);
		        	   Q.add(v);
		           }
		        }
		}
	}
	public double[][] floydWarshall() {
		int v = adjVertices.size();
		double[][] dist = new double[v][v];
		for(int i=0;i<v;i++) {
			for(int j=0;j<v;j++)
				dist[i][j]=Vertex.INFINITY;
		}
		for(int i = 0,j=0;i<v;i++,j++) {
			dist[i][j]=0;
		}
		edgeWeight.forEach(e->{
			dist[vertexNum.get(e.a)][vertexNum.get(e.b)]=e.weight;
		});
		//foreach dist[v][v]=0
		for(int k=0;k<v;k++) {
			for(int i=0;i<v;i++) {
				for(int j=0;j<v;j++) {
					if (dist[i][j]>(dist[i][k]+dist[k][j]))
						dist[i][j]=dist[i][k]+dist[k][j];
				}
			}
		}
		return dist;
	}
	public void prim(Graph<E> g) {
		initGraph(g);
		PriorityQueue<Vertex<E>> q =new PriorityQueue<>(new PQComparator());
		for(Map.Entry<Vertex<E>, List<Vertex<E>>> v : g.adjVertices.entrySet()) {
			Vertex<E> vertex = v.getKey();
			vertex.dist=Vertex.INFINITY;
			vertex.pre=null;
			q.add(vertex);
		}
		Vertex<E> r = q.poll();
		r.dist=0;
		q.add(r);
		while(!q.isEmpty()) {
			Vertex<E> u = q.poll();
			for (Vertex<E> v : g.getAdjVertices(u.obj)) {
				Edge<E>x=g.getEdge(u, v);
				double w=0;
				if(x!=null)
					w=x.weight;
		           if(q.contains(v)&&(v.dist>w)) {
		        	   v.dist=w;
		        	   v.pre=u;
		        	   q.remove(v);
		        	   q.add(v);
		           }
		        }
		}
	}
	public void makeSet(int [] parent){
        //Make set- creating a new element with a parent pointer to itself.
        for (int i = 0; i <gSize ; i++) {
            parent[i] = i;
        }
    }
	public int find(int [] parent, int vertex){
        //chain of parent pointers from x upwards through the tree
        // until an element is reached whose parent is itself
        if(parent[vertex]!=vertex)
            return find(parent, parent[vertex]);;
        return vertex;
    }
	public void union(int [] parent, int x, int y){
        int x_set_parent = find(parent, x);
        int y_set_parent = find(parent, y);
        //make x as parent of y
        parent[y_set_parent] = x_set_parent;
    }
	public ArrayList<Edge<E>> kruskal(){
        PriorityQueue<Edge<E>> pq = new PriorityQueue<>(adjVertices.size(), Comparator.comparingDouble(o -> o.weight));

        //add all the edges to priority queue, //sort the edges on weights
        for(Edge<E> e: edgeWeight)
        	pq.add(e);
        //create a parent []
        int [] parent = new int[gSize];

        //makeset
        makeSet(parent);

        ArrayList<Edge<E>> mst = new ArrayList<>();

        //process vertices - 1 edges
        int index = 0;
        while((index<gSize-1)||!pq.isEmpty()){
            Edge<E> edge = pq.poll();
            //check if adding this edge creates a cycle
            int x_set = find(parent, vertexNum.get(findVertex(edge.a.obj)));
            int y_set = find(parent, vertexNum.get(findVertex(edge.b.obj)));

            if(x_set==y_set){
                //ignore, will create cycle
            	index++;
            }else {
                //add it to our final result
                mst.add(edge);
                index++;
                union(parent,x_set,y_set);
            }
        }
        //print MST
//        System.out.println("Minimum Spanning Tree: ");
//        printMST(mst);
        int gNum=0;
        int[] c = new int[gSize];
        for(int i=0;i<gSize;i++){
        	c[parent[i]]+=1;
        }
        for(int i=0;i<gSize;i++) {
        	if(c[i]!=0)
        		gNum++;
        }
        System.out.println(gNum);
        return mst;
    }
	   public void printMST(ArrayList<Edge<E>> edgeList){
           for (int i = 0; i <edgeList.size() ; i++) {
               Edge<E> edge = edgeList.get(i);
               System.out.println("Edge-" + i + " source: " + edge.a +
                       " destination: " + edge.a +
                       " weight: " + edge.weight);
           }
	   }
}
@SuppressWarnings("rawtypes")
class PQComparator implements Comparator<Vertex>{
	@Override
	public int compare(Vertex o1, Vertex o2) {
		Double a1=o1.dist;
		Double a2 = o2.dist;
		return a2.compareTo(a1);
	}
}
