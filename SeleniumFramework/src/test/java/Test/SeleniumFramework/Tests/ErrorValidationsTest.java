package Test.SeleniumFramework.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import Test.SeleniumFramework.Components.BaseTest;
import Test.SeleniumFramework.pageobjects.CartPage;
import Test.SeleniumFramework.pageobjects.ProductCatalogue;



public class ErrorValidationsTest extends BaseTest{

	@Test
	
	public void LoginErrorValidations() throws IOException, InterruptedException
	{
		String productName = "BANARSI SAREE";
		 
        landingPage.LoginApplication("np260@gmail.com","Navn@7275");      
        Assert.assertEquals("Incorrect email  password.", landingPage.getErrorMessage());
        
        
	}
	
	@Test(groups = {"ErrorHandling"})
	public void productErrorValidations() throws IOException, InterruptedException
	{
		String productName = "BANARSI SAREE";
        ProductCatalogue productCatalogue = landingPage.LoginApplication("zxc1234@gmail.com","Qwerty@7275");
        
       
        List <WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
        
        Boolean match = cartPage.verifyProductDisplay("BANARSI AREE");
        Assert.assertFalse(match);
        
	}

}
