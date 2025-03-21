package com.tutorialsninja.qa.testcases;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.tutorialsninja.qa.base.Base;
import com.tutorialsninja.qa.pageobjects.AccountSuccessPage;
import com.tutorialsninja.qa.pageobjects.HomePage;
import com.tutorialsninja.qa.pageobjects.RegisterPage;
import com.tutorialsninja.qa.utils.Utilities;

public class RegisterTest extends Base{
	
	public WebDriver driver;
	RegisterPage registerPage;
	AccountSuccessPage accountSuccessPage;
	
	public RegisterTest()
	{
		super();
	}
	
	@BeforeMethod
	public void setup()
	{
		driver = initializeBrowserAndOpenApplication(prop.getProperty("browser"));
		HomePage homePage = new HomePage(driver);	
		homePage.clickOnMyAccount();
		registerPage = homePage.selectRegisterOption();
	}
	
	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
	
	@Test(priority=1)
	public void verifyRegisteringAnAccountWithMandatoryFields()
	{	
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.eneterEmailAdress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		
		String acctualSuccessmsg = accountSuccessPage.retrieveaccountSuccessPageHeading();
		
		Assert.assertEquals(acctualSuccessmsg,dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Account Success Page is not displayed");	
	}
	
	@Test(priority=2)
	public void verifyReisterAccountByProvidingAllFields()
	{
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.eneterEmailAdress(Utilities.generateEmailWithTimeStamp());
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerPage.selectYesNewsLetterOption();
		registerPage.selectPrivacyPolicy();
		accountSuccessPage = registerPage.clickOnContinueButton();
		
		String acctualSuccessmsg = accountSuccessPage.retrieveaccountSuccessPageHeading();

		Assert.assertEquals(acctualSuccessmsg, dataProp.getProperty("accountSuccessfullyCreatedHeading"), "Account Success Page is not displayed");
	}
	
	@Test(priority=3)
	public void verifyRegisteringAccountWithExistingEmail()
	{	
		registerPage.enterFirstName(dataProp.getProperty("firstName"));
		registerPage.enterLastName(dataProp.getProperty("lastName"));
		registerPage.eneterEmailAdress(prop.getProperty("validEmail"));
		registerPage.enterTelephone(dataProp.getProperty("telephoneNumber"));
		registerPage.enterPassword(prop.getProperty("validPassword"));
		registerPage.enterPasswordConfirm(prop.getProperty("validPassword"));
		registerPage.selectYesNewsLetterOption();
		registerPage.selectPrivacyPolicy();
		registerPage.clickOnContinueButton();
		
		String actWarningMsg = registerPage.retrieveDuplicateEmailWarning();
		
		Assert.assertTrue(actWarningMsg.contains(dataProp.getProperty("duplicateEmailWarning")), "This Email is Valid/Already Register");
	}
	
	@Test(priority=4)
	public void verifyRegisteringAccoutWithoutFillingAnyDetails()
	{	
		registerPage.clickOnContinueButton();
		
		String actualPrivacyPolicyWarning = driver.findElement(By.xpath("//div[@class='alert alert-danger alert-dismissible']")).getText();
		Assert.assertTrue(actualPrivacyPolicyWarning.contains(dataProp.getProperty("privacyPolicyWarning")), "Warning message not displayed");
	
		String actFirstNameWarning = driver.findElement(By.xpath("//input[@id='input-firstname']/following-sibling::div")).getText();
		Assert.assertEquals(actFirstNameWarning, dataProp.getProperty("firstNameWarning") , "Warning message not displayed");
		
		String actLastNameWarning = driver.findElement(By.xpath("//input[@id='input-lastname']/following-sibling::div")).getText();
		Assert.assertEquals(actLastNameWarning, dataProp.getProperty("lastNameWarning") , "Warning message not displayed");
		
		String actEmailWarning = driver.findElement(By.xpath("//input[@id='input-email']/following-sibling::div")).getText();
		Assert.assertEquals(actEmailWarning, dataProp.getProperty("emailWarning") , "Warning message not displayed");
		
		String actTelephoneWarning = driver.findElement(By.xpath("//input[@id='input-telephone']/following-sibling::div")).getText();
		Assert.assertEquals(actTelephoneWarning, dataProp.getProperty("telephoneWarning") , "Warning message not displayed");
		
		String actPasswordWarning = driver.findElement(By.xpath("//input[@id='input-password']/following-sibling::div")).getText();
		Assert.assertEquals(actPasswordWarning, dataProp.getProperty("passwordWarning") , "Warning message not displayed");	
	}

}
