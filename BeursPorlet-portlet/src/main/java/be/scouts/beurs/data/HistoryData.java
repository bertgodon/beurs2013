package be.scouts.beurs.data;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class HistoryData implements Serializable{


	/**
	 * HistoryData model. This object contains the data from the DB historyData table 
	 * 
	 */
	private static final long serialVersionUID = -3957430586077219544L;

	public HistoryData() {
		// TODO Auto-generated constructor stub
	}
	public HistoryData(Calendar cal, String json){
		this.calendar = cal;
		this.jsonValue = json;
	}
	String tijd;
	Calendar calendar;
	String jsonValue;
	List<DrankDataBase> drinks;
	
	public String getTijd() {
		return tijd;
	}
	public void setTijd(String tijd) {
		this.tijd = tijd;
	}
	public List<DrankDataBase> getDrinks() {
		return drinks;
	}
	public void setDrinks(List<DrankDataBase> drinks) {
		this.drinks = drinks;
	}
	public Calendar getCalendar() {
		return calendar;
	}
	public void setCalendar(Calendar calendar) {
		this.calendar = calendar;
	}
	public String getJsonValue() {
		return jsonValue;
	}
	public void setJsonValue(String jsonValue) {
		this.jsonValue = jsonValue;
	}
 
	
}

