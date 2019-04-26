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
	private String loginUrl, email, userID, password;
	AbstractPage abstractPage;

	@Test
	public void TC_01_Register() {
		driver.get("http://demo.guru99.com/v4/");
		//loginUrl = driver.getCurrentUrl();
		loginUrl = abstractPage.getCurrentUrl(driver);
		
		//driver.findElement(By.xpath("//a[text()='here']")).click();
		abstractPage.clickToElement(driver, "//a[text()='here']");
		
		//Assert.assertTrue(driver.findElement(By.xpath("//input[@name='emailid']")).isDisplayed());
		Assert.assertTrue(abstractPage.isControlDisplayed(	driver, "//input[@name='emailid']"));
		
		//driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
		abstractPage.sendKeyToElement(driver, "//input[@name='emailid']", email);
		
		//driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		abstractPage.clickToElement(driver, "//input[@name='btnLogin']");
		// get userId va password cho TC02
		
		//userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
		userID = abstractPage.getTextElement(driver, "//td[text()='User ID :']/following-sibling::td");
		
		password = abstractPage.getTextElement(driver, "//td[text()='Password :']/following-sibling::td");
		System.out.println("Username = " + userID);
		System.out.println("Password = " + password);

	}

	@Test
	public void TC_02_LoginWithAboveInfo() {
		abstractPage.openUrl(driver, "http://demo.guru99.com/v4/");
		
		//driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
		abstractPage.sendKeyToElement(driver, "//input[@name='uid']", userID);
		
		//driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
		abstractPage.sendKeyToElement(driver, "//input[@name='password']", password);
		
		//driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
		abstractPage.clickToElement(driver, "//input[@name='btnLogin']");
		//Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
		Assert.assertTrue(abstractPage.isControlDisplayed(driver, "//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]"));
		
		
		//Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : " + userID + "']")).isDisplayed());
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
