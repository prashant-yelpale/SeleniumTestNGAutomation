package testCompanycom.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import testCompanycom.TestComponents.BaseTest;
import testCompanycom.packageObjects.CartPage;
import testCompanycom.packageObjects.CheckOutPage;
import testCompanycom.packageObjects.OrderConfirmationPage;
import testCompanycom.packageObjects.OrderPage;
import testCompanycom.packageObjects.ProductCatalogue;

public class StandAloneTest2 extends BaseTest {

	String productName = "qwerty";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> productsList = productCatalogue.getProductsList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductsDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkOutPage = cartPage.goToCheckOut();
		checkOutPage.selectCountry("India");
		OrderConfirmationPage orderConfirmation = checkOutPage.submitOrder();
		String confirmMessage = orderConfirmation.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = "submitOrder")
	public void OrderHistoryTest() throws InterruptedException, IOException {
		// TODO Auto-generated method stub

		// "qwerty";
		ProductCatalogue productCatalogue = landingPage.loginApplication("selautoframedesign@gmail.om", "Test@143");
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));

	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//testCompanycom//data//PurchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}
	/*
	 * @DataProvider public Object[][] getData() {
	 * 
	 * return new Object[][] {{"selautoframedesign@gmail.om","Test@143","qwerty"},{
	 * "selautoframedesign1@gmail.om","Test@143","IPHONE 13 PRO"}};
	 * 
	 * 
	 * @DataProvider public Object[][] getData() { HashMap<String, String> map = new
	 * HashMap<String, String>(); map.put("email", "selautoframedesign@gmail.om");
	 * map.put("password", "Test@143"); map.put("product", "QWERTY");
	 * 
	 * HashMap<String, String> map1 = new HashMap<String, String>();
	 * map1.put("email", "selautoframedesign1@gmail.om"); map1.put("password",
	 * "Test@143"); map1.put("product", "IPHONE 13 PRO");
	 * 
	 * return new Object[][] {{map},{map1}}; }}
	 */

	
}
