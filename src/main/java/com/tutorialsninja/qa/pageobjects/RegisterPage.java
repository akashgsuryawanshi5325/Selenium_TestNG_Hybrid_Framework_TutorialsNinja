package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class RegisterPage {
	
	WebDriver driver;
	
	@FindBy(id="input-firstname")
	private WebElement firstNameField;
	
	@FindBy(id="input-lastname")
	private WebElement lastNameField;
	
	@FindBy(id="input-email")
	private WebElement emailAdressField;
	
	@FindBy(id="input-telephone")
	private WebElement telephoneField;
	
	@FindBy(id="input-password")
	private WebElement passwordField;
	
	@FindBy(id="input-confirm")
	private WebElement passwordConfirmField;
	
	@FindBy(xpath="//input[@name='newsletter'][@value=1]")
	private WebElement yesNewsLetterOption;
	
	@FindBy(xpath="//input[@name='agree']")
	private WebElement privacyPolicyField;
	
	@FindBy(xpath="//input[@type='submit']")
	private WebElement continueButton;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement duplicateEmailWarning;
	
	@FindBy(xpath="//div[@class='alert alert-danger alert-dismissible']")
	private WebElement privacyPolicyWarning;
	
	@FindBy(xpath="///input[@id='input-firstname']/following-sibling::div")
	private WebElement firstNameWarning;
	
	@FindBy(xpath="//input[@id='input-lastname']/following-sibling::div")
	private WebElement lastNameWarning;
	
	@FindBy(xpath="//input[@id='input-email']/following-sibling::div")
	private WebElement emailWarning;
	
	@FindBy(xpath="//input[@id='input-telephone']/following-sibling::div")
	private WebElement telephoneWarning;
	
	@FindBy(xpath="//input[@id='input-password']/following-sibling::div")
	private WebElement passwordWarning;
	
	public RegisterPage(WebDriver driver)
	{
		this.driver =driver;
		PageFactory.initElements(driver,this);
	}
	
	public void enterFirstName(String firstNameText)
	{
		firstNameField.sendKeys(firstNameText);
	}
	
	public void enterLastName(String lastNameText)
	{
		lastNameField.sendKeys(lastNameText);
	}
	
	public void eneterEmailAdress(String emailText)
	{
		emailAdressField.sendKeys(emailText);
	}
	
	public void enterTelephone(String telephoneText)
	{
		telephoneField.sendKeys(telephoneText);
	}
	
	public void enterPassword(String passwordText)
	{
		passwordField.sendKeys(passwordText);
	}
	
	public void enterPasswordConfirm(String passwordConfirmText)
	{
		passwordConfirmField.sendKeys(passwordConfirmText);
	}
	
	public void selectYesNewsLetterOption()
	{
		yesNewsLetterOption.click();
	}
		
	public void selectPrivacyPolicy()
	{
		privacyPolicyField.click();
	}
	
	public AccountSuccessPage clickOnContinueButton()
	{
		continueButton.click();
		return new AccountSuccessPage(driver);
	}
	
	public String retrieveDuplicateEmailWarning()
	{
		String  duplicateEmailWarningText = duplicateEmailWarning.getText();
		return duplicateEmailWarningText;
	}
	
	public String retrievePrivacyPolicyWarning()
	{
		String  privacyPolicyWarningText = privacyPolicyWarning.getText();
		return privacyPolicyWarningText;
	}
	
	public String retrieveFirstNameWarning()
	{
		String  firstNameWarningText = firstNameWarning.getText();
		return firstNameWarningText;
	}
	
	public String retrievLastNameWarning()
	{
		String  lastNameWarningText = lastNameWarning.getText();
		return lastNameWarningText;
	}
	
	public String retrieveEmailWarning()
	{
		String  emailWarningText = emailWarning.getText();
		return emailWarningText;
	}
	
	public String retrievTelephoneWarning()
	{
		String  telephoneWarningText = telephoneWarning.getText();
		return telephoneWarningText;
	}
	
	public String retrievePasswordWarning()
	{
		String  passwordWarningText = passwordWarning.getText();
		return passwordWarningText;
	}
}
