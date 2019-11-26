package model;

import java.util.ArrayList;
import java.util.List;

public class Station {
	
	private float x;
	private float y;
	private String name;
	private List<String> Lines;
	
	public Station(float x, float y, String name) {
		this.x = x;
		this.y = y;
		this.name = name;
		Lines = new ArrayList<>();
	}
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void addLine(String line) {
		Lines.add(line);
	}
	public List<String> getLines() {
		return Lines;
	}
	public void setLines(List<String> lines) {
		Lines = lines;
	}
	@Override
	public String toString() {
		return "Station [x=" + x + ", y=" + y + ", name=" + name + ", Lines=" + Lines + "]";
	}

}
