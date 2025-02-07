package testCompanycom.packageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testCompanycom.AbstractComponents.AbstractComponent;


public class LandingPage extends AbstractComponent{
    WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userName = driver.findElement(By.id("userEmail"));
	//PageFactory
	
	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement password;
	
	@FindBy(xpath="//input[@id='login']")
	WebElement login;
	
	public ProductCatalogue loginApplication(String uname,String pword) {
		userName.sendKeys(uname);
		password.sendKeys(pword);
		login.click();
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
	
}
