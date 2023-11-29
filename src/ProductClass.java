import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ProductClass {
	
	 String myUrl = "https://www.saucedemo.com/";
	 String UserName = "standard_user";
	 String Passwoard = "secret_sauce";
	 
	 WebDriver driver = new ChromeDriver();

		@BeforeTest
		public void SetUPtest() {

			driver.get(myUrl);
			WebElement UserNameElemnt = driver.findElement(By.id("user-name"));
			WebElement PasswordElemnt = driver.findElement(By.id("password"));
			UserNameElemnt.sendKeys(UserName);
			PasswordElemnt.sendKeys(Passwoard);
			driver.findElement(By.id("login-button")).click();
			driver.manage().window().maximize();
		}

		@Test
		public void Test() {
			List<WebElement> AddToCartBTN = driver.findElements(By.className("btn"));
			List<WebElement> productsName = driver.findElements(By.className("inventory_item_name"));
			List<WebElement> ItemsPrices= driver.findElements(By.className("inventory_item_price"));

			for (int i = 0; i < AddToCartBTN.size(); i++) {
				if (productsName.get(i).getText().contains("Labs Bolt") || productsName.get(i).getText().contains("Onesie")
						|| productsName.get(i).getText().contains("Labs Fleece")) {
					System.out.println(productsName.get(i).getText() + " Not added");

					continue;
				} else {
					AddToCartBTN.get(i).click();
					
					System.out.println("the Old price without Tax for "+ productsName.get(i).getText()+" is " +ItemsPrices.get(i).getText());
					String Price = ItemsPrices.get(i).getText().replace("$", "");
					double PriceToDouble = Double.parseDouble(Price);
					double Tax = 0.10;
					double NetPrice = PriceToDouble * Tax + PriceToDouble;
					if (NetPrice % 2 == 0) {
						System.out.println("the final price is even and the value of this price: " + NetPrice);
					} else {
						System.out.println("the final price is an odd and the value of this price: " + NetPrice);
					}
				}

			}
		}

		@AfterTest
		public void MyAfterTest() {

		}
	 

}
