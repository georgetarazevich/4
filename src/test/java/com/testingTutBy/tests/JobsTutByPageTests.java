package com.testingTutBy.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.testingTutBy.AppModel.JobsTutByPage;
import com.testingTutBy.AppModel.TutByMainPage;

public class JobsTutByPageTests {
	protected Logger logger = LoggerFactory.getLogger(JobsTutByPageTests.class);
	private WebDriver driver;
	private int listElementsSize;

	@Parameters({ "pathChromeDriver" })
	@BeforeTest
	private void setUp(String pathChromeDriver) {
		System.setProperty("webdriver.chrome.driver", pathChromeDriver);
		try {
			driver = new ChromeDriver();
		} catch (Exception e) {
			logger.debug("Can't create driver: " + driver.toString() + e);
		}

	}

	@Parameters({ "infoToFind" })
	@Test
	public void searchForVacanciesTest_True(String infoToFind) throws Throwable {

		TutByMainPage tutByMainPage = new TutByMainPage(driver);
		JobsTutByPage jobsTutByPage = new JobsTutByPage(driver);

		tutByMainPage.openMainPage().clickRabotaLink()
				.waitForLoad(jobsTutByPage.getSearchJobsTextBoxLocator());
		jobsTutByPage.findRequaredInfo(infoToFind);
		listElementsSize = jobsTutByPage.getElementsWithInfo().size();

		Assert.assertTrue(listElementsSize > 0);
	}

	@AfterTest
	private void tearDown() {
		driver.quit();
	}

}
