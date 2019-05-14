package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.HomePageUI;
import commons.AbstractPage;

public class HomePageObject extends AbstractPage{
	public HomePageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	private WebDriver driver;

	public boolean isWelcomeMsgDisplayed() {
		waitForElementVisible(driver, HomePageUI.WELCOME_MESSAGE);
		return isControlDisplayed(driver, HomePageUI.WELCOME_MESSAGE);
	}

	public boolean isUserIDisplayed(String userID) {
		String USER_ID_FORMAT = String.format(HomePageUI.USER_ID_TEXT, userID);
		waitForElementVisible(driver, USER_ID_FORMAT);
		return isControlDisplayed(driver, USER_ID_FORMAT);
	}

}