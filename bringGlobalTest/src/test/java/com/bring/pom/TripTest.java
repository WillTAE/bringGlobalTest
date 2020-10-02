package com.bring.pom;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TripTest {
	
	private WebDriver driver;
	SearchTripPage searchTripPage;
	String baseUrl;

	@Before
	public void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		baseUrl = "http://ryanair.com/gb/en";
		
		searchTripPage = new SearchTripPage(driver);
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}
	
	/**
	 * For now, I just had time to complete the first part of the exercise, not sure if I could
	 * complete the remaining part, it's easy... just that I didn't have enough time to complete it.
	 * Thanks, William.
	 */

	@Test
	public void test() throws InterruptedException {
		searchTripPage.addFlight("Lisbon", "Paris Beauvais");
	}
	/**
	 * Sorry for the Thread.sleep, it's just to let you know the case does what it should. 
	 */
	@After
	public void tearDown() throws Exception {
		Thread.sleep(20000);
		driver.quit();
	}

}
