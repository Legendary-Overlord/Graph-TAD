package model;

import java.util.List;

public class Station {
	
	private float x;
	private float y;
	private String name;
	private List<String> Lines;
	
	public Station(float x, float y, String name, List<String> lines) {
		super();
		this.x = x;
		this.y = y;
		this.name = name;
		Lines = lines;
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
	public List<String> getLines() {
		return Lines;
	}
	public void setLines(List<String> lines) {
		Lines = lines;
	}

}
