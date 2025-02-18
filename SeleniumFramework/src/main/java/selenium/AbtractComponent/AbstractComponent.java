package selenium.AbtractComponent;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Test.SeleniumFramework.pageobjects.CartPage;
import Test.SeleniumFramework.pageobjects.OrderPage;

public class AbstractComponent {
	
	WebDriver driver;
	public AbstractComponent(WebDriver driver) 
	{
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	@FindBy(css="app-dashboard[class='ng-star-inserted'] nav")
	WebElement topSection;
	
	

	public void waitForElementToApper(By findBy)
	{
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    
	}
	
	public void waitForWebElementToApper(WebElement findBy)
	{
	
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(2));
    wait.until(ExpectedConditions.visibilityOf(findBy));
    
	}
	
	public void waitForElementToDisapper(WebElement Ele) throws InterruptedException
	{
	 Thread.sleep(3000);
	//WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
	//wait.until(ExpectedConditions.invisibilityOf((Ele)));
    
	}
	
	public void scrollDown()
	{
		Actions a = new Actions(driver);
	    a.sendKeys(Keys.PAGE_DOWN).build().perform();
	}
	
	public void scrollUp()
	{
		Actions a = new Actions(driver);
	    a.sendKeys(Keys.PAGE_UP).build().perform();
	}
	
	public CartPage goToCartPage( )
	{
		scrollUp();
		waitForWebElementToApper(topSection);
		cartHeader.click();
		CartPage cartPage =  new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrdersPage( )
	{
		scrollUp();
		waitForWebElementToApper(topSection);
		orderHeader.click();
		OrderPage orderPage =  new OrderPage(driver);
		return orderPage;
	}
}
