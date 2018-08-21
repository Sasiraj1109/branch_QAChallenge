package com.qa.tech.testpages;

import static org.testng.Assert.fail;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Listeners;

import com.qa.tech.base.TestBase;
import com.qa.tech.extentreportlistener.TestEventListener;
import com.qa.tech.util.HelperUtil;

@Listeners({TestEventListener.class})
public class TeamPage extends TestBase{

	//Object Repository
	@FindBy(xpath = "//div[@class='row row-centered']/div[@style='display: inline-block;'][contains(@class,'category-all')]")
	List<WebElement> allTeam;
	@FindBy(xpath = "//ul[@class='team-categories']/li")
	List<WebElement> tabs;
	@FindBy(xpath= "//div[@class='row row-centered']/div[@style='display: inline-block;'][contains(@class,'category-all')]/div/div/div[@class='info-block']/a[@class='profile-link']")
	List<WebElement> empSocialLink;
	@FindBy(xpath = "//div[@class='row row-centered']/div[@style='display: inline-block;'][contains(@class,'category-data')]")
	List<WebElement> data_team;
	@FindBy(xpath = "//div[@class='row row-centered']/div[@style='display: inline-block;'][contains(@class,'category-engineering')]")
	List<WebElement> engg_team;
	@FindBy(xpath = "//div[@class='row row-centered']/div[@style='display: inline-block;'][contains(@class,'category-product')]")
	List<WebElement> prod_team;
	@FindBy(xpath = "//div[@class='row row-centered']/div[@style='display: inline-block;'][contains(@class,'category-operations')]")
	List<WebElement> ops_team;
	@FindBy(xpath = "//div[@class='row row-centered']/div[@style='display: inline-block;'][contains(@class,'category-recruiting')]")
	List<WebElement> recruiting_team;
	@FindBy(xpath = "//div[@class='row row-centered']/div[@style='display: inline-block;'][contains(@class,'category-partner-growth')]")
	List<WebElement> partner_growth_team;
	@FindBy(xpath = "//div[@class='row row-centered']/div[@style='display: inline-block;'][contains(@class,'category-marketing')]")
	List<WebElement> marketing_team;
		
	@FindBy(xpath = "//a[@rel='all']")
	WebElement tabAll;
	@FindBy(xpath = "//a[@rel='data']")
	WebElement tabData;
	@FindBy(xpath = "//a[@rel='engineering']")
	WebElement tabEngg;
	@FindBy(xpath = "//a[@rel='marketing']")
	WebElement tabMarketing;
	@FindBy(xpath = "//a[@rel='operations']")
	WebElement tabOps;
	@FindBy(xpath = "//a[@rel='partner-growth']")
	WebElement tabGrowth;
	@FindBy(xpath = "//a[@rel='product']")
	WebElement tabProduct;
	@FindBy(xpath = "//a[@rel='recruiting']")
	WebElement tabRecruit;
	
	//@FindBy(xpath = "//div[@class='info-block']/h2")
	
	
	@FindBy(xpath = "//div[@class='info-block']")
	List<WebElement> empCount;
	@FindBy(xpath = "//div[@class='info-block']/h4")
	List<WebElement> deptsCount;
	
	// Initializing the Page Objects
	public TeamPage() {
		PageFactory.initElements(driver, this);
	}

	public String validateTeamPageUrl() {
		return driver.getCurrentUrl();
	}

	public String validateTeamPageTitle() {
		return driver.getTitle();
	}

	public void verifyTabAllDisplayed() {
		tabAll.isDisplayed();
		// Assert.assertTrue(tabAll.isSelected(), "Tab All is not selected");
		tabAll.click();
	}

	public void verifyTabData() {
		tabData.isDisplayed();
		tabData.isSelected();
		tabData.click();
	}

	public void allEmpoyeesCount() {
		System.out.println(allTeam.size());
	}

	public void getEmployeesCount() {
		Map<String, Set<String>> dept = new HashMap<String, Set<String>>();
		Map<String, Integer> deptEmpCount = new HashMap<String, Integer>();
		Set<String> empAdded = new HashSet<String>();
		for (int i = 0; i < allTeam.size(); i++) {
			HashSet<String> emp = new HashSet<String>();
			String[] emp_dept = allTeam.get(i).getText().split("\\n");
			String empName = emp_dept[0].toLowerCase();
			String deptName = emp_dept[1].split("/").length > 1 ? (emp_dept[1].split("/"))[1].trim().toLowerCase()
					: emp_dept[1].toLowerCase();
			deptName = deptName.equals("coo") ? "partner growth" : deptName;
			deptName = deptName.equals("ceo") ? "engineering" : deptName;

			if (!empAdded.contains(empName) && !dept.containsKey(deptName)) {
				emp.add(empName);
				empAdded.add(empName);
			} else {
				continue;
			}

			for (int j = i + 1; j < allTeam.size(); j++) {
				emp_dept = allTeam.get(j).getText().split("\\n");
				String deptName_temp = emp_dept[1].split("/").length > 1
						? (emp_dept[1].split("/"))[1].trim().toLowerCase()
						: emp_dept[1].toLowerCase();
				deptName_temp = deptName_temp.equals("coo") ? "partner growth" : deptName_temp;
				deptName_temp = deptName_temp.equals("ceo") ? "engineering" : deptName_temp;
				if (!empAdded.contains(emp_dept[0].toLowerCase()) && deptName.equals(deptName_temp.toLowerCase())) {
					emp.add(emp_dept[0].toLowerCase());
					empAdded.add(emp_dept[0].toLowerCase());
				} else {
					continue;
				}
			}
			if (!dept.containsKey(deptName)) {
				dept.put(deptName, emp);
				deptEmpCount.put(deptName, emp.size());
			}
		}
		System.out.println("Department Size: " + dept.size());
		Iterator it = deptEmpCount.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			System.out.println("Dept Name: " + pair.getKey() + " = " + "Emp Count: " + pair.getValue());

		}

	}

	// Scenario 4
	public void validate_DepartmentsCount() {
		HashSet<String> depts = new HashSet<String>();
		for (int i = 0; i < allTeam.size(); i++) {
			String[] emp_dept = allTeam.get(i).getText().split("\\n");
			String deptName = emp_dept[1].split("/").length > 1 ? (emp_dept[1].split("/"))[1].trim().toLowerCase()
					: emp_dept[1].toLowerCase();
			deptName = deptName.equals("coo") ? "partner growth" : deptName;
			deptName = deptName.equals("ceo") ? "engineering" : deptName;
			depts.add(deptName);
		}
		System.out.println("Unique Department Count: " + depts.size());
		System.out.println("Unique Dept List: " + depts);
		Assert.assertEquals(depts.size() + 1, tabs.size());
		for (WebElement tab : tabs) {
			if (!depts.contains(tab.getText().trim().toLowerCase()) && !tab.getText().trim().toLowerCase().equals("all")) {
				fail("Tab does not match with unique department tabs. Expected: Tabs match with All tab Department. Actual: Dept '"+tab.getText().trim()+"' doesnt exists in all tab");
				break;
			}
		}
		Assert.assertTrue(true, "Tab matched with unique department tabs");
	}

	// scenario 5

	public void validate_EmployeesWithEachTab() {
		tabAll.click();
		int all_Emp_Count = allTeam.size();
		int tab_team_count = 0;
		tabData.click();
		tab_team_count = tab_team_count + data_team.size();
		tabEngg.click();
		tab_team_count = tab_team_count + engg_team.size();
		tabMarketing.click();
		tab_team_count = tab_team_count + marketing_team.size();
		tabOps.click();
		tab_team_count = tab_team_count + ops_team.size();
		tabGrowth.click();
		tab_team_count = tab_team_count + partner_growth_team.size();
		tabProduct.click();
		tab_team_count = tab_team_count + prod_team.size();
		tabRecruit.click();
		tab_team_count = tab_team_count + recruiting_team.size();
		Assert.assertEquals(tab_team_count, all_Emp_Count, "Team count does not match with total count. Expected:"+all_Emp_Count+".Actual:"+tab_team_count);
	}

	// scenario 6

	public void validate_EmployeeNamesWithEachTab() {
		tabAll.click();
		List<String> all_employees_names = new ArrayList<String>();
		all_employees_names = allTeam.stream().map(x -> (x.getText().split("\\n")[0]).toLowerCase())
				.collect(Collectors.toList());
		System.out.println(all_employees_names.size());
		List<String> tab_employees_names = new ArrayList<String>();
		tabData.click();
		data_team.stream().map(x -> (x.getText().split("\\n")[0]).toLowerCase())
				.forEachOrdered(tab_employees_names::add);
		System.out.println(tab_employees_names.size());
		System.out.println("data_team" + data_team.size());
		tabEngg.click();
		engg_team.stream().map(x -> (x.getText().split("\\n")[0]).toLowerCase())
				.forEachOrdered(tab_employees_names::add);
		System.out.println(tab_employees_names.size());
		System.out.println("engg" + engg_team.size());
		tabMarketing.click();
		marketing_team.stream().map(x -> (x.getText().split("\\n")[0]).toLowerCase())
				.forEachOrdered(tab_employees_names::add);
		System.out.println(tab_employees_names.size());
		System.out.println("marketing_team" + marketing_team.size());
		tabOps.click();
		ops_team.stream().map(x -> (x.getText().split("\\n")[0]).toLowerCase())
				.forEachOrdered(tab_employees_names::add);
		System.out.println(tab_employees_names.size());
		System.out.println("ops_team" + ops_team.size());
		tabGrowth.click();
		partner_growth_team.stream().map(x -> (x.getText().split("\\n")[0]).toLowerCase())
				.forEachOrdered(tab_employees_names::add);
		System.out.println(tab_employees_names.size());
		System.out.println("partner_growth_team" + partner_growth_team.size());
		tabProduct.click();
		prod_team.stream().map(x -> (x.getText().split("\\n")[0]).toLowerCase())
				.forEachOrdered(tab_employees_names::add);
		System.out.println(tab_employees_names.size());
		System.out.println("prod_team" + prod_team.size());
		tabRecruit.click();
		recruiting_team.stream().map(x -> (x.getText().split("\\n")[0]).toLowerCase())
				.forEachOrdered(tab_employees_names::add);
		System.out.println(tab_employees_names.size());
		System.out.println("recruiting_team" + recruiting_team.size());
		Collection<String> aMinusB = CollectionUtils.subtract(all_employees_names, tab_employees_names);
		System.out.println(aMinusB);
		Assert.assertTrue(HelperUtil.compareList(tab_employees_names, all_employees_names),
				"Dept Employees names does not match with all employee names. Expected:"+all_employees_names.size()+". Actual:"+tab_employees_names.size());

	}

	// scenario 7

	public void validate_EmployeeDeptsWithEachTab() {
		tabAll.click();
		List<String> all_employees_names = new ArrayList<String>();
		List<String[]> all_employees = new ArrayList<String[]>();
		List<String> temp_Names = new ArrayList<String>();
		all_employees_names = allTeam.stream().map(x -> (x.getText().split("\\n")[0]).toLowerCase()).collect(Collectors.toList());
		all_employees = allTeam.stream().map(x -> new String[] {x.getText().split("\\n")[0].trim().toLowerCase(),x.getText().split("\\n")[1].trim().toLowerCase()})
		.collect(Collectors.toList());
		System.out.println("all_employees_names" + all_employees_names.size());

		Map<String, Set<String>> dept = new HashMap<String, Set<String>>();
		Map<String, Integer> deptEmpCount = new HashMap<String, Integer>();
		Set<String> empAdded = new HashSet<String>();
		for (int i = 0; i < allTeam.size(); i++) {
			HashSet<String> emp = new HashSet<String>();
			String[] emp_dept = allTeam.get(i).getText().split("\\n");
			String empName = emp_dept[0].toLowerCase();
			String deptName = emp_dept[1].split("/").length > 1 ? (emp_dept[1].split("/"))[1].trim().toLowerCase()
					: emp_dept[1].toLowerCase();
			deptName = deptName.equals("coo") ? "partner growth" : deptName;
			deptName = deptName.equals("ceo") ? "engineering" : deptName;

			if (!empAdded.contains(empName) && !dept.containsKey(deptName)) {
				emp.add(empName);
				empAdded.add(empName);
			} else {
				continue;
			}

			for (int j = i + 1; j < allTeam.size(); j++) {
				emp_dept = allTeam.get(j).getText().split("\\n");
				String deptName_temp = emp_dept[1].split("/").length > 1
						? (emp_dept[1].split("/"))[1].trim().toLowerCase()
						: emp_dept[1].toLowerCase();
				deptName_temp = deptName_temp.equals("coo") ? "partner growth" : deptName_temp;
				deptName_temp = deptName_temp.equals("ceo") ? "engineering" : deptName_temp;
				if (!empAdded.contains(emp_dept[0].toLowerCase()) && deptName.equals(deptName_temp.toLowerCase())) {
					emp.add(emp_dept[0].toLowerCase());
					empAdded.add(emp_dept[0].toLowerCase());
				} else {
					continue;
				}
			}
			if (!dept.containsKey(deptName)) {
				dept.put(deptName, emp);
				deptEmpCount.put(deptName, emp.size());
			}
		}

		List<String> tab_employees_names = new ArrayList<String>();
		tabData.click();
		tab_employees_names = data_team.stream().map(x -> (x.getText().split("\\n")[0]).toLowerCase())
				.collect(Collectors.toList());
		System.out.println("data_team" + tab_employees_names.size());
		// System.out.println("All data Team"+all_data_team_names.size());
		Assert.assertEquals(tab_employees_names.size(), (dept.get(tabData.getText().trim().toLowerCase())).size(),
				"Data dept Employee names does not match with all tab data department employee names. Expected:"+tab_employees_names.size()+". Actual:"+(dept.get(tabData.getText().trim().toLowerCase())).size());

		tabEngg.click();
		tab_employees_names = engg_team.stream().map(x -> (x.getText().split("\\n")[0]).toLowerCase())
				.collect(Collectors.toList());
		System.out.println("engg_team" + tab_employees_names.size());
		Assert.assertEquals(tab_employees_names.size(), (dept.get(tabEngg.getText().trim().toLowerCase())).size(),
				"Engg dept Employee names does not match with all tab Engg department employee names. Expected:"+tab_employees_names.size()+". Actual:"+(dept.get(tabEngg.getText().trim().toLowerCase())).size());

		tabMarketing.click();
		tab_employees_names = marketing_team.stream().map(x -> (x.getText().split("\\n")[0]).toLowerCase())
				.collect(Collectors.toList());
		Assert.assertEquals(tab_employees_names.size(), (dept.get(tabMarketing.getText().trim().toLowerCase())).size(),
				"Marketing dept Employee names does not match with all tab Marketing department employee names. Expected:"+tab_employees_names.size()+". Actual:"+(dept.get(tabMarketing.getText().trim().toLowerCase())).size());

		tabOps.click();
		tab_employees_names = ops_team.stream().map(x -> (x.getText().split("\\n")[0]).toLowerCase())
				.collect(Collectors.toList());
		Assert.assertEquals(tab_employees_names.size(), (dept.get(tabOps.getText().trim().toLowerCase())).size(),
				"Ops dept Employee names does not match with all tab Ops department employee names. Expected:"+tab_employees_names.size()+". Actual:"+(dept.get(tabOps.getText().trim().toLowerCase())).size());

		tabGrowth.click();
		tab_employees_names = partner_growth_team.stream().map(x -> (x.getText().split("\\n")[0]).toLowerCase())
				.collect(Collectors.toList());
		Assert.assertEquals(tab_employees_names.size(), (dept.get(tabGrowth.getText().trim().toLowerCase())).size(),
				"Growth dept Employee names does not match with all tab Growth department employee names. Expected:"+tab_employees_names.size()+". Actual:"+(dept.get(tabGrowth.getText().trim().toLowerCase())).size());

		tabProduct.click();
		tab_employees_names = prod_team.stream().map(x -> (x.getText().split("\\n")[0]).toLowerCase())
				.collect(Collectors.toList());
		Assert.assertEquals(tab_employees_names.size(), (dept.get(tabProduct.getText().trim().toLowerCase())).size(),
				"Product dept Employee names does not match with all tab Product department employee names. Expected:"+tab_employees_names.size()+". Actual:"+(dept.get(tabProduct.getText().trim().toLowerCase())).size());

		tabRecruit.click();
		tab_employees_names = recruiting_team.stream().map(x -> (x.getText().split("\\n")[0]).toLowerCase())
				.collect(Collectors.toList());
		Assert.assertEquals(tab_employees_names.size(), (dept.get(tabRecruit.getText().trim().toLowerCase())).size(),
				"Recruiting dept Employee names does not match with all tab Recruiting department employee names. Expected:"+tab_employees_names.size()+". Actual:"+(dept.get(tabRecruit.getText().trim().toLowerCase())).size());

	}
	
	//Scenario 8
	public void validate_EmpSocialAccount_clickEvent(){
		tabAll.click();
		System.out.println(empSocialLink.size());
		for(int i=0; i<empSocialLink.size(); i++) {
			List<WebElement> links= empSocialLink.get(i).findElements(By.xpath("//img"));
			if(links.stream().anyMatch(x->x.getAttribute("src").contains("twitter")))
			{
				empSocialLink.get(i).click();
			}
			if(links.stream().anyMatch(x->x.getAttribute("src").contains("github")))
			{
				empSocialLink.get(i).click();
			}
		
		}
		
		
	}
	

}