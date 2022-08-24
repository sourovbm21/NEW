package com.qa.Selenium;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CloseDom {

	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://selectorshub.com/xpath-practice-page/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		//This Element is inside single shadow DOM.
		String cssSelectorForHost1 = "#userName";
		Thread.sleep(1000);
		SearchContext shadow = driver.findElement(By.cssSelector("#userName")).getShadowRoot();
		Thread.sleep(1000);
		shadow.findElement(By.cssSelector("#concepts"));;
	}

}
