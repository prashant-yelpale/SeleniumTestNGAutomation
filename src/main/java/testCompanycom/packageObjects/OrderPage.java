package testCompanycom.packageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testCompanycom.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent {

	WebDriver driver;
	public OrderPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="li.totalRow button")
	WebElement checkOutElement;

	@FindBy(css=".ng-star-inserted td:nth-child(3)")
	List<WebElement> productNames;
	
	public Boolean VerifyOrderDisplay(String productname) {
		Boolean match =productNames.stream().anyMatch(carProduct->carProduct.getText().equalsIgnoreCase(productname));
	    return match;
	}
	
	
}
