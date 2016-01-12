package com.pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

//Page object class for Login screen
public class LoginScreen {
	
//	private static WebElement element = null;
	
	//Method to get the element for username
	public static WebElement userName(WebDriver driver){
		return driver.findElement(By.name("userName"));
	}
 
	
	//Method to get the element for Password
	public static WebElement password(WebDriver driver){
		return driver.findElement(By.name("password"));
	}
	
	//Method to get the element for login button
	public static WebElement loginButton(WebDriver driver){
		return driver.findElement(By.name("login"));
	}
}
