package com.georgeludwigtech.highstock.components;

import org.apache.tapestry5.BindingConstants;
import org.apache.tapestry5.ClientElement;
import org.apache.tapestry5.ComponentResources;
import org.apache.tapestry5.MarkupWriter;
import org.apache.tapestry5.annotations.AfterRender;
import org.apache.tapestry5.annotations.Import;
import org.apache.tapestry5.annotations.Parameter;
import org.apache.tapestry5.annotations.Property;
import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

import com.georgeludwigtech.highstock.services.HighStockStack;

/**
 * This component is the base of any HighStock !! You can use it for all your charts, and 
 * define all the parameters by using the options parameters or by adding datas to the jquery 
 * object : $("#client").data("highstock", {...}); The Java and JavaScript JSON parameter 
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
public class AbstractHighStock implements ClientElement{
	
	@Parameter(name="clientId", defaultPrefix=BindingConstants.LITERAL, required=true)
	private String clientId;
	
	@Parameter(name="options", required=false)
	@Property
	private JSONObject options;
	
	@Inject
	private JavaScriptSupport javascript;
	
	@Inject
	private ComponentResources resources;
	
	@SetupRender
	public void addDiv(MarkupWriter writer){
		writer.element("div", "id", clientId);
	}
	
	@AfterRender
	public void setJS(MarkupWriter writer){
 		resources.renderInformalParameters(writer);
		writer.end();
		
		JSONObject opt = new JSONObject();
		opt.put("id", getClientId());
		
		JSONObject params = getComponentOptions();	
		
		JSONObject merged=merge(params, options);
		
		opt.put("opt", merged);
		javascript.addInitializerCall("highcharts", opt);
	}
	
	public JSONObject getComponentOptions(){
		JSONObject json = new JSONObject();
		String id=getClientId();
		json.put("chart", new JSONObject("renderTo", id));
		return json;
	}
	
	public String getClientId() {
		return clientId;
	}
	
	// object 2 has priority over identical properties
	public final static JSONObject merge(JSONObject obj1, JSONObject obj2) {
		JSONObject ret=new JSONObject();
		
		// copy obj1 to ret
		if(obj1!=null) {
			for (String key : obj1.keys()) {
				ret.put(key, obj1.get(key));
			}
		}
		
		// merge obj2 to ret
		if(obj2!=null) {
			for (String key : obj2.keys()) {
				if(ret.has(key)) {
					JSONObject temp=merge(ret.getJSONObject(key),obj2.getJSONObject(key));
					ret.put(key, temp);
				} else {
					ret.put(key, obj2.get(key));
				}
			}
		}
        
        return ret;
    }
}
