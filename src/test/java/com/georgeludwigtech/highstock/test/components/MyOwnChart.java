package com.georgeludwigtech.highstock.test.components;

import org.apache.tapestry5.json.JSONObject;

import com.georgeludwigtech.highstock.components.AbstractHighStock;

public class MyOwnChart extends AbstractHighStock{

	public JSONObject getComponentOptions(){
		
		JSONObject opt = new JSONObject();
		opt.put("text", "Test Override");
		opt.put("x", -20);
		
		JSONObject high = new JSONObject();
		high.put("subtitle", opt);
		high.put("chart", new JSONObject("renderTo", getClientId()));
		return high;
		
	}
}
