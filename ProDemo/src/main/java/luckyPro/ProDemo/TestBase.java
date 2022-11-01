package luckyPro.ProDemo;



import java.time.Duration;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {

	public static WebDriver driver; 
	public static Logger logger;
	
	//made changes as Chrome to chrome;
	@BeforeClass
	public void start()
	{
		logger=  Logger.getLogger("DalalStreetIB");
		PropertyConfigurator.configure("Log4j.properties");
	
		logger.info("Framework execution started");
	}
	
	@AfterClass
	public void stop()
	{
		logger.info("Framework execution Finished");
	}
	
	@Parameters("browser")
	@BeforeMethod
	
	//Browser Selection
		public void browserSetUp( String br) throws InterruptedException
		{
			if(br.equalsIgnoreCase("chrome"))
			{
				WebDriverManager.chromedriver().setup();
					driver =new ChromeDriver();
			}
			else if(br.equalsIgnoreCase("Edge"))
			{
				WebDriverManager.edgedriver().setup();
				driver = new EdgeDriver();
			}
			else if(br.equalsIgnoreCase("Opera"))
			{
				WebDriverManager.operadriver().setup();
				driver = new EdgeDriver();
			}
			else if (br.equalsIgnoreCase("Firefox"))
			{
				WebDriverManager.firefoxdriver().setup();
				driver = new FirefoxDriver();	
			}
			else
			{
				System.out.println("Enter Correct name of driver");
			}
			logger.info("Browser Luanched");
		
		//--------Site Open-----------	
		driver.get("https://www.apps.dalalstreet.ai/login");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
		driver.manage().deleteAllCookies();	
		
		logger.info("url Opened, window maximized, implicite wait applied");
		
		
		}

	@AfterMethod
	
	//Browser Close
		public void tearDown() throws InterruptedException 
		{
			driver.quit();
			logger.info("Browser closed successfully");
			Thread.sleep(3000);
		}
		
}