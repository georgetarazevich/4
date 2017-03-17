package com.testingTutBy.AppModel;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JobsTutByPage {
	protected Logger logger = LoggerFactory.getLogger(JobsTutByPage.class);

	private static final By SEARCH_JOBS_TEXTBOX_LOCATOR = By
			.xpath("//select/preceding::div[@class='bloko-control-group__main']/input");
	private List<WebElement> webElements;
	private WebDriver driver;

	public JobsTutByPage(WebDriver driver) {
		this.driver = driver;
	}

	public JobsTutByPage typeSearchInfoAndClick(String searchInfo) {
		return this;
	}

	public By getSearchJobsTextBoxLocator() {
		return SEARCH_JOBS_TEXTBOX_LOCATOR;
	}

	public List<WebElement> getElementsWithInfo() {
		return webElements;
	}

	public JobsTutByPage findRequaredInfo(String infoToFind) {

		driver.findElement(SEARCH_JOBS_TEXTBOX_LOCATOR).sendKeys(
				infoToFind + Keys.ENTER);
		webElements = driver.findElements(By.xpath("//div/a[contains(text(),'"
				+ infoToFind + "')]"));

		logger.info("Number of matches is: " + webElements.size());
		for (WebElement element : webElements) {
			logger.info(element.getText());
		}

		return new JobsTutByPage(driver);
	}

}
