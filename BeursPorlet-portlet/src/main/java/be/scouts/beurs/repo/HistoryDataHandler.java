package be.scouts.beurs.repo;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import be.scouts.beurs.data.DrankDataBase;
import be.scouts.beurs.data.HistoryData;
import be.scouts.beurs.json.JsonUtils;

public class HistoryDataHandler implements ResultSetExtractor<List<HistoryData>> {

	@Override
	public List<HistoryData> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<HistoryData> history = new ArrayList<HistoryData>();
		while(rs.next()){
			HistoryData data = new HistoryData();
				DrankDataBase[] drink;
				List<DrankDataBase> res;
				try {
					drink = JsonUtils.fromJson(rs.getString("jsonValue"), DrankDataBase[].class);
					res = Arrays.asList(drink);
					data.setDrinks(res);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			Date date = rs.getDate("tijd");
			Calendar cal = new GregorianCalendar(Locale.FRANCE);
			data.setJsonValue(rs.getString("jsonValue"));
			data.setTijd(rs.getString("tijd").substring(11, 19));
			history.add(data);
			
		}
		return history;
	}

}
