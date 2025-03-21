package com.tutorialsninja.qa.testcases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pageobjects.AccountPage;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.LoginPage;
import com.tutorialsninja.qa.utils.Utilities;

public class LoginTest extends Base{
	
	public WebDriver driver;
	LoginPage loginPage;

	public LoginTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);	
		loginPage = homePage.navigateToLoginPage();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority = 1, dataProvider="validCredentialsSipplier")
	public void verifyLoginWithValidCredentials(String email, String password)
	{

		loginPage.enterEmailAdress(email);
		loginPage.enterPassword(password);
		AccountPage accountPage = loginPage.clcikOnLoginButton();

		Assert.assertTrue(accountPage.getDisplayStatusOfEditYourAccountInformationOption(), "Try with another credentials");
	}
	
	@DataProvider(name="validCredentialsSipplier")
	public Object[][] supplyTestData()
	{
		Object[][] data = Utilities.getTestDataFromExcel("Login");
		return data;
	}
	
	@Test(priority=2)
	public void verifyLoginWithInvalidCredentials()
	{
		loginPage.enterEmailAdress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clcikOnLoginButton();
		
		String actWarningMessage = loginPage.retriveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertTrue(actWarningMessage.contains(expectedWarningMessage),"Expected Warning Message not displayed");
	}
	
	@Test(priority=3)
	public void verifyLoginWithInvalidEmailAndValidPassword() throws InterruptedException
	{
		loginPage.enterEmailAdress(Utilities.generateEmailWithTimeStamp());
		loginPage.enterPassword(prop.getProperty("validPassword"));
		loginPage.clcikOnLoginButton();
		
		String actWarningMessage = loginPage.retriveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertTrue(actWarningMessage.contains(expectedWarningMessage),"Expected Warning Message not displayed");
	}
	
	@Test(priority=4)
	public void verifyLoginWithValidEmailAndInvalidPassword()
	{
		loginPage.enterEmailAdress(prop.getProperty("validEmail"));
		loginPage.enterPassword(dataProp.getProperty("invalidPassword"));
		loginPage.clcikOnLoginButton();
		
		String actWarningMessage = loginPage.retriveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertTrue(actWarningMessage.contains(expectedWarningMessage),"Expected Warning Message not displayed");
	}
	
	@Test(priority=5)
	public void verifyLoginWithoutProvidingCredentials()
	{		
		loginPage.clcikOnLoginButton();
		
		String actWarningMessage = loginPage.retriveEmailPasswordNotMatchingWarningMessageText();
		String expectedWarningMessage = dataProp.getProperty("emailPasswordNoMatchWarning");
		
		Assert.assertTrue(actWarningMessage.contains(expectedWarningMessage),"Expected Warning Message not displayed");
	}
}
