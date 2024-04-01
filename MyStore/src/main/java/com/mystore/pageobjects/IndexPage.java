package com.mystore.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.mystore.base.BaseClass;
import com.mystore.actiondriver.Action;

 public class IndexPage extends BaseClass {
	
	Action action = new Action();
	
	public IndexPage(WebDriver driver) 
		{
		this.driver = driver;
		}

	@FindBy (xpath = "//a[@href='/login']")
	@CacheLookup
	WebElement LoginSignUp;
	
	@FindBy (xpath = "//img[@alt='Website for automation practice']")
	@CacheLookup
	WebElement myStoreLogo;

	@FindBy (xpath = "//a[@href='/products']")
	@CacheLookup
	WebElement ProductLink;
	
	@FindBy (id = "search_product")
	@CacheLookup
	WebElement SearchProduct;
	
	@FindBy (xpath = "//i[@class='fa fa-search']")
	@CacheLookup
	WebElement SearchButton;
	
	public LoginPage ClickOnSignIn() {
		action.click(driver, LoginSignUp);
		return new LoginPage();
	}

	public boolean ValidateLogo() {
		boolean FlagLogo = myStoreLogo.isDisplayed();
		return FlagLogo;
	}
	
	public String getMyStoreTitle() {
		//action.fluentWait(driver, ProductLink, 10);
		String myStoreTitle = driver.getTitle();
		return myStoreTitle;
	}
	
	public SearchResultPage SearchProduct(String ProductName) {
		action.click(driver, ProductLink);
		action.type(SearchProduct, ProductName);
		action.click(driver, SearchButton);
		return new SearchResultPage();
	}
}
