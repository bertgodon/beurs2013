package be.scouts.beurs.tv;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;
import javax.portlet.ResourceRequest;
import javax.portlet.ResourceResponse;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.portlet.bind.annotation.RenderMapping;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import be.scouts.beurs.IDrankService;
import be.scouts.beurs.data.DrankData;
import be.scouts.beurs.data.HistoryData;
import be.scouts.beurs.json.JsonUtils;

@Controller
@RequestMapping("VIEW")
public class TvClientController {
	private IDrankService drankService;
	private  static Logger log = Logger.getLogger(TvClientController.class.getName());

	public TvClientController() {
	}
	
	public void setDrankService(IDrankService drankService){
		this.drankService = drankService;
	}
	
	@RenderMapping
	public ModelAndView handleRenderRequest(RenderRequest request, RenderResponse response) throws Exception {
		Map<String, Object> model = new HashMap<String, Object>();
		List<DrankData> drinks = drankService.getAllDrinks();
		List<HistoryData> history = drankService.getHistory();
		model.put("drinks", drinks);
		return new ModelAndView("view", model);
	}

	
	@ResourceMapping("drinks")
	public void getReports(ResourceRequest request, ResourceResponse response) throws IOException{
		try{
			log.info("responding AJAX call");
			List<DrankData> drinks = drankService.getAllDrinks();
			response.setContentType("text/json");
			JsonUtils.toJson(response, drinks);
		}catch(Exception e){
			throw new IOException("An internal error has occurred on the server:", e);
		}	
	}
}


