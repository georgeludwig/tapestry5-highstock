package com.georgeludwigtech.highstock.test.pages;

import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.json.JSONArray;
import org.apache.tapestry5.json.JSONLiteral;
import org.apache.tapestry5.json.JSONObject;
import org.apache.tapestry5.services.javascript.JavaScriptSupport;

public class BasicComponent {
	
	@Inject
	private JavaScriptSupport javascript;
	
	public JSONObject getOptions() {  
        JSONObject opts = new JSONObject();  
        buildChart(opts);         
        buildTitle(opts);         
        buildXAxis(opts);  
        buildYAxis(opts);  
        buildPlotOptions(opts);  
        buildSeries(opts);  
          
        return opts;  
    }  
  
    protected void buildChart(JSONObject opts) {  
        JSONObject chartC = new JSONObject("type", "spline");  
  
        opts.put("chart", chartC);  
    }  
  
    protected void buildTitle(JSONObject opts) {  
        JSONObject titleText = new JSONObject();  
        titleText.put("text", "<b>Hourly Visits in The last 24 Hours</b>");  
        opts.put("title", titleText);  
  
    }  
  
    protected void buildXAxis(JSONObject opts) {  
  
        JSONObject xCategories = new JSONObject();        
        JSONArray values = new JSONArray("18:00-00:00", "12:00-18:00",  
                "06:00-12:00", "00:00-06:00");  
        xCategories.put("categories", values);  
          
        JSONObject xAxisC = new JSONObject();  
        xAxisC.put("xAxis", xCategories);  
          
        JSONObject xTitleText = new JSONObject();  
        xTitleText.put("text", "Time interval");  
        xAxisC.put("title", xTitleText);  
          
        opts.put("xAxis", xAxisC );       
//                  
    }  
  
    protected void buildYAxis(JSONObject opts) {  
        JSONObject yTitleText = new JSONObject();  
        yTitleText.put("text", "No of visits");  
        JSONObject yTitleC = new JSONObject();  
        yTitleC.put("title", yTitleText);  
        opts.put("yAxis", yTitleC);  
    }  
      
      
    protected void buildPlotOptions(JSONObject opts) {  
  
        JSONObject dataLabelsEnabled = new JSONObject();  
        dataLabelsEnabled.put("enabled", true);  
        JSONObject dataLabels = new JSONObject();  
        dataLabels.put("dataLabels", dataLabelsEnabled);  
  
        JSONObject bar = new JSONObject();  
        bar.put("dataLabels", dataLabelsEnabled);  
        bar.put("pointWidth", 20);  
        bar.put("borderWidth", 1);  
  
        JSONObject barC = new JSONObject();  
        barC.put("bar", bar);  
        opts.put("plotOptions", barC);  
  
    }  
      
    protected void buildSeries(JSONObject opts) {  
  
        JSONObject seriesInner = new JSONObject(new String[] { "name",  
                "No of visits", "color", "#0000FF" });  
        seriesInner.put("showInLegend", new JSONLiteral("false"));  
        
       
        JSONArray seriesData = new JSONArray();
        long dayMillis=86400000;
        long time=System.currentTimeMillis()-(dayMillis*101);
        //long day
        for(int i=0;i<100;i++) {
        	seriesData.put(new JSONArray(time-(dayMillis*(100-i)),i));
        }
        
        seriesInner.put("data", seriesData);  
        opts.put("series", new JSONArray(seriesInner));  
  
    }  
}
