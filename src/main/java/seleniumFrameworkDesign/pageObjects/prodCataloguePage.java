package seleniumFrameworkDesign.pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponents.AbstractComponents;

public class prodCataloguePage extends AbstractComponents
{
	WebDriver driver;
	public prodCataloguePage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css=".mb-3") //declaration
	List <WebElement> prodList; //to get the product list from the cssSelector ".mb-3"
	
	By productsBy=By.cssSelector(".mb-3");
	By addToCartBy=By.cssSelector(".card-body button:last-of-type");
	By toasterwaitBy=By.cssSelector("#toast-container");

	@FindBy(css=".ng-animating")
	WebElement spinnerWait;
	
	
	public List<WebElement> getProductList() //method to return the product list
	{
		waitForElementToAppear(productsBy); //wait till products are appear and then return he product list
		return prodList;
	}
	
	public WebElement getProductByName(String ProductName)
	{
		WebElement prod=prodList.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equals(ProductName)).findFirst().orElse(null);
		return prod;
		//here we are choosing the single product name from the 'prodList' and returning that productname if its
		//present in the list
	}
	
	public void addProductToCart(String ProductName)
	{
		WebElement prod=getProductByName(ProductName);
		prod.findElement(addToCartBy).click();//prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		//here based on the productname given through the arguments we are clicking the 'Add to Cart' button present in that product WebElement
		//as we are locating a product not WebElement so we locate using that product only 
		
		waitForElementToAppear(toasterwaitBy);
		waitForElementToDisappear(spinnerWait);
	}
	
}
