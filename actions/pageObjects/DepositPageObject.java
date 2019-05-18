package pageObjects;

import org.openqa.selenium.WebDriver;

import bankguru.HomePageUI;
import commons.AbstractPage;
import commons.PageFactoryManage;

public class DepositPageObject extends AbstractPage{
	public DepositPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	private WebDriver driver;

}
