package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.HomePageUI;
import commons.AbstractPage;
import commons.PageFactoryManage;

public class NewAccountPageObject extends AbstractPage{
	public NewAccountPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	private WebDriver driver;

}
