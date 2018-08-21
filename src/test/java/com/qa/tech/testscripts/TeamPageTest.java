package com.qa.tech.testscripts;

import java.net.URISyntaxException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.tech.testpages.HomePage;
import com.qa.tech.testpages.SocialPage;
import com.qa.tech.testpages.TeamPage;

public class TeamPageTest extends TeamPage{
	TeamPage teamPage;
	HomePage homePage;
	SocialPage socialPage;
	
	public TeamPageTest() {
	   super();
	}
	
	@BeforeMethod
	public void setUp() throws URISyntaxException {
		
		InitializeBrowser();
		homePage = new HomePage();
		homePage = homePage.SearchSite();
		teamPage = homePage.clickOnTeamPageLink();
		
	}
	
	@Test(priority=1)
	public void teamPageTitleUrlTest() {
		String actTeamPageUrl = teamPage.validateTeamPageUrl();
		Assert.assertEquals(actTeamPageUrl, prop.getProperty("expTeamPageUrl"), "Actual team page Url: "+actTeamPageUrl+" "
				+ "is not matched with expected: "+prop.getProperty("expTeamPageUrl"));
		String actTeamPageTitle = teamPage.validateTeamPageTitle();
		Assert.assertEquals(actTeamPageTitle, prop.getProperty("expteamPageTitle"));
	  }
	
	@Test(priority=2)
	public void teamPageDataTabTest() {
		teamPage.verifyTabData();
		teamPage.getEmployeesCount();
	}
	
	@Test(priority=3)
	public void teamPageDepartmentCountTest() {
		//teamPage.getEmployeesCount();
		//teamPage.allEmpoyeesCount();
		teamPage.validate_DepartmentsCount();
	  }
		
	@Test(priority=4)
	public void teamPageEmployeesWithEachTabTest() {
		teamPage.validate_EmployeesWithEachTab();
	   }
	
	@Test(priority=5)
	public void teamPageEmployeeNamesWithEachTabTest() {
		teamPage.validate_EmployeeNamesWithEachTab();
	   }
	
	@Test(priority=6)
	public void teamPageEmployeeDeptsWithEachTabTest() {
		teamPage.validate_EmployeeDeptsWithEachTab();
	   }
	
	@Test(priority=7)
	public void teamPageEmpSocialTwitterAccount_clickEventTest() throws URISyntaxException {
		teamPage.validate_EmpSocialAccountTwitter_clickEvent();
	   }
	
	@Test(priority=8)
	public void teamPageEmpSocialGithubAccount_clickEventTest() throws URISyntaxException {
		teamPage.validate_EmpSocialAccountgitHub_clickEvent();
	   }
	
	@AfterMethod
	public void tearDown() {
		driver.quit();
	}

}
