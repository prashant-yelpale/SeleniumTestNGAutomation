package testCompanycom.packageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import testCompanycom.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
     
	 @FindBy(xpath="//input[@placeholder='Select Country']")
	 WebElement country;
	 
	 @FindBy(xpath="//button[contains(@class,'ta-item')][2]")
	 WebElement selectCountry;
	 
	 @FindBy(css=".action__submit")
	 WebElement submit;
	 
	 By autosuggestionList = By.cssSelector(".ta-results");
	
	public void selectCountry(String countryName) {
		Actions action = new Actions(driver);
        action.sendKeys(country, countryName).build().perform();
        waitForElementToAppear(autosuggestionList);
        selectCountry.click();
	}
	
	public OrderConfirmationPage submitOrder() {
		submit.click();
		return new OrderConfirmationPage(driver);
	}
}
