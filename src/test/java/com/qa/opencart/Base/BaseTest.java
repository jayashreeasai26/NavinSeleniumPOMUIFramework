package com.qa.opencart.Base;

import java.util.Properties;

import javax.naming.directory.SearchResult;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.qa.opencart.factory.Driverfactory;
import com.qa.opencart.pages.AccountsPage;
import com.qa.opencart.pages.LoginPage;
import com.qa.opencart.pages.RegisterPage;
import com.qa.opencart.pages.productInfoPage;
import com.qa.opencart.pages.searchResultPage;

public class BaseTest {
	
	//to maintain that object of driver factory
	Driverfactory df;
	
	public WebDriver driver;
	public Properties prop;
	
	public 	LoginPage loginpage;
	public AccountsPage accPage;
	public searchResultPage searchResultPage;
	public productInfoPage productInfoPage;
	public RegisterPage regPage; 
	
	@BeforeTest
	public void setup() {
		//who is initializing driver-initDriver(then we have to create eobject)
		df= new Driverfactory();
		prop = df.initprop();
		driver = df.initDriver(prop);
		loginpage = new LoginPage(driver);
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
