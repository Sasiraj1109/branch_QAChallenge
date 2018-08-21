package com.qa.tech.testpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.qa.tech.base.TestBase;

public class RequestDemoPage extends TestBase {

	
	@FindBy(xpath = "//div/ul/li/a[contains(text(),'Request a demo')]")
	WebElement request_demo_link;
	
	@FindBy(xpath = "//div[@class='form-container']")
	WebElement request_demo_form;
	
	//Initializing the Page Objects
		public RequestDemoPage() {
		    PageFactory.initElements(driver, this);
		    }
		
		//scenario 8.1
		public void validate_RequestDemoPage() {
			request_demo_link.click();
			String pageTitle = driver.getTitle();
			
			
		}
	
}
