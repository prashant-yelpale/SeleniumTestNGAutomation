package testCompanycom.packageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testCompanycom.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent {

	WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="li.totalRow button")
	WebElement checkOutElement;

	@FindBy(css="div.cartSection h3")
	List<WebElement> cartsProductsTitle;
	
	public Boolean VerifyProductsDisplay(String productname) {
		Boolean match =cartsProductsTitle.stream().anyMatch(carProduct->carProduct.getText().equalsIgnoreCase(productname));
	    return match;
	}
	
	public CheckOutPage goToCheckOut() {
		checkOutElement.click();
		return new CheckOutPage(driver);
	}
}
