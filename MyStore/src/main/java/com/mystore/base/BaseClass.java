package com.mystore.base;

//PROJECT URL = https://automationexercise.com/login


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

public class BaseClass {
	
	public static WebDriver driver = null;
	public static Properties Config = new Properties();
	public static FileInputStream fis ;
	public static ExtentSparkReporter sparkReporter;
    public static ExtentReports extentReports;
    public static ExtentTest extentTest;
	
	//@BeforeSuite(groups = {"api", "regression", "smoke"})
	@BeforeSuite(groups = {"api", "regression", "smoke"})
	public void BaseClass1() throws IOException 
	{
		try {
			 fis = new FileInputStream(System.getProperty("user.dir") + "\\Configuration\\Config.properties");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			Config.load(fis);
			} catch (IOException e) {
				e.printStackTrace();
			}
		
		   if (Config.getProperty("browser").equalsIgnoreCase("chrome")) {
			   driver = new ChromeDriver();
			   
		   }
		   else if (Config.getProperty("browser").equalsIgnoreCase("firefox")) {
			   driver = new FirefoxDriver();
			   
		   }
		   else if (Config.getProperty("browser").equalsIgnoreCase("edge")) {
			   driver = new EdgeDriver();
			   
		   }
		   driver.get(Config.getProperty("url"));
		   driver.manage().window().maximize();
		   driver.manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("implicitWait")), TimeUnit.SECONDS);
		   
		   initializeReport();
	}
	public String getBrowserName() {
        if (Config.getProperty("browser") != null) {
            return Config.getProperty("browser");
        } else {
            return null;
        }
    }
	public void initializeReport() throws IOException {
		extentReports = new ExtentReports();
		extentReports.getStats();
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter("ExtentReport.html");
		/* insert below lines of Code into extentconfig.json file
		extentSparkReporter.config().setTheme(Theme.DARK);
		extentSparkReporter.config().setDocumentTitle("Automation TestNG Report");
		extentSparkReporter.config().setReportName("Extent Report Demo 001");
		startTest: Executes preconditions of a test case
		endTest: Executes postconditions of a test case
		*/
		
		final File CONF = new File("extentconfig.json");
		extentSparkReporter.loadJSONConfig(CONF);

		extentReports.attachReporter(extentSparkReporter);
        extentReports.setSystemInfo("QA Engineer", "Dilip");
        extentReports.setSystemInfo("OS", System.getProperty("os.name"));
        extentTest = extentReports.createTest("Extent Report Demo");
        
        ExtentTest test1 = extentReports.createTest("Test 1");
        test1.pass("Window Maximize has done");
        test1.pass("Navigate to automationexercise.com");
        test1.pass("This test case pass").assignAuthor("DL VDL").assignCategory("Smoke").assignDevice("Chrome123");

        ExtentTest test2 = extentReports.createTest("Test 2");
        test2.log(Status.FAIL, "This test case fail").assignAuthor("HDL MDL").assignCategory("Sanity").assignDevice("Edge100");

        ExtentTest test3 = extentReports.createTest("Test 3");
        test3.skip("This test case is skipped").assignAuthor("STL").assignCategory("Sanity").assignDevice("Edge100");
        
	}
	
	   @AfterSuite(groups = {"api", "regression", "smoke"})
	    public void generateReport() throws IOException {
	        extentReports.flush();
	        Desktop.getDesktop().browse(new File("ExtentReport.html").toURI());
	    }

//    @AfterSuite
//	public void CloseBrowser() throws InterruptedException {
//    	Thread.sleep(5000);
//    	BaseClass.driver.close();
//    	BaseClass.driver.quit();
//	}

}
