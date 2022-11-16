package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.log4testng.Logger;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.Util.ElementUtil;
import com.qa.opencart.factory.Driverfactory;

import io.qameta.allure.Step;

public class LoginPage {
	

	// every page its having is own private driver, driver is having default value
	// is nul, dont want to initialize my driver = new chromedriver

	private WebDriver driver;
	private ElementUtil eleUtil;

	// 1. By Locator
	// all variable should be private and it should be access by he public page
	// actions.
	// anyone can access this by locator from outside the login so we have to create
	// private. this is encapsulation concept

	// drier related work should be inside the page class

	By emailId = By.id("input-email");
	By pwd = By.id("input-password");
	By loginbtn = By.xpath("//input[@value='Login']");
	By LogoImage = By.cssSelector("img[title='naveenopencart']");
	By forgotpassword = By.linkText("Forgotten Password");
	By registerLink = By.linkText("Register");
	
	private static final Logger LOG = Logger.getLogger(LoginPage.class);

	// 2.page constructor
	// create constructor- restrict a constructor, just give WebDrver driver,
	// otherwise you will not creating constructor
	// initializing a driver with the help of one public constructor is known as
	// encapsulation

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}

	// 3. page actions:
	
	@Step("Waiting for login page title and fetching the title")
	public String getLoginPageTitle() {
	String title = eleUtil.waitForTitleIs(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_TITLE);
		System.out.println("login page title:" + title);
		LOG.info("login page title:" +title);
		return title;
	}
	
	@Step("Waiting for login page url and fetching the url")
	public boolean getLoginPageUrl() {
		String url = eleUtil.waitForUrlContains(AppConstants.DEFAULT_TIME_OUT, AppConstants.LOGIN_PAGE_URL_PARAM);	
		System.out.println("login page url:" + url);
		if (url.contains(AppConstants.LOGIN_PAGE_URL_PARAM)) {
			return true;
		} else {
			return false;
		}
	}
	
	@Step("checking forgot password link is displayed on login page")
	public boolean forgotPasswordLinkExist() {
		return eleUtil.doEleIsDisplayed(forgotpassword);
	}
	
	@Step("login with username: {0} and password {1}")
	public AccountsPage doLogin(String username, String password) {
		System.out.println("user cred are:" + username + " : " + password);	
		eleUtil.doSendKeysWithWait(emailId, AppConstants.DEFAULT_LARGE_TIME_OUT, username);
		eleUtil.doSendKeys(pwd, password);
		eleUtil.doClick(loginbtn);
		return new AccountsPage(driver);
	}
	
	@Step("navigating to registration page")
	public RegisterPage navigateToRegisterPage() {
		System.out.println("navigating to register page..........");
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}

}
