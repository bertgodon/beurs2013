package be.scouts.beurs.repo;

import static org.junit.Assert.assertTrue;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import be.scouts.beurs.IDrankService;
import be.scouts.beurs.data.DrankDataBase;
import be.scouts.beurs.data.GraphData;
import be.scouts.beurs.data.HistoryData;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("applicationContext.xml")
public class TestGraphData {

	@Autowired
	public IDrankService drankService;
	
	@Test
	public void bert(){
		System.out.println("start");
		List<HistoryData> data = drankService.getHistory();
		assertTrue(data != null);
		SimpleDateFormat dateFormatter = new SimpleDateFormat("kk:mm:ss");
		for(HistoryData his : data){
			System.out.println("-----------------");
			System.out.println("Json: " +his.getJsonValue());
			System.out.println("Time: " +his.getTijd());
			for(DrankDataBase d : his.getDrinks()){
				System.out.println("- " +d.getName() +" - " +d.getCurrentPrice());
			}
		}
		System.out.println("parsed Time: " +parsedHistory(data));
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
				addValueToJsonString(values.get(counter), value.getCurrentPrice());
				counter ++;
			}
		}
		graphData.setHorizontalValue(endString(times.toString()));
		int counter = 0;
		for(StringBuilder builder : values){
			graphData.getLineValue().add(endString(builder.toString()));
			graphData.getBeers().add((String)beerNames.toArray()[counter]);
			counter++;
		}
		
		
		return graphData;
	}
//
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
}
