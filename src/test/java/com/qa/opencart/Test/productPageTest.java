package com.qa.opencart.Test;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.Constants.AppConstants;

public class productPageTest extends BaseTest{
	
	@BeforeClass
	public void proInfoSetup() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}

	@Test
	public void productHeaderPage() {
		searchResultPage = accPage.performSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");
		String accProdutHeader = productInfoPage.getProductHeader("MacBook Pro");
		Assert.assertEquals(accProdutHeader, "MacBook Pro");
	}
	
	@DataProvider
	public Object[][] getProductInfoData() {
		return new Object[][] {
			{"MacBook", "MacBook Pro", AppConstants.MACBOOK_PRO_IMAGES_COUNT},
			{"MacBook", "MacBook Air", AppConstants.MACBOOK_AIR_IMAGES_COUNT},
			{"iMac", "iMac", AppConstants.IMAC_IMAGES_COUNT},
			};
	}
	
	@Test(dataProvider = "getProductInfoData")
	public void productImagesCountTest(String searchkey, String mainProductName, int ImagesCount) {
		searchResultPage = accPage.performSearch(searchkey);
		productInfoPage = searchResultPage.selectProduct(mainProductName);
		int actProductImages = productInfoPage.getProductCount();
		System.out.println("actual product images:" +actProductImages);
		Assert.assertEquals(actProductImages, ImagesCount);
	}
	
	@Test
	public void productMetaDataTest() {
		searchResultPage = accPage.performSearch("MacBook");
		productInfoPage = searchResultPage.selectProduct("MacBook Pro");	
		Map<String, String> actMetaDataMap = productInfoPage.getProductMetaData();
		Assert.assertEquals(actMetaDataMap.get("Brand"), "Apple");
		Assert.assertEquals(actMetaDataMap.get("Product Code"), "Product 18");
		Assert.assertEquals(actMetaDataMap.get("Reward Points"), "800");
		Assert.assertEquals(actMetaDataMap.get("Availability"), "In Stock");
	}
	
}
