package testCasesLoginPkg;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import testCases.BaseTest;

public class LoginTest extends BaseTest{

	@BeforeMethod
	public void loginTearUp()
	{
		switch(browser.toUpperCase())
		{
		case "CHROME":
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			break;
		case "FIREFOX":
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
			break;
		case "EDGE":
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
			break;
		}	
			driver.navigate().to(propConfig.getProperty("webSiteUrl"));
			int tm = Integer.parseInt(propConfig.getProperty("timeOut"));
		// Check what happens in selenium 4?
		//	driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(tm));
			driver.manage().timeouts().implicitlyWait(tm, TimeUnit.SECONDS);
 			if (propConfig.getProperty("windowMaximize").toUpperCase().equals("YES"))
 				driver.manage().window().maximize();
	}
	
	
	@Test(dataProvider="getLoginData")
	public void doLogin(String username, String password, String expMsg, String locator) throws InterruptedException
	{
		utils.utilities.elementType("username_ID",username);
		utils.utilities.elementType("password_ID",password);
		utils.utilities.elementClick("loginBtn_ID");
		utils.utilities.verifyEquals(expMsg, locator);
	}

	@Test
	public void verifyLoginTitle() throws InterruptedException
	{
		doLogin("standard_user","secret_sauce","PRODUCTS","verifictionMsg_XPATH");
		utils.utilities.verifyWindowTitle("Swag Labs",utils.utilities.elementGetTitle());
	}
	
	@AfterMethod
	public void logInTearDown()
	{
		if (driver!=null)
			driver.quit();
	}
	
	@DataProvider
	public Object[][] getLoginData()
	{
		Object[][] data = new Object[4][4];
		
		data[0][0]="standard_user";
		data[0][1]="secret_sauce";
		data[0][2]="PRODUCTS";
		data[0][3]="verifictionMsg_XPATH";
		
		
		data[1][0]="standard_user1";
		data[1][1]="secret_sauce";
		data[1][2]="Epic sadface: Username and password do not match any user in this service";
		data[1][3]="verifictionErrMsg_XPATH";
		
		
		data[2][0]="standard_user";
		data[2][1]="secret_sauce2";
		data[2][2]="Epic sadface: Username and password do not match any user in this service";
		data[2][3]="verifictionErrMsg_XPATH";

		data[3][0]="standard_user3";
		data[3][1]="secret_sauce3";
		data[3][2]="Epic sadface: Username and password do not match any user in this service";
		data[3][3]="verifictionErrMsg_XPATH";

		return data;
	}
	
	
	
	
	
	
}
