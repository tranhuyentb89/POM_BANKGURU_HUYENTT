package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class ChangePasswordPageObject extends AbstractPage{
	public ChangePasswordPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	private WebDriver driver;

}
