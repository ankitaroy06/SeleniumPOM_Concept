package Selenium.PageObjectClass;

/*
 * click on "checkout" button
 * 
 */

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Selenium.AbstractComponents.AbstractComponents;

//using the concept of inheritance by extending to "AbstractComponents" class. 
public class OrderDetails extends AbstractComponents {

	WebDriver driver;
	
	public OrderDetails(WebDriver driver){
		//a constructor. Initializing driver. Also sending driver to the AbstractComponents class and all the child class has to send the super parameter to the parent
		super(driver); 
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	// to store checkout button as web element	
	@FindBy(className="hero-primary")
	WebElement orderStatus;	
	
	@FindBy(xpath="//label[@class='ng-star-inserted']")
	WebElement orderIDField;
	
	public String collectOrderDetails() { 
		// wait for the contents to load; fetch the order ID; Fetch the order confirmation message and return the same
		waitForElementVisibility(By.className("box")); // waiting for contents to load
		
		//fetching Order ID
		System.out.println("Order ID: " + orderIDField.getText().split(" ")[1].split(" ")[0]);
		//checking for order confirmed message
		String orderConfirmationMessage= orderStatus.getText();
		System.out.println(orderConfirmationMessage);
		return orderConfirmationMessage;
	}
}
