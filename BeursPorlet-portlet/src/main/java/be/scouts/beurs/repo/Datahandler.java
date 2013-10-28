package be.scouts.beurs.repo;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import be.scouts.beurs.data.DrankData;

public class Datahandler implements ResultSetExtractor<List<DrankData>> {

	@Override
	public List<DrankData> extractData(ResultSet rs) throws SQLException, DataAccessException {
		List<DrankData> drinks = new ArrayList<DrankData>();
		while(rs.next()){
			DrankData drink = new DrankData();
			drink.setName(rs.getString("name"));
			drink.setCurrentPrice(rs.getString("currentPrice"));
			drink.setMaxPrice(rs.getString("maxPrice"));
			drink.setMinPrice(rs.getString("minPrice"));
			drink.setWeight(rs.getInt("weight"));
			drink.setColor(rs.getString("color"));
			drinks.add(drink);
		}
		return drinks;
	}

}
