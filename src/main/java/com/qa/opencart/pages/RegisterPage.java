package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.Constants.AppConstants;
import com.qa.opencart.Util.ElementUtil;

public class RegisterPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	
	private By FN = By.id("input-firstname");
	private By LN = By.id("input-lastname");
	private By Email = By.id("input-email");
	private By Tel = By.id("input-telephone");
	private By pwd = By.id("input-password");
	private By confpwd = By.id("input-confirm");
	
	private By agreeCheckBox = By.name("agree");
	private By continueButton = By.xpath("//input[@value='Continue' and @type='submit']");
	
	private By  subscribeYes = By.xpath("//input[@name='newsletter' and @value='1']");
	private By  subscribeNo = By.xpath("//input[@name='newsletter' and @value='0']");
	
	private By registerSuccessMsg = By.cssSelector("div#content h1");
	private By logoutLink = By.linkText("Logout");
	private By registerLink = By.linkText("Register");
	
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(driver);
	}
	
	
	public String	 userRegister(String firstName, String lastName, String email, String telephone, String password, String subscribe) {
		
		eleUtil.doSendKeysWithWait(FN, AppConstants.DEFAULT_LARGE_TIME_OUT, firstName);
		eleUtil.doSendKeys(LN, lastName);
		eleUtil.doSendKeys(Email, email);
		eleUtil.doSendKeys(Tel, telephone);
		eleUtil.doSendKeys(pwd, password);
		eleUtil.doSendKeys(confpwd, password);
		
		if(subscribe.equalsIgnoreCase("Yes")) {
			eleUtil.doClick(this.subscribeYes);
		}
		else {
			eleUtil.doClick(this.subscribeNo);
		}
		
		eleUtil.doClick(this.agreeCheckBox);
		eleUtil.doClick(this.continueButton);
		
		String succMesg = eleUtil.waitForElementVisible(registerSuccessMsg, AppConstants.DEFAULT_LARGE_TIME_OUT).getText();
		System.out.println("Success Message....." +succMesg);
		
		eleUtil.doClick(logoutLink);
		eleUtil.doClick(registerLink);
		return succMesg;
		
	}

}
