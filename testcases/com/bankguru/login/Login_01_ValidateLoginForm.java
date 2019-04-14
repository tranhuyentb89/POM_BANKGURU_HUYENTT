package com.bankguru.login;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class Login_01_ValidateLoginForm {
	WebDriver driver;
	private String loginUrl, email, userID, password;
  @Test
  public void TC_01_Register() {
	  driver.get("http://demo.guru99.com/v4/");
	  loginUrl= driver.getCurrentUrl();
	  driver.findElement(By.xpath("//a[text()='here']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//input[@name='emailid']")).isDisplayed());
	  driver.findElement(By.xpath("//input[@name='emailid']")).sendKeys(email);
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  
	  //get userId va password cho TC02
	  userID = driver.findElement(By.xpath("//td[text()='User ID :']/following-sibling::td")).getText();
	  password = driver.findElement(By.xpath("//td[text()='Password :']/following-sibling::td")).getText();
	  System.out.println("Username = " + userID);
	  System.out.println("Password = " + password);

  }
  @Test
  public void TC_02_LoginWithAboveInfo() {
	  driver.get(loginUrl);
	  driver.findElement(By.xpath("//input[@name='uid']")).sendKeys(userID);
	  driver.findElement(By.xpath("//input[@name='password']")).sendKeys(password);
	  driver.findElement(By.xpath("//input[@name='btnLogin']")).click();
	  Assert.assertTrue(driver.findElement(By.xpath("//marquee[text()=\"Welcome To Manager's Page of Guru99 Bank\"]")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//td[text()='Manger Id : "+ userID +"']")).isDisplayed());

  }
  @BeforeClass
  public void beforeClass() {
	  driver = new FirefoxDriver();
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
