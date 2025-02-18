package Test.SeleniumFramework.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Test.SeleniumFramework.Components.BaseTest;
import Test.SeleniumFramework.pageobjects.CartPage;
import Test.SeleniumFramework.pageobjects.CheckoutPage;
import Test.SeleniumFramework.pageobjects.ConfirmationPage;
import Test.SeleniumFramework.pageobjects.OrderPage;
import Test.SeleniumFramework.pageobjects.ProductCatalogue;


public class SubmitOrderTest extends BaseTest{
	
	String productName = "BANARSI SAREE";

	@Test(dataProvider="getData",groups = {"Purchase"})
	
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException
	{
        ProductCatalogue productCatalogue = landingPage.LoginApplication(input.get("email"),input.get("password"));
        
       
        List <WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();
        
        
        Boolean match = cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckOut();
        
        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        
        String confirmMessage = confirmationPage.verifyConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
        
        

	}
	
	@Test(dependsOnMethods = {"submitOrder"})
	
	public void OrderHistoryTest()
	{
		ProductCatalogue productCatalogue = landingPage.LoginApplication("poiuyt@gmail.com","Poiuyt@7275");
		OrderPage orderPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		/*HashMap<String, String> map = new HashMap<String, String>();
		map.put("email", "np26900@gmail.com");
		map.put("password", "Naveen@7275");
		map.put("product", "BANARSI SAREE");
		
		HashMap<String, String> map1 = new HashMap<String, String>();
		map1.put("email", "zxc1234@gmail.com");
		map1.put("password", "Qwerty@7275");
		map1.put("product", "ADIDAS ORIGINAL");*/
		
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\Test\\SeleniumFramework\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}

}
