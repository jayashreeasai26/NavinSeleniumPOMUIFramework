package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.Util.ElementUtil;

public class searchResultPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By productSearchMsg = By.cssSelector("div.product-layout");

	public searchResultPage(WebDriver driver) {
		this.driver=driver;
		eleUtil = new ElementUtil(driver);
		
		
	}

	public boolean isSearchSuccessfull() {
		List<WebElement> searchList = eleUtil.waitForElementsToBeVisible(productSearchMsg, AppConstants.DEFAULT_LARGE_TIME_OUT);
		if(searchList.size() > 0) {
			System.out.println("Search is successfully done.....");
			return true;
		}
		return false;
	}
	
	
	public productInfoPage selectProduct(String mainProcuctName) {
		By mainPrName = By.linkText(mainProcuctName);
		eleUtil.doClick(mainPrName);
		return new productInfoPage(driver);
		
	}
}
