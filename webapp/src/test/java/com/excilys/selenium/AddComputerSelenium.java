package selenium;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddComputerSelenium {
	private WebDriver driver;
	private Actions builder;
	
	
	final static Logger logger = LoggerFactory.getLogger(AddComputerSelenium.class);
	public AddComputerSelenium() {
		System.setProperty("webdriver.chrome.driver", "/opt/WebDriver/bin/chromedriver");
	}

	@Before
	public void setUp() {
		driver = new ChromeDriver();
		builder = new Actions(driver);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("http://localhost:8080/computer-database/addComputer");
	}

	@After
	public void tearDown() {
		driver.quit();
	}

	@Test
	public void addComputerTest() throws InterruptedException {
		logger.debug("test");

		WebElement txtUsername = driver.findElement(By.id("computerName"));
		Action seriesOfActions = builder.moveToElement(txtUsername).click().keyDown(txtUsername, Keys.SHIFT)
				.sendKeys(txtUsername, "test").keyUp(txtUsername, Keys.SHIFT).doubleClick(txtUsername).contextClick()
				.build();
		seriesOfActions.perform();

		for (int i = 0; i < 10; i++) {
			builder.moveToElement(driver.findElement(By.id("add"))).perform();
			builder.pause(1000).click().perform();
		}
	}
}
