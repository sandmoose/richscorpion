package com.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.test.CommonUtilities;

public abstract class BaseClass extends CommonUtilities{
	
	protected HSSFSheet inputSheet;
	
	protected List<TestResultBean> beanList = new ArrayList<TestResultBean>();
	
	protected TestResultBean bean = null;
	
	protected String username;
	
	protected String password;
	
	protected String testId;
	
	protected SoftAssert sa;
	
	public abstract void setUpData() throws Exception; 
	
	//instantiate bean for test step
	public TestResultBean getBean(WebDriver driver, String userName, String passWord, String testName){
		
		return TestCaseData.getInstance().getBean(driver, userName, passWord, testName);
		
	}
	
	//read input sheet for 
	public HSSFSheet readInput(String fileName, String sheetName){
		try {
			
			inputSheet = ExcelHandler.readInputSheet(fileName, sheetName);
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		return inputSheet;
	}
}
