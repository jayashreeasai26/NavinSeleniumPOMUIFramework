package com.qa.opencart.Test;

import org.testng.Assert;


import org.testng.annotations.Test;
import com.qa.opencart.Base.BaseTest;
import com.qa.opencart.Constants.AppConstants;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;

//@listeners(ExtendReportListener.class)

@Epic("Epic - 100: Open cart application login page design")
@Story("US -101: Design Login page feature")

public class LoginPageTest extends BaseTest {

	
	@Description("login page tite test....")
	@Severity(SeverityLevel.MINOR)
	@Test(priority = 1)
	public void loginPageTitleTest() {
	String actualTitle = loginpage.getLoginPageTitle();
	Assert.assertEquals(actualTitle, AppConstants.LOGIN_PAGE_TITLE);
	}
	
	
	@Description("login page url test....")
	@Severity(SeverityLevel.NORMAL)
	@Test(priority = 2)
	public void loginPageUrlTest() {
		Assert.assertTrue(loginpage.getLoginPageUrl());
	}
	
	@Description("login page isdisplaying forgot password link test....")
	@Severity(SeverityLevel.CRITICAL)
	@Test(priority = 3)
	public void isForgotPasswordLinkExist() {
		Assert.assertEquals(loginpage.forgotPasswordLinkExist(), true);
	}
	
	@Description("login page login test with correct username and password....")
	@Severity(SeverityLevel.BLOCKER)
	@Test(priority = 4)
	public void loginTest() {
		accPage = loginpage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertTrue(accPage.isLogOutLinkExist());
		
	}
}