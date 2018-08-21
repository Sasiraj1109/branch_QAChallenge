package com.qa.tech.util;

import java.io.BufferedWriter;
import java.util.logging.Level;

import org.apache.log4j.Logger;

import com.qa.tech.base.TestBase;
import com.qa.tech.extentreportlistener.ExtentReporterNG;


public class TestUtil extends TestBase{
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 10;
	String ScreenshotName="";
	public TestBase testBase;
	public BufferedWriter writer=null;
	Logger logger = Logger.getLogger(TestBase.class.getName());
	ExtentReporterNG report=new ExtentReporterNG();
	

	public void switchToFrame() {
	    driver.switchTo().frame(0);
	}
	
	public void logMessage(String strDescr,String strExpected,String Actual,String status)
	{
		try{
			
			report.logMessage(driver,writer,strDescr,strExpected,Actual,status);
		}
		catch(Exception e){
			logger.log(status, null, e, e);
		}
		
	}
	
}
