package be.scouts.beurs.flowhandler;

import javax.portlet.PortletRequest;
import javax.portlet.RenderResponse;

import org.springframework.webflow.core.FlowException;
import org.springframework.webflow.execution.repository.NoSuchFlowExecutionException;
import org.springframework.webflow.mvc.portlet.AbstractFlowHandler;

public class MyFlowHandler extends AbstractFlowHandler {

	@Override
	public String getFlowId(){
		return "beurs-admin-flow";
	}
	@Override
	public String handleException(FlowException e, PortletRequest portletRequest, RenderResponse renderResponse){
		if(e instanceof NoSuchFlowExecutionException){
			return "/systemerror.jsp";
		} else{
			throw e;
		}
	}
}
