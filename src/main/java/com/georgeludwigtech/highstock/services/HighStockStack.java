package com.georgeludwigtech.highstock.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.StylesheetLink;

import com.georgeludwigtech.highstock.HighStockSymbolConstants;

public class HighStockStack implements JavaScriptStack{
	
	public static final String STACK_ID = "highStockStack";
	
	private final boolean productionMode;

    private final List<Asset> javaScriptStack;

    private final List<StylesheetLink> stylesheetStack;

	public HighStockStack(@Symbol(SymbolConstants.PRODUCTION_MODE) final boolean productionMode, 
			final AssetSource assetSource) {
		super();
		this.productionMode = productionMode;
		
        stylesheetStack = CollectionFactory.newList();
        
        List<Asset>assetList=new ArrayList<Asset>();
        
        if (productionMode) {
        	
        	Asset asset=assetSource.getExpandedAsset("${"+HighStockSymbolConstants.JQUERY_HIGHSTOCK_CORE_PATH+"}/highstock.js");
            assetList.add(asset);
        	
            asset=assetSource.getExpandedAsset("${"+HighStockSymbolConstants.JQUERY_HIGHSTOCK_CORE_PATH+"}/highcharts-more.js");
            assetList.add(asset);
            
        } else {
        	
        	Asset asset=assetSource.getExpandedAsset("${"+HighStockSymbolConstants.JQUERY_HIGHSTOCK_CORE_PATH+"}/highstock.src.js");
            assetList.add(asset);
        	
            asset=assetSource.getExpandedAsset("${"+HighStockSymbolConstants.JQUERY_HIGHSTOCK_CORE_PATH+"}/highcharts-more.src.js");
            assetList.add(asset);
   
        }
        
         javaScriptStack=assetList;
		
	}
    
	public String getInitialization()
    {
        return productionMode ? null : "Tapestry.DEBUG_ENABLED = true;";
    }

    public List<Asset> getJavaScriptLibraries()
    {
        return javaScriptStack;
    }

    public List<StylesheetLink> getStylesheets()
    {
        return stylesheetStack;
    }

    public List<String> getStacks()
    {
        return Collections.emptyList();
    }
}
