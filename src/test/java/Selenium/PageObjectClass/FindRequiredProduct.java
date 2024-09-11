package Selenium.PageObjectClass;

/*
 * wait to appear the elements
 * search for the required item
 * add to cart the required item
 * wait the successful toast notification to disappear.
 */
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import Selenium.AbstractComponents.AbstractComponents;

//using the concept of inheritance by extending to "AbstractComponents" class. 
public class FindRequiredProduct extends AbstractComponents {

	WebDriver driver;
	
	public FindRequiredProduct(WebDriver driver){
		super(driver); //a constructor. Initializing driver. Also sending driver to the AbstractComponents class and all the child class has to send the super parameter to the parent
		this.driver= driver;
		PageFactory.initElements(driver, this);
	}
	
	// to store list of product as web elements	
	@FindBy(xpath="//div[@class='card-body']/h5/b")
	List<WebElement> products; 
	
	public List<WebElement> waitBeforeProductsLoad() { 
		//this method will wait for products to load and then again it will send back the list of the items.
		waitForElementVisibility(By.xpath("//div[@class='card-body']/h5/b"));
		return products;
	}
		
	public void getRequiredProduct(String searchingProduct) {
		// this method will search for the required products and return the product once found. Else return null
		WebElement ProductFound= waitBeforeProductsLoad().stream().filter(s->s.getText().equals(searchingProduct)).findFirst().orElse(null);
		addToCart(ProductFound);
	}
	
	public void addToCart(WebElement ProductFound) {
		// this method will click on the "add to cart" button for the required product. Also wait for the confirms notification to appear and disappear.
		ProductFound.findElement(By.xpath("parent::h5/parent::div/button[2]")).click();
		waitForElementVisibility(By.id("toast-container"));
		waitForElementInvisibility(By.id("toast-container"));
	}
	
}
