package com.tutorialsninja.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage {
	

	WebDriver driver;
	
	@FindBy(linkText ="HP LP3065")
	private WebElement validHPProduct;
	
	@FindBy(xpath ="//div[@id='content']/descendant::p/following-sibling::p")
	private WebElement NoProductMessage;
	
	public SearchPage(WebDriver driver)
	{
		this.driver =driver;
		PageFactory.initElements(driver,this);
	}

	//Actions
	public boolean displayStatusOfValidHPProduct()
	{
		boolean displayStatus = validHPProduct.isDisplayed();
		return displayStatus;
	}
	
	public String retriveNoProductMessageText()
	{
		String noProductMessageText = NoProductMessage.getText();
		return noProductMessageText;
	}

}
