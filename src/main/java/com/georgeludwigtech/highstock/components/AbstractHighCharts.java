package com.georgeludwigtech.highstock.components;

import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;
import org.got5.tapestry5.jquery.utils.JQueryUtils;

import com.georgeludwigtech.highstock.services.HighStockStack;

/**
 * This component is the base of any HighStock !! You can use it for all your charts, and 
 * define all the parameters by using the options parameters or by adding datas to the jquery 
 * object : $("#clientId").data("highstock", {...}); The Java and JavaScript JSON parameter 
 * will be merged during the initialization of the HighStock. 
 * If you set these parameters by JavaScript, you have to do it before the initialization of the HighStock. 
 * In your Java Class, for example, you need to use this method : javascript.addInitializerCall(
 * InitializationPriority.EARLY, "index", new JSONObject()); of the JavaScriptSupport service.
 * 
 * You can also create your HighChart component, by extending this class and overriding the getComponentOptions()
 * method. The returned value of this method will be merged to the options parameter.
 * 
 * @author Emmanuel DEMEY
 */
@Import(stack=HighStockStack.STACK_ID, library="classpath:com/georgeludwigtech/jquery/highstock/asset/jquery-highstock.js")
public class AbstractHighCharts implements ClientElement{
	
	private String clientId;
	
	@Parameter
	private JSONObject options;
	
	@Inject
	private JavaScriptSupport javascript;
	
	@Inject
	private ComponentResources resources;
	
	@SetupRender
	public void addDiv(MarkupWriter writer){
		clientId = javascript.allocateClientId(resources);
		writer.element("div", "id", clientId);
	}
	
	@AfterRender
	public void setJS(MarkupWriter writer){
		resources.renderInformalParameters(writer);
		writer.end();
		
		JSONObject opt = new JSONObject();
		opt.put("id", clientId);
		
		JSONObject params = getComponentOptions();	
		
		JQueryUtils.merge(params, options);
		
		opt.put("opt", params);
		javascript.addInitializerCall("highstock", opt);
	}
	
	public JSONObject getComponentOptions(){
		JSONObject json = new JSONObject();
		json.put("chart", new JSONObject("renderTo", getClientId()));
		return json;
	}
	
	public String getClientId() {
		return clientId;
	}
}
