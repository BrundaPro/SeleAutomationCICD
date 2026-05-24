package SeleniumFrameworkDesign.Tests;

import java.time.Duration;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

//import seleniumFrameworkDesign.pageObjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) 
	{
		String productToCart="ADIDAS ORIGINAL";
		WebDriver driver=new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		
		//LandingPage landingpage=new LandingPage(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.id("userEmail")).sendKeys("bhamchandu@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Udemy@123");
		driver.findElement(By.id("login")).click();
		driver.manage().window().maximize();
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));   //Explicit wait 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3"))); 
		//waiting until all the products appears on the page
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod=products.stream().filter(product->
						product.findElement(By.cssSelector("b")).getText().equals(productToCart)).findFirst().orElse(null);
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cart h3"));
		
		Boolean cartYes=cartProducts.stream().anyMatch(cartproduct->cartproduct.getText().equalsIgnoreCase(productToCart));
		Assert.assertTrue(cartYes);
		
		driver.findElement(By.xpath("//button[text()='Checkout']")).click();
		
		Actions a=new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();
		//to select the place holder and type india in it for search , build and perform is compulsory for actions
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-item")));
		// explicit wait to show all the results containing 'india' text in dropdown
		
		driver.findElement(By.xpath("//div[@class='payment__shipping']//button[2]")).click();
		//there were 2 results so we chose 2nd option using this locator
		
		driver.findElement(By.cssSelector(".btnn.action__submit")).click();
		
		String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//assert to compare the message displayed on screen ignoring case of the text
		driver.close();
		
	}

}
