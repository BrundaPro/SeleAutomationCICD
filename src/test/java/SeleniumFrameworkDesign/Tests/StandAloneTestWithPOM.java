package SeleniumFrameworkDesign.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.TestComponents.BaseTest;
import seleniumFrameworkDesign.pageObjects.CartPage;
import seleniumFrameworkDesign.pageObjects.CheckOutPage;
import seleniumFrameworkDesign.pageObjects.ConfirmationPage;
import seleniumFrameworkDesign.pageObjects.OrderPage;
import seleniumFrameworkDesign.pageObjects.prodCataloguePage;

public class StandAloneTestWithPOM extends BaseTest{
	String productToCart="ADIDAS ORIGINAL"; //declaring here for class level access
	@Test(dataProvider="getData",groups={"Purchase"})
	//public void sumitOrder(String email,String password,String productToCart) throws IOException //this is providing through @DataProvider annotation
	public void sumitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		//String productToCart="ADIDAS ORIGINAL";//declare here for method level access
		 //WebDriver driver=new ChromeDriver(); //driver.manage().window().maximize(); //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5)); //LandingPage landingpage=new LandingPage(driver); //landingpage.goTo();
		//LandingPage landingpage=launchApplication(); //becz Landingpage object is created in child class BaseTest.java from there we have and can access it
		prodCataloguePage cataloguePage=landingpage.loginApplication(input.get("email"),input.get("password"));//prodCataloguePage cataloguePage=new prodCataloguePage(driver);
		
		List<WebElement> products=cataloguePage.getProductList();
		cataloguePage.addProductToCart(input.get("product"));//cataloguePage.addProductToCart(productToCart);
		CartPage cartPage=cataloguePage.goToCartPage();    //CartPage cartPage=new CartPage(driver);
		
		Boolean cartMatch=cartPage.VerifyCartProductDisplay(input.get("product"));	//Boolean cartMatch=cartPage.VerifyCartProductDisplay(productToCart);		
		Assert.assertTrue(cartMatch); //Assertions always stays in testcase file only , validations do not go to Page object files
		CheckOutPage checkoutPage=cartPage.goToCheckout();
	
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmPage=checkoutPage.submitOrder();
		
		String confirmMessage=confirmPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//driver.close(); this method is defined in BaseTest class with @AfterEmthod Annotation
		
	}
	
	@Test(dependsOnMethods={"sumitOrder"})
	public void OrderHistoryTest()
	{
		prodCataloguePage cataloguePage=landingpage.loginApplication("bhamchandu@gmail.com", "Udemy@123");
		OrderPage orderPage=cataloguePage.gotoOrderPage(); //using cataloguePage object we are receiving orderpage oject which has a data after clicking the order page button
		Assert.assertTrue(orderPage.VerifyOrderProductDisplay(productToCart));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\SeleniumFrameworkDesign\\data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	//Providing data or ddriving data using @DataProvider annotation 
//	@DataProvider
//	public Object[][] getData()
//	{
//		return new Object[][] {{"bhamchandu@gmail.com","Udemy@123","ADIDAS ORIGINAL"},{"bhamchandu@gmail.com","Udemy@123","ZARA COAT 3"}};
//	}
	
	
	//We can provide data using HashMap too 
//	@DataProvider
//	public Object[][] getData()
//	{
//		HashMap<String,String> map=new HashMap<String,String>();
//		map.put("email", "bhamchandu@gmail.com");
//		map.put("password", "Udemy@123");
//		map.put("product","ADIDAS ORIGINAL");
//		
//		HashMap<String,String> map1=new HashMap<String,String>();
//		map1.put("email", "bhamchandu@gmail.com");
//		map1.put("password", "Udemy@123");
//		map1.put("product","ZARA COAT 3");
//		
//		return new Object[][] {{map},{map1}};
//	}

}
