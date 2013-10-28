package be.scouts.beurs.repo;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import be.scouts.beurs.IDrankService;
import be.scouts.beurs.data.DrankData;
import be.scouts.beurs.data.DrankDataBase;
import be.scouts.beurs.data.HistoryData;
import be.scouts.beurs.json.JsonUtils;

public class JdbcDrankRepo implements IDrankService {

	private final static String timeCompare = "05:30:00";
	private JdbcTemplate jdbcTemplate;
	public JdbcDrankRepo(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	public List<HistoryData> getHistory(){
		HistoryDataHandler dataHandler = new HistoryDataHandler();
		List<HistoryData> history = jdbcTemplate.query("SELECT TIMEDIFF(NOW() ,tijd), tijd, jsonValue FROM drankhistory where TIMEDIFF(NOW() ,tijd) < '"+timeCompare +"'",dataHandler );
//		List<DrankData> actualPrice = getAllDrinks();
//		history.add(addActualPriceToHistory(actualPrice));
		return history;
	}
	private List<DrankDataBase> format(List<DrankData> data){
		List<DrankDataBase> list = new ArrayList<DrankDataBase>();
		for(DrankData d : data){
			list.add(new DrankDataBase(d.getName(), d.getCurrentPrice()));
		}
		return list;
	}
	private HistoryData addActualPriceToHistory(List<DrankData> actualPrice) {
		String json = null;
		try {
			json = JsonUtils.toJson(format(actualPrice));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Calendar cal = new GregorianCalendar(Locale.FRANCE);
		HistoryData data = new HistoryData(cal, json);
		SimpleDateFormat dateFormatter = new SimpleDateFormat("kk:mm:ss");

		data.setTijd(dateFormatter.format(cal.getTime()));
		data.setDrinks(format(actualPrice));
		return data;
	}

	@Override
	public List<DrankData> getAllDrinks() {
		Datahandler dhl = new Datahandler();
		return (List<DrankData>) jdbcTemplate.query("select * from DrankTable", dhl);
	}

	@Override
	public void upDataDrinks(List<DrankData> drinks) {
		List<DrankDataBase> miniDrinks =  new ArrayList<DrankDataBase>();
		for(DrankData drankje : drinks){
			updateRow(drankje.getName(), drankje.getCurrentPrice());	
			miniDrinks.add( new DrankDataBase(drankje.getName(), drankje.getCurrentPrice()));
		}
		updateHistory(miniDrinks);
	}
	
	private void updateHistory(List<DrankDataBase> drinkjes){
		StringBuilder sql = new StringBuilder();
		Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("GMT+1"));
		cal.getTimeInMillis();
		try {
			sql.append("insert into drankhistory (tijd, jsonvalue) VALUES (NOW(), '" + JsonUtils.toJson(drinkjes) +"' )" );
			jdbcTemplate.execute(sql.toString());
		} catch (IOException e) {
			System.out.println("someting went wrong with te json");
			e.printStackTrace();
		}
	}
	private void updateRow(String name, String newPrice){
		StringBuilder sql = new StringBuilder();
		sql.append("update DrankTable set currentPrice = '");
		sql.append(newPrice);
		sql.append("' where name = '");
		sql.append(name);
		sql.append("'");
		jdbcTemplate.execute(sql.toString());
	}
	
	@Override
	public DrankData getDrinkbyName(String name) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from DrankTable where name ='");
		sql.append(name);
		sql.append("'");
		return (DrankData) jdbcTemplate.queryForObject(sql.toString(), DrankData.class);
	}
}
