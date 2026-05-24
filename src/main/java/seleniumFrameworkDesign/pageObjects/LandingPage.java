package seleniumFrameworkDesign.pageObjects;

//import org.openqa.selenium.By;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents
{
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userLogin=driver.findElement(By.id("userEmail"));
	//This is converted to simplier version using @FindBy annotation
	
	@FindBy(id="userEmail")
	WebElement userlog;

	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	
	public prodCataloguePage loginApplication(String username,String password2)
	{
		userlog.sendKeys(username);
		password.sendKeys(password2);
		submit.click();
		prodCataloguePage cataloguePage=new prodCataloguePage(driver);
		return cataloguePage;
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public String errorMsgCheck()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
}
