package Selenium;

/*
 * This Program implements:
 * 1. login to the Website: class "LoginPage"
 * 2. adding required product to the cart: class "FindRequiredProduct"
 * 3. checking the added product is correct: class "GoToCart"
 * 4. proceeding for checkout and filling the address and card details.: class "Checkout"
 * 5. check for "confirmation" message and print the order ID.: class "OrderDetails"
 */

import Selenium.PageObjectClass.Checkout;
import Selenium.PageObjectClass.FindRequiredProduct;
import Selenium.PageObjectClass.GoToCart;
import Selenium.PageObjectClass.LoginPage;
import Selenium.PageObjectClass.OrderDetails;

import org.testng.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class StandAloneTest2 {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver= new ChromeDriver();
		driver.manage().window().maximize();
		
		//credentials to sign in: email: seleniumhandson@gmail.com; password: Selenium@9
		LoginPage obj1= new LoginPage(driver);
		obj1.goTo();
		obj1.loginApplication("seleniumhandson@gmail.com", "Selenium@9");
		
		
		String searchForProduct= "ZARA COAT 3";
		//finding the required element and adding elements to cart
		FindRequiredProduct obj2 = new FindRequiredProduct(driver);
		obj2.getRequiredProduct(searchForProduct);
				
		//clicking on "cart" to get redirected to the cart page & checking the added to the cart list, with the requested item.
		GoToCart obj3 = new GoToCart(driver);
		obj3.clickCartButton();
		Boolean matchFound = obj3.checkAddedItems(searchForProduct);
		Assert.assertTrue(matchFound);
		
		//clicking on the "checkout" option and filling all the details to place the order.
		String requiredCountryName= "India";
		Checkout obj4= new Checkout(driver);
		obj4.clickCheckoutButton(requiredCountryName);
		
		//to fetch order ID and confirmation message 
		OrderDetails obj5 = new OrderDetails(driver);
		String confirmationMessage= obj5.collectOrderDetails();
		Assert.assertEquals(confirmationMessage,"THANKYOU FOR THE ORDER.");		
		driver.quit();
	}

	// we can remove the object creation process, by creating and sending in the objects from one test page class to another. This is known as encapsulation. Eg: We are sure that after handling login page, it will redirect to FindRequiredProduct. So we can directly create object of FindRequiredProduct class in log in page, rather than craeting in the main class. 
}
