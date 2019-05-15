package com.bankguru.account;

import org.testng.annotations.Test;

import commons.PageFactoryManage;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Account_Level_05_PageFactoryManager_SingletonPattern {
	WebDriver driver;
	private String email, userID, password, loginPageUrl;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		email = "tranhuyentb89" + ramdomNumber() + "@gmail.com";
		//loginPage = new LoginPageObject(driver);
		loginPage = PageFactoryManage.getLoginPage(driver);
	}

	@Test
	public void TC_01_Register() {
		// khoi tạo login page để map driver giữa 2 tầng
		//loginPage = new LoginPageObject(driver);
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
		loginPage= registerPage.openLoginPage(loginPageUrl);
		//loginPage = new LoginPageObject(driver);
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(password);
		homePage= loginPage.clickToLoginButton();

		Assert.assertTrue(homePage.isWelcomeMsgDisplayed());
		Assert.assertTrue(homePage.isUserIDisplayed(userID));
		
		//Logout
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
