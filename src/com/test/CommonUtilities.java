package com.test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.utils.ExcelHandler;

public class CommonUtilities extends ExcelHandler {
	
	//create screen shots
	public static void getSceenshot(WebDriver driver, String testId, String stepNo) throws Exception {
		File dir = new File(testId);
		 if(!dir.exists()){
			 dir.mkdir();
		 }
		 File srcfile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		 FileUtils.copyFile(srcfile,new File("resources/screenshots/"+testId+"/"+stepNo+".png"));
	}
	
	//type in input field
	//accepts element, text to type, clearing the field before typing true/false 
	public static void sendKeys(WebElement element, String text, boolean clear){
		if(clear){
			element.clear();
		}
			element.sendKeys(text);
	}
	
	//clear input field
	public static void clear(WebElement element){
		element.clear();
	}
	
	//get element text
	public static String getText(WebElement element){
		return element.getText();
	}
	
	
	//click on element
	public static void click(WebElement element){
		element.click();
	}
	
	//check if element is present by size
	public static boolean isElementPresent(WebElement element){
		try {
			if(element.getSize() != null )
				return true;
			else
				return false;
		} catch (NoSuchElementException e){
			return false;
		}
	}
	
	//check Element present by By
	public static boolean isElementPresent(WebDriver driver,By by){
		try {
			driver.findElement(by); 
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	//wait for page to load
	public static void pageLoadTimeout(WebDriver driver,int seconds){
		driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
	}
}
