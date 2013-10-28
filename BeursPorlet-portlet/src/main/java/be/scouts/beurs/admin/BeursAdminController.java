package be.scouts.beurs.admin;

import java.util.List;

import org.apache.log4j.Logger;

import be.scouts.beurs.IDrankService;
import be.scouts.beurs.data.DrankData;


public class BeursAdminController {

	private IDrankService drankService;
	private  static Logger log = Logger.getLogger(BeursAdminController.class.getName());

	public BeursAdminController() {
	}
	
	public void setDrankService(IDrankService drankService){
		this.drankService = drankService;
	}
	
	
	public AdminData init(){
		AdminData data = new AdminData();
		data.setDrinks(drankService.getAllDrinks());
		return data;
	}
	
	public String update(AdminData data){
		log.info("prices are manualy updated!");
		drankService.upDataDrinks(data.getDrinks());
		return "ok";
	}
	
	public String updateDb(AdminData data){
		if(null != data.getTotalAmount()){
			data.setPreviousTotal(data.getTotalAmount());
		}
		else{
			data.setPreviousTotal("0,00");
		}
		updatePrice(data);
		drankService.upDataDrinks(data.getDrinks());
		return "done";
	}
	
	private void updatePrice2(AdminData data){

		float price = BeursUtils.FromStringToFloat(data.getDrinks().get(0).getCurrentPrice());
		price = (float) (price + Float.parseFloat(data.getDrinks().get(0).getAmount()) * data.getDrinks().get(0).getWeight() /100.0);
	}
	
	
	private void updatePrice(AdminData data){
		for(DrankData drank : data.getDrinks()){
			float price = BeursUtils.FromStringToFloat(drank.getCurrentPrice());
			for(DrankData innerDrank : data.getDrinks()){
				//Enkel bij de zelde data bij optellen.
				if(innerDrank.getName().equals(drank.getName())){
					price = (float) (price + Float.parseFloat(innerDrank.getAmount()) * drank.getWeight() / 100.0 );
				}
				else{
					price = (float) (price - Float.parseFloat(innerDrank.getAmount()) * drank.getWeight() / 100.0 / 5.0) ;
				}
			}
			if(price < BeursUtils.FromStringToFloat(drank.getMinPrice())){
				drank.setCurrentPrice(drank.getMinPrice());
			}
			else{
				drank.setCurrentPrice(BeursUtils.roundAmount(BeursUtils.FromFloatToString(price)));
			}
		}
	}

	
	
}
