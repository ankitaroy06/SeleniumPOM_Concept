package Selenium.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponents {
	
//containing the wait elements
	
	WebDriver driver;
	
	public AbstractComponents(WebDriver driver) {
		this.driver= driver;
	}

	
	//parameter "element" is for which we need to apply wait. it is coming from the method where we are invoking these below methods for wait purpose
	public void waitForElementInvisibility(By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}
	
	public void waitForElementVisibility(By element) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}

}
