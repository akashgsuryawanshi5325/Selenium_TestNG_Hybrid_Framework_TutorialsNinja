package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
	
	WebDriver driver;
	
	@FindBy(xpath="//span[text()='My Account']")
	private WebElement myAccountDropMenue;
	
	@FindBy(xpath="//a[text()='Login']")
	private WebElement loginOption;
	
	@FindBy(xpath="//a[text()='Register']")
	private WebElement registerOption;
	
	@FindBy(xpath="//input[@name='search']")
	private WebElement searchField;
	
	@FindBy(xpath="//button[@class='btn btn-default btn-lg']")
	private WebElement searchButton;
	
	
	public HomePage(WebDriver driver)
	{
		this.driver =driver;
		PageFactory.initElements(driver,this);
	}

	//Actions
	public void clickOnMyAccount()
	{
		myAccountDropMenue.click();
	}
	
	public LoginPage selectLoginOption()
	{
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public LoginPage navigateToLoginPage()
	{
		myAccountDropMenue.click();
		loginOption.click();
		return new LoginPage(driver);
	}
	
	public RegisterPage selectRegisterOption()
	{
		registerOption.click();
		return new RegisterPage(driver);
	}
	
	public void enterProductName(String productNameText)
	{
		searchField.sendKeys(productNameText);
	}
	
	public SearchPage clickOnSearchButton()
	{
		searchButton.click();
		return new SearchPage(driver);
	}
}
