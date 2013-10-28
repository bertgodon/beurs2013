package be.scouts.beurs.admin;

import java.io.Serializable;
import java.util.List;

import be.scouts.beurs.data.DrankData;

public class AdminData implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6808369758007140902L;
	private List<DrankData> drinks;
	private String totalAmount;
	private String previousTotal;
	public List<DrankData> getDrinks() {
		return drinks;
	}
	public void setDrinks(List<DrankData> drinks) {
		this.drinks = drinks;
	}
	public String getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
	public String getPreviousTotal() {
		return previousTotal;
	}
	public void setPreviousTotal(String previousTotal) {
		this.previousTotal = previousTotal;
	}
	
	
	
}
