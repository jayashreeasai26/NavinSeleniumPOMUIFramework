package com.qa.opencart.Test;

import java.util.Random;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.Util.ExcelUtil;

public class RegisterTest extends BaseTest{
	
	@BeforeClass
	public void regSetup() {
		regPage = loginpage.navigateToRegisterPage();
	}
	
	
	@DataProvider
	public Object[][] getRegTestData() {
		Object regData [][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAMES);
		return regData;
	}
	
	public String getRandomEmail() {
		Random random = new Random();
		String email = "automationtest"+random.nextInt(10000)+"@gmail.com";
		return email;
	}

	@Test(dataProvider= "getRegTestData")
	public void regUserTest(String firstname, String lastname, String telephone, String password, String subscribe) {
		
		String actSuccMesg = regPage.userRegister(firstname, lastname, getRandomEmail(), telephone, password, subscribe);
		Assert.assertEquals(actSuccMesg, AppConstants.ACC_CREATE_SUCC_MESSG);
		
	}
}
