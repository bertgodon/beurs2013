package be.scouts.beurs.data;

import java.io.Serializable;
import java.util.Calendar;

public class DrankDataBase implements Serializable {

	/**
	 * DrankDataBase: this is for the DrankHistoryTable
	 */
	private static final long serialVersionUID = -3395818802152700240L;
	private String name;
	private String currentPrice;
	private Calendar time;

	public DrankDataBase(String name, String currentPrice) {
		this.name = name;
		this.currentPrice = currentPrice;
	}
	
	public DrankDataBase(String name, String currentPrice, Calendar time) {
		super();
		this.name = name;
		this.currentPrice = currentPrice;
		this.time = time;
	}
	
	public DrankDataBase() {
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCurrentPrice() {
		return currentPrice;
	}
	public void setCurrentPrice(String currentPrice) {
		this.currentPrice = currentPrice;
	}
	public Calendar getTime() {
		return time;
	}
	public void setTime(Calendar time) {
		this.time = time;
	}
}
