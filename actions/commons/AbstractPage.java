package commons;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractPage {
	/* WEB BROWSER */
	public void openUrl(WebDriver driver, String Url) {
		driver.get(Url);
	}

	public String getCurrentUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void back(WebDriver driver) {
		driver.navigate().back();
	}

	public void forward(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refresh(WebDriver driver) {
		driver.navigate().refresh();
	}

	public void acceptAlert(WebDriver driver) {
		driver.switchTo().alert().accept();
	}

	public void cancelAlert(WebDriver driver) {
		driver.switchTo().alert().dismiss();
	}

	public void sendkeysAlert(WebDriver driver, String value) {
		driver.switchTo().alert().sendKeys(value);
	}

	public String gettextAlert(WebDriver driver) {
		return driver.switchTo().alert().getText();
	}

	/* WEB ELEMENT */
	public void clickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(value);
	}

	public void selectItemInHtmlDropdown(WebDriver driver, String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		select.selectByVisibleText(value);
	}

	public String getSelectedItemInHtmlDropdown(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Select select = new Select(element);
		return select.getFirstSelectedOption().getText();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String scrollXpath, String parentXpath, String childXpath,
			String expectedValue) throws Exception {
		// scroll toi element (cha)
		JavascriptExecutor javascript;
		javascript = (JavascriptExecutor) driver;
		WebDriverWait wait = new WebDriverWait(driver, 30);
		javascript.executeScript("arguments[0].scrollIntoView(true);", driver.findElement(By.xpath(scrollXpath)));
		Thread.sleep(1000);

		// click vao dropdown
		WebElement element = driver.findElement(By.xpath(parentXpath));
		element.click();
		Thread.sleep(1000);

		// get tat ca cac item trong dropdown vao 1 list elements
		List<WebElement> childList = driver.findElements(By.xpath(childXpath));

		// wait de tat ca cac phan tu trong dropdown duoc hien thi
		wait.until(ExpectedConditions.visibilityOfAllElements(childList));

		// dung vong lap for duyet qua tung phan tu sau do gettext
		for (WebElement child : childList) {
			String textItem = child.getText().trim();

			// neu actual text = expected text thi click vao phan tu do va break ra khoi
			// vong lap
			if (textItem.equals(expectedValue)) {
				// scroll den expected item de click
				javascript.executeScript("arguments[0].scrollIntoView(true);", child);
				Thread.sleep(1000);
				child.click();
				break;
			}
		}

	}

	public String getAttributeInElement(WebDriver driver, String locator, String attributeName) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getAttribute(attributeName);
	}

	public String getTextInElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();
	}

	public int countElementNumber(WebDriver driver, String locator) {
		List<WebElement> element = driver.findElements(By.xpath(locator));
		return element.size();
	}

	public void checkToCheckbox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToCheckbox(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isControlDisplayed(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}
	public boolean isSelected(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isSelected();
	}
	public boolean isEnable(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isEnabled();
	}
	
	public void doubleClickToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.doubleClick(element);
	}
	
	public void hoverToElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.moveToElement(element);
	}
	
	public void waitToElementVisible(WebDriver driver, String locator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		By byLocator = By.xpath(locator);
		explicitwait.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}
	
	public void waitToElementPresent(WebDriver driver, String locator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		By byLocator = By.xpath(locator);
		explicitwait.until(ExpectedConditions.presenceOfElementLocated(byLocator));
	}
	public void waitToElementInvisible(WebDriver driver, String locator) {
		WebDriverWait explicitwait = new WebDriverWait(driver, 30);
		By byLocator = By.xpath(locator);
		explicitwait.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
	}


}
