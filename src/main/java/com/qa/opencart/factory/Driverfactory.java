package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.log4testng.Logger;

import com.qa.opencart.Errors.AppError;
import com.qa.opencart.Exceptions.FrameworkException;
import com.qa.opencart.Util.JavaScriptUtil;
import com.qa.opencart.pages.AccountsPage;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Driverfactory {
	
	public WebDriver driver;
	public Properties prop;
	public AccountsPage accPage;
	public OptionsManager optionsManager;
	
	private static final Logger LOG = Logger.getLogger(Driverfactory.class);

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	public static String highlight;
	
	/* this driver is used to initialize the driver on the basis of given browser name
	 * @parameter browser name
	 * return- this will return the driver instance
	 */
	
	public WebDriver initDriver(Properties prop) { //just fetch the value with help of prop.getproperty().
		String BrowserName = prop.getProperty("browser").toLowerCase();
		System.out.println("Browser Name is:" +BrowserName);
		LOG.info("browser name is:" +BrowserName);
		
		highlight = prop.getProperty("highlight").trim();
		optionsManager = new OptionsManager(prop);
		
		if(BrowserName.equals("chrome")) {
			WebDriverManager.chromedriver().setup();
		//	driver = new ChromeDriver();
			
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
		}	else if(BrowserName.equals("firefox")) {
				WebDriverManager.firefoxdriver().setup();
			//	driver = new FirefoxDriver();
				tlDriver.set(new FirefoxDriver());
		}
		else if(BrowserName.equals("edge")) {
			WebDriverManager.edgedriver().setup();
		//	driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver());
	}  else {
		System.out.println("please pass the right browser:" +BrowserName);
		LOG.error("please pass the right browser name :" +BrowserName);
		throw new FrameworkException(AppError.BROWSER_NOT_FOUND);
		
	}
		
		getDriver1().manage().deleteAllCookies();
		getDriver1().manage().window().maximize();
		getDriver1().get(prop.getProperty("url"));
		
		return getDriver1();
	}
	
	public static synchronized WebDriver getDriver1() {
		return tlDriver.get();
	}
	//threadlocal says it will give local coy for each and every thread that will help you parallel testig also
	//testng.xml just generating threds
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	/**
	 * this method is used to initialize the config preperties
	 * @return
	 */
	
	public Properties initprop() {
		prop =new Properties();   //properties class will help me to load the preperty
		FileInputStream ip = null;
		
		//maven clean install -Denv="qa"       --D is command line argument and env is parameter name
		
		String envName = System.getenv("env");
		System.out.println("-----> Running test cases on enviornment: ---------->" + envName);
		LOG.info("-----> Running test cases on enviornment: ---------->" + envName);
		
		if(envName== null) {
			System.out.println("No env is given.. hence running it on QA dev");
			try {
				ip = new FileInputStream("./scrTestResources\\Configue\\qa.configue.properties");
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		
		else {
			try {
			switch (envName) {
			case "dev":
				ip = new FileInputStream("./scrTestResources\\Configue\\dev.configue.properties");
				break;	
			case "stage":
				ip = new FileInputStream("./scrTestResources\\Configue\\stage.configue.properties");
				break;
			case "uat":
				ip = new FileInputStream("./scrTestResources\\Configue\\uat.configue.properties");
				break;
			case "qa":
				ip = new FileInputStream("./scrTestResources\\Configue\\qa.configue.properties");
				break;
			case "prod":
				ip = new FileInputStream("./scrTestResources\\Configue\\configue.properties");
				break;	
			default:
				System.out.println("please pass the right enviornmnt..."+envName);
				throw new FrameworkException(AppError.ENV_NOT_FOUND);
				//break;
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	try {
		prop.load(ip);
	} catch (IOException e) {
		e.printStackTrace();
	}
	
		return prop;
	}
	
	/*
	 * take screenshot
	 */
	
public static String getScreenshot() {
		
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		
		
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);
		
		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return path;
		
	}
	}
