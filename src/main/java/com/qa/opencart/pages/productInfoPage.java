package com.qa.opencart.pages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.Util.ElementUtil;

public class productInfoPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private Map<String, String>  productInfoMap;
	
	private By productImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");

	public productInfoPage(WebDriver driver) {
		
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
		
	}


	
	public String getProductHeader(String mainProductName) {
		String xpath = "//h1[text()='"+mainProductName+"']";
		String productHeader= eleUtil.doGetText(By.xpath(xpath));
		return productHeader;
	}
	
	public int getProductCount() {
		return eleUtil.waitForElementsToBeVisible(productImages, AppConstants.DEFAULT_LARGE_TIME_OUT).size();
	}
	public String getProductPageTitle(String productTitle) {
		return eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, productTitle);
	}
	
	public String getProductPageUrl(String serachkey) {
		return eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, serachkey);
	}
	
	public Map<String, String> getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		productInfoMap = new HashMap<String, String>();
		for(WebElement e : metaList) {
		String metaText= 	e.getText();
		String meta[] = metaText.split(":");
		String metaKey = meta[0].trim();
		String metaValue = meta[1].trim();
		productInfoMap.put(metaKey, metaValue);
		}
		return productInfoMap;
		
	}
}

