package com.bankguru.account;

import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import java.util.Random;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Account_Level_06_MultiBrowser_ParallelTesting extends AbstractTest{
	WebDriver driver;
	private String email, userID, password, loginPageUrl;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultiBrowser(browserName);
		email = "tranhuyentb89" + ramdomNumber() + "@gmail.com";
		loginPage = new LoginPageObject(driver);

	}

	@Test
	public void TC_01_Register() {
		// khoi tạo login page để map driver giữa 2 tầng
		// loginPage = new LoginPageObject(driver);
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPageUrl = loginPage.getLoginPageUrl();
		registerPage = loginPage.clickToHereLink();
		Assert.assertTrue(registerPage.isRegisterPageDisplayed());
		registerPage.inputToEmailTextbox(email);
		registerPage.clickToLoginButton();
		userID = registerPage.getUserIDInfo();
		password = registerPage.getPasswordInfo();
	}

	@Test
	public void TC_02_LoginWithAboveInfo() {
		loginPage = registerPage.openLoginPage(loginPageUrl);
		// loginPage = new LoginPageObject(driver);
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(password);
		homePage = loginPage.clickToLoginButton();

		Assert.assertTrue(homePage.isWelcomeMsgDisplayed());
		Assert.assertTrue(homePage.isUserIDisplayed(userID));

		// Logout
		loginPage = homePage.clickToLougoutLink();
		Assert.assertTrue(loginPage.isLoginFormDisplayed());

	}

	@AfterClass
	public void afterClass() {
		driver.quit();
	}

	public int ramdomNumber() {
		Random radom = new Random();
		int number = radom.nextInt(9999999);
		return number;
	}

}
