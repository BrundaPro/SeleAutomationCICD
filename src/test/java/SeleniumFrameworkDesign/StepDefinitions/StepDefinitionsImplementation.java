package SeleniumFrameworkDesign.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFrameworkDesign.TestComponents.BaseTest;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import seleniumFrameworkDesign.pageObjects.CartPage;
import seleniumFrameworkDesign.pageObjects.CheckOutPage;
import seleniumFrameworkDesign.pageObjects.ConfirmationPage;
import seleniumFrameworkDesign.pageObjects.LandingPage;
import seleniumFrameworkDesign.pageObjects.prodCataloguePage;

public class StepDefinitionsImplementation extends BaseTest
{
	public LandingPage landingpage;
	public prodCataloguePage cataloguePage;
	public ConfirmationPage confirmPage;
	public CartPage cartPage;
	public CheckOutPage checkoutPage;
	
	@Given("I Landed on Ecommerce Page")
	public void I_Landed_on_Ecommerce_Page() throws IOException
	{
		landingpage=launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_username_and_password(String username,String password)
	{
		cataloguePage=landingpage.loginApplication(username,password);
	}
	
	@When("^I add product (.+) to cart$")
	public void add_product_to_cart(String productName)
	{
		List<WebElement> products=cataloguePage.getProductList();
		cataloguePage.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and sumbit the order$")
	public void checkout_and_submit_order(String productName) throws InterruptedException
	{
		cartPage=cataloguePage.goToCartPage();    
		Boolean cartMatch=cartPage.VerifyCartProductDisplay(productName);			
		Assert.assertTrue(cartMatch); 
		checkoutPage=cartPage.goToCheckout();
	
		checkoutPage.selectCountry("India");
		confirmPage=checkoutPage.submitOrder();
		
	}
	
	@Then ("{string} message is displayed on ConfirmationPage")
	public void message_is_displayed_on_conformationPage(String string)
	{
		String confirmMessage=confirmPage.getConfirmMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string)
	{
		Assert.assertEquals(string, landingpage.errorMsgCheck());
		driver.close();
	}
}
