package Selenium.PageObjectClass;

/*
 * click the cart button 
 * check the elements added to the cart are valid or not   
 */

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Selenium.AbstractComponents.AbstractComponents;

//using the concept of inheritance by extending to "AbstractComponents" class. 
public class GoToCart extends AbstractComponents {

	WebDriver driver;
	
	public GoToCart(WebDriver driver){
		// a constructor. Sending value to parent class. Initializing driver.
		super(driver); 
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	// to store Cart button WebElement
	@FindBy(xpath="//button[@routerlink='/dashboard/cart']")
	WebElement cartButton;
	
	@FindBy(xpath="//div[@class='cart']//h3")
	List<WebElement> addedItems;
	
	public void clickCartButton() { 
		// this method for clicking on the cart button
		cartButton.click();
	}
	
	public Boolean checkAddedItems(String requiredItem) {
		Boolean result= addedItems.stream().anyMatch(s->(s.getText()).equals(requiredItem));
		return result;
	}
	
}
