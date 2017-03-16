package com.testingTutBy;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobTutByTests {
	Logger logger = LoggerFactory.getLogger(JobTutByTests.class);

	private WebDriver driver;
	private int listElementsSize;
	private static final String BASE_URL = "https://www.tut.by/";

	@Parameters({ "pathChromeDriver" })
	@BeforeTest
	private void setUp(String pathChromeDriver) {
		System.setProperty("webdriver.chrome.driver", pathChromeDriver);
		driver = new ChromeDriver();
		driver.get(BASE_URL);
	}

	@Parameters({ "infoToFind" })
	@Test
	public void searchForVacanciesTest_True(String infoToFind) throws Throwable {

		openJobsTutByPageAndWait();
		List<WebElement> webElements = findRequaredInfo(infoToFind);
		logger.info("Number of matches is: " + webElements.size());
		for (WebElement element : webElements) {
			logger.info(element.getText());
		}
		listElementsSize = webElements.size();
		Assert.assertTrue(listElementsSize > 0);
	}

	@AfterTest
	private void setUp() {
		driver.quit();
	}

	private void openJobsTutByPageAndWait() {
		driver.findElement(
				By.xpath("//div[@id=\"mainmenu\"]//ul[@class=\"b-topbar-i\"]//a[contains(text(),'Работа')]"))
				.click();
		(new WebDriverWait(driver, 30))
				.until(ExpectedConditions.elementToBeClickable(By
						.xpath("//select/preceding::div[@class='bloko-control-group__main']")));
		logger.info("Page title is: " + driver.getTitle());
	}

	private List<WebElement> findRequaredInfo(String infoToFind) {

		driver.findElement(
				By.xpath("//select/preceding::div[@class='bloko-control-group__main']/input"))
				.sendKeys(infoToFind + Keys.ENTER);
		return driver.findElements(By.xpath("//div/a[contains(text(),'"
				+ infoToFind + "')]"));
	}

}
