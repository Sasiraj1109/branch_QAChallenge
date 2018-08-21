package com.qa.tech.testscripts;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.tech.base.TestBase;
import com.qa.tech.testpages.HomePage;
import com.qa.tech.testpages.TeamPage;

public class HomePageTest extends TestBase {
	HomePage homePage;
	TeamPage teamPage;
	
	public HomePageTest() {
		super();
	}
	
	@BeforeMethod
	public void setUp() {
		InitializeBrowser();
		homePage = new HomePage();
		teamPage = new TeamPage();
	}
	
	@Test(priority=1)
	public void searchNavigateToHomePageTest() {
	    homePage.SearchSite();
	    String homeUrl = homePage.validateHomePageUrl();
	    Assert.assertEquals(homeUrl, prop.getProperty("homePageUrl"));
	    String title = homePage.validateHomePageTitle();
	    Assert.assertEquals(title, prop.get("homePagetitle"));
	    boolean homeLogo = homePage.validateHomeLogo();
	    Assert.assertTrue(homeLogo);
	}
	
	@Test(priority=2)
	public void clickOnTeamLinkTest() {
		homePage.SearchSite();
		teamPage = homePage.clickOnTeamPageLink();
	}
	
		
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
