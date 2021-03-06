package osbot.account.webdriver;

import java.io.IOException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebdriverFunctions {

	public static void killAll() {
		try {
			Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM phantomjs.exe");

			System.out.println("Succesfully killed old firefox, phantomjs & geckodrivers");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			System.out.println("Couldn't kill tasks Firefox");
		}
	}

	public static boolean hasQuit(WebDriver driver) {
		return ((RemoteWebDriver) driver).getSessionId() == null;
	}

	/**
	 * Checks if the current element on the page is visible for the next 15 seconds
	 * 
	 * @param driver
	 * @param element
	 */
	public static void waitForElementToBeVisible(WebDriver driver, WebElement element) {
		if (WebdriverFunctions.hasQuit(driver)) {
			System.out.println("Breaking out of loop");
			return;
		}

		try {
			Thread.sleep(1000);
			System.out.println("Waiting for element visibility");
			WebDriverWait wait = new WebDriverWait(driver, 45);
			wait.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Checks if the current element on the page is visible for the next 15 seconds
	 * 
	 * @param driver
	 * @param element
	 */
	public static void waitForUrl(WebDriver driver, String url) {
		if (WebdriverFunctions.hasQuit(driver)) {
			System.out.println("Breaking out of loop");
			return;
		}

		try {
			Thread.sleep(1000);
			System.out.println("Waiting for element visibility");
			WebDriverWait wait = new WebDriverWait(driver, 40);
			wait.until(ExpectedConditions.urlToBe(url));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Checks if the current element on the page is visible for the next 15 seconds
	 * 
	 * @param driver
	 * @param element
	 */
	public static void waitForUrlContains(WebDriver driver, String url) {
		if (WebdriverFunctions.hasQuit(driver)) {
			System.out.println("Breaking out of loop");
			return;
		}

		try {
			Thread.sleep(1000);
			System.out.println("Waiting for element visibility");
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.urlContains(url));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * Waiting for the full page to load
	 * 
	 * @param driver
	 */
	public static void waitForLoad(WebDriver driver) {
		if (WebdriverFunctions.hasQuit(driver)) {
			System.out.println("Breaking out of loop");
			return;
		}
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				if (WebdriverFunctions.hasQuit(driver)) {
					System.out.println("Breaking out of loop");
					return true;
				}
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		WebDriverWait wait = new WebDriverWait(driver, 180);
		wait.until(pageLoadCondition);
	}
}
