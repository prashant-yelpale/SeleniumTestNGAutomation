package testCompanycom.packageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import testCompanycom.AbstractComponents.AbstractComponent;


public class ProductCatalogue extends AbstractComponent{
    WebDriver driver;
	
	public ProductCatalogue(WebDriver driver) {
		// TODO Auto-generated constructor stub
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory


	@FindBy(css="div.col-lg-4")
	List<WebElement> productsList;
	
	@FindBy(className="ng-animating")
	WebElement spinner; 
	
    By Products = By.cssSelector("div.col-lg-4");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector(".toast-message");
	
	public List<WebElement> getProductsList()
	{
		waitForElementToAppear(Products);
		return productsList;
	}
	
	public WebElement getProductByName(String productname) {
		WebElement itemtoAdd=getProductsList().stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productname)).findFirst().orElse(null);
		
		return itemtoAdd;
	}
	
	public void addProductToCart(String productname) throws InterruptedException {
		WebElement itemtoAdd=getProductByName(productname);
		itemtoAdd.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDisappear(spinner);
	}
	
	
}
