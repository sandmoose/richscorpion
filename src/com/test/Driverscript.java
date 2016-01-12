package com.test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.utils.BrowserUtilities;
import com.utils.ExcelHandler;
import com.utils.TestResultBean;

public class Driverscript extends BrowserUtilities {
	
public static WebDriver driver;
public static String testName;
public static List<TestResultBean> resultList = new ArrayList<TestResultBean>();
public static Map<String, List<TestResultBean>> resultMap = new HashMap<String, List<TestResultBean>>();

	@BeforeClass
	public static void setup(){
		//code for setting things before every methods/test run
	}
	
	//configuration to execute before every test method
	@BeforeMethod
	@Parameters ({"browser"})
	public void init(Method method,String browser){
		System.out.println("Executing :: "+ method.getName());
		driver = BrowserUtilities.getBrowser(browser);
	}
	
	//store the test result in a Map
	public static void createMap(List<TestResultBean> resultList){
		if(resultMap.containsKey(testName)){
			resultList.addAll(resultMap.get(testName));
		} 
		resultMap.put(testName, resultList);
	}
	
	//test to login
	@Test
	public void sampleTest() throws Exception{
		testName = "TC-01";
		Drivingscript drScript = new Drivingscript();
		resultList.addAll(drScript.main(driver, testName));
		createMap(resultList);
	}
	
	//configuration to execute after every test method
	@AfterMethod
	public void tearDown(Method method){
		System.out.println("Completed Executing :: "+method.getName());
		driver.close();
	}
	
	//configuration to execute once per test after all the tests
	@AfterTest
	public void finish(){
		driver.quit();
	}
	
	//execute after all the test
	@AfterClass
	public static void completeReporting(){
		//Code to write results to Excel
		ExcelHandler.writeToOutputSheet(resultMap);
	}
}
