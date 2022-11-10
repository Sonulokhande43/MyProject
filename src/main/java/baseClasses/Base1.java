package baseClasses;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Base1 {

	// Initialization of driver
	// Extend Report
	//
	public static WebDriver getDriver() {

		// Options of the chrome driver
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--disable-notifications");

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver(options);

		driver.get("https://kite.zerodha.com/");

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		return driver;

	}

}
