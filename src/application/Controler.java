package application;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Set;

import exceptions.UserInputException;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.StrokeType;
import model.BVBS;
import model.Station;
import v1.Vertex;

public class Controler {
	
	@FXML
	private Pane pane;
	@FXML
    void calculatePath(ActionEvent event) {
		try {
			TextInputDialog originDialog = new TextInputDialog("");
			originDialog.setTitle("Origin");
			originDialog.setHeaderText("Please type the origin Station:");
			originDialog.setContentText("Station Name: ");
			TextInputDialog destinyDialog = new TextInputDialog("");
			destinyDialog.setTitle("Destination");
			destinyDialog.setHeaderText("Please type your destination station:");
			destinyDialog.setContentText("Station Name: ");
			TextInputDialog methodDialog = new TextInputDialog("");
			methodDialog.setTitle("Factor");
			methodDialog.setHeaderText("Please select an option (1-2) on how to find your route");
			methodDialog.setContentText("1. Least ammount on stations to travel. 2. Least amount of time spent");
			boolean opt = false;
			String origin="";
			String dest="";
			Optional<String> resultOrigin = originDialog.showAndWait();
			if(resultOrigin.isPresent()) {
				origin = resultOrigin.get();
			}
			Optional<String> resultDestination = destinyDialog.showAndWait();
			if(resultDestination.isPresent())
				dest=resultDestination.get();
			Optional<String> result = methodDialog.showAndWait();
			if (result.isPresent()){
				if(result.get().equals("1"))
					opt=true;
				else if(result.get().equals("2"))
						opt=false;
				else {
					throw new UserInputException();
				}
					
			}
			ArrayList<Station> pathos = new ArrayList<>();
			Set<Vertex<Station>> vertexPathos = (opt)?sys.findPathBFS(origin, dest):sys.findPathDijkstra(origin, dest);
			vertexPathos.forEach(e->pathos.add(e.getObj()));
//			pathos.forEach(e->System.out.println(e.toString()));
			displayPath(pathos);
			
		}catch(UserInputException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error");
			alert.setHeaderText("UserInputException");
			alert.setContentText("Your Input is invalid. Please Try Again.");

			alert.showAndWait();
		}
    }
	
	private BVBS sys = new BVBS(false);
	    
//	private ObservableList<Node> childrenList = pane.getChildren();

	    

    public void displayPath(ArrayList<Station> visit){
//    	pane.getChildren().clear();
//    	pane.getChildren().addAll(childrenList);
    	visit.forEach(e->{
    		Circle x = new Circle();
    		x.setRadius(7.0);
    		x.setLayoutX(e.getX());
    		x.setLayoutY(e.getY());
    		if(e.getLines().size()>1)
    			x.setFill(Paint.valueOf("#000000"));
    		else
    			x.setFill(Paint.valueOf(giveStationColor(e.getLines().get(0))));
    		x.setStroke(Paint.valueOf("#000000"));
    		x.setStrokeType(StrokeType.INSIDE);
    		pane.getChildren().add(x);
    	});
    	System.out.println(pane.getChildren().size());
    }
    private String giveStationColor(String line) {
    	String color="";
    	switch(line) {
    	case "U1":
    		color="#1fff4a";
    		break;
    	case "U2":
    		color="#f35b0e";
    		break;
    	case "U3":
    		color="#61abd0";
    		break;
    	case "U4":
    		color="#ffea4e";
    		break;
    	case "U5":
    		color="#804000";
    		break;
    	case "U55":
    		color="#804001";
    		break;
    	case "U6":
    		color="#8c6797";
    		break;
    	case "U7":
    		color="#61abd0";
    		break;
    	case "U8":
    		color="#2742b9";
    		break;
    	case "U9":
    		color="#fe9737";
    		break;
    	case "S1":
    		color="#f885eb";
    		break;
    	case "S2":
    		color="#4cc234";
    		break;
    	case "S3":
    		color="#2742b8";
    		break;
    	case "S25":
    		color="#fe9737";
    		break;
    	case "S41":
    		color="#eb984e";
    		break;
    	case "S45":
    		color="#eb983e";
    		break;
    	case "S7":
    		color="#d868da";
    		break;
    	case "S8":
    		color="#4cc236";
    		break;
    	}
    	return color;
    }
}
	    

	
	