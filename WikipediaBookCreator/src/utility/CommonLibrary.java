package utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;

/*
 * Common reusable code across many applications will be Stored Here!
 */
public class CommonLibrary {
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	Properties TestDataFile;
	Properties ORFile;
	
	public CommonLibrary() {

		File TDfile = new File("./TestData.properties");
		try {
			FileInputStream TDfis = new FileInputStream(TDfile);
			TestDataFile = new Properties();
			TestDataFile.load(TDfis);
			
			File ORfile = new File("./ObjectRepository.properties");
			FileInputStream ORfis = new FileInputStream(ORfile);
			ORFile = new Properties();
			ORFile.load(ORfis);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/*
	 * Browser Launcher
	 * Define wait
	 */
	public void LaunchBrowser(WebDriver driver)
	{
		this.driver = driver;
		driver.manage().window().maximize();
		
		wait = new WebDriverWait(driver, 60);
	}
	
	/*
	 * Launches URL passed in the function call
	 * wait for 20 seconds to sync
	 */
	public void LaunchURL(String URL)
	{
		driver.get(URL);
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}
	
	/*
	 * Get Title of a WebPage
	 */
	public String getTitle()
	{
		return driver.getTitle();
	}
	
	/*
	 * Waits for Page Load
	 */
	public void waitForPageLoad(WebDriver wdriver) {
	    WebDriverWait wait = new WebDriverWait(wdriver, 60);

	    Predicate<WebDriver> pageLoaded = new Predicate<WebDriver>() {

	        @Override
	        public boolean apply(WebDriver input) {
	            return ((JavascriptExecutor) input).executeScript("return document.readyState").equals("complete");
	        }

	    };
	    wait.until(pageLoaded);
	}

	
	/*
	 * Test Data Reader
	 */

	public String ReadTestData(String name)
	{
		return TestDataFile.getProperty(name);
		
	}

	/*
	 * Object Finder
	 */
	public WebElement ObjectLocator(String locatorType, String elementName)
	{
		String locatorValue = null;
		 
		String str = ORFile.getProperty(elementName);
		
		String str1[] = str.split(";");
		
		for(int i = 0; i < str1.length; i++)
		{
			if(locatorType.equalsIgnoreCase(str1[i].split("\\$")[0]))
			{
				locatorValue = str1[i].split("\\$")[1];
				System.out.println(locatorValue+" Found!");
				break;
			}
		}
		
		if(locatorType.equalsIgnoreCase("id"))
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locatorValue)));
			return driver.findElement(By.id(locatorValue));
		}
		else if(locatorType.equalsIgnoreCase("xpath"))
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorValue)));
			return driver.findElement(By.xpath(locatorValue));
		}
		else if(locatorType.equalsIgnoreCase("CSS"))
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorValue)));
			return driver.findElement(By.cssSelector(locatorValue));
		}
		else if(locatorType.equalsIgnoreCase("linktext"))
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText(locatorValue)));
			return driver.findElement(By.linkText(locatorValue));
		}
		else if(locatorType.equalsIgnoreCase("partialLinkText"))
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.partialLinkText(locatorValue)));
			return driver.findElement(By.partialLinkText(locatorValue));
		}
		else if(locatorType.equalsIgnoreCase("className"))
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.className(locatorValue)));
			return driver.findElement(By.className(locatorValue));
		}
		else if(locatorType.equalsIgnoreCase("name"))
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.name(locatorValue)));
			return driver.findElement(By.name(locatorValue));
		}
		else if(locatorType.equalsIgnoreCase("tagname"))
		{
			wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(locatorValue)));
			return driver.findElement(By.tagName(locatorValue));
		}
		else
			return null;
	}
}
