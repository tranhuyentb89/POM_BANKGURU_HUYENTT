package pageObjects;

import org.openqa.selenium.WebDriver;

import commons.AbstractPage;

public class NewCustomerPageObject extends AbstractPage{
	public NewCustomerPageObject(WebDriver mappingDriver) {
		driver = mappingDriver;
	}

	private WebDriver driver;

}
