package com.qa.tech.testpages;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.qa.tech.base.TestBase;

public class HomePage extends TestBase {

	//Object Repository
	@FindBy(name = "q")
	WebElement googleSearchBox;
	@FindBy(xpath = "resultStats")
	WebElement SearchResults;
	@FindBy(id = "logo")
	WebElement homeLogo;
	@FindBy(xpath = "//*[@id='rso']//h3/a")
	List<WebElement> listElements;
	
	//Home Page Main links
	@FindBy(xpath = "//a[contains(text(),'Pricing')]")
	WebElement pricingLink;
	@FindBy(xpath = "//a[contains(text(),'Request a demo')]")
	WebElement requestDemoLink;
	
	//Team Page link
	@FindBy(xpath = "//ul[@class='bds-accordian']//li/a")
	List<WebElement> footerLinks;
	@FindBy(xpath = "//a[@class='c-button']")
	WebElement cookieButton;
	
	
	//Initializing the Page Objects
	public HomePage() {
	    PageFactory.initElements(driver, this);
	    }
	
	//************************** Scenario 1 & 2 **********************************************//
	public HomePage SearchSite() {
		googleSearchBox.sendKeys(prop.getProperty("searchTerm"));
		googleSearchBox.submit();
		// wait until the google page shows the result
        WebElement SearchResultsWait = new WebDriverWait(driver,10).until(ExpectedConditions.presenceOfElementLocated(By.id("resultStats")));
        System.out.println("Number of search results found: "+listElements.size());
        System.out.println("Matched links list:");
        for(int i=0; i<listElements.size(); i++) {
        	System.out.println(listElements.get(i).getAttribute("href"));
        	if(listElements.get(i).getAttribute("href").contains(prop.getProperty("homePageUrl"))) {
        		listElements.get(i).click();
        		break;
        	}
          }
          return new HomePage();
	}
        
	
	public String validateHomePageUrl() {
		return driver.getCurrentUrl();
	    }
	
	public String validateHomePageTitle() {
		return driver.getTitle();
	    }
	
	public boolean validateHomeLogo() {
		return homeLogo.isDisplayed();
	    }
	
	
	
	public RequestDemoPage clickOnRequestDemoLink() {
		requestDemoLink.isDisplayed();
		requestDemoLink.click();
	    return new RequestDemoPage();
	    }
	
	//********************************* scenario 2 *******************************************//
	public TeamPage clickOnTeamPageLink() {
		System.out.println("Number of footer links found: "+footerLinks.size());
		System.out.println("Matched list:");
		for(int i=0; i<footerLinks.size(); i++) {
			System.out.println(footerLinks.get(i).getText());
			if(footerLinks.get(i).getText().contains("Team")) {
				//Alert alert = driver.switchTo().i
				if (cookieButton.isDisplayed()) {
					cookieButton.click();
				}
				    footerLinks.get(i).click();
				    break;
			    }
			}  
		
	  	   return new TeamPage();
	}
}
