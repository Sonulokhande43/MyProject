package fundModule;

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
import utilClasses.Util1;

public class VerifyUserCanAddFunds {

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

	@Test(priority = 2, dependsOnMethods = "VerifyUserCanLogin")
	public void VerifyUserCanOpenFundWindow() throws IOException {
		hp.clickOnFundText();
		Assert.assertTrue(fp.checkForFundPage());
	}

	@Test(priority = 3, dependsOnMethods = "VerifyUserCanOpenFundWindow")
	public void VerifyUserCanOpenDepositeFundWindow() throws IOException {
		fp.clickOnAddFund();
		Assert.assertTrue(fp.checkForDepositePage());
	}

	@Test(priority = 4, dependsOnMethods = "VerifyUserCanOpenDepositeFundWindow")
	public void VerifyUserCanFillDepositeDetailsAdnContinue() throws IOException {
		// String amountToDeposite = "10";
		// fp.addDepositeAmount(amountToDeposite);
		fp.addDepositeAmount();
		fp.enterUPI();
		fp.clickOnContinueBtn();

		Assert.assertTrue(fp.checkForConfirmationAmount());

		// Assert.assertEquals(fp.getDepositeConfirmationAmount(), amountToDeposite);
		Assert.assertEquals(fp.getDepositeConfirmationAmount(), Util1.getDataFromConfig("amount"));

	}

	@AfterMethod
	public void afterMethod() {

	}

	@AfterClass
	public void afterClass() {

	}

}
