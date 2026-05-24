package seleniumFrameworkDesign.pageObjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class CartPage extends AbstractComponents
{
	WebDriver driver;
	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cart h3")
	List<WebElement> CartProductList;
	
	@FindBy(xpath="//button[text()='Checkout']")
	WebElement checkOutButton;
	
	public Boolean VerifyCartProductDisplay(String ProductName)
	{
		Boolean cartMatch=CartProductList.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(ProductName));
		return cartMatch;

	}
	
	public CheckOutPage goToCheckout()
	{
		checkOutButton.click();
		CheckOutPage checkoutPage=new CheckOutPage(driver);
		return checkoutPage;
	}
	
	
	
}
