package Selenium;

/*
 * This Program implements:
 * 1. login to the Website
 * 2. adding required product to the cart
 * 3. checking the added product is correct
 * 4. proceeding for checkout and filling the address and card details.
 * 5. check for "confirmation" message and print the order ID. 
 */
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver= new ChromeDriver();
		driver.get("https://rahulshettyacademy.com/client");
		//credentials to sign in: email: seleniumhandson@gmail.com; password: Selenium@9
		driver.manage().window().maximize();
		
		WebDriverWait wait= new WebDriverWait(driver,Duration.ofSeconds(5)); //explict wait
		
		driver.findElement(By.id("userEmail")).sendKeys("seleniumhandson@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Selenium@9");
		driver.findElement(By.id("login")).click();
		
		//WebDriverWait(driver,Duration.ofSeconds(5)).until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']/h5/b")));
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='card-body']/h5/b")));
		
		String product= "ZARA COAT 3";
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container"))); // this is added here just to make sure the "login successfully" notification disappears before next toast notification appears. 
		//finding and adding elements to cart
		List<WebElement> items= driver.findElements(By.xpath("//div[@class='card-body']/h5/b"));
		WebElement foundItem= items.stream().filter(s->s.getText().equals(product)).findFirst().orElse(null);
		foundItem.findElement(By.xpath("parent::h5/parent::div/button[2]")).click();
		
		/*
		 * Another way to find the text from webelements.
		 * List<WebElement> items= driver.findElements(By.className("card-body"));
		 * WebElement foundItem = items.stream().filter(i->i.findElement(By.tagName("h5")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		 * foundItem.findElement(By.xpath("button[2]")).click();
		 */
		
		//waiting for sometime for the website to become responsive.
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ng-animating")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		System.out.println(driver.findElement(By.id("toast-container")).getText());
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("toast-container")));
		
		//clicking on "cart" to get redirected to the cart page.
		driver.findElement(By.xpath("//button[@routerlink='/dashboard/cart']")).click();
		
		//checking the added to the cart list, with the requested item.
		List<WebElement> addedToCartList= driver.findElements(By.xpath("//div[@class='cart']//h3"));
		Boolean result= addedToCartList.stream().anyMatch(s->(s.getText()).equals(product));
		Assert.assertTrue(result);
		
		//clicking on the "checkout" option and filling all the details to place the order.
		WebElement checkout= driver.findElement(By.xpath("//li[@class='totalRow']/button"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkout);

		// Now you can interact with the element
		checkout.click();
		
		driver.findElement(By.xpath("//div[@class='form__cc']/div[2]/div[2]/input")).sendKeys("123"); // entering CVV
		driver.findElement(By.xpath("//div[@class='form__cc']/div[3]/div/input")).sendKeys("Selenium"); //entering name on the card.
		
		//selecting country
		driver.findElement(By.xpath("//input[@placeholder='Select Country']")).sendKeys("India");
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("ta-results")));
		List<WebElement> countryList= driver.findElements(By.className("ta-item"));
		for(WebElement i: countryList) {
			if((i.getText()).equalsIgnoreCase("India"))
				i.click();
		}
		
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("ta-results")));
		WebElement placeOrderButton= driver.findElement(By.className("action__submit"));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", placeOrderButton);
		placeOrderButton.click();
		
		//printing order ID 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("box")));
		System.out.println("Oreder ID: " + driver.findElement(By.xpath("//label[@class='ng-star-inserted']")).getText().split(" ")[1].split(" ")[0]);
		
		//checking for order confirmed message
		String orderConfirmationMessage= driver.findElement(By.className("hero-primary")).getText();
		System.out.println(orderConfirmationMessage);
		Assert.assertEquals(orderConfirmationMessage,"THANKYOU FOR THE ORDER.");
		
		driver.quit();
	}

}
