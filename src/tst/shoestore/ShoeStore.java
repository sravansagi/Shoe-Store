package tst.shoestore;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;

import tst.shoestore.pages.ShoeStoreLandingHomePage;
import tst.shoestore.pages.ShoeStoreMonthLandingPage;
import tst.shoestore.util.CommonConstants;

public class ShoeStore {

	WebDriver driver;
	/**
	 * This test checks new releases of shoes in all months pages. The Acceptance criteria 
	 * for the test is for each new release shoe the following criteria have to be met.
	 * 1) Month should display small Blurb(Description of the shoe)
	 * 2) Month should display an image for each shoe being displayed
	 * 3) Each shoe should have a suggested price pricing
	 */
	@Test(priority = 0)
	public void monthNewReleaseDisplay() {
		String check;
		try{
			check = ShoeStoreMonthLandingPage.check(driver);
		}catch(IllegalArgumentException ex){
			check = ex.getMessage();
		}
		Assert.assertEquals(check, CommonConstants.ALL_SHOE_RELEASE_SUCCESS_MSG);
	}


	/**
	 * This test checks email remainder for the upcoming shoes. The Acceptance criteria 
	 * 1) There should be an area to submit email address
	 * 2) on successful submission of a valid email address user should receive a message 
	 * "Thanks! We will notify you of our new shoes at this email: users email address"
	 */
	@Test(priority = 1)
	public void submitReminderEmail() {
		String check;
		try{
			check = ShoeStoreLandingHomePage.check(driver);
		}catch(IllegalArgumentException ex){
			check = ex.getMessage();
		}
		Assert.assertEquals(check, CommonConstants.EMAIL_REMAINDER_SUCCESS_MSG);
	}



	/**This method takes the browser type from testNG and open the corresponding browser for automation
	 * By default if "firefox" or "chrome" is not provided then firefox is taken by default
	 * 
	 * @param browser
	 */
	@BeforeMethod
	@Parameters("browser")
	public void beforeMethod(String browser) {
		if("firefox".equalsIgnoreCase(browser)){
			System.setProperty("webdriver.gecko.driver", CommonConstants.GECKO_DRIVER_PATH);
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
		}else if("chrome".equalsIgnoreCase(browser)){
			System.setProperty("webdriver.chrome.driver", CommonConstants.CHROME_DRIVER_PATH);
			driver = new ChromeDriver();
		} else{
			System.setProperty("webdriver.gecko.driver", CommonConstants.GECKO_DRIVER_PATH);
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("marionette", true);
			driver = new FirefoxDriver(capabilities);
		}
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(CommonConstants.STORE_URL);
	}



	@AfterMethod
	public void afterMethod() {
		if (driver != null) {
			driver.quit();
		}
	}
}
