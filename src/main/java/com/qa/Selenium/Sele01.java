package com.qa.Selenium;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Sele01 {

	
		
		private WebDriver driver;
		private Actions act;

		public Sele01(WebDriver driver) {
			this.driver = driver;
			act = new Actions(driver);
		}

		public By getBy(String locatorType, String selector) {

			By locator = null;

			switch (locatorType.toLowerCase()) {
			case "id":
				locator = By.id(selector);
				break;
			case "name":
				locator = By.name(selector);
				break;
			case "class":
				locator = By.className(selector);
				break;
			case "xpath":
				locator = By.xpath(selector);
				break;
			case "cssselector":
				locator = By.cssSelector(selector);
				break;
			case "linktext":
				locator = By.linkText(selector);
				break;
			case "partiallinktext":
				locator = By.partialLinkText(selector);
				break;
			case "tagname":
				locator = By.tagName(selector);
				break;

			default:
				break;
			}

			return locator;

		}

		public void doSendKeys(String locatorType, String selector, String value) {
			By locator = getBy(locatorType, selector);
			getElement(locator).sendKeys(value);
		}

		public void doSendKeys(By locator, String value) {
			getElement(locator).sendKeys(value);
		}

		public void doActionsSendKeys(By locator, String value) {
			act.sendKeys(getElement(locator), value).perform();
		}

		public void doActionsClick(By locator) {
			act.click(getElement(locator)).perform();
		}

		public void doClick(String locatorType, String selector) {
			By locator = getBy(locatorType, selector);
			getElement(locator).click();
		}

		public void doClick(By locator) {
			getElement(locator).click();
		}

		public WebElement getElement(By locator) {
			return driver.findElement(locator);
		}

		public String doGetAttributeValue(By locator, String attrName) {
			return getElement(locator).getAttribute(attrName);
		}

		public String doElementGetText(By locator) {
			String eleText = getElement(locator).getText();
			return eleText;
		}

		// non blank list count:
		public int getElementsTextCount(By locator) {
			return getElementsTextList(locator).size();
		}

		// non blank text list:
		public List<String> getElementsTextList(By locator) {
			List<WebElement> eleList = getElements(locator);
			List<String> eleTextList = new ArrayList<String>();
			for (WebElement e : eleList) {
				String text = e.getText();
				if (!text.isEmpty()) {
					eleTextList.add(text);
				}
			}
			return eleTextList;
		}

		public int getEmptyEleTextList(By locator) {
			int totalLinks = getPageElementsCount(locator);
			int totalNonEmptyLinks = getElementsTextCount(locator);
			return totalLinks - totalNonEmptyLinks;
		}

		// 1. total number of links on the page
		public int getPageElementsCount(By locator) {
			return getElements(locator).size();
		}

		public List<WebElement> getElements(By locator) {
			return driver.findElements(locator);
		}

		public List<String> getEleAttributeList(By locator, String attrName) {
			List<WebElement> eleList = getElements(locator);
			List<String> eleAttrValList = new ArrayList<String>();
			for (WebElement e : eleList) {
				String attrValue = e.getAttribute(attrName);
				eleAttrValList.add(attrValue);
			}
			return eleAttrValList;
		}

		public void performSearch(By search, String searchKey, By suggListLocator, String suggName)
				throws InterruptedException {
			doSendKeys(search, searchKey);
			Thread.sleep(2000);
			List<WebElement> suggList = getElements(suggListLocator);
			System.out.println("total suggs: " + suggList.size());
			for (WebElement e : suggList) {
				String text = e.getText();
				System.out.println(text);
				if (text.contains(suggName)) {
					e.click();
					break;
				}
			}

		}

		public void selectItem(String productName) {
			By locator = By.xpath("//li[text()='" + productName + "']");
			doClick(locator);
		}

		// **************************element displayed utils*****************//
		public boolean doIsDisplayed(By locator) {
			return getElement(locator).isDisplayed();
		}

		public int getElementCount(By locator) {
			int eleCount = getElements(locator).size();
			return eleCount;
		}

		public boolean checkSingleElementExist(By locator) {
			if (getElementCount(locator) == 1) {
				return true;
			}
			return false;
		}

		public boolean checkElementMultipleExist(By locator) {
			if (getElementCount(locator) > 1) {
				return true;
			}
			return false;
		}

		// ********************select tag based drop down utils**********************/
		public void doSelectByIndex(By locator, int index) {
			Select select = new Select(getElement(locator));
			select.selectByIndex(index);
		}

		public void doSelectByVisibleText(By locator, String visibleText) {
			Select select = new Select(getElement(locator));
			select.selectByVisibleText(visibleText);
		}

		public void doSelectByValue(By locator, String value) {
			Select select = new Select(getElement(locator));
			select.selectByValue(value);
		}

		public int getDropDownCount(By locator) {
			Select select = new Select(getElement(locator));
			return select.getOptions().size();
		}

		public List<String> getDropDownValuesList(By locator) {
			Select select = new Select(getElement(locator));
			List<String> optionsValList = new ArrayList<String>();
			List<WebElement> optionsEleList = select.getOptions();
			for (WebElement e : optionsEleList) {
				String text = e.getText();
				optionsValList.add(text);
			}
			return optionsValList;
		}

		public void doSelectValueUsingOptions(By locator, String value) {
			Select select = new Select(getElement(locator));
			List<WebElement> optionsList = select.getOptions();

			for (WebElement e : optionsList) {
				String text = e.getText();
				System.out.println(text);
				if (text.equals(value)) {
					e.click();
					break;
				}
			}
		}

		public void doSelectValueFromDropDown(By locator, String value) {
			List<WebElement> optionsList = getElements(locator);
			for (WebElement e : optionsList) {
				String text = e.getText();
				System.out.println(text);
				if (text.equals(value)) {
					e.click();
					break;
				}
			}
		}

		// **********************Wait Utils***********************//
		public Alert waitForAlert(int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.alertIsPresent());
		}

		public String getAlertText(int timeOut) {
			return waitForAlert(timeOut).getText();
		}

		public void aceptAlert(int timeOut) {
			waitForAlert(timeOut).accept();
		}

		public void dismissAlert(int timeOut) {
			waitForAlert(timeOut).dismiss();
		}

		public void alertSendKeys(String value, int timeOut) {
			waitForAlert(timeOut).sendKeys(value);
		}

		public String waitForTitleContains(String titleFractionValue, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			try {
				if (wait.until(ExpectedConditions.titleContains(titleFractionValue))) {
					return driver.getTitle();
				}
			} catch (Exception e) {
				System.out.println("title is not found.....");

			}
			return null;
		}

		public String waitForTitleToBe(String titleValue, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			try {
				if (wait.until(ExpectedConditions.titleIs(titleValue))) {
					return driver.getTitle();
				}
			} catch (Exception e) {
				System.out.println("title is not found.....");

			}
			return null;
		}

		public String waitForUrl(int timeOut, String urlFractionValue) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			try {
				if (wait.until(ExpectedConditions.urlContains(urlFractionValue))) {
					return driver.getCurrentUrl();
				}
			} catch (Exception e) {
				System.out.println("url is not found....");
			}
			return null;
		}

		public String waitForUrlToBe(int timeOut, String urlValue) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			try {
				if (wait.until(ExpectedConditions.urlToBe(urlValue))) {
					return driver.getCurrentUrl();
				}
			} catch (Exception e) {
				System.out.println("url is not found....");
			}
			return null;

		}

		public void waitForFrameUsingLocatorAndSwitchToIt(int timeOut, By frameLocator) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
		}

		public void waitForFrameUsingIndexAndSwitchToIt(int timeOut, int frameIndex) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameIndex));
		}

		public void waitForFrameUsingNameOrIdAndSwitchToIt(int timeOut, String frameNameOrId) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameNameOrId));
		}

		public void waitForFrameUsingElementOrIdAndSwitchToIt(int timeOut, By frameLocator) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(getElement(frameLocator)));
		}

		public void doSendKeysWithWait(By locator, int timeOut, String value) {
			waitForElementVisible(locator, timeOut).sendKeys(value);
		}

		public void doClickWithWait(By locator, int timeOut) {
			waitForElementVisible(locator, timeOut).click();
		}

		/**
		 * An expectation for checking that an element is present on the DOM of a page.
		 * This does not necessarily mean that the element is visible.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementPresence(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		}

		/**
		 * An expectation for checking that an element is present on the DOM of a page
		 * and visible. Visibility means that the element is not only displayed but also
		 * has a height and width that is greater than 0. Default polling Time = 500 ms
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public WebElement waitForElementVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}

		/**
		 * An expectation for checking that an element is present on the DOM of a page
		 * and visible. Visibility means that the element is not only displayed but also
		 * has a height and width that is greater than 0. Default polling Time =
		 * customized
		 * 
		 * @param locator
		 * @param timeOut
		 * @param pollingTime
		 * @return
		 */
		public WebElement waitForElementVisible(By locator, int timeOut, int pollingTime) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut), Duration.ofSeconds(pollingTime));
			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}

		/**
		 * An expectation for checking an element is visible and enabled such that you
		 * can click it.
		 * 
		 * @param locator
		 * @param timeOut
		 */
		public void clickWhenReady(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
		}

		/**
		 * An expectation for checking an element is visible and enabled such that you
		 * can click it.
		 * 
		 * @param locator
		 * @param timeOut
		 */
		public void clickElementWhenReady(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			wait.until(ExpectedConditions.elementToBeClickable(getElement(locator))).click();
		}

		/**
		 * An expectation for checking that all elements present on the web page that
		 * match the locator are visible. Visibility means that the elements are not
		 * only displayed but also have a height and width that is greater than 0.
		 * 
		 * @param locator
		 * @param timeOut
		 * @return
		 */
		public List<WebElement> waitForElementsToBeVisible(By locator, int timeOut) {
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeOut));
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		}

		public void printAllElementsText(By locator, int timeOut) {
			List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut);
			for (WebElement e : eleList) {
				String text = e.getText();
				System.out.println(text);
			}
		}

		public List<String> getAllElementsTextList(By locator, int timeOut) {
			List<WebElement> eleList = waitForElementsToBeVisible(locator, timeOut);
			List<String> eleTextList = new ArrayList<String>();
			for (WebElement e : eleList) {
				String text = e.getText();
				eleTextList.add(text);
			}
			return eleTextList;
		}

		public WebElement waitForElementVisibleWithFluentWait(By locator, int timeOut, int pollingTime) {

			Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOut))
					.pollingEvery(Duration.ofSeconds(pollingTime))
					.ignoring(NoSuchElementException.class, StaleElementReferenceException.class)
					.withMessage("element is not present on the page.....Sorry.....");

			return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		}

		// **************************Custom Wait********************************//
		public static void shortWait() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public static void mediumWait() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public static void longWait() {
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public static void waitFor(int timeOut) {
			try {
				Thread.sleep(timeOut * 1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		public void waitForPageLoad(int timeOut) {
			long endTime = System.currentTimeMillis() + timeOut;
			while (System.currentTimeMillis() < endTime) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				String pageState = js.executeScript("return document.readyState;").toString();
				System.out.println("page loading state: " + pageState);
				if (pageState.equals("complete")) {
					System.out.println("page is fully loaded with all scripts, images, css...");
					break;
				}
			}

		}

		public WebElement retryingElement(By locator, int timeOut) {
			WebElement element = null;
			int attempts = 0;
			while (attempts < timeOut) {
				try {
					element = getElement(locator);
					System.out.println("element is found....in attempt: " + (attempts + 1));
					break;
				} catch (NoSuchElementException e) {
					System.out.println("element is not found in attempt: " + attempts + " for locator: " + locator);
					try {
						Thread.sleep(500);// default interval time
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}
				attempts++;
			}

			if (element == null) {
				try {
					throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
				} catch (Exception e) {
					System.out.println("element is not found....tried for :" + timeOut + " secs "
							+ "with the interval of : " + 500 + " ms");
				}
			}

			return element;

		}

		public WebElement retryingElement(By locator, int timeOut, int intervalTime) {
			WebElement element = null;
			int attempts = 0;
			while (attempts < timeOut) {

				try {
					element = getElement(locator);
					System.out.println("element is found....in attempt: " + (attempts + 1));
					break;
				} catch (NoSuchElementException e) {
					System.out.println("element is not found in attempt: " + attempts + " for locator: " + locator);
					try {
						Thread.sleep(intervalTime);// interval time
					} catch (InterruptedException e1) {
						e1.printStackTrace();
					}
				}

				attempts++;

			}

			if (element == null) {
				try {
					throw new Exception("ELEMENTNOTFOUNDEXCEPTION");
				} catch (Exception e) {
					System.out.println("element is not found....tried for :" + timeOut + " secs "
							+ "with the interval of : " + intervalTime + " ms");
				}
			}

			return element;

		}

	}