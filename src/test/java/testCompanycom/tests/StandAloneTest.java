package testCompanycom.tests;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import testCompanycom.packageObjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//CI-CD Connection Check
		String productName = "qwerty";

		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		LandingPage landingPage = new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("selautoframedesign@gmail.om");
		driver.findElement(By.id("userPassword")).sendKeys("Test@143");
		driver.findElement(By.xpath("//input[@id='login']")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.col-lg-4")));
		
		List<WebElement> productsList = driver.findElements(By.cssSelector("div.col-lg-4"));
		
		//I used stream to filter and match the product
	 WebElement itemtoAdd=productsList.stream().filter(product->
		product.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)).findFirst().orElse(null);
		
	 itemtoAdd.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	  
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".toast-message")));
	 wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.className("ng-animating"))));
        driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
        
        
        List<WebElement> cartsProducts =  driver.findElements(By.cssSelector("div.cartSection h3"));
        Boolean match =cartsProducts.stream().anyMatch(carProduct->carProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        
        driver.findElement(By.cssSelector("li.totalRow button")).click();
        
       // driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("Ind");
        
      /*  List<WebElement> searchList = driver.findElements(By.cssSelector(".ta-item"));
       WebElement searchedAutoSuggestion= searchList.stream().filter(search->
        search.findElement(By.cssSelector(".ta-item span")).getText().equalsIgnoreCase("India")).findFirst().orElse(null);
      
      searchedAutoSuggestion.findElement(By.cssSelector("span")).click();*/
        Actions action = new Actions(driver);
        action.sendKeys(driver.findElement(By.xpath("//input[@placeholder='Select Country']")), "india").build().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        
        driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
        
      driver.findElement(By.cssSelector(".action__submit")).click();
      String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
      Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
      
      driver.close();
       
		}

}
