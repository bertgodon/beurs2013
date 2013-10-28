package be.scouts.beurs.client;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import be.scouts.beurs.IDrankService;
import be.scouts.beurs.data.DrankData;
import be.scouts.beurs.data.DrankDataBase;
import be.scouts.beurs.data.GraphData;
import be.scouts.beurs.data.HistoryData;
import be.scouts.beurs.json.JsonUtils;

public class BeursClientController {
	private IDrankService drankService;
	
	public BeursClientController() {
	}
	
	public void setDrankService(IDrankService drankService){
		this.drankService = drankService;
	}
	
	@RenderMapping
	public ModelAndView handleRenderRequest(RenderRequest request, RenderResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		List<DrankData> drinks = drankService.getAllDrinks();
		List<HistoryData> history = drankService.getHistory();
		List<String> colors = new ArrayList<String>();
		for(DrankData data: drinks){
			colors.add(data.getColor());
		}
		model.put("colors",colors );
		model.put("drinks", drinks);
		model.put("graphData" , parsedHistory(history));
		return new ModelAndView("view", model);
	}
	
	public GraphData parsedHistory(List<HistoryData> history){
		GraphData graphData = new GraphData();
		List<StringBuilder> values = new ArrayList<StringBuilder>(10);
		for(int i = 0; i<8; i++){
			values.add(new StringBuilder("["));
		}
		Set<String> beerNames = new HashSet<String>();
		StringBuilder times = new StringBuilder();
		times.append("[");
		for(HistoryData data : history){
			addValueToJsonString(times, data.getTijd());
			int counter = 0;
			for(DrankDataBase value: data.getDrinks()){
				beerNames.add(value.getName());
				addValueToJsonString(values.get(counter), value.getCurrentPrice().replace(",", "."));
				counter ++;
			}
		}
		graphData.setHorizontalValue(endString(times.toString()));
		int counter = 0;
		for(StringBuilder builder : values){
			graphData.getLineValue().add(endString(builder.toString()));
			List<String> bla = graphData.getBeers();
			bla= new ArrayList<String>();
			graphData.getBeers().add((String)beerNames.toArray()[counter]);
			counter++;
		}
		
		
		return graphData;
	}

	private String endString(String time) {
		if(time.endsWith(",")){
			time = time.substring(0, time.length()-1);
		}
		time +="]";
		return time;
	}

	private void addValueToJsonString(StringBuilder times, String value) {
		times.append("'");
		times.append(value);
		times.append("'");
		times.append(",");
	}
	
	@ResourceMapping("drinks")
	public void getReports(ResourceRequest request, ResourceResponse response) throws IOException{
		try{
			List<DrankData> drinks = drankService.getAllDrinks();
			response.setContentType("text/json");
			JsonUtils.toJson(response, drinks);
		}catch(Exception e){
			throw new IOException("An internal error has occurred on the server:", e);
		}	
	}
}
