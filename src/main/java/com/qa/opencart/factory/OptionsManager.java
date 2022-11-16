package com.qa.opencart.factory;

import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;

public class OptionsManager {
	
	//optionsmanage we just give me chrome option
	//i dont need any driver with optionsmanager
	//
	
	private Properties prop;
	private ChromeOptions chromeOptions;
	
	
	public OptionsManager(Properties prop) {
		this.prop = prop;
		
	}
	
	public ChromeOptions getChromeOptions() {
		chromeOptions = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))){
			chromeOptions.setHeadless(true);
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))){
			chromeOptions.addArguments("--incognito");
			
		}
		return chromeOptions;
		}
	}

	
	


