package utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ObjectRep {

	Properties ORFile;
	
	public ObjectRep()
	{
		File file = new File("./ObjectRepository.properties");
		try {
			FileInputStream fis = new FileInputStream(file);
			ORFile = new Properties();
			ORFile.load(fis);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public WebElement id_CreateABook_ID(WebDriver driver)
	{
		return driver.findElement(By.id(ORFile.getProperty("CreateABook_ID")));
	}
	
	public void split()
	{
		String str = ORFile.getProperty("CreateABook_ID");
		System.out.println();
		//split
		String str1[] = str.split(";");
		for (String s : str1)
		{
			System.out.println(s);
		}
	}
}
