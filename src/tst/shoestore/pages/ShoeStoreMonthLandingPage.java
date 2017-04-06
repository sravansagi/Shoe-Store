package tst.shoestore.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import tst.shoestore.util.*;

public class ShoeStoreMonthLandingPage {

	/*This method returns the xpath of the links in the header tag 
	 */
	private static By headerLinksXpath(){
		return By.xpath("//*[@id='header_nav']//a");
	}

	/* This method returns the xpath of the Home link of the Page
	 */
	private static By homeLinkXpath(){
		return By.xpath("//*[@id='home_link_div']/a");
	}

	/* This method returns the xpath of the corresponding months heading  
	 */
	private static By monthHeading(){
		return By.xpath("//div[@class='title']/h2");
	}

	/* This method returns the locator of the brands drop down
	 */
	private static By brandsDropDown(){
		return By.id("brand");
	}

	/* This method returns the locator of the shoes  results in corresponding month
	 */
	private static By shoeResults(){
		return By.xpath(("//*[@class='shoe_result']"));
	}

	/* This method returns the locator of the description of shoe in shoe results
	 */
	private static By descriptionLocator(){
		return By.xpath(".//*[contains(text(), 'Description')]//following-sibling::*");
	}

	/* This method returns the locator of the price of shoe in shoe results
	 */
	private static By priceLocator(){
		return By.xpath(".//*[contains(text(), 'Price')]//following-sibling::*");
	}


	/* This method returns the locator of the Image of shoe in shoe results
	 */
	private static By imgLocator(){
		return By.xpath(".//*[@class='shoe_image']/img");
	}



	/* This method clicks on each of the month and checks if all the products has a small description,
	 * valid price and image
	 */
	public static String check(WebDriver driver){

		if(driver == null){
			throw new IllegalArgumentException("The driver has not been initialized");
		}

		List<WebElement> aWebElementList = driver.findElements(headerLinksXpath());
		if(aWebElementList.size() != 13){
			return CommonConstants.INVALID_MONTH_LINKS_NUMBER_MSG;
		}
		else{
			WebElement webElement;
			/* The loop is iterated for all the 12 months
			 */			
			for(int i=0;i<12;i++){
				webElement = driver.findElements(headerLinksXpath()).get(i);
				if(CommonConstants.monthsDescription[i].equals(webElement.getText())){
					String month = webElement.getText();
					webElement.click();
					WebDriverWait wait = new WebDriverWait(driver, 10);
					wait.until(ExpectedConditions.visibilityOfElementLocated(monthHeading()));
					(wait).until(new ExpectedCondition<Boolean>() {
						public Boolean apply(WebDriver d) {
							return driver.findElement(monthHeading())
									.getText().equalsIgnoreCase(month + "'s Shoes");
						}
					});
					String returnValue =  checkProductContents(driver, month);
					if(!CommonConstants.ALL_SHOE_CRITERIA_MET_MSG.equalsIgnoreCase(returnValue)){
						return returnValue;
					}
					driver.findElement(homeLinkXpath()).click();
					wait.until(ExpectedConditions.visibilityOfElementLocated(brandsDropDown()));							
				}							
				else{
					return "The link description is not as expected. The expected value is " + CommonConstants.monthsDescription[i] +
							" actual value is "+ webElement.getText();
				}

			}
		}
		return CommonConstants.ALL_SHOE_RELEASE_SUCCESS_MSG;
	}

	private static String checkProductContents(WebDriver driver2, String month) {
		List<WebElement> divShoeClass = driver2.findElements(shoeResults());
		for (int i = 0; i < divShoeClass.size(); i++) {
			List<WebElement> divShoeDesp = divShoeClass.get(i)
					.findElements(descriptionLocator());
			if(!divShoeDesp.isEmpty()){
				String description = divShoeClass.get(i)
						.findElement(descriptionLocator()).getText();
				System.out.println("Description" + description);
				if(description.isEmpty()){
					return "The description for " + (i+1) +" shoe item in "+ month + "'s product is empty";
				}
			}
			else{
				return "There is no description element for " + (i+1) +" shoe item in "+ month + "'s products";
			}

			List<WebElement> divShoePrice = divShoeClass.get(i)
					.findElements(priceLocator());
			if(!divShoePrice.isEmpty()){
				String price = divShoeClass.get(i)
						.findElement(priceLocator()).getText();
				System.out.println("Price" + price);
				/*
				 * The following regex is used to check for valid price of the product in dollers
				 */
				if(price.trim().isEmpty() || !price.trim().matches("\\$\\d+,{0,1}\\d*.\\d{2}")){
					return "There is no valid price for " + (i+1) +" shoe item in "+ month + "'s product is empty";
				}
			}
			else{
				return "There is no price element for " + (i+1) +" shoe item in "+ month + "'s products";
			}

			List<WebElement> divShoeImage = divShoeClass.get(i)
					.findElements(imgLocator());
			if(!divShoeImage.isEmpty()){
				String image = divShoeClass.get(i)
						.findElement(imgLocator()).getAttribute("src");
				System.out.println("Image" + image);
				if(image.isEmpty()){
					return "There is no image for " + (i+1) +" shoe item in "+ month + "'s products";
				}
			}
			else{
				return "There is no image element for " + (i+1) +" shoe item in "+ month + "'s products";
			}
		}
		return CommonConstants.ALL_SHOE_CRITERIA_MET_MSG;
	}

}
