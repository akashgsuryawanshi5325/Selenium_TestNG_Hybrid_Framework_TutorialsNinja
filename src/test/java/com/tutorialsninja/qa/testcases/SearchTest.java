package com.tutorialsninja.qa.testcases;


import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.SearchPage;

//Updated comment --> Added more details

public class SearchTest extends Base {
	
	public WebDriver driver;
	HomePage homePage;
	SearchPage searchPage;
	
	public SearchTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		homePage = new HomePage(driver);
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	
	@Test(priority=1)
	public void verifySearchWithValidProduct()
	{
		homePage.enterProductName(dataProp.getProperty("validProduct"));
		searchPage = homePage.clickOnSearchButton();
		
		Assert.assertTrue(searchPage.displayStatusOfValidHPProduct(), "Valid product HP is not displayed in search result");
	}
	
	@Test(priority=2)
	public void verifySearchWithInvalidProduct()
	{
		homePage.enterProductName(dataProp.getProperty("invalidProduct"));
		searchPage = homePage.clickOnSearchButton();
		
		String actMessage = searchPage.retriveNoProductMessageText();
		Assert.assertEquals(actMessage, "abcd" ,"No Product messagenot displayed");
		//dataProp.getProperty("noProductText")
	}
	
	@Test(priority=3, dependsOnMethods= {"verifySearchWithInvalidProduct","verifySearchWithValidProduct"})
	public void verifySearchWithoutAnyProduct()
	{
		searchPage = homePage.clickOnSearchButton();
		
		String actMessage = searchPage.retriveNoProductMessageText();
		Assert.assertEquals(actMessage, dataProp.getProperty("noProductText"), "No Product messagenot displayed");
	}
}
