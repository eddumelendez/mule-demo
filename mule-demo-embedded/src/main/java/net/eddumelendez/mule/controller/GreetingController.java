package net.eddumelendez.mule.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.log4j.Logger;
import org.mule.api.MuleContext;
import org.mule.api.MuleException;
import org.mule.api.MuleMessage;
import org.mule.api.client.MuleClient;
import org.mule.api.config.MuleProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class GreetingController {
	
	private static final Logger LOGGER = Logger.getLogger(GreetingController.class);
	
	@Autowired
	private ServletContext servletContext;
	
	private MuleClient getMuleClient() {
		MuleContext muleContext = (MuleContext) servletContext.getAttribute(MuleProperties.MULE_CONTEXT_PROPERTY);
		return muleContext.getClient();
	}
	
	@RequestMapping("/greet/{name}")
	public ModelAndView getFiles(@PathVariable("name") String name){
		LOGGER.info("name : " + name);
		MuleClient muleClient = getMuleClient();
		Map<String, String> params = new HashMap<String, String>();
		params.put("name", name);
		MuleMessage muleMessage = null;
		String payload = "";
		try {
			muleMessage = muleClient.send("vm://test", params, null);
			payload = muleMessage.getPayloadAsString();
			LOGGER.info("payload : " + payload);
		} catch (MuleException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ModelAndView mav = new ModelAndView("message");
		mav.addObject("message", payload);
		return mav;
	}
	
}
