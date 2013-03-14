package com.georgeludwigtech.highstock.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.tapestry5.Asset;
import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.func.F;
import org.apache.tapestry5.func.Mapper;
import org.apache.tapestry5.ioc.annotations.Symbol;
import org.apache.tapestry5.ioc.internal.util.CollectionFactory;
import org.apache.tapestry5.services.AssetSource;
import org.apache.tapestry5.services.javascript.JavaScriptStack;
import org.apache.tapestry5.services.javascript.StylesheetLink;

public class HighStockStack implements JavaScriptStack{
	
	public static final String STACK_ID = "highStockStack";
	
	private final boolean productionMode;

    private final List<Asset> javaScriptStack;

    private final List<StylesheetLink> stylesheetStack;

	public HighStockStack(@Symbol(SymbolConstants.PRODUCTION_MODE) final boolean productionMode, 
			final AssetSource assetSource) {
		super();
		this.productionMode = productionMode;
		
		final Mapper<String, Asset> pathToAsset = new Mapper<String, Asset>()
        {

            public Asset map(String path)
            {
                return assetSource.getExpandedAsset(path);
            }
        };


        stylesheetStack = CollectionFactory.newList();
        
        List<Asset>assetList1 = null;
        List<Asset>assetList2 = null;
        
        if (productionMode) {
        	
        	assetList1 = F
                .flow("${jquery.highstock.core.path}/highstock.js")
                .map(pathToAsset).toList();
        	
        	assetList2 = F
        		.flow("${jquery.highstock.core.path}/highcharts-more.js")
            	.map(pathToAsset).toList();
        	
   	
        } else {
        	
        	assetList1 = F
                    .flow("${jquery.highstock.core.path}/highstock.src.js")
                    .map(pathToAsset).toList();
            	
        	assetList2 = F
        		.flow("${jquery.highstock.core.path}/highcharts-more.src.js")
            	.map(pathToAsset).toList();

        }
        
        List<Asset>sum=new ArrayList<Asset>();
        sum.addAll(assetList1);
        sum.addAll(assetList2);
        javaScriptStack=sum;
		
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
