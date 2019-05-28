package commons;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.jetty.html.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import bankguru.AbstractPageUI;
import pageObjects.ChangePasswordPageObject;
import pageObjects.DepositPageObject;
import pageObjects.NewAccountPageObject;
import pageObjects.NewCustomerPageObject;

public class AbstractPage {
	WebDriverWait explicit;
	By byLocator;
	long shortTimeout = 5;
	long longTimeout = 30;

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
	public String getTextElement(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();

	}

	public String getTextElement(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.getText();

	}

	public void clickToElement(WebDriver driver, String locator) {
		highlightElement(driver, locator);
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}

	public void sendKeyToElement(WebDriver driver, String locator, String value) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(value);
	}

	public void sendKeyToElement(WebDriver driver, String locator, String valueToSendkeys, String... values) {
		locator = String.format(locator, (Object[]) values);
		highlightElement(driver, locator);
		WebElement element = driver.findElement(By.xpath(locator));
		element.clear();
		element.sendKeys(valueToSendkeys);
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
		highlightElement(driver, locator);
		WebElement element = driver.findElement(By.xpath(locator));
		return element.isDisplayed();
	}

	public boolean isControlDisplayed(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		highlightElement(driver, locator);
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
		highlightElement(driver, locator);
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.doubleClick(element);
	}

	public void hoverToElement(WebDriver driver, String locator) {

		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.moveToElement(element);
	}

	public void sendKeyboardToElement(WebDriver driver, String locator, Keys key) {
		WebElement element = driver.findElement(By.xpath(locator));
		Actions action = new Actions(driver);
		action.sendKeys(element, key);
	}

	public void switchToChildWindowByID(WebDriver driver, String parent) throws Exception {
		// get ra tat ca cac tab dang co
		Set<String> allWindows = driver.getWindowHandles();
		Thread.sleep(3000);
		// dung vong for duyet qua tung cua so
		for (String runWindow : allWindows) {
			// kiem tra neu Id cua cua so nao khac voi parentId thi switch qua
			if (!runWindow.equals(parent)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}

	public void switchWindowByTitle(WebDriver driver, String title) {
		Set<String> allWindow = driver.getWindowHandles();
		for (String runWindow : allWindow) {
			driver.switchTo().window(runWindow);
			String currentWindow = driver.getTitle();
			if (currentWindow.equals(title)) {
				break;
			}
		}
	}

	public boolean closeAllWithoutParentWindows(WebDriver driver, String parentWindow) throws Exception {
		Set<String> allWindows = driver.getWindowHandles();
		for (String runWindows : allWindows) {
			if (!runWindows.equals(parentWindow)) {
				driver.switchTo().window(runWindows);
				driver.close();
			}
		}
		Thread.sleep(3000);
		driver.switchTo().window(parentWindow);
		if (driver.getWindowHandles().size() == 1)
			return true;
		else
			return false;
	}

	public void switchToIframe(WebDriver driver, String locator) {
		WebElement iframe = driver.findElement(By.xpath(locator));
		driver.switchTo().frame(iframe);
	}

	public void backToTopWindow(WebDriver driver) {
		driver.switchTo().defaultContent();
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
		Date date = new Date();
		By byLocator = By.xpath(locator);
		WebDriverWait waitExplicit = new WebDriverWait(driver, Constants.SHORT_TIMEOUT);
		System.out.println("start time for wait invisible = " + date.toString());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
		System.out.println("End time for wait invisible = " + new Date().toString());
	}
	
	public boolean isControlUndisplayed(WebDriver driver, String locator) {
		Date date = new Date();
		System.out.println("Start time to check control undisplayed " + date.toString());
		overideTimeout(driver, Constants.SHORT_TIMEOUT);
		List <WebElement> elements = driver.findElements(By.xpath(locator));
		System.out.println("Element size = : " + elements.size());
		if(elements.size()==0) {
			System.out.println("Element khong co trong DOM");
			System.out.println("End time to check control undisplayed	= " + new Date().toString());
			overideTimeout(driver, Constants.LONG_TIMEOUT);
			return true;
		}
		else if(elements.size()>0 && !elements.get(0).isDisplayed()) {
			System.out.println("Element co trong DOM nhung ko visible");
			System.out.println("End time to check control undisplayed	= " + new Date().toString());
			overideTimeout(driver, Constants.LONG_TIMEOUT);
			return true;
		}
		else {
			System.out.println("Element co trong dom nhung visible");
			System.out.println("End time to check control undisplayed	= " + new Date().toString());
			overideTimeout(driver, Constants.LONG_TIMEOUT);
			return false;
		}
	}
	public void overideTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	// UPLOAD
	public void upload1File(WebDriver driver, String locator, String filePath) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(filePath);
	}

	public void uploadmultiFile(WebDriver driver, String locator, String filePath01, String filePath02,
			String filePath03) {
		WebElement element = driver.findElement(By.xpath(locator));
		element.sendKeys(filePath01 + "\n" + filePath02 + "\n" + filePath03);
	}

	public void highlightElement(WebDriver driver, String locator) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement element = driver.findElement(By.xpath(locator));
		String originalStyle = element.getAttribute(locator);
		js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, "style",
				"border:3px solid red; border-style:dashed;");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		js.executeScript("arguments[0].setAttribute(arguments[1],arguments[2])", element, "style", originalStyle);

	}

	public Object executeForBrowser(WebDriver driver, String javaSript) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(javaSript);
	}

	public Object clickToElementByJS(WebDriver driver, String xpathName) {
		WebElement element = driver.findElement(By.xpath(xpathName));
		highlightElement(driver, xpathName);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].click();", element);
	}

	public Object sendkeyToElementByJS(WebDriver driver, String xpathName, String value) {
		WebElement element = driver.findElement(By.xpath(xpathName));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public Object removeAttributeInDOM(WebDriver driver, String xpathName, String attribute) {
		WebElement element = driver.findElement(By.xpath(xpathName));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].removeAttribute('" + attribute + "');", element);
	}

	public Object scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public Object scrollToElementByJS(WebDriver driver, String locator) {
		WebElement element = driver.findElement(By.xpath(locator));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public boolean isImageDisplayed(WebDriver driver, String locator) {
		try {
			WebElement element = driver.findElement(By.xpath(locator));
			JavascriptExecutor js = (JavascriptExecutor) driver;
			return (boolean) js.executeScript(
					"return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
					element);
		} catch (Exception e) {
			e.getMessage();
			return false;
		}
	}

	public Object navigateToUrlByJS(WebDriver driver, String url) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript("window.location = '" + url + "'");
	}

	public void waitForElementPresence(WebDriver driver, String locator) {
		explicit = new WebDriverWait(driver, longTimeout);
		byLocator = By.xpath(locator);
		explicit.until(ExpectedConditions.presenceOfElementLocated(byLocator));
	}

	public void waitForElementVisible(WebDriver driver, String locator) {
		explicit = new WebDriverWait(driver, longTimeout);
		byLocator = By.xpath(locator);
		explicit.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}

	public void waitForElementClickAble(WebDriver driver, String locator) {
		explicit = new WebDriverWait(driver, longTimeout);
		byLocator = By.xpath(locator);
		explicit.until(ExpectedConditions.elementToBeClickable(byLocator));

	}

	public void waitForElementInvisible(WebDriver driver, String locator) {
		explicit = new WebDriverWait(driver, longTimeout);
		byLocator = By.xpath(locator);
		explicit.until(ExpectedConditions.invisibilityOfElementLocated(byLocator));
	}

	public void waitForAlertPresence(WebDriver driver) {
		explicit = new WebDriverWait(driver, longTimeout);
		explicit.until(ExpectedConditions.alertIsPresent());
	}

	// open page
	public ChangePasswordPageObject openChangePasswordPage(WebDriver driver) {
		waitToElementVisible(driver, AbstractPageUI.CHANGE_PASSWORD_LINK);
		clickToElement(driver, AbstractPageUI.CHANGE_PASSWORD_LINK);
		return PageFactoryManage.getChangPasswordPage(driver);
	}

	public DepositPageObject openDepositPage(WebDriver driver) {
		waitToElementVisible(driver, AbstractPageUI.DEPOSIT_LINK);
		clickToElement(driver, AbstractPageUI.DEPOSIT_LINK);
		return PageFactoryManage.getDepositPage(driver);
	}

	public NewAccountPageObject openNewAccountPage(WebDriver driver) {
		waitToElementVisible(driver, AbstractPageUI.NEW_ACCOUNT_LINK);
		clickToElement(driver, AbstractPageUI.NEW_ACCOUNT_LINK);
		return PageFactoryManage.getNewAccountPage(driver);
	}

	public NewCustomerPageObject openNewCustomerPage(WebDriver driver) {
		waitToElementVisible(driver, AbstractPageUI.NEW_CUSTOMER_LINK);
		clickToElement(driver, AbstractPageUI.NEW_CUSTOMER_LINK);
		return PageFactoryManage.getNewCustomerPage(driver);
	}

	public AbstractPage openMultiplePage(WebDriver driver, String pageName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, pageName);
		switch (pageName) {
		case "Manager":
			return PageFactoryManage.getHomePage(driver);
		case "Deposit":
			return PageFactoryManage.getDepositPage(driver);
		case "Change Password":
			return PageFactoryManage.getChangPasswordPage(driver);
		case "New Customer":
			return PageFactoryManage.getNewCustomerPage(driver);
		default:
			return PageFactoryManage.getHomePage(driver);
		}
	}

	private void clickToElement(WebDriver driver, String locator, String... values) {
		locator = String.format(locator, (Object[]) values);
		highlightElement(driver, locator);
		WebElement element = driver.findElement(By.xpath(locator));
		element.click();
	}

	private void waitForElementVisible(WebDriver driver, String locator, String... dynamicValue) {
		locator = String.format(locator, (Object[]) dynamicValue);
		explicit = new WebDriverWait(driver, longTimeout);
		byLocator = By.xpath(locator);
		explicit.until(ExpectedConditions.visibilityOfElementLocated(byLocator));
	}
	
	public void openMultiplePages(WebDriver driver, String pageName) {
		waitForElementVisible(driver, AbstractPageUI.DYNAMIC_LINK, pageName);
		clickToElement(driver, AbstractPageUI.DYNAMIC_LINK, pageName);
	}

}
