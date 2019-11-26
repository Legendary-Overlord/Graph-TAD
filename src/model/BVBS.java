package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import v1.Graph;

public class BVBS {
	
	private static final String CSV_PATH = "NodeMap.csv";
	
	Graph<Station> graph;
	int stationNum;
	ArrayList<Station> sts;
	
	public BVBS(boolean directed) {
		this.graph = new Graph<>(directed);
		this.stationNum = 0;
		sts = new ArrayList<>();
		try {
			loadCSV();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				graph.addEdge(findStation(sts,s[0]), findStation(sts,stations.get(i-1).split(",")[0]), dist);
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
	
	
	

}
