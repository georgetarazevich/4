package com.testingTutBy.AppModel;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutByMainPage {
	protected Logger logger = LoggerFactory.getLogger(TutByMainPage.class);

	private WebDriver driver;
	private static final By LINK_RABOTA_LOCATOR = By
			.xpath("//div[@id=\"mainmenu\"]//ul[@class=\"b-topbar-i\"]//a[contains(text(),'Работа')]");
	private static final String BASE_URL = "https://www.tut.by/";

	public TutByMainPage(WebDriver driver) {
		this.driver = driver;
	}

	public TutByMainPage openMainPage() {
		driver.get(BASE_URL);
		logger.info("Page title is: " + driver.getTitle());
		return new TutByMainPage(driver);
	}

	public TutByMainPage clickRabotaLink() {
		driver.findElement(LINK_RABOTA_LOCATOR).click();
		return this;
	}

	public TutByMainPage waitForLoad(By by) {

		(new WebDriverWait(driver, 30)).until(ExpectedConditions
				.elementToBeClickable(by));
		logger.info("Page title is: " + driver.getTitle());

		return this;
	}

}
