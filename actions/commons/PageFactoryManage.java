package commons;

import org.openqa.selenium.WebDriver;

import pageObjects.HomePageObject;
import pageObjects.LoginPageObject;
import pageObjects.RegisterPageObject;

public class PageFactoryManage {
//	private static LoginPageObject loginPage;
//	private static RegisterPageObject registerPage;
//	private static HomePageObject homePage;
	
	public static LoginPageObject getLoginPage(WebDriver driver) {
		return new LoginPageObject(driver);
	}
	public static RegisterPageObject getRegisterPage(WebDriver driver) {
//		if(registerPage==null) {
//			registerPage = new RegisterPageObject(driver);
//		}
		return new RegisterPageObject(driver);
	}
	public static HomePageObject getHomePage(WebDriver driver) {
//		if(homePage==null) {
//			homePage = new HomePageObject(driver);
//		}
//		return homePage;
		return new HomePageObject(driver);
	}
}
