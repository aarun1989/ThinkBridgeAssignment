package commonfunctions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.*;


import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonFunctions 
{
	public WebDriver driver;

	public static Properties prop;

	public Properties loadPropertyFile() throws IOException
	{
		FileInputStream fis=new FileInputStream("config.properties");

		prop=new Properties();

		prop.load(fis);

		return prop;
	}
	@BeforeSuite
	public void launchBrowser() throws IOException
	{		
		loadPropertyFile();		

		String browserName=prop.getProperty("browser");

		String url=prop.getProperty("AppUrl");

		if(browserName.equalsIgnoreCase("chrome"))
		{
			WebDriverManager.chromedriver().setup();
			driver=new ChromeDriver();			
		}
		else if(browserName.equalsIgnoreCase("firefox"))
		{
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();			
		}
		else if(browserName.equalsIgnoreCase("edge"))
		{
			WebDriverManager.edgedriver().setup();
			driver=new EdgeDriver();			
		}
		else if(browserName.equalsIgnoreCase("IE"))
		{
			WebDriverManager.iedriver().setup();
			driver=new InternetExplorerDriver();			
		}
		driver.manage().window().maximize();

		driver.get(url);

		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);

	}

	@AfterSuite
	public void tearDown()
	{
		driver.quit();
	}


}
