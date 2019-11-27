package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;

import v1.Graph;
import v1.Vertex;

public class BVBS {
	
	private static final String CSV_PATH = "NodeMap.csv";
	
	Graph<Station> graph;
	int stationNum;
	ArrayList<Station> sts;
	
	public BVBS(boolean directed) {
		this.graph = new Graph<>(directed);
		sts = new ArrayList<>();
		try {
			loadCSV();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		stationNum = sts.size();
	}
	
	private void loadCSV() throws IOException {
		File data = new File(CSV_PATH);
		BufferedReader br = new BufferedReader(new FileReader(data));
		ArrayList<String> stations = new ArrayList<>();
		String st;
		boolean ft = true;
		while((st=br.readLine())!=null) {
			if(ft) {
				ft=false;
			}else {
				String[] s = st.split(",");
				Station a = findStation(sts,s[0]);
				if(a!=null) {
					a.addLine(s[1]);
				}else {
					Station x = new Station(Float.parseFloat(s[2]),Float.parseFloat(s[3]),s[0]);
					x.addLine(s[1]);
					sts.add(x);
				}
				stations.add(st);
			}
		}
		br.close();
		sts.forEach(e->graph.addVertex(e));
		for(int i=0;i<stations.size();i++) {
			String[] s = stations.get(i).split(",");
			int dist = Integer.parseInt(s[4]);
			if(dist!=0) {
				graph.addEdge(findStation(sts,stations.get(i-1).split(",")[0]),findStation(sts,s[0]), dist);
			}
		}
	}
	private Station findStation(ArrayList<Station> c, String name) {
		Station x=null;
		for(Station s:c) {
			if(s.getName().equals(name)) {
				x=s;
				break;
			}
		}
		return x;
	}
	public Set<Vertex<Station>> findPathBFS(String rootStation, String destination){
		Station origin = findStation(sts,rootStation);
		Station dest = findStation(sts,destination);
		graph.breadthFirstSearch(graph, graph.findVertex(origin));
		return graph.printPath(graph, graph.findVertex(origin), graph.findVertex(dest));
	}
	public Set<Vertex<Station>> findPathDijkstra(String rootStation, String destination){
		Station origin = findStation(sts,rootStation);
		Station dest = findStation(sts,destination);
		graph.dijkstra(graph, graph.findVertex(origin));
		return graph.printPath(graph, graph.findVertex(origin), graph.findVertex(dest));
	}

	public ArrayList<Station> getSts() {
		return sts;
	}

	public void setSts(ArrayList<Station> sts) {
		this.sts = sts;
	}
	
	
	

}
