package Selenium.PageObjectClass;

/*
 * click on "checkout" button
 * fill card details
 * select the country from the suggested list
 * click on place order button
 */
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Selenium.AbstractComponents.AbstractComponents;

//using the concept of inheritance by extending to "AbstractComponents" class. 
public class Checkout extends AbstractComponents {

	WebDriver driver;
	
	public Checkout(WebDriver driver){
		//a constructor. Initializing driver. Also sending driver to the AbstractComponents class and all the child class has to send the super parameter to the parent
		super(driver); 
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	// to store checkout button as web element	
	@FindBy(xpath="//li[@class='totalRow']/button")
	WebElement checkoutButton; 
			
	@FindBy(xpath="//div[@class='form__cc']/div[2]/div[2]/input")
	WebElement cvvField;
	
	@FindBy(xpath="//div[@class='form__cc']/div[3]/div/input")
	WebElement nameonCard;
	
	@FindBy(xpath= "//input[@placeholder='Select Country']")
	WebElement CountryField;
	
	@FindBy(className= "ta-item")
	List<WebElement> CountryList;
	
	@FindBy(className="action__submit")
	WebElement placeOrderButton;
	
	
	public void clickCheckoutButton(String requiredCountryName) { 
		//this method will using JavascriptExecutor, to scroll down and find checkout button. Once found, clicking on the button
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkoutButton);
		checkoutButton.click();
		fillCardDetails();
		selectDeliveryCountry(requiredCountryName);
		clickPlaceOrderButton();
	}

	public void fillCardDetails() {
		// to fill the unfilled card details
		cvvField.sendKeys("123"); // entering CVV
		nameonCard.sendKeys("Selenium"); //entering name on the card.
	}
	
	public void selectDeliveryCountry(String requiredCountryName) {
		//to select the country
		CountryField.sendKeys(requiredCountryName);
		waitForElementVisibility(By.className("ta-results"));
		for(WebElement i: CountryList) {
			if((i.getText()).equalsIgnoreCase("India"))
				i.click();
		}
	}
	
	public void clickPlaceOrderButton() {
		// to scroll down and click on place order button
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrderButton);
		placeOrderButton.click();
	}
}
