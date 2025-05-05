package MyTestCase;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Task extends Parameter {
	private static boolean hasFailed = false;

	@BeforeTest
	public void PreTesting() {
		System.out.println("Launching Chrome browser...");
		driver.get(myWebsite);
		driver.manage().window().maximize();
	}

	@BeforeMethod
	public void checkIfPreviousFailed() {
		if (hasFailed) {
			System.out.println("A previous test failed. Skipping this test.");
			throw new SkipException("Skipping because a previous test failed.");
		}
	}

	@Test(priority = 1)
	public void singUpTest() throws InterruptedException {
		System.out.println("Running Sign Up Test...");
		try {
			singUp();
		} catch (Exception e) {
			hasFailed = true;
			System.out.println("Sign Up Test failed!");
			throw e;
		}
	}

	@Test(priority = 2)
	public void loginTest() throws InterruptedException {
		System.out.println("Running Login Test...");
		try {
			login();
		} catch (Exception e) {
			hasFailed = true;
			System.out.println("Login Test failed!");
			throw e;
		}
	}

	@Test(priority = 3)
	public void contactTest() throws InterruptedException {
		System.out.println("Running Contact Test...");
		try {
			Contact();
		} catch (Exception e) {
			hasFailed = true;
			System.out.println("Contact Test failed!");
			throw e;
		}
	}

	@Test(priority = 4)
	public void productTest() throws InterruptedException {
		System.out.println("Running Product Test...");
		try {
			product("Samsung galaxy s6");
		} catch (Exception e) {
			hasFailed = true;
			System.out.println("Product Test failed!");
			throw e;
		}
	}

	@Test(priority = 5)
	public void cartAndCheckoutTest() throws InterruptedException {
		System.out.println("Running Cart and Checkout Test...");
		try {
			CartAndCheckout();
		} catch (Exception e) {
			hasFailed = true;
			System.out.println("Cart and Checkout Test failed!");
			throw e;
		}
	}

	@Test(priority = 6)
	public void categoriesTest() throws InterruptedException {
		System.out.println("Running Categories Test...");
		try {
			Categories();
		} catch (Exception e) {
			hasFailed = true;
			System.out.println("Categories Test failed!");
			throw e;
		}
	}

	@AfterMethod
	public void waitBetweenTests() throws InterruptedException {
		System.out.println("Waiting for 3 seconds before next test...");
		Thread.sleep(3000);
	}

	@AfterTest
	public void PostTesting() {
		driver.quit();
	}
}
