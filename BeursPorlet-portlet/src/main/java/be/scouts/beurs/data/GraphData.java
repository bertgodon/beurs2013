package be.scouts.beurs.data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class GraphData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7638150272205000675L;

	
	public GraphData() {
		this.lineValue = new ArrayList<String>();
		this.beers = new ArrayList<String>();

	}
	private String horizontalValue;
	private List<String> lineValue;
	private List<String> beers;
	
	public String getHorizontalValue() {
		return horizontalValue;
	}
	public void setHorizontalValue(String horizontalValue) {
		this.horizontalValue = horizontalValue;
	}
	public List<String> getLineValue() {
		return lineValue;
	}
	public void setLineValue(List<String> lineValue) {
		this.lineValue = lineValue;
	}
	public List<String> getBeers() {
		return beers;
	}
	public void setBeers(List<String> beers) {
		this.beers = beers;
	}
	
	
}
