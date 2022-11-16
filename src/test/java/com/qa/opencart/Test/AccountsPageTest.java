package com.qa.opencart.Test;

import java.util.ArrayList;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.Constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;


@Epic("Epic - 200: Open cart application Accounts page design")
@Story("US -101: Design Accounts page feature")

public class AccountsPageTest extends BaseTest{
	
	
	
	@BeforeClass
	public void accSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Description("accPageTitleTest-- Dev Name: @Vijay")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void accPageTitleTest() {
		String actAccPageTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actAccPageTitle, AppConstants.ACC_PAGE_TITLE);
		
	}
	@Description("accPageUrlTest-- Dev Name: @Vijay")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void accPageUrl() {
		Assert.assertTrue(accPage.getAccPageUrl());
	}
	@Description("accPageSearchTest-- Dev Name: @Vijay")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void searchExistTest() {
		Assert.assertTrue(accPage.issearchEsxit());
	}
	@Description("accPage logout link exist test-- Dev Name: @Vijay")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 4)
	public void logoutLinkExist() {
		Assert.assertTrue(accPage.isLogOutLinkExist());
	}
	@Description("accPage header Test-- Dev Name: @Vijay")
	@Severity(SeverityLevel.TRIVIAL)
	@Test(priority = 5)
	public void accHeadersTest() {
		ArrayList<String> accHeaderList = accPage.getAccSectionHeadersList();
		System.out.println("Actual Header:" + accHeaderList); 
		Assert.assertEquals(accHeaderList, AppConstants.ACC_PAGE_SECTIONS_HEADERS);
		
	}
	
	@DataProvider
	public Object[][] getProductKey() {
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Samsung"},
		};
	}
	@Description("accPage Search check Test-- Dev Name: @Vijay")
	@Severity(SeverityLevel.CRITICAL)	
	@Test(dataProvider = "getProductKey", priority = 6)
	public void searchCheckTest(String ProductKey) {
		searchResultPage = accPage.performSearch(ProductKey);
		Assert.assertTrue(searchResultPage.isSearchSuccessfull());
	}
	
	@DataProvider
	public Object[][] getProductData() {
		return new Object[][] {
			{"Macbook", "MacBook Pro"},
			{"Macbook", "MacBook Air"},
			{"iMac", "iMac"},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Samsung", "Samsung Galaxy Tab 10.1"},
		};
	}
	@Description("accPage Product Search Test-- Dev Name: @Vijay")
	@Severity(SeverityLevel.BLOCKER)
	@Test (dataProvider = "getProductData", priority = 7)
	public void searchTest(String searchKey, String mainProductName) {
		searchResultPage = accPage.performSearch(searchKey);
		if(searchResultPage.isSearchSuccessfull()) {
			productInfoPage = searchResultPage.selectProduct(mainProductName);
			String actaulProductHeader = productInfoPage.getProductHeader(mainProductName);
			Assert.assertEquals(actaulProductHeader, mainProductName);
		}
	}
}
