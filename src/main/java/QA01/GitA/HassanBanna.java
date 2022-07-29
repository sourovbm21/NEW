package QA01.GitA;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HassanBanna {
	
	
	
	
	@Test 
	public void loginTest () {
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("");
		String actualTitle = driver.getTitle();
		String expectedTitle ="";
		
		Assert.assertTrue(actualTitle.equals(expectedTitle),"Page title does not matched ");
		
		while (!actualTitle.equals(expectedTitle)) {
			System.out.println(" Both page title does not matched ");
		}
		System.out.println("actural Title is : " + actualTitle);
		System.out.println("actural Title is : " + expectedTitle);
	
	
		
	}
	
	
	@Test 
	public void validateForgetPasswordLinkTest() {
		//
		
		
	}

}
