package bankguru.pageFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import commons.AbstractPage;

public class LoginPageFactory extends AbstractPage{
	private WebDriver driver;
	public LoginPageFactory(WebDriver mappingDriver) {
		this.driver = mappingDriver;
		PageFactory.initElements(driver, this.getClass());
	}

	@FindBy(how = How.XPATH, using ="//form[@name='frmLogin']")
	private WebElement loginForm;
	
	@FindBy(how = How.NAME, using ="uid")
	private WebElement userIDTextbox;
	
	@FindBy(how = How.NAME, using ="password")
	private WebElement passwordTextbox;
	
	@FindBy(how = How.CSS, using ="input[name='btnLogin']")
	private WebElement loginButton;

	@FindBy(how = How.XPATH, using ="//a[text()='here']")
	private WebElement hereLink;

	public boolean isLoginFormDisplayed() {
		return true;
	}
	
	public String getLoginUrl() {
		return null;
	}
	
	public void clickToHereLink() {
		
	}
	
	public void inpuToUserIDTextbox(String userIDInfor) {
		
	}
	
	public void inputToPasswordTextbox(String passwordInfo) {
		
	}
	
	public void clickToLoginButton() {
		
	}
}
