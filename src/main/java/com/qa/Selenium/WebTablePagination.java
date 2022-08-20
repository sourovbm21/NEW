package com.qa.Selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebTablePagination {


	static WebDriver driver;

	public static void main(String[] args) throws InterruptedException {

		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.get("https://selectorshub.com/xpath-practice-page/");

		Thread.sleep(4000);
		
		//multiple selection:
		while(true) {
			
			if(driver.findElements(By.xpath("//td[text()='India']")).size()>0) {
				selectMultipleCountry("India");
			}
			
			// pagination logic:
			WebElement next = driver.findElement(By.linkText("Next"));

			if (next.getAttribute("class").contains("disabled")) {
				System.out.println("pagination is over...country is not found....");
				break;
			}
			next.click();
			Thread.sleep(1000);
			
			
		}

		
		//single selection:
//		while (true) {
//			if (driver.findElements(By.xpath("//td[text()='Hong Kong']")).size() > 0) {
//				selectCity("Hong Kong");
//				break;
//			} else {
//				// pagination logic:
//				WebElement next = driver.findElement(By.linkText("Next"));
//
//				if (next.getAttribute("class").contains("disabled")) {
//					System.out.println("pagination is over...country is not found....");
//					break;
//				}
//				next.click();
//				Thread.sleep(1000);
//			}
//
//		}

	}
	
	public static void selectMultipleCountry(String country) {
		List<WebElement> eleList = 
				driver.findElements(By.xpath("(//td[text()='"+country+"'])/preceding-sibling::td/input[@type='checkbox']"));
		for(WebElement e : eleList) {
			e.click();
		}
	}

	public static void selectCity(String country) {
		driver.findElement(By.xpath("//td[text()='" + country + "']/preceding-sibling::td/input[@type='checkbox']"))
				.click();
	}

}