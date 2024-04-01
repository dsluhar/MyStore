package com.mystore.testcases;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import org.testng.annotations.Parameters;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.mystore.actiondriver.Action;
import com.mystore.base.BaseClass;
import com.mystore.pageobjects.IndexPage;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class IndexPageTest extends BaseClass {

	public static String getBrowserName = null;
	Action action = new Action();
	
	@Override
	public String getBrowserName() {
		getBrowserName = super.getBrowserName();
		return getBrowserName;
	}
	
	@Test(testName = "Verify Product Logo", groups = {"regression"})
	public void VerifyLogo()  {
		IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
		indexPage.ClickOnSignIn();
		ExtentTest test5 = extentReports.createTest("Test 5");
		test5.pass("Test Case 5 is passed");
		ExtentTest test4 = extentReports.createTest("TestCase 4 - ebay Search Test", "test to validate search box ");
		test4.pass("This is VerifyProductLogo Testcase").assignAuthor("DL").assignCategory("Smoke").assignDevice("Chrome123");
		boolean result = indexPage.ValidateLogo();
		extentTest.log(Status.INFO, "Starting test case");
		Assert.assertTrue(result);
	    assertThat(driver.getCurrentUrl()).contains("login");
	    /* AssertThat Assertion
	    assertThat("Page title does not match").isEqualTo(driver.getTitle());
	    assertThat("Validation message for Corrective Condition",driver.getTitle(), equalTo("Automation Exercise - Signup / Login"));
	    */
	    System.out.println("Browser being used is " + getBrowserName());
	    extentTest.log(Status.INFO, "Ending test case");
	}
	
	@Test(testName = "Verify Title of the Page", groups = {"smoke"})
	public void VerifyTitle() throws InterruptedException {
		IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
		String ActualTitle = indexPage.getMyStoreTitle();
		Assert.assertEquals(ActualTitle, "Automation Exercise - All Products");
		extentTest.pass("title is correct");
	}
	@Test(testName = "Verify Search Product", groups = {"regression"})
	public void VerifySearchProduct() {
		IndexPage indexPage = PageFactory.initElements(driver, IndexPage.class);
		indexPage.SearchProduct("Top");
		WebElement vBrands = driver.findElement(By.xpath("//h2[text()=\"Brands\"]"));
		action.scrollByVisibilityOfElement(driver, vBrands);
	}
	@Test
	@Parameters({ "first-name" })
	public void testMethod(String firstName) {
	    System.out.println("First Name: " + firstName);
	 }
}
