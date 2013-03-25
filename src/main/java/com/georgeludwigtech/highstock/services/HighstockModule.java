package com.georgeludwigtech.highstock.services;

import org.apache.tapestry5.ioc.Configuration;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.services.LibraryMapping;
import org.apache.tapestry5.services.javascript.JavaScriptStack;

import com.georgeludwigtech.highstock.HighStockSymbolConstants;

public class HighstockModule {

	public static void contributeJavaScriptStackSource(
			MappedConfiguration<String, JavaScriptStack> configuration) {
		configuration.addInstance(HighStockStack.STACK_ID,
				HighStockStack.class);
	}

	public static void contributeComponentClassResolver(
			Configuration<LibraryMapping> configuration) {
		configuration.add(new LibraryMapping("jquery-highstock",
				"com.georgeludwigtech.highstock"));
	}

	public static void contributeClasspathAssetAliasManager(
			MappedConfiguration<String, String> configuration) {
		configuration.add("tap-jquery-highstock", "com/georgeludwigtech");
	}
	
	public static void contributeFactoryDefaults(MappedConfiguration<String, String> configuration)
    {
       
        configuration.add(HighStockSymbolConstants.JQUERY_HIGHSTOCK_CORE_PATH, "classpath:com/georgeludwigtech/jquery/highstock/asset");
        
    }
}
