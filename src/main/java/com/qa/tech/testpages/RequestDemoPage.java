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
	
	@FindBy(xpath = "//*[@id='mktoForm_1488']/div[23]/span/button")
	WebElement request_demo_submit;
	
	//Initializing the Page Objects
		public RequestDemoPage() {
		    PageFactory.initElements(driver, this);
		    }
		
//****************************** Scenario 9 **************************************//
//Leaving all the fields blank and clicking on submit button throws only one validation at a time
		public void validate_RequestDemoPage() {
			try{
				request_demo_link.click();
				request_demo_form.click();
			    request_demo_submit.click();
			 }
			catch(Error e) {
				e.getMessage();
			}
		}
}
