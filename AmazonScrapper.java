package com.scrap;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

/**
 * @author ARUN
 *
 */
public class AmazonScrapper {

	public static void main(final String[] args) {
		final AmazonScrapper scrapObj = new AmazonScrapper();
		final WebDriver driver = scrapObj.setupFirefoxDriver();
		try {
			scrapObj.scrapAmazon(driver);
		} catch (final Exception e) {
			if(driver != null) {
				driver.close();  	//will close browser
				driver.quit();		//will remove geckodriver from System.
			}
		}
		finally {
			if(driver != null) {
				driver.close();  	//will close browser
				driver.quit();		//will remove geckodriver from System.
			}
		}
		
		System.out.println("Finished");
	}

	private WebDriver setupFirefoxDriver() {
		System.setProperty("webdriver.firefox.bin", 
				"C:\\Users\\dev\\AppData\\Local\\Mozilla Firefox\\firefox.exe");		
		System.setProperty("webdriver.gecko.driver", "C:\\workspace\\geckodriver.exe");	
		final FirefoxOptions options = new FirefoxOptions().addPreference("browser.startup.page", 1);		
		
		options.setCapability("marionette", true);
		final WebDriver driver = new FirefoxDriver(options);
		driver.manage().window().maximize();			      
		return driver;		
	}
	
	private void scrapAmazon(final WebDriver driver) throws Exception {
		driver.get("https://www.amazon.com");		
		
		try {
			Thread.sleep(3000);
		} catch (final InterruptedException e) {
			//do nothing
		}
		
		final WebElement searchElem = driver.findElement(By.id("twotabsearchtextbox"));		
		searchElem.sendKeys("iPhone 7");		
		driver.findElement(By.xpath("/html/body/div[1]/header/div/div[1]/div[3]/div/form/div[2]/div/input")).click();
		try {
			Thread.sleep(2000);
		} catch (final InterruptedException e) {
			//do nothing
		}
		
		((JavascriptExecutor)
				driver).executeScript("window.scrollBy(0,250)", "");
		try {
			Thread.sleep(2000);
		} catch (final InterruptedException e) {
			//do nothing
		}		
		driver.findElement(By.id("pagnNextString")).click();
		
	}

}
