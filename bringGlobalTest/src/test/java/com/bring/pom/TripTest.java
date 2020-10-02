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

	@Test
	public void test() throws InterruptedException {
		searchTripPage.addFlight("Lisbon", "Paris Beauvais");
	}
	
	@After
	public void tearDown() throws Exception {
		Thread.sleep(10000);
		driver.quit();
	}

}
