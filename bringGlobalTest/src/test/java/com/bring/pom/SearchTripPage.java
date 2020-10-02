package com.bring.pom;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchTripPage {
	WebDriver driver; 	
	
	public SearchTripPage(WebDriver driver){
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(how=How.ID, using="input-button__departure")
    private WebElement departureAirport;
	
	@FindBy(how=How.ID, using="input-button__destination")
    private WebElement destinationAirport;
	
	@FindBy(how=How.XPATH, using="//calendar[contains(@class,'left')]/div")
    private WebElement leftMonthOnCalendar;
	
	@FindBy(how=How.XPATH, using="//calendar[@class='datepicker__calendar']/div")
    private WebElement rightMonthOnCalendar;
	
	@FindBy(how=How.XPATH, using="//calendar[@class='datepicker__calendar']/following-sibling::div")
    private WebElement rightArrowCalendar;
	
	@FindBy(how=How.XPATH, using="//fsw-passengers-container[@class='ng-star-inserted']/fsw-passengers")
    private WebElement passangersContainer;
	
		
	@FindBy(how=How.XPATH, using="(//div[contains(@class,'input-button__input ng-star-inserted')])[1]")
	WebElement departureDate;
	@FindBy(how=How.XPATH, using="(//div[contains(@class,'input-button__input ng-star-inserted')])[2]")
	WebElement destinationDate;
	@FindBy(how=How.XPATH, using="//button[contains(@class,'flight-search-widget__start-search ry-button--gradient-yellow')]")
	WebElement searchButton;
	
	private WebElement btn_select_origin_destiny(String city){
		return driver.findElement(By.xpath("//span[contains(text(),'"+city+"')]/ancestor::fsw-airport-item"));
	}
	
	private WebElement btn_select_depart_day(int day){
		return driver.findElement(By.xpath("(//calendar-body)[1]//div[@data-value='"+day+"']"));
	}
	
	private WebElement btn_select_return_day(int day){
		return driver.findElement(By.xpath("(//calendar-body)[2]//div[@data-value='"+day+"']"));
	}
	
	private WebElement get_number_of_passangers(String passangerType){
		return driver.findElement(By.xpath("//div[contains(text(),'"+passangerType+"')]/parent::div/following-sibling::div//div[@class='counter__value']"));
	}
	
	private WebElement btn_increase_passangers(String passangerType){
		return driver.findElement(By.xpath("//div[contains(text(),'"+passangerType+"')]/parent::div/following-sibling::div//div[@class='counter__button-wrapper--enabled']"));
	}
	
	public void addFlight(String origen, String destiny) throws InterruptedException{
		try {
			WebDriverWait ewait = new WebDriverWait(driver, 15);
			ewait.until(ExpectedConditions.elementToBeClickable(departureAirport));
			departureAirport.clear();
			departureAirport.clear();
			ewait.until(ExpectedConditions.elementToBeClickable(departureAirport));
			departureAirport.sendKeys(origen);
			btn_select_origin_destiny(origen).click();
			ewait.until(ExpectedConditions.elementToBeClickable(destinationAirport));
			destinationAirport.clear();
			ewait.until(ExpectedConditions.elementToBeClickable(destinationAirport));
			destinationAirport.sendKeys(destiny);
			btn_select_origin_destiny(destiny).click();
			ewait.until(ExpectedConditions.elementToBeClickable(departureDate));
			selectDepartDate("December 2020");
			ewait.until(ExpectedConditions.elementToBeClickable(destinationDate));
			selectReturnDate("January 2021");
			selectAdults(2);
			selectChildren(1);
			searchButton.click();
		} catch (Exception e) {
			System.out.println("Error Message: " +e.getMessage());
		}
		
	}
	
	public void selectDepartDate(String dateToSelect){
		try {
			while(!dateToSelect.equals(leftMonthOnCalendar.getText())){
				rightArrowCalendar.click();
				if(leftMonthOnCalendar.getText().contains(dateToSelect)){
					break;
				}
			}
			btn_select_depart_day(6).click();
		} catch (Exception e) {
			System.out.println("There was an error selecting Trip dates "+e.getMessage());
		}
	}
	
	public void selectReturnDate(String dateToSelect){
		try {
			while(!dateToSelect.equals(rightMonthOnCalendar.getText())){
				rightArrowCalendar.click();
				if(leftMonthOnCalendar.getText().contains(dateToSelect)){
					break;
				}
			}
			btn_select_return_day(2).click();
		} catch (Exception e) {
			System.out.println("There was an error selecting Trip dates "+e.getMessage());
		}
	}
	
	public void selectAdults(int adults){
		try {
			WebDriverWait ewait = new WebDriverWait(driver, 10);
			ewait.until(ExpectedConditions.visibilityOf(passangersContainer));
			String adultsByDefault = get_number_of_passangers("Adults").getText();
			int adultsValue = Integer.parseInt(adultsByDefault);
			while(adultsValue < adults){
				btn_increase_passangers("Adults").click();
				adultsByDefault = get_number_of_passangers("Adults").getText();
				adultsValue = Integer.parseInt(adultsByDefault);
				if(adultsValue == adults){
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("There was an error selecting adults");
		}
	}
	
	public void selectChildren(int children){
		try {
			String childrenByDefault = get_number_of_passangers("Children").getText();
			int childrenValue = Integer.parseInt(childrenByDefault);
			while(childrenValue < children){
				btn_increase_passangers("Children").click();
				childrenByDefault = get_number_of_passangers("Children").getText();
				childrenValue = Integer.parseInt(childrenByDefault);
				if(childrenValue == children){
					break;
				}
			}
		} catch (Exception e) {
			System.out.println("There was an error selecting children");
		}
	}

}
