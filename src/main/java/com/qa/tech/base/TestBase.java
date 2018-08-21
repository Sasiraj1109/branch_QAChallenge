package com.qa.tech.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.qa.tech.util.TestUtil;

public class TestBase {
	
	public static WebDriver driver = null;
	public static Properties prop = null;
	

	//Create a constructor
	public TestBase() {
		try {
			prop = new Properties();
			InputStream input = this.getClass().getClassLoader().getResourceAsStream("global.properties");
			prop.load(input);
		 }catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void InitializeBrowser() {
		String browserName = prop.getProperty("browser");
		System.out.println(browserName);
		if(browserName.equals("chrome")) {
			driver = new ChromeDriver();	
			//System.setProperty("webdriver.gecko.driver", "C://Softwares//Drivers/geckodriver.exe");
		}else if (browserName.equals("firefox")){
			
			//System.setProperty("webdriver.gecko.driver", "C://Softwares//Drivers/geckodriver.exe");
			driver = new FirefoxDriver();
		}
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("googleURL"));
			
		//driver.navigate().to(prop.getProperty("googleURL"));
	
		
	}
}
