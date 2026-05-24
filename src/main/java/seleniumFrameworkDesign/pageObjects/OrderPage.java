package seleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents
{
	WebDriver driver;
	public OrderPage(WebDriver driver) 
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="tbody td:nth-child(3)")
	List<WebElement> orderList;
	
	public Boolean VerifyOrderProductDisplay(String ProductName)
	{
		Boolean orderMatch=orderList.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(ProductName));
		return orderMatch;

	}
	
}
