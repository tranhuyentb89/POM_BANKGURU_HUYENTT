package com.bankguru.account;

import java.util.Random;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import commons.AbstractTest;
import pageObjects.ChangePasswordPageObject;
import pageObjects.DepositPageObject;
import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.NewAccountPageObject;
import pageObjects.NewCustomerPageObject;
import pageObjects.RegisterPageObject;

public class Account_Level_10_Assert_Verify_LogReportHTML extends AbstractTest {
	WebDriver driver;
	private String email, userID, password, loginPageUrl;
	LoginPageObject loginPage;
	RegisterPageObject registerPage;
	HomePageObject homePage;
	ChangePasswordPageObject changePwPage;
	DepositPageObject depositPage;
	NewAccountPageObject newAccountPage;
	NewCustomerPageObject newCustomerPage;

	@Parameters("browser")
	@BeforeClass
	public void beforeClass(String browserName) {
		driver = openMultiBrowser(browserName);
		email = "tranhuyentb89" + ramdomNumber() + "@gmail.com";
		loginPage = new LoginPageObject(driver);

	}

	@Test
	public void TC_01_Register() {
		log.info("Register - Step 01: Verify Login form displayed");
		verifyTrue(loginPage.isLoginFormDisplayed());
		
		log.info("Register - Step 02: Get URL of Login form");
		loginPageUrl = loginPage.getLoginPageUrl();
		
		log.info("Register - Step 03: Click to here link");
		registerPage = loginPage.clickToHereLink();
		
		log.info("Register - Step 04: Verify Register page is displayed");
		verifyFalse(registerPage.isRegisterPageDisplayed());
		
		log.info("Register - Step 05: Input email and click to Login button");
		registerPage.inputToEmailTextbox(email);
		registerPage.clickToLoginButton();
		
		log.info("Register - Step 06: Get username and password");
		userID = registerPage.getUserIDInfo();
		password = registerPage.getPasswordInfo();
	}

	@Test
	public void TC_02_LoginWithAboveInfo() {
		log.info("Login - Step 01: Open login form");
		loginPage = registerPage.openLoginPage(loginPageUrl);
		// loginPage = new LoginPageObject(driver);
		
		log.info("Login - Step 02: Verify login form dislayed");
		verifyTrue(loginPage.isLoginFormDisplayed());
		
		log.info("Login - Step 03: Input username and password");
		loginPage.inputToUserIDTextbox(userID);
		loginPage.inputToPasswordTextbox(password);
		
		log.info("Login - Step 04: Click to login button");
		homePage = loginPage.clickToLoginButton();

		log.info("Login - Step 05: Verify welcome message displayed");
		verifyTrue(homePage.isWelcomeMsgDisplayed());
		
		log.info("Login - Step 06: Verify login form displayed");
		verifyTrue(homePage.isLoginFormUndisplayed());
		
		// Logout
		// loginPage = homePage.clickToLougoutLink();
		// Assert.assertTrue(loginPage.isLoginFormDisplayed());

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
