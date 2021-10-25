package testCases;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeSuite;
 

// BaseTest or driverScript which will be executed before executing any of the test script

public class BaseTest {

	public static String browser        = null;     
	public static WebDriver driver      = null;
	public static Properties propConfig = new Properties();
	public static Properties propOR     = new Properties();
	public static Logger log = Logger.getLogger(BaseTest.class);
	
	@BeforeSuite
	public void Setup() throws IOException
	{
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/config.properties");
		propConfig.load(fis);
		
		fis = new FileInputStream(System.getProperty("user.dir")+ "/src/test/resources/OR.properties");
		propOR.load(fis);
		browser = propConfig.getProperty("browser");
		
		Date currentDate = new Date();
		System.setProperty("current.date",currentDate.toString().replace(":", "_").replace(" ","_"));
		PropertyConfigurator.configure(System.getProperty("user.dir") + "\\src\\test\\resources\\log4j.properties");
		
		String loglevel = propConfig.getProperty("loglevel").toUpperCase();
		
		if (loglevel.equals("INFO"))
			log.setLevel(Level.INFO);
		else if (loglevel.equals("ERROR"))
			log.setLevel(Level.ERROR);
		}
		
	}
