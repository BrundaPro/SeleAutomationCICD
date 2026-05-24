package seleniumFrameworkDesign.pageObjects;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponents.AbstractComponents;

public class CheckOutPage extends AbstractComponents
{
	WebDriver driver;
	public CheckOutPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement nation;
	
	@FindBy(xpath="//div[@class='payment__shipping']//button[2]")
	WebElement selectNation;
	
	@FindBy(css=".btnn.action__submit")
	WebElement submit;
	
	By resultToShow=By.cssSelector(".ta-item");
	
	public void selectCountry(String countryName)
	{
		Actions a=new Actions(driver);
		a.sendKeys(nation, countryName).build().perform();	
		waitForElementToAppear(resultToShow);
		selectNation.click();		
	}
	
	public ConfirmationPage submitOrder()
	{
		submit.click();
		ConfirmationPage confirmPage=new ConfirmationPage(driver);
		return confirmPage;
	}
	
	
}
