package com.mystore.pageobjects;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;

public class LoginPage extends BaseClass {
	
	Action action = new Action();
	
	@FindBy (xpath = "//input[@name='email' and @data-qa='login-email']")
	@CacheLookup
	WebElement UserEmail;
	
	@FindBy (xpath = "//input[@name='password']")
	@CacheLookup
	WebElement UserPassword;	
	
	@FindBy (xpath = "//button[@data-qa='login-button']")
	@CacheLookup
	WebElement ClickLoginBtn;	

	@FindBy (xpath = "//input[@data-qa='signup-name']")
	@CacheLookup
	WebElement UserNameForNewAccount;
	
	@FindBy (xpath = "//input[@data-qa='signup-email']")
	@CacheLookup
	WebElement EmailForNewAccount;	
	
	@FindBy (xpath = "//button[@data-qa='signup-button']")
	@CacheLookup
	WebElement ClickLoginBtnForNewAccount;		
	
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}
	
    public HomePage Login(String useremail, String password) {
    	action.type(UserEmail, useremail);
    	action.type(UserPassword, password);
    	action.click(driver, ClickLoginBtn);
    	return new HomePage();
    }
	
    public AccountCreationPage CreateNewAccount(String useremailforNewaccount, String emailforNewAccount) {
    	action.type(UserNameForNewAccount, useremailforNewaccount) ;
    	action.type(EmailForNewAccount, emailforNewAccount) ;
    	action.click(driver, ClickLoginBtnForNewAccount);
    	return new AccountCreationPage();
    	}
    
}
