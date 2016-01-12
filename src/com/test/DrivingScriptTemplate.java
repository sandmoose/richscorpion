package com.test;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import com.utils.BaseClass;
import com.utils.ConstantUtilities;
import com.utils.TestResultBean;

//Template for driving script
public class DrivingScriptTemplate  extends BaseClass {
	
	public String fileName= ConstantUtilities.LOGIN_INPUT;
	public String sheetName = "sampleData";
	
	public DrivingScriptTemplate() throws Exception {
		sa = new SoftAssert();
		setUpData();
	}
	
	public List<TestResultBean> main(WebDriver driver,String testname){
		
		sa.assertAll();
		return beanList;
	}

	@Override
	public void setUpData() throws Exception {
		inputSheet = readInput(fileName, sheetName);
		username = getCellString("B2");
		password = getCellString("B3");
	}

}
