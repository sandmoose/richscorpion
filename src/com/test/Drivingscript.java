package com.test;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;
import org.testng.asserts.SoftAssert;

import com.pageObjects.LoginScreen;
import com.utils.BaseClass;
import com.utils.ConstantUtilities;
import com.utils.TestResultBean;

public class Drivingscript extends BaseClass {
	
	public String fileName= ConstantUtilities.LOGIN_INPUT;
	public String sheetName = "sampleData";
	
	public Drivingscript() throws Exception {
		//create soft assert object
		sa = new SoftAssert();
		setUpData();
	}
	
	public List<TestResultBean> main(WebDriver driver, String testName){
		try {
			testId = testName +".01";
			bean = getBean(driver, username, password, testId);
			driver.get(getCellString("B4"));
			sa.assertTrue(isElementPresent(LoginScreen.userName(driver)));
			driver.findElement(By.linkText(getCellString("B6"))).click();
			pageLoadTimeout(driver, 3);
			sendKeys(LoginScreen.userName(driver), getCellString("B2"),true);
			sendKeys(LoginScreen.password(driver), getCellString("B3"),true);
			click(LoginScreen.loginButton(driver));
			pageLoadTimeout(driver, 3);
			sa.assertTrue(driver.getTitle().equals(getCellString("B5")));
			Reporter.log("Assertion Passed");
			driver.findElement(By.linkText(getCellString("B7"))).click();
		} catch(Exception e){
			bean.setResult("Fail");
			bean.setException(e.toString());
			e.printStackTrace();
		} 
		beanList.add(bean);

		//mark all assertion failures in previous steps
		sa.assertAll();	
		return beanList;
	}

	//read input sheet and set password and username
	@Override
	public void setUpData() throws Exception {
		inputSheet = readInput(fileName, sheetName);
		username = getCellString("B2");
		password = getCellString("B3");
	}
}
