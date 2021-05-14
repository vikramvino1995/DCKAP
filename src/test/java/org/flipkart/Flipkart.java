package org.flipkart;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

//This is Updated............................................................................................................................................

public class Flipkart {

	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, AWTException {

		// Open Browser

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Driver\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().deleteAllCookies();

		driver.get("https://www.flipkart.com/");

		driver.manage().window().maximize();

		driver.findElement(By.xpath("(//button[contains(@class,'_2doB4z')])[1]")).click();

		// Searching product

		WebElement searchBar = driver.findElement(By.xpath("//input[@class='_3704LK']"));

		searchBar.sendKeys("iphone 11");

		Thread.sleep(2000);

		Robot rob = new Robot();

		rob.keyPress(KeyEvent.VK_DOWN);
		rob.keyRelease(KeyEvent.VK_DOWN);

		rob.keyPress(KeyEvent.VK_ENTER);
		rob.keyRelease(KeyEvent.VK_ENTER);

		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);

		// Selecting Product

		List<WebElement> allPhones = driver.findElements(By.className("_4rR01T"));

		List<WebElement> allPrices = driver.findElements(By.xpath("//div[contains(@class,'_1_WHN1')]"));

		String prodName = allPhones.get(0).getText();

		String prodPrice = allPrices.get(0).getText();

		allPhones.get(0).click();

		// switching Window

		String parentId = driver.getWindowHandle();

		Set<String> parentChildId = driver.getWindowHandles();

		List<String> list = new LinkedList<String>();

		list.addAll(parentChildId);

		String requiredWindow = list.get(1);

		driver.switchTo().window(requiredWindow);

		WebElement addToCart = driver.findElement(By.xpath("(//button[contains(@class,'_2U9uOA ')])[1]"));

		addToCart.click();

		WebElement prod1 = driver.findElement(By.className("_2-uG6-"));

		WebElement price1 = driver.findElement(By.xpath("(//span[contains(@class,'_2-ut7f ')])[1]"));

		String selectedProd = prod1.getText();

		String selectedPrice = price1.getText();

		// Assertion for Product Name

		System.out.println("Actual: " + selectedProd);

		System.out.println("Expected: " + prodName);

		System.out.println("Actual: " + selectedPrice);

		System.out.println("Expected: " + prodPrice);

		Assert.assertTrue(selectedProd.equals(prodName));

		Assert.assertTrue(selectedPrice.equals(prodPrice));

		// close Browser

		driver.quit();
	}
}
