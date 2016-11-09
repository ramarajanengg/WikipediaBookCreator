package utility;

import java.io.File;
import org.openqa.selenium.WebDriver;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class EnvSetUp extends CommonLibrary {

	Properties EnvFile;
	
	public EnvSetUp()
	{
		File file = new File("./Environment.properties");
		try {
			FileInputStream fis = new FileInputStream(file);
			EnvFile = new Properties();
			EnvFile.load(fis);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public WebDriver ChromeDriver()
	{
		System.setProperty("webdriver.chrome.driver", ReadTestData("ChromeDriverPath"));
		WebDriver driver = new ChromeDriver();
		return driver;
		
	}
	
	public WebDriver FirefoxDriver()
	{
		System.setProperty("webdriver.gecko.driver", ReadTestData("FirefoxDriverPath"));
		WebDriver driver = new FirefoxDriver();
		return driver;
		
	}

	public WebDriver IEDriver()
	{
		System.setProperty("webdriver.ie.driver", ReadTestData("IEDriverPath"));
		WebDriver driver = new InternetExplorerDriver();
		return driver;
	}

}
