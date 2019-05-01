package com.bankguru.account;

import org.testng.annotations.Test;

import commons.AbstractPage;

import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Account_Level_02_ApplyAbstractPage {
	WebDriver driver;
	private String  email, userID, password;
	AbstractPage abstractPage;

	@Test
	public void TC_01_Register() {
		driver.get("http://demo.guru99.com/v4/");
		abstractPage.clickToElement(driver, "//a[text()='here']");
		Assert.assertTrue(abstractPage.isControlDisplayed(	driver, "//input[@name='emailid']"));
		abstractPage.sendKeyToElement(driver, "//input[@name='emailid']", email);
		abstractPage.clickToElement(driver, "//input[@name='btnLogin']");
		userID = abstractPage.getTextElement(driver, "//td[text()='User ID :']/following-sibling::td");
		password = abstractPage.getTextElement(driver, "//td[text()='Password :']/following-sibling::td");
		System.out.println("Username = " + userID);
		System.out.println("Password = " + password);
	}

	@Test
	public void TC_02_LoginWithAboveInfo() {
		abstractPage.openUrl(driver, "http://demo.guru99.com/v4/");
		abstractPage.sendKeyToElement(driver, "//input[@name='uid']", userID);
		abstractPage.sendKeyToElement(driver, "//input[@name='password']", password);
		abstractPage.clickToElement(driver, "//input[@name='btnLogin']");
		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"));
		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//td[text()='Manger Id : " + userID + "']"));
	}

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		abstractPage = new AbstractPage();
		abstractPage.openUrl( driver, "http://demo.guru99.com/v4/");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		email = "tranhuyentb89" + ramdomNumber() + "@gmail.com";
		System.out.println(email);
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
