import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DemoTest {

	WebDriver driver;

	@BeforeClass
	public void setup() {
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();
	}

	@Test
	public void google_SampleTest() {
		String expectedTitle_Firstpage = "Google", searchValue = "Jenkins";

		driver.get("https://www.google.com");

		String actualTitle_Firstpage = driver.getTitle();

		driver.manage().window().maximize();

		driver.manage().deleteAllCookies();

		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

		System.out.println(actualTitle_Firstpage);

		if (expectedTitle_Firstpage.equals(actualTitle_Firstpage)) {
			System.out.println("Passed - Expected page displayed");
		}

		else {
			System.out.println("Failed - Expected page is not displayed");

			driver.quit();
		}

		WebElement searchbox = driver.findElement(By.xpath("//input[@title = 'Search']"));

		if (searchbox.isDisplayed()) {
			System.out.println("Search Text box is present");

			searchbox.sendKeys(searchValue + Keys.ENTER);

		}

		else {

			System.out.println("Failed - earchbox is not present");

			driver.quit();

		}

		String actualTitle_Resultpage = driver.getTitle();

		if (actualTitle_Resultpage.contains(searchValue)) {
			System.out.println("Passed - Expected page displayed");
		}

		else {
			System.out.println("Failed - Expected page is not displayed");

			driver.quit();
		}
	}

	@AfterClass
	public void exit() {
		driver.quit();
	}

}




