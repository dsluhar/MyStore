package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class HomePage extends BaseClass {
	
	Action action = new Action();

	@FindBy (xpath = "//b[text()='rajat']")
	WebElement LoggedUser;
	
	@FindBy (xpath = "//a[contains(., 'Logged in as')]")
	WebElement LoggedUserText;	
	
	public HomePage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean ValidateLoggedinMsg() {
		return action.isDisplayed(driver, LoggedUserText);
	}
}