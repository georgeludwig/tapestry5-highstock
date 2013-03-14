package com.georgeludwigtech.highstock.test;

import org.apache.tapestry5.test.SeleniumTestCase;
import org.testng.annotations.Test;

import com.thoughtworks.selenium.Wait;

public class HighchartsTest extends SeleniumTestCase{
	
	@Test
	public void testJSFile(){
		open("/BasicComponent");
		
		new Wait() {
			
			@Override
			public boolean until() {
				return isElementPresent("//head/script[contains(@src,'highstock.src.js')]");
			}
		}.wait("The HighStock JavaScript file is missing.", 5000l);
	}
}
