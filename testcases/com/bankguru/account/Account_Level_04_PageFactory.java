package com.bankguru.account;

import org.testng.annotations.Test;

import bankguru.pageFactory.LoginPageFactory;
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

public class Account_Level_04_PageFactory {
	WebDriver driver;
	private String  email, userID, password, loginPageUrl;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	LoginPageFactory loginPF;

	@Test
	public void TC_01_Register() {
		// khoi tạo login page để map driver giữa 2 tầng
		//loginPage = new LoginPageObject(driver);
		loginPF = new LoginPageFactory(driver);
		
		//Assert.assertTrue(loginPage.isLoginFormDisplayed());
		Assert.assertTrue(loginPF.isLoginFormDisplayed());

		loginPageUrl = loginPF.getLoginUrl();
		loginPF.clickToHereLink();

	}

	@Test
	public void TC_02_LoginWithAboveInfo() {
		registerPage.openLoginPage(loginPageUrl);
		loginPage = new LoginPageObject(driver);
		Assert.assertTrue(loginPage.isLoginFormDisplayed());
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(password);
		loginPage.clickToLoginButton();
		
		homePage = new HomePageObject(driver);
		Assert.assertTrue(homePage.isWelcomeMsgDisplayed());
		Assert.assertTrue(homePage.isUserIDisplayed(userID));

	}

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.get("http://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		email = "tranhuyentb89" + ramdomNumber() + "@gmail.com";
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
