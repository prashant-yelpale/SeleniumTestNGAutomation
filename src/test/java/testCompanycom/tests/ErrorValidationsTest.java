package testCompanycom.tests;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import org.testng.IRetryAnalyzer;
import org.testng.annotations.Test;


import testCompanycom.TestComponents.BaseTest;
import testCompanycom.TestComponents.Retry;
import testCompanycom.packageObjects.CartPage;
import testCompanycom.packageObjects.CheckOutPage;
import testCompanycom.packageObjects.LandingPage;
import testCompanycom.packageObjects.OrderConfirmationPage;
import testCompanycom.packageObjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

	@Test(groups={"ErrorHandling"},retryAnalyzer= Retry.class)
	public void loginErrorValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		landingPage.loginApplication("selautoframedesign@gmail.om", "Tet@143");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

	}
	
	@Test
	public void productErrorValidation() throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		String productName = "qwerty";
		ProductCatalogue productCatalogue = landingPage.loginApplication("selautoframedesign1@gmail.om", "Test@143");
		List<WebElement> productsList = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductsDisplay("qwerty1");
		Assert.assertFalse(match);
		
		

	}

}
