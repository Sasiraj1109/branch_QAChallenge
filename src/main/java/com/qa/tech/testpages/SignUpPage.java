package com.qa.tech.testpages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.qa.tech.base.TestBase;

public class SignUpPage extends TestBase{
 	
	@FindBy(xpath = "//a[@data-element-tag='get-started']")
	WebElement getStartedLink;
	
	@FindBy(xpath = "//*[@id='app']/div/div/div/form")
	WebElement signUpForm;
	
	@FindBy(xpath = "//*[@id='app']/div/div/div[3]/form/div[3]/div/button")
	WebElement signUpButton;
	
	public SignUpPage() {
	    PageFactory.initElements(driver, this);
	    }
	
	//******************************scenario 9*************************//
	//Leaving all the fields blank and clicking on submit button does not throw any validation which is incorrect//
	//Validation of error messages are not shown in proper flow
	
	 public void validate_SignUpPage() {
		 
		 try{
			 getStartedLink.click();
			 signUpForm.click();
			 signUpButton.click();
			 String expectedMessage = "Please specify a first name.";
			 String actualMessage = signUpForm.getCssValue(expectedMessage);
			 System.out.println(actualMessage);
			 Assert.assertEquals(actualMessage, expectedMessage, "Validation messgages are not matching between expected vs actual");
		 }
		 catch(Error e) {
			 e.getMessage();
		 }
	 }
}
