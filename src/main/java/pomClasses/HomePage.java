package pomClasses;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilClasses.Util1;

public class HomePage extends Util1 {

	WebDriver driver;

	@FindBy(xpath = "//span[text()='Dashboard']")
	private WebElement dashboardText;

	@FindBy(xpath = "//span[text()='Funds']")
	private WebElement fundText;

	@FindBy(xpath = "//input[contains(@placeholder,'Search')]")
	private WebElement stockSearchField;

	@FindBy(xpath = "//ul[@class='omnisearch-results']/div/li/span[@class='symbol']")
	private List<WebElement> searchStockList;

	@FindBy(xpath = "//button[contains(@data-balloon,'Add')]")
	private WebElement addToWatchlistBtn;

	@FindBy(xpath = "//div[contains(@class,'vddl-list')]//span[@class='nice-name']")
	private List<WebElement> verifingAddedStockInWatchlist;

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}

	public boolean checkForHomePage() throws IOException {
		try {
			explicitWait(driver, dashboardText, 20);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public void clickOnFundText() {
		fundText.click();
	}

	public void searchStocks() {
		// comes from excel sheet
		stockSearchField.sendKeys("Tata Motors");
	}

	public boolean getStocksListAndValidate() {
		for (WebElement name : searchStockList) {
			System.out.println(name.getText());
			if (name.getText().contains("TATAMOTORS") || name.getText().contains("TATAMTR")) {
			} else {
				return false;
			}
		}
		return true;
	}

	public String clickOnAddToWatchlistButton() {
		hoverToElement(driver, searchStockList.get(0));
		explicitWait(driver, addToWatchlistBtn, 5);
		hoverToElement(driver, addToWatchlistBtn);
		String atributeValue = addToWatchlistBtn.getAttribute("data-balloon");
		addToWatchlistBtn.click();
		return atributeValue;
	}

	public boolean getWatchListStock() {
		for (WebElement name : verifingAddedStockInWatchlist) {
			System.out.println(name.getText());
			if (name.getText().equalsIgnoreCase("TATAMOTORS")) {
				System.out.println("yes");
			} else {
				System.out.println("no");
				return false;
			}
		}
		return true;
	}
}
