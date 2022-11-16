package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.Util.ElementUtil;

import io.qameta.allure.Step;

public class AccountsPage {

	public WebDriver driver;
	private ElementUtil eleUtil;

	private By logoutLink = By.linkText("Logout");
	private By search = By.name("search");
	private By searchicon = By.cssSelector("div#search button");
	private By accSectionHeader = By.cssSelector("div#content h2");

	public AccountsPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	@Step("getAccPageTitle..")
	public String getAccPageTitle() {
		String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACC_PAGE_TITLE);
		System.out.println("Acc page title:" + title);
		return title;
	}
	
	@Step("getAccPageUrl...")
	public boolean getAccPageUrl() {
		String url = eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.ACC_PAGE_URL_PARAM);
		System.out.println("Acc page url:" + url);
		if (url.contains(AppConstants.ACC_PAGE_URL_PARAM)) {
			return true;
		}
		return false;
	}

	@Step("isLogOutLinkExist...")
	public boolean isLogOutLinkExist() {
		return eleUtil.doEleIsDisplayed(logoutLink);

	}

	@Step("issearchEsxit...")
	public boolean issearchEsxit() {
		return eleUtil.doEleIsDisplayed(search);

	}
	
	@Step("performSearch...{0}")
	public searchResultPage performSearch(String productKey) {
		System.out.println("product name is:" +productKey);
		if(issearchEsxit()) {
			eleUtil.doSendKeys(search, productKey);
			eleUtil.doClick(searchicon);
			return new searchResultPage(driver);
		}
		else {
			System.out.println("search field is not present on the page.....");
			return null;
		}
	}
	
	@Step("getAccSectionHeadersList")
	public ArrayList<String> getAccSectionHeadersList() {

		List<WebElement> sectList = eleUtil.waitForElementsToBeVisible(accSectionHeader, AppConstants.DEFAULT_LARGE_TIME_OUT);
		System.out.println("total section headers:" + sectList.size());
		ArrayList<String> actSectTextList = new ArrayList<String>();
		for (WebElement e : sectList) {
			String text = e.getText();
			actSectTextList.add(text);
		}
		return actSectTextList;

	}

}
