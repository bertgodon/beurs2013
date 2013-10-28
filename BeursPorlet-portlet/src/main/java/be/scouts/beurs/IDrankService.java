package be.scouts.beurs;

import java.util.List;

import be.scouts.beurs.data.DrankData;
import be.scouts.beurs.data.DrankDataBase;
import be.scouts.beurs.data.HistoryData;

public interface IDrankService {

	List<DrankData> getAllDrinks();
	
	void upDataDrinks(List<DrankData> drinks);
	
	DrankData getDrinkbyName(String name);
	/**
	 * This method returns all the information about the prices for the last half our.
	 * @return
	 */
	List<HistoryData> getHistory();
}
