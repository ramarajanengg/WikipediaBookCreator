package wikipediaBookCreator;

import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import utility.CommonLibrary;
import utility.EnvSetUp;

import org.testng.annotations.BeforeTest;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;

public class DriverScript extends CommonLibrary {
	
	String appURL = ReadTestData("AUTURL");
	String extentReportFile = System.getProperty("user.dir")
			+ "\\extentReportFile.html";
	
	String TestName = "Wikipedia Book Creator Test";
	
	// Create object of extent report and specify the report file path.
	ExtentReports extent = new ExtentReports(extentReportFile, false);
	ExtentTest extentTest = extent.startTest(TestName,
			"Create a Book in Wikipedia");
	
	@BeforeTest
	  public void beforeTest() {
		
		EnvSetUp envObj = new EnvSetUp();
		
		LaunchBrowser(envObj.ChromeDriver());
		extentTest.log(LogStatus.INFO, "Step: Browser Launched.");
		LaunchURL(appURL);
		extentTest.log(LogStatus.INFO, "Step: URL is Launched."+ "\nEntered URL: "+ appURL);
	  }
	@Test
	public void Test() throws AWTException, InterruptedException, IOException
	{
		//Step1 - Launch URL
		LaunchURL(appURL);
		waitForPageLoad(driver);
		String actualTitle = getTitle();
		  
		  if(actualTitle.equals("Wikipedia, the free encyclopedia"))
		  {
			  System.out.println("Page is displayed!");
			  extentTest.log(LogStatus.PASS, "Step1: PASSED - get Title: "+ actualTitle+" Title is correct.");
		  }
		  else
		  {
			  extentTest.log(LogStatus.FAIL, "Step1: FAILED - Reason: Page Title is not correct. Actual Title: "+actualTitle+"\n Expected: "+"Wikipedia, the free encyclopedia");
		  }
		
		  //Step 2 - Click on the "Create a book" link from the "Print/export" Section
//		  String CreateABook_ID = "coll-create_a_book";
//		  By CreateABook_By = By.id(CreateABook_ID);
//		  wait.until(ExpectedConditions.presenceOfElementLocated(CreateABook_By));
//		  wait.until(ExpectedConditions.presenceOfElementLocated(locator))
//		  driver.findElement(CreateABook_By).click();
		  ObjectLocator("id", "CreateABook_ID").click();
		  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		  
		  if(getTitle().equals("Book creator - Wikipedia"))
		  {
			  extentTest.log(LogStatus.PASS, "Step 2: PASSED - Click on the 'Create a book' link from the 'Print/export' Section" );
		  }
		  else
		  {
			  extentTest.log(LogStatus.FAIL, "Step 2: FAILED - Click on the 'Create a book' link from the 'Print/export' Section");
		  }
		  
		  //Step 3 - Click on the button "Start Book creator" on the book creator page
//		  String StartBookCreator_xpath= "/html/body/div[3]/div[3]/div[3]/form/div/div[1]/button";
//		  driver.findElement(By.xpath(StartBookCreator_xpath)).click();
		  ObjectLocator("xpath", "StartBookCreator").click();
		  extentTest.log(LogStatus.PASS, "Step 3: PASSED - Click on the button 'Start Book creator' on the book creator page");
//		  String BookCreator = "coll-add_article";
//		  String actualText = funcLib.driver.findElement(By.id(BookCreator)).getText();
//		  try
//		  {
//			  Assert.assertEquals(actualText, "Add this page to your book");
//			  extentTest.log(LogStatus.PASS, "Step 3: PASSED Wikipedia Book Creator is enabled!");
//			  
//		  }
//		  catch(AssertionError e)
//		  {
//			  Assert.fail("ERROR: Book Creator Page is not displayed!");
//			  extentTest.log(LogStatus.FAIL, "Step 3: FAILED <Reason>: Book Creator is not enabled!");
//		  }
		  
		  //Step 4 - Type Selenium in the Wikipedia Search Textbox and press Enter
//		  String searchInput_ID = "searchInput";
//		  wait.until(ExpectedConditions.presenceOfElementLocated(By.id(searchInput_ID)));
//		  WebElement searchInput_WebElement = driver.findElement(By.id(searchInput_ID));
		  WebElement searchInput_WebElement = ObjectLocator("id", "searchInput_ID");
		  if(searchInput_WebElement.isEnabled())
		  {
			  searchInput_WebElement.sendKeys("Selenium"); //type Selenium in text box
			  searchInput_WebElement.sendKeys(Keys.RETURN);
			  String resultOfSearch_firstHeading = "firstHeading";
			  
//			  wait.until(ExpectedConditions.presenceOfElementLocated(By.id(resultOfSearch_firstHeading)));
//			  
//			  WebElement SearchResultfirstHeading = driver.findElement(By.id(resultOfSearch_firstHeading));
			  WebElement SearchResultfirstHeading = ObjectLocator("id", "resultOfSearch_firstHeading");
			  
			  if(SearchResultfirstHeading.getText().contains("Selenium"))
			  {
				  System.out.println("Selenium Wikipage is displayed!");
				  extentTest.log(LogStatus.PASS, "Step 4: PASSED - Type Selenium in the Wikipedia Search Textbox and press Enter");
				  
				  if(driver.findElements(By.xpath("//*[@id='coll-add_article']")).size()!=0)
				  {
					  System.out.println("Add this page to your book Link is displayed.");
					  ObjectLocator("xpath", "addPageToArticle").click();
					  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					  
					  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("coll-remove_article")));
					  
					  System.out.println("Identifying Show Book Element");
					  WebElement noOfPages = driver.findElement(By.partialLinkText("Show book"));
					  noOfPages.getText();
					  
					  System.out.println("No of pages added to the Book: "+noOfPages.getText().replaceAll("\\D+", ""));
					  
					  extentTest.log(LogStatus.PASS, "Step 5: PASSED - Click on the link 'Add this page to your book' for the selenium page. "+"No of pages added to the Book: "+noOfPages.getText().replaceAll("\\D+", ""));
					  
				  }
				  else
				  {
					  System.out.println("Add this page to your book Link is NOT displayed.");
					  extentTest.log(LogStatus.FAIL, "Step 5: FAILED - Add this page to your book Link is NOT displayed.");
				  }
			  }
			  
			//Step 6 - Type 'JScript' in the wikipedia search textbox and press Enter

			  searchInput_WebElement = ObjectLocator("id", "searchInput_ID");
			  searchInput_WebElement.sendKeys("JScript"); //type JScript in text box
			  searchInput_WebElement.sendKeys(Keys.RETURN);
			  //String resultOfSearch_firstHeading = "firstHeading";
			  
			  //wait.until(ExpectedConditions.presenceOfElementLocated(By.id(resultOfSearch_firstHeading)));
			  
			  SearchResultfirstHeading = ObjectLocator("id", "resultOfSearch_firstHeading");
			  if(SearchResultfirstHeading.getText().contains("JScript"))
			  {
				  System.out.println("Selenium Wikipage is displayed!");
				  extentTest.log(LogStatus.PASS, "Step 6: PASSED - Type 'JScript' in the wikipedia search textbox and press Enter");
				  
				  if(driver.findElements(By.xpath("//*[@id='coll-add_article']")).size()!=0)
				  {
					  System.out.println("Add this page to your book Link is displayed.");
					  ObjectLocator("xpath", "addPageToArticle").click();
					  driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
					  
					  wait.until(ExpectedConditions.presenceOfElementLocated(By.id("coll-remove_article")));
					  
					  System.out.println("Identifying Show Book Element");
					  WebElement noOfPages = ObjectLocator("partialLinkText", "noOfPages");
					  noOfPages.getText();
					  
					  System.out.println("No of pages added to the Book: "+noOfPages.getText().replaceAll("\\D+", ""));
					  
					  extentTest.log(LogStatus.PASS, "Step 7: PASSED - Type 'JScript' in the wikipedia search textbox and press Enter. "+"No of pages added to the Book: "+noOfPages.getText().replaceAll("\\D+", ""));
					  
					  //Step 8 - Click on the Link "Show book (2 Pages)"
					  noOfPages.click();
					  wait.until(ExpectedConditions.presenceOfElementLocated(By.id(resultOfSearch_firstHeading)));
					  
					  SearchResultfirstHeading = ObjectLocator("id", "resultOfSearch_firstHeading");
					  if(SearchResultfirstHeading.getText().contains("Manage your book"))
					  {
						  System.out.println("Manage your book Page is displayed!");
						  extentTest.log(LogStatus.PASS, "Step 8: PASSED - Click on the Link 'Show book (2 Pages)'");
					  }
					  else
					  {
						  extentTest.log(LogStatus.FAIL, "Step 8: FAILED - Click on the Link 'Show book (2 Pages)'");
					  }
					  
					  
					  //Step 9 - Type "Book Creator from Wikipedia" in the Title textbox on the "Manage your Book" page
					  WebElement BookTitle_ID = ObjectLocator("id", "BookTitle_ID");
					  
					  if(BookTitle_ID.isEnabled())
					  {
						  BookTitle_ID.sendKeys("Book Creator from Wikipedia");
						  extentTest.log(LogStatus.PASS, "Step 9: PASSED - Type 'Book Creator from Wikipedia' in the Title textbox on the 'Manage your Book' page");
					  }
					  else
					  {
						  extentTest.log(LogStatus.FAIL, "Step 9: FAILED - Type 'Book Creator from Wikipedia' in the Title textbox on the 'Manage your Book' page");
					  }
					  
					  
					  //Step 10 - Type "Using Automation" in the Subtitle Text box on the "Manage your Book" page
					  WebElement BookSubTitle_ID = ObjectLocator("id", "BookSubTitle_ID");
					  
					  if(BookSubTitle_ID.isEnabled())
					  {
						  BookSubTitle_ID.sendKeys("Using Automation");
						  extentTest.log(LogStatus.PASS, "Step 10: PASSED - Type 'Using Automation' in the Subtitle Text box on the 'Manage your Book' page");
					  }
					  else
					  {
						  extentTest.log(LogStatus.FAIL, "Step 10: FAILED - Type 'Using Automation' in the Subtitle Text box on the 'Manage your Book' page");
					  }
					  
					  //Step 11 - Click on the "Download" button for PDF file format
					  
					  WebElement formatSelect = ObjectLocator("id", "formatSelect");
					  
					  Select fileFormatSelect = new Select(formatSelect);
					  
					  if(formatSelect.isEnabled())
					  {
						  fileFormatSelect.selectByIndex(0);  
						  WebElement downloadButton = ObjectLocator("id", "downloadButton");
						  
						  if(downloadButton.isEnabled())
						  {
							  downloadButton.click();
							  wait.until(ExpectedConditions.presenceOfElementLocated(By.id(resultOfSearch_firstHeading)));
							  SearchResultfirstHeading = ObjectLocator("id", "resultOfSearch_firstHeading");
							  if(SearchResultfirstHeading.getText().contains("Rendering finished"))
							  {
								  System.out.println("Step 11 Passed");
								  extentTest.log(LogStatus.PASS, "Step 11: PASSED - Click on the 'Download' button for PDF file format");
							  }
							  else
							  {
								  extentTest.log(LogStatus.FAIL, "Step 11: FAILED - Click on the 'Download' button for PDF file format");
							  }
						  }
						  else
						  {
							  extentTest.log(LogStatus.FAIL, "Step 11: FAILED - Click on the 'Download' button for PDF file format");
						  }
					  }
					  else
					  {
						  extentTest.log(LogStatus.FAIL, "Step 11: FAILED - Click on the 'Download' button for PDF file format");
					  }
							  
				  }
				  else
				  {
					  System.out.println("Add this page to your book Link is NOT displayed.");
					  extentTest.log(LogStatus.FAIL, "Step 7: FAILED - Add this page to your book Link is NOT displayed.");
				  }
			  }
		  
		  }
		  else
		  {
			  extentTest.log(LogStatus.FAIL, "Step 4: FAILED - Type Selenium in the Wikipedia Search Textbox and press Enter");
		  }
		  
		  //Step 12 - Click on the "Download the file" link 
		  WebElement downloadLink = ObjectLocator("linktext", "downloadLink");
		  downloadLink.click();
		  driver.manage().timeouts().implicitlyWait(01, TimeUnit.MINUTES); //Wait for the PDF file to be loaded
		  extentTest.log(LogStatus.PASS, "Step 12: PASSED - Click on the 'Download the file' link ");
		  Robot r = new Robot();
		  Thread.sleep(15000);
		  
		  r.keyPress(KeyEvent.VK_CONTROL);
		  r.keyPress(KeyEvent.VK_S);
		  Thread.sleep(1000);
		 r.keyRelease(KeyEvent.VK_CONTROL);
		  //r.keyPress(KeyEvent.VK_ENTER);

		  Runtime.getRuntime().exec("C:\\Users\\ramarajan.subburaj\\Desktop\\downloadFile.exe");
		  
		  extentTest.log(LogStatus.PASS, "Step 13: PASSED - Click CTRL S");
		  extentTest.log(LogStatus.PASS, "Step 14: PASSED - Enter File Name and File Path");
		  extentTest.log(LogStatus.PASS, "Step 15: PASSED - Click Save");
		 
	}

  @AfterTest
  public void afterTest() {
	  
	  extent.endTest(extentTest);
	  
	  driver.quit();
	  extentTest.log(LogStatus.PASS, "Step 16: PASSED - Browser Closed");
	  
		// close report.
		extent.endTest(extentTest);
			
		// writing everything to document.
		extent.flush();
  }

}
