package com.georgeludwigtech.highstock.test.services;

import org.apache.tapestry5.SymbolConstants;
import org.apache.tapestry5.ioc.MappedConfiguration;
import org.apache.tapestry5.ioc.annotations.SubModule;

import com.georgeludwigtech.highstock.services.HighstockModule;

@SubModule(HighstockModule.class)
public class AppModule {
	
	public static void contributeApplicationDefaults(MappedConfiguration<String, String> configuration)
    {
        configuration.add(SymbolConstants.PRODUCTION_MODE, "false");
        configuration.add("demo-src-dir","/Users/George/git/tapestry5-highstock/src/test/");
    }
	
}
