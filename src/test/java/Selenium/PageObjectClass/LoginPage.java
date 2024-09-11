package Selenium.PageObjectClass;

import org.openqa.selenium.By;

/* this class is being used for handling the login page.
 * Entering the username and password. Finally submitting the credentials. 
 * Successfully able to sign in to the website.  
 */

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Selenium.AbstractComponents.AbstractComponents;

//using the concept of inheritance by extending to "AbstractComponents" class. 
public class LoginPage extends AbstractComponents {

	WebDriver driver;
	
	public LoginPage(WebDriver driver){
		// a constructor. Sending value to parent class. Initializing driver.
		super(driver); //sending driver to the AbstractComponents class and all the child class has to send the super parameter to the parent
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(id="userEmail")
	WebElement loginEmail;
	
	@FindBy(id="userPassword")
	WebElement loginPassword;
	
	@FindBy(id="login")
	WebElement loginButton;
	
	public void goTo() {
		// this method launch the target WebSite
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	public void loginApplication(String email, String password) {
		// this method: entering mail; entering password; clicking on login button; waiting for success message to disappear.
		loginEmail.sendKeys(email);
		loginPassword.sendKeys(password);
		loginButton.click();
		waitForElementInvisibility(By.xpath("//div[@class='card-body']/h5/b"));
	}
	
	
}
