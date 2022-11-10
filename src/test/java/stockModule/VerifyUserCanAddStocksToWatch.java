package stockModule;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import baseClasses.Base1;
import pomClasses.FundPage;
import pomClasses.HomePage;
import pomClasses.LoginPage;

public class VerifyUserCanAddStocksToWatch {

	WebDriver driver;

	LoginPage lp;
	HomePage hp;
	FundPage fp;

	@BeforeClass
	public void beforeClass() {
		driver = Base1.getDriver();

	}

	@BeforeMethod
	public void beforeMethod() {
		lp = new LoginPage(driver);
		hp = new HomePage(driver);
		fp = new FundPage(driver);

	}

	@Test(priority = 1)
	public void VerifyUserCanLogin() throws IOException {

		lp.enterUserName();
		lp.enterPassword();
		lp.clickOnLoginBtn();

		Assert.assertTrue(hp.checkForHomePage());
	}

	@Test(priority = 2)
	public void verifyUserCanSearchProductAndGetList() {

		hp.searchStocks();
		Assert.assertTrue(hp.getStocksListAndValidate());
	}

	@Test(priority = 3)
	public void verifyUserCanAddStock() {
		hp.clickOnAddToWatchlistButton();
	}

	@Test(priority = 4)
	public void verifysearchStockAddedInWatchlist() {
	//	Assert.assertEquals(hp.clickOnAddToWatchlistButton(), "Added");
		Assert.assertTrue(hp.getWatchListStock());
	}

	@AfterMethod
	public void afterMethod() {
	}

	@AfterClass
	public void afterClass() {
	}

}
