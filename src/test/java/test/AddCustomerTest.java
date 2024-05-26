package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import page.AddCustomerPage;
import page.DashboardPage;
import page.LoginPage;
import util.BrowserFactory;
import util.ExcelReader;

public class AddCustomerTest {
	
	WebDriver driver;
	
	ExcelReader exlread = new ExcelReader("testData\\TF_TestData.xlsx");
	String userName = exlread.getCellData("LoginInfo", "UserName", 2);
	String password = exlread.getCellData("LoginInfo", "Password", 2);
	String expectedDashboadHeaderText = exlread.getCellData("DashboardInfo", "DashboardHeader", 2);
	String expectedAddCustomerHeaderText = exlread.getCellData("AddContactInfo", "AddContactValidationText", 2);
	String fullName = exlread.getCellData("AddContactInfo", "CompanyName", 2);
	String email = exlread.getCellData("AddContactInfo", "Email", 2);
	String company = exlread.getCellData("AddContactInfo", "CompanyName", 2);
	String phone = exlread.getCellData("AddContactInfo", "Phone", 2);
	String country = exlread.getCellData("AddContactInfo", "Country", 2);
	
	@Test
	public void userShouldBeAbleToCreateNewCustomer() {
		
		driver = BrowserFactory.init();
		LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);
		loginPage.insertUserName(userName);
		loginPage.insertPassword(password);
		loginPage.clickSigninButton();
		
		DashboardPage dashboardPage = PageFactory.initElements(driver, DashboardPage.class);
		Assert.assertEquals(dashboardPage.getDashboardValidaitonText(), expectedDashboadHeaderText, "Dashboard page not available!!");
		dashboardPage.clickOnCustomer();
		dashboardPage.clickOnAddCustomer();
		
		AddCustomerPage addCustomerPage = PageFactory.initElements(driver, AddCustomerPage.class);
		Assert.assertEquals(addCustomerPage.getNewCustomerValidaitonText(), expectedAddCustomerHeaderText, "Add Customer page not found!!");
		
		addCustomerPage.insertFullName(fullName);
		addCustomerPage.selectCompany(company);
		addCustomerPage.insertEmail(email);
		addCustomerPage.selectCountry(country);
		System.out.println("===========" + phone);
		
		
	}

}
