package com.bring.test.bringGlobalTest;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class RyanairTests {
	private WebDriver driver;
	String baseUrl = "http://ryanair.com/gb/en";
	@Before
	public void setUp(){
		
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(baseUrl);
	}
	
	@Test
	public void testRyanair(){
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		assertEquals("Official Ryanair website | Cheap flights from Ireland | Ryanair", driver.getTitle());
	}
	
	@After
	public void tearDown(){
		driver.quit();
	}
}
