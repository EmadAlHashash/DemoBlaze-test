package MyTestCase;

import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Parameter {
	static WebDriver driver = new ChromeDriver();
	static String myWebsite = "https://www.demoblaze.com/";
	static String Username = "emadhashash2234sss";
	static String Password = "123456emad";
	static String Name = "emad";
	static String Country = "jordan";
	static String City = "amman";
	static String Creditcard = "123456";
	static String Month = "2";
	static String Year = "2025";
	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

	static void singUp() throws InterruptedException {
		WebElement UsernamesingUp = driver.findElement(By.id("signin2"));
		UsernamesingUp.click();
		WebElement UserNameSingUp = driver.findElement(By.id("sign-username"));
		WebElement PasswordSingUp = driver.findElement(By.cssSelector("#sign-password"));
		WebElement ButtonSingUp = driver.findElement(By.cssSelector("button[onclick='register()']"));
		Thread.sleep(1000);
		UserNameSingUp.sendKeys(Username);
		PasswordSingUp.sendKeys(Password);
		ButtonSingUp.click();
		Thread.sleep(1000);
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		Assert.assertTrue(alertText.contains("Sign up successful."), "FAILL");
		alert.accept();
	}

	static void login() throws InterruptedException {
		WebElement Login = driver.findElement(By.cssSelector("#login2"));
		Login.click();
		WebElement UsernameLogin = driver.findElement(By.cssSelector("#loginusername"));
		WebElement PasswordLogin = driver.findElement(By.cssSelector("#loginpassword"));
		Thread.sleep(1000);
		UsernameLogin.sendKeys(Username);
		PasswordLogin.sendKeys(Password);
		WebElement ButtonLogin = driver.findElement(By.cssSelector("button[onclick='logIn()']"));
		ButtonLogin.click();
		WebElement userDisplayName = driver.findElement(By.cssSelector("#nameofuser"));
		String actualDisplayedName = userDisplayName.getText();
		String extractedName = actualDisplayedName.replace(actualDisplayedName, Username);
		Assert.assertEquals(extractedName, Username, "Failed");
	}

	public void product(String ProName) throws InterruptedException {

		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h4[@class='card-title']/a")));

		List<WebElement> products = driver.findElements(By.xpath("//h4[@class='card-title']/a"));

		String targetProduct = ProName;

		for (WebElement product : products) {
			String productName = product.getText();
			System.out.println("Found product: " + productName);

			if (productName.equalsIgnoreCase(targetProduct)) {
				System.out.println("Clicking on: " + productName);
				product.click();
				break;
			}
		}

		WebElement addToCartBtn = wait
				.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']")));

		addToCartBtn.click();

		Thread.sleep(3000);
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		Assert.assertTrue(alertText.contains("Product added"), "FAILL");
		alert.accept();

	}

	public void CartAndCheckout() throws InterruptedException {

		WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Cart']")));
		cartButton.click();
		WebElement PlaceOrder = driver.findElement(By.cssSelector(".btn.btn-success"));
		PlaceOrder.click();
		Thread.sleep(2000);
		WebElement NameInput = driver.findElement(By.cssSelector("#name"));
		WebElement CountryInput = driver.findElement(By.cssSelector("#country"));
		WebElement CityInput = driver.findElement(By.cssSelector("#city"));
		WebElement CreditcardInput = driver.findElement(By.cssSelector("#card"));
		WebElement MonthInput = driver.findElement(By.cssSelector("#month"));
		WebElement YearInput = driver.findElement(By.cssSelector("#year"));
		NameInput.sendKeys(Name);
		CountryInput.sendKeys(Country);
		CityInput.sendKeys(City);
		CreditcardInput.sendKeys(Creditcard);
		MonthInput.sendKeys(Month);
		YearInput.sendKeys(Year);
		Thread.sleep(2000);
		WebElement Purchase = driver.findElement(By.xpath("//button[normalize-space()='Purchase']"));
		Purchase.click();
		WebElement thankYouMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), 'Thank you for your purchase!')]")));
		String details = thankYouMsg.getText();
		System.out.println(details);
		Thread.sleep(2000);
		WebElement OkModel = driver.findElement(By.xpath("//button[normalize-space()='OK']"));
		OkModel.click();
	}

	public void Categories() throws InterruptedException {
		Map<String, String> categoryProductMap = new HashMap<>();
		categoryProductMap.put("Phones", "Samsung galaxy s6");
		categoryProductMap.put("Laptops", "Sony vaio i5");
		categoryProductMap.put("Monitors", "Apple monitor 24");

		for (Map.Entry<String, String> entry : categoryProductMap.entrySet()) {
			String category = entry.getKey();
			String targetProduct = entry.getValue();
			System.out.println("Visiting category: " + category);
			WebElement categoryElement = wait.until(ExpectedConditions.elementToBeClickable(By.linkText(category)));
			categoryElement.click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".card-title a")));
			List<WebElement> products = driver.findElements(By.cssSelector(".card-title a"));

			boolean found = false;
			for (WebElement product : products) {
				String productName = product.getText();
				System.out.println("Found product: " + productName);

				if (productName.equalsIgnoreCase(targetProduct)) {
					System.out.println("Clicking on: " + productName);
					product.click();
					found = true;
					break;
				}
			}
			if (found) {
				WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add to cart']")));
				addToCartButton.click();
				wait.until(ExpectedConditions.alertIsPresent());
				driver.switchTo().alert().accept();
			} else {
				System.out.println("❌ Product not found in category: " + category);
			}
			WebElement homeLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='index.html']")));
			homeLink.click();
			System.out.println("Current URL: " + driver.getCurrentUrl());
		}
		
		CartAndCheckout();
	}
	
	public void Contact() throws InterruptedException {
		WebElement ContactClick = driver.findElement(By.xpath("//a[normalize-space()='Contact']"));
		ContactClick.click();
		WebElement contactEmail = driver.findElement(By.xpath("//input[@id='recipient-email']"));
		WebElement contactName = driver.findElement(By.xpath("//input[@id='recipient-name']"));
		WebElement contactText = driver.findElement(By.xpath("//textarea[@id='message-text']"));
		Thread.sleep(1000);
		contactEmail.sendKeys("emad@test.com");
		contactName.sendKeys("emadhashash");
		contactText.sendKeys("TEST TEST TEST ");
		WebElement SendMsg = driver.findElement(By.xpath("//button[normalize-space()='Send message']"));
		SendMsg.click();
		
		Thread.sleep(1000);
		Alert alert = driver.switchTo().alert();
		String alertText = alert.getText();
		Assert.assertTrue(alertText.contains("Thanks for the message!!"), "FAILL");
		alert.accept();
	}

}
