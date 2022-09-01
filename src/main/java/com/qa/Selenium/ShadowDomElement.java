package com.qa.Selenium;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ShadowDomElement {

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://selectorshub.com/xpath-practice-page/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		WebElement ScrollingTo = driver.findElement(By.xpath("//button[text()='Checkout here']"));
		jse.executeScript("arguments[0].scrollIntoView();", ScrollingTo);
		// Switch to frame with frame id 
		driver.switchTo().frame("pact");
		// Loving tea (inside open root shadow Dom )
		WebElement elementTea = (WebElement) jse
				.executeScript("return document.querySelector(\"#snacktime\").shadowRoot.querySelector(\"#tea\")");
		String elementTeaValue = "arguments[0].setAttribute('value','Love Green Tea')";
		jse.executeScript(elementTeaValue, elementTea);
		// Loving Lunch
		WebElement elementLunch = (WebElement) jse
				.executeScript("return document.querySelector(\"#snacktime\").shadowRoot.querySelector(\"#app2\")"
						+ ".shadowRoot.querySelector(\"#pizza\")");
		String lunchItemValue = "arguments[0].setAttribute('value','Chicken Tikka Masala Please')";
		jse.executeScript(lunchItemValue, elementLunch);
		//closed shadow dom which is inside open shadow Dom
		
        //inside closed shadow Dom
		driver.switchTo().defaultContent();
		driver.findElement(By.xpath("//div[@id='userPass']")).click();
		Actions ac = new Actions(driver);
		ac.sendKeys(Keys.TAB).perform();
		ac.sendKeys("sourov").perform();
		
		Thread.sleep(3000);
		driver.quit();
	}

}
