package tst.shoestore.pages;

import java.util.Random;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import tst.shoestore.util.CommonConstants;

public class ShoeStoreLandingHomePage {

	/*
	 * This method returns the locator of the Home link of the Page
	 */
	private static By homeLink() {
		return By.xpath("//*[@id='home_link_div']/a");
	}

	/*
	 * This method returns the locator of remainder email input
	 */
	private static By emailRemainderInput() {
		return By.id("remind_email_input");
	}

	/*
	 * This method returns the locator of remainder email submit button
	 */
	private static By emailRemainderSubmitBtnk() {
		return By.id("remind_email_submit");
	}

	/*
	 * This method returns the locator of remainder email message
	 */
	private static By emailRemainderMsg() {
		return By.xpath(".//*[@id='flash']/div");
	}

	/*
	 * This method returns the xpath of remainder email message
	 */
	private static String emailRemainderXpath() {
		return ".//*[@id='flash']/div";
	}

	/*
	 * The Check method will check the email remainder functionality of the Shoe
	 * Store by entering various inputs.
	 * 
	 */
	public static String check(WebDriver driver) {

		if (driver == null) {
			throw new IllegalArgumentException("The driver has not been initialized");
		}

		if (!checkEmailArea(driver)) {
			return CommonConstants.EMAIL_REMAINDER_INPUT_AREA_NOTAVAILABLE;
		}

		WebDriverWait wait = new WebDriverWait(driver, 2);

		driver.findElement(emailRemainderSubmitBtnk()).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailRemainderMsg()));
		if (waitForPresence(wait, emailRemainderXpath())) {
			if (!driver.findElement(emailRemainderMsg()).getText().
					equalsIgnoreCase(CommonConstants.EMAIL_REMAINDER_ENTER_EMAIL_MSG)) {
				return CommonConstants.EMAIL_REMAINDER_ENTER_EMAIL_EMPTY_MSG;
			}
		} else {
			return CommonConstants.EMAIL_REMAINDER_ENTER_EMAIL_EMPTY_NOTAVAILABLE;
		}
		driver.findElement(homeLink()).click();
		waitForInvisibility(wait, emailRemainderXpath());
		if (!checkEmailArea(driver)) {
			return CommonConstants.EMAIL_REMAINDER_INPUT_AREA_NOTAVAILABLE;
		}
		driver.findElement(emailRemainderInput()).sendKeys(generateInvalidEmail(10));
		driver.findElement(emailRemainderSubmitBtnk()).click();
		if (waitForPresence(wait, emailRemainderXpath())) {
			if (!driver.findElement(emailRemainderMsg()).getText()
					.equalsIgnoreCase(CommonConstants.EMAIL_REMAINDER_INVALIDINPUT_STRING)) {
				return CommonConstants.EMAIL_REMAINDER_INVALIDINPUT_NOT_CORRECT_MSG;
			}
		} else {
			return CommonConstants.EMAIL_REMAINDER_INVALIDINPUT_NOTAVAILABLE;
		}

		driver.findElement(homeLink()).click();
		waitForInvisibility(wait, emailRemainderXpath());
		if (!checkEmailArea(driver)) {
			return CommonConstants.EMAIL_REMAINDER_INPUT_AREA_NOTAVAILABLE;
		}
		String emailAddress = generateEmail(10);
		driver.findElement(emailRemainderInput()).sendKeys(emailAddress);
		driver.findElement(emailRemainderSubmitBtnk()).click();
		wait.until(ExpectedConditions.visibilityOfElementLocated(emailRemainderMsg()));
		System.out.println(driver.findElement(emailRemainderMsg()).getText());
		if (!driver.findElement(emailRemainderMsg()).getText()
				.equalsIgnoreCase("Thanks! We will notify you of our new shoes at this email: " + emailAddress)) {
			return CommonConstants.EMAIL_REMAINDER_VALIDINPUT_MSG_NOTCORRECT;
		}
		return CommonConstants.EMAIL_REMAINDER_SUCCESS_MSG;

	}

	/*
	 * This method checks the if the email remainder is available and has enough
	 * area will return false if the field not available or area is not enough
	 * else will return true
	 */
	private static boolean checkEmailArea(WebDriver driver) {
		if (driver.findElements(emailRemainderInput()).size() > 0) {
			Dimension dimen = driver.findElements(emailRemainderInput()).get(0).getSize();
			if (dimen.height < 10 || dimen.width < 35) {
				return false;
			}
			return true;
		} else {
			return false;
		}
	}

	/*
	 * This method will Explicitly wait for wait(sec) for the presence of the
	 * element provided provided by xpath locator
	 */
	public static boolean waitForPresence(WebDriverWait wait, String xPath) {
		try {
			wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xPath)));
			return true;
		} catch (TimeoutException ex) {
			return false;
			// Capturing the exception
		}
	}

	public static String generateEmail(int maxLength) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz1234567890_-";
		String alphabets = "abcdefghijklmnopqrstuvwxyz";
		String email = "";
		String temp = RandomStringUtils.random(maxLength, allowedChars);
		int domainLength = new Random().nextInt(10) + 2;
		String domain = RandomStringUtils.random(domainLength, alphabets);
		int countryLength = new Random().nextInt(5) + 3;
		String country = RandomStringUtils.random(countryLength, alphabets);
		email = temp + "@" + domain + "." + country;
		return email;
	}

	public static String generateInvalidEmail(int maxLength) {
		String allowedChars = "abcdefghijklmnopqrstuvwxyz1234567890_-.";
		int length = new Random().nextInt(maxLength) + 2;
		String email = RandomStringUtils.random(length, allowedChars);
		System.out.println(email);
		return email;
	}

	/*
	 * This method will Explicitly wait for wait(sec) for the invisibility of
	 * the element provided provided by xpath locator
	 */
	public static boolean waitForInvisibility(WebDriverWait wait, String xPath) {
		try {
			wait.until(ExpectedConditions.invisibilityOfElementLocated((By.xpath(xPath))));
			return true;
		} catch (TimeoutException ex) {
			// The TimeoutException the is generated for non availability of the
			// element is supressed
			return false;
		}

	}

}
