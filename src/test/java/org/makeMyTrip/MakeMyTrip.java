package org.makeMyTrip;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.google.common.base.Ticker;

public class MakeMyTrip {

	public static WebDriver driver;

	public static void main(String[] args) throws InterruptedException, AWTException, IOException {

		// Browser Invocation

		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\Driver\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

		driver.get("https://www.makemytrip.com/");

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.navigate().refresh();

		driver.findElement(By.xpath("(//div[@class='landingContainer'])[1]")).click();

		driver.navigate().refresh();

		WebElement bus = driver.findElement(By.xpath("(//a[contains(@class,'hrtlCenter')])[6]"));

		bus.click();

		driver.findElement(By.xpath("//input[@id='fromCity']")).click();

		Thread.sleep(1000);

		// Select Source and Destination

		WebElement from = driver.findElement(By.xpath("//input[@placeholder='From']"));

		from.sendKeys("Hyderabad");

		Thread.sleep(1000);

		Robot rob = new Robot();

		rob.keyPress(KeyEvent.VK_DOWN);
		rob.keyRelease(KeyEvent.VK_DOWN);

		rob.keyPress(KeyEvent.VK_ENTER);
		rob.keyRelease(KeyEvent.VK_ENTER);

		WebElement to = driver.findElement(By.xpath("//input[@placeholder='To']"));

		to.sendKeys("Goa");

		Thread.sleep(1000);

		rob.keyPress(KeyEvent.VK_DOWN);
		rob.keyRelease(KeyEvent.VK_DOWN);

		rob.keyPress(KeyEvent.VK_ENTER);
		rob.keyRelease(KeyEvent.VK_ENTER);

		// Select Travel Date

		String flag = "False";

		while (flag == "False") {

			if (driver.findElements(By.xpath("//div[@class='DayPicker-Day'][contains(@aria-label,'May 14 2021')]"))
					.size() > 0) {

				driver.findElement(By.xpath("//div[@class='DayPicker-Day'][contains(@aria-label,'May 14 2021')]"))
						.click();
				flag = "True";
				Thread.sleep(500);
			}

			else {
				Thread.sleep(500);
				driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
			}

			WebElement search = driver.findElement(By.xpath("(//button[@type='button'])[1]"));

			search.click();

			// Printing Lowest Ticket Prices with the travels name

			List<WebElement> buses = driver.findElements(By.xpath("//span[contains(@class,'appendRight15')]"));

			List<WebElement> allPrice = driver.findElements(By.xpath("//span[@id='price']"));

			Map<String, String> map = new TreeMap<>();

			String busName = null;
			String busFare = null;

			List<String> list = new LinkedList<>();
			for (int i = 0; i < buses.size(); i++) {

				WebElement travels = buses.get(i);

				WebElement amount = allPrice.get(i);

				busName = travels.getText();

				busFare = amount.getText();

				map.put(busFare, busName);
				list.add(busFare);
			}

			System.out.println("Price List Sorted: " + map);

			Collections.sort(list);

			System.out.println(list);

			// Selecting seats from the travels which has lowest price

			for (int i = 0; i < list.size(); i++) {

				if (allPrice.get(i).getText().equals(list.get(0))) {

					i++;

					WebDriverWait wait = new WebDriverWait(driver, 20);

					wait.until(ExpectedConditions
							.elementToBeClickable(By.xpath("(//a[@data-test-id='select-seats'])[" + i + "]"))).click();

					break;
				}
			}

			Thread.sleep(2000);

			// Screenshot of Lowest Price ticket Booking

			TakesScreenshot tk = (TakesScreenshot) driver;

			File temp = tk.getScreenshotAs(OutputType.FILE);

			File permanent = new File("C:\\Users\\hp\\DCKAP_workspace\\DCKAP\\screenshots\\LowestTravelPrice.jpeg");

			FileUtils.copyFile(temp, permanent);

			WebElement upperBerth = driver.findElement(By.xpath("(//div[contains(@class,'kpbvII')])[1]"));

			List<WebElement> seats = upperBerth.findElements(By.tagName("img"));

			for (int i = 0; i < seats.size(); i++) {

				if (!(seats.get(i).isSelected())) {

					seats.get(i).click();

					break;
				}

			}

			WebElement bookSeat = driver.findElement(By.xpath("//span[contains(text(),'BOOK SEATS')]"));

			if (bookSeat.isEnabled()) {

				bookSeat.click();
			}

			WebElement fromLocation = driver.findElement(By.xpath("(//span[contains(text(),'Hyderabad')])[1]"));

			WebElement toLocation = driver.findElement(By.xpath("(//span[contains(text(),'Goa')])[1]"));

			String source = fromLocation.getText();

			String destination = toLocation.getText();

			// Asserting Source and Destination Name

			Assert.assertTrue(source.equals("Hyderabad"));

			Assert.assertTrue(destination.contains("Goa"));

			// Browser Close

			driver.close();

		}

	}
}
