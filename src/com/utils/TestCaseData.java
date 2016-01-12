package com.utils;

import java.io.FileInputStream;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.Capabilities;


public class TestCaseData {
	
	private static TestCaseData instance= null;
	
	private static Properties prop = new Properties();
	
	public static TestCaseData getInstance() {
		if(instance == null){
			instance = new TestCaseData();
		}
		return instance;
	}
	
	//load the properties file containing test steps
	public static void loadProperties(String fileName){
		try {
			prop.load(new FileInputStream("resources/properties/"+ fileName));
		} catch(Exception e){
			e.printStackTrace();
		}
	}
	
	//instantiate bean 
	public TestResultBean getBean(WebDriver driver,String userName, String password,String testId) {
		TestResultBean bean = new TestResultBean();
		Capabilities cap = ((RemoteWebDriver)driver).getCapabilities();
		loadProperties("testCaseInfo.properties");
		bean.setTestId(testId);
		bean.setAction(prop.getProperty(testId));
		bean.setUserName(userName);
		bean.setPassword(password);
		bean.setResult("Pass");
		bean.setBrowser(cap.getBrowserName()+" - "+cap.getVersion());
		return bean;
	}

}
