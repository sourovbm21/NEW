package com.qa.Selenium;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
public class DropDownBootAuto {
	
	
	
	@Test 
	public void auto_Suggestive_DropDown() throws InterruptedException {	
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().deleteAllCookies();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//div[@class='_2QfC02']/button")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//input[@class='_3704LK']")).sendKeys("iphone 12");
		Thread.sleep(5000);
	List<WebElement> totalList = driver.findElements(By.xpath("//div[contains(@class,'lrtEPN')]"));
	for (WebElement singleItem : totalList) {
		singleItem.getText();
		System.out.println("single list : " +singleItem.getText());
		if (singleItem.getText().equalsIgnoreCase("iphone 12 mini")) {
			singleItem.click();
		}	
	}
		Thread.sleep(2000);
		driver.quit();
	}
	
	
	
//	
//	@Test 
//	public void boot_Streap_DropDown() throws InterruptedException {
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get("https://www.jquery-az.com/boots/demo.php?ex=63.0_2");
//		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(5));
//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
//		driver.manage().deleteAllCookies();
//		driver.findElement(By.xpath("//button[contains(@class,'multiselect')]")).click();
//	List<WebElement>allItems = driver.findElements(By.xpath("//ul[contains(@class,'multiselect')]//li//a//label"));
//		System.out.println("all list item : " + allItems.size());
//		for (int i=0;i<allItems.size();i++) {
//			System.out.println(  allItems.get(i).getText());
//			//clicking all items
//			//allItems.get(i).click();			
//			//clicking specific items.
////			if (allItems.get(i).getText().contains("Java")){
////				allItems.get(i).click();
////				break;
////			}			
//		if (	allItems.get(i).getAttribute("class").equalsIgnoreCase("active")) {
//			System.out.println(  allItems.get(i).getText());
//		}else {
//			allItems.get(i).click();
//		}
//	}	
//		Thread.sleep(2000);
//		driver.quit();		
//	}

}
