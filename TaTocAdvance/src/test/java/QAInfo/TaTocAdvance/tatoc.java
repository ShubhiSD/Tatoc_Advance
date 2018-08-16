package QAInfo.TaTocAdvance;
import java.io.File;
import java.nio.file.Path;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import util.UtilityFileReader;
import util.ExcelUtils;
import util.Log;

public class tatoc {
	UtilityFileReader configReader=new UtilityFileReader();;
	WebDriver driver; 
	@Parameters({"browser"})
	@BeforeTest
	 public void initializeCourse(String browser) throws Exception{
		String logFilePath=configReader.getlog4jXmlpath()+"\\"+configReader.getlog4jXmlFilName()+".log";
		File log=new File(logFilePath); 
		if(log.exists() )
		log.delete();  
		if(browser.equalsIgnoreCase("firefox")) 
		{
			System.setProperty("webdriver.firefox.marionette","C:\\geckodriver.exe"); 
			 driver=new FirefoxDriver();
		}
		else if(browser.equalsIgnoreCase("chrome"))
		{
		System.setProperty("webdriver.chrome.driver","C:\\chrome\\chromedriver.exe");
		driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("ie"))
		{ DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
        ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS,
true);
        //need to add above code else ie give error of opened browser is closed
			System.setProperty("webdriver.ie.driver","F:\\IE webdriver\\IEDriverServer.exe");
			driver=new InternetExplorerDriver();
		}
		configReader=new UtilityFileReader();
        Log.info("New driver instantiated");
        driver.get(configReader.getApplicationUrl()); 
        Log.info("URL path set");
        ExcelUtils.setExcelFile();
	  }
	@Test (priority=1)
  public void hoverMenu() {
	WebElement menu=driver.findElement(By.cssSelector("div.menutop.m2"));
	menu.click();
	WebElement menuItem=driver.findElement(By.cssSelector("div.menutop.m2 span:nth-child(5)"));
	menuItem.click();
	Boolean result=(driver.getTitle()).contains("Query");
	Assert.assertTrue(result, "Menuitem clicked, proceeded to next test");
	try{ExcelUtils.setCellData(((result==Boolean.TRUE)? "Pass":"Fail"), 1, 1);System.out.println("written to excel");}
    catch(Exception e)
    {Log.error(e.getMessage());}
  }
  
	@Test (priority=2)
	  public void connectToDB() {
		String[] name;
		WebElement symbolElement=driver.findElement(By.cssSelector("div#symboldisplay"));
		String symbol=symbolElement.getText();
		Log.info("symbol="+symbol);
		try{
			ConnectToMySQL  conn=new ConnectToMySQL ();
			name=conn.getName(symbol); 
			Log.info("name and passkey returned from function");
			driver.findElement(By.cssSelector("input#name")).sendKeys(name[0]);
			driver.findElement(By.cssSelector("input#passkey")).sendKeys(name[1]);
			try {Thread.sleep(300);}catch (Exception e) {}
			driver.findElement(By.cssSelector("input#submit")).click();
		}
		catch (Exception e) {System.out.println(e.getMessage());}
		Boolean result=(driver.getTitle()).contains("Video");
		Assert.assertTrue(result, "Query executed, proceeded to next test");
		try{ExcelUtils.setCellData(((result==Boolean.TRUE)? "Pass":"Fail"), 2, 1);
		}
	    catch(Exception e)
	    {Log.error(e.getMessage());}
	}
	@Test (priority=3)
	  public void videoPlayer() {
		//Ooyala Player V3 is deprecated and is scheduled to be disabled on 2018-01-31. After that date, Player V3 will no longer play your video or audio content. Customers still using Player V3 need to migrate to Player V4
		
	}
	@AfterTest
	 public void closeDriver() {
		
		driver.quit(); 
String logFilePath=configReader.getlog4jXmlpath()+"\\"+configReader.getlog4jXmlFilName()+".log";
	  
File log=new File(logFilePath); 
String dest=configReader.getlog4jXmlpath()+"\\"+"Archive"+"\\"+configReader.getlog4jXmlFilName()+System.currentTimeMillis()+".log";
System.out.println(logFilePath+" dest "+dest);	  
File destFile=new File(dest);
try {
FileUtils.copyFile(log, destFile);}
catch(Exception e)
{Log.error(e.getMessage());}
	//log.delete();  
	}
}
