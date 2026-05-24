package SeleniumFrameworkDesign.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.TestComponents.BaseTest;
import SeleniumFrameworkDesign.TestComponents.Retry;
import seleniumFrameworkDesign.pageObjects.CartPage;
import seleniumFrameworkDesign.pageObjects.prodCataloguePage;

public class ErrorValidation extends BaseTest{

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException
	{
		//String productToCart="ADIDAS ORIGINAL";  
		landingpage.loginApplication("bhamchanadu@gmail.com", "Udemy@123");
		Assert.assertEquals("Incorrect email or password.", landingpage.errorMsgCheck());
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException
	{
		String productToCart="ADIDAS ORIGINAL"; 
		prodCataloguePage cataloguePage=landingpage.loginApplication("bhamchandu@gmail.com", "Udemy@123");//prodCataloguePage cataloguePage=new prodCataloguePage(driver);
		
		cataloguePage.addProductToCart(productToCart);
		CartPage cartPage=cataloguePage.goToCartPage();    //CartPage cartPage=new CartPage(driver);
		
		Boolean cartMatch=cartPage.VerifyCartProductDisplay("ADIDAS");		
		Assert.assertFalse(cartMatch); 
	}

}
