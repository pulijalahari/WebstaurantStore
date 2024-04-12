package com.qa.store.tests;

import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.testng.annotations.BeforeClass;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;

public class WebstaurantStoreTest {
	WebDriver driver = null;
	WebDriverWait wait = null;
	
	@BeforeClass(alwaysRun=true)
	public void beforeClass() {
		Reporter.log("Launching the chromebrowser", true);
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();

		Reporter.log("maximize the window", true);
		driver.manage().window().maximize();
		Reporter.log("add implicitwait", true);
		driver.manage().timeouts().implicitlyWait(Duration.ofMillis(10000));
		Reporter.log("add explicitwait object", true);
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		Reporter.log("creating Object for WebDriverUtils class", true);
		Reporter.log("Open the application url-https://www.webstaurantstore.com/", true);
		driver.get("https://www.webstaurantstore.com/");
		Reporter.log("Wait and verify for the webstaurant store title", true);
		wait.until(ExpectedConditions.titleContains("WebstaurantStore: Restaurant Supplies & Foodservice Equipment"));
		Assert.assertEquals("WebstaurantStore: Restaurant Supplies & Foodservice Equipment", driver.getTitle());
	}



  @Test
  public void SearchItemAddEmptyCartTest() {
	  Reporter.log("Test case - SearchItemAddEmptyCartTest started", true);
	 Reporter.log("2.Search for 'stainless work table'.", true);
	// Search for 'stainless work table'
     WebElement searchBox = driver.findElement(By.id("searchval"));
     searchBox.sendKeys("stainless work table");
     searchBox.submit();
     Reporter.log("wait for the page title:Stainless Work Table - WebstaurantStore", true);
     wait.until(ExpectedConditions.titleIs("Stainless Work Table - WebstaurantStore"));
     Reporter.log("wait and assert the search results header shows stainless work table",true);
     wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("h1.page-header.search--title")));
	 String searchResultHeaderTxt=driver.findElement(By.cssSelector("h1.page-header.search--title")).getText();
     Assert.assertTrue(searchResultHeaderTxt.contains("stainless work table"), "stainless work table is not present in search results header");
     
     Reporter.log("3.Check the search result ensuring every product has the word 'Table' in its title.", true);
     boolean allProductsHaveTableInTitle = driver.findElements(By.cssSelector("#product_listing>div>div:nth-child(1)>a:nth-child(1)>span")).stream()
             .allMatch(element -> element.getText().toLowerCase().contains("table"));
     if (!allProductsHaveTableInTitle) {
         throw new RuntimeException("Not all products have 'Table' in their title.");
     }
	 Reporter.log("4.Add the last of found items to Cart", true);
	 WebElement lastProduct = driver.findElement(By.cssSelector("#product_listing>div:last-child>div:nth-child(1)>a:nth-child(1)>span"));
     lastProduct.click();
     Reporter.log("wait for the last product details page header", true);
     wait.until(ExpectedConditions.presenceOfElementLocated(By.id("page-header-description")));
     Reporter.log("click on Add To cart button", true);
     driver.findElement(By.cssSelector("#buyButton")).click();
     Reporter.log("checking the success message after adding the product to the cart", true);
     wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#subject > div.success-container > div > div")));
     Reporter.log("click on View Cart button", true);
     driver.findElement(By.cssSelector("#subject > div.success-container > div > a.btn.btn-primary")).click();
     Reporter.log("wait for the Cart header in Viewcart page", true);
     wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[data-hypernova-key='ViewCart']>div>h1")));
    
     Reporter.log("verifying the cart has item",true);
     wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.cartItems>div:nth-child(2)>ul>li:nth-child(2)")));
     Reporter.log("5.Empty Cart.", true);
     Reporter.log("click on empty cart button", true);
     driver.findElement(By.cssSelector("#main > div.cart-recommended > div > div.cartItemsHeader.toolbar.clears > div > button")).click();
     Reporter.log("verify the modal cart dialog title -empty", true);
     wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#empty-cart-title")));
     Reporter.log("click on Empty Cart Button in modal popup", true);
     driver.findElement(By.cssSelector("footer[data-testid=modal-footer] > button:nth-of-type(1)")).click();
  
     Reporter.log("wait and verify the cart empty text",true);
     wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#main > div > div.cartEmpty > div.box.padded.box--empty > div > div.empty-cart__text > p.header-1")));
     boolean isCartEmpty=driver.findElement(By.cssSelector("#main > div > div.cartEmpty > div.box.padded.box--empty > div > div.empty-cart__text > p.header-1")).isDisplayed();
     Assert.assertTrue(isCartEmpty,"Cart is not empty");
     
     Reporter.log("Test case - SearchItemAddEmptyCartTest completed", true);
  }
     

	@AfterClass(alwaysRun=true)
	public void afterClass() {
		Reporter.log("started executing the @AfterClass", true);
		Reporter.log("close the browser", true);
		if (driver != null) {
			driver.quit();
		}
	}

}
