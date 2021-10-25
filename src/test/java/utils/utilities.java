package utils;

import org.openqa.selenium.By;
import org.testng.Assert;

import testCases.BaseTest;

public class utilities extends BaseTest{

	public static String elementGetTitle()
	{
		return driver.getTitle();
	}

	public static void verifyWindowTitle(String expWindowTitle, String actWindowTitle)
	{
		System.out.println("expWindowTitle:" + expWindowTitle);
		System.out.println("actWindowTitle:" + actWindowTitle);
		
		Assert.assertTrue(actWindowTitle.equals(expWindowTitle), "ACtual Window Title: " + actWindowTitle + " does not match with expected window Title:" + expWindowTitle);
	}
	
	public static void verifyEquals(String expMsg, String pLocator)
	{
		String actMsg = utils.utilities.elementGetText(pLocator);
		Assert.assertTrue(actMsg.equals(expMsg), "Exp Msg: " + expMsg + " did NOT match with actMsg: " + actMsg);
	}
	
	public static String elementGetText(String pLocator)
	{
		String txt=null;
		String locator = propOR.getProperty(pLocator);
		if (pLocator.contains("_ID"))
			txt = driver.findElement(By.id(locator)).getText();
		else if (pLocator.contains("_XPATH"))
			txt = driver.findElement(By.xpath(locator)).getText();
		else if (pLocator.contains("_CSS"))
			txt = driver.findElement(By.cssSelector(locator)).getText();
		return txt;
	}

	
	public static void elementClick(String pLocator)
	{
		String locator = propOR.getProperty(pLocator);
		if (pLocator.contains("_ID"))
			driver.findElement(By.id(locator)).click();
		else if (pLocator.contains("_XPATH"))
			driver.findElement(By.xpath(locator)).click();
		else if (pLocator.contains("_CSS"))
			driver.findElement(By.cssSelector(locator)).click();
	}

	public static void elementType(String pLocator, String pData)
	{
		String locator = propOR.getProperty(pLocator);
		System.out.println("locator: " + pLocator);

		if (pLocator.contains("_ID"))
			driver.findElement(By.id(locator)).sendKeys(pData);
		else if (pLocator.contains("_XPATH"))
			driver.findElement(By.xpath(locator)).sendKeys(pData);
		else if (pLocator.contains("_CSS"))
			driver.findElement(By.cssSelector(locator)).sendKeys(pData);
	}

	
	
	
	
	
	
	
	
	
}
