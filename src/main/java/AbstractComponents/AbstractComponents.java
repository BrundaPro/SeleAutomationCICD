package AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import seleniumFrameworkDesign.pageObjects.CartPage;
import seleniumFrameworkDesign.pageObjects.OrderPage;

public class AbstractComponents 
{
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css="[routerlink*='cart']")
	WebElement cartClick;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderClick;
	
	public void waitForElementToAppear(By findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));   //Explicit wait 
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy)); 
	}
	
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));   //Explicit wait 
		wait.until(ExpectedConditions.visibilityOf(findBy)); 
	}
	
	
	public void waitForElementToDisappear(WebElement ele)
	{
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));   //Explicit wait 
		wait.until(ExpectedConditions.invisibilityOf(ele));
		//We can use Thread.sleep(1000) to make it faster because invisibilityOf is waiting for the internal spinner to invisible and 
		//all the elements to load and then it going to next page - taking 4 to 5 seconds of time
	}
	
	public CartPage goToCartPage() throws InterruptedException
	{
		//Thread.sleep(2000);
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));   //Explicit wait 
		wait.until(ExpectedConditions.visibilityOf(cartClick));
		cartClick.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage gotoOrderPage()
	{
		orderClick.click();
		OrderPage orderPage=new OrderPage(driver);
		return orderPage;
	}

}
