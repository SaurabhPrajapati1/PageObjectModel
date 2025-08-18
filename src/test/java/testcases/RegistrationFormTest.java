package testcases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.RegistrationPage;
import pages.SuccessfulMessagePage;

@Listeners(utils.TestListener.class) // Register the TestListener for reporting
public class RegistrationFormTest {

	private WebDriver driver;
	private HomePage homePage;
	private RegistrationPage registrationPage;
	private SuccessfulMessagePage validationPage;

	@BeforeMethod
	public void setUp(ITestContext context) {
		// Set up Chrome in headless mode
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless"); // Run in headless mode
		options.addArguments("--disable-gpu"); // Recommended for Windows
		options.addArguments("--window-size=1366,768"); // Emulate full screen
		options.addArguments("--remote-allow-origins=*"); // Prevent CORS issues (ChromeDriver 111+)

		driver = new ChromeDriver(options);
		// driver.manage().window().maximize(); // Do not maximize in headless mode
		driver.get("https://vinothqaacademy.com/");

		// Share WebDriver with listeners
		context.setAttribute("WebDriver", driver);

		// Initialize Page Objects
		homePage = new HomePage(driver);
		registrationPage = new RegistrationPage(driver);
		validationPage = new SuccessfulMessagePage(driver);
	}

	@Test
	public void navigateToRegistrationFormTest() {
		homePage.navigateToRegistrationForm();
		registrationPage.enterFirstName("Saurabh");
		registrationPage.enterLastName("Prajapati");
		registrationPage.clickMaleRadioBtn();
		registrationPage.checkSeleniumWebDriverCheckBox();
		registrationPage.unCheckDevOpsCheckBox();
		registrationPage.enterStreetAddress("123 Main St");
		registrationPage.enterAptSuite("Apt 456");
		registrationPage.enterCity("Ghaziabad");
		registrationPage.enterState("Uttar Pradesh");
		registrationPage.enterPostalCode("201302");
		registrationPage.selectCountry("India");
		registrationPage.enterEmail("Saurabh.abc@gmail.com");
		registrationPage.enterDateOfDemo("07/03/2023");
		registrationPage.selectConvenientTimeHH("10");
		registrationPage.selectConvenientTimeMM("30");
		registrationPage.enterPhoneNumber("9878654321");
		registrationPage.enterYourQuery("What is the course fees?");
		registrationPage.enterVerificationNumber();
		registrationPage.clickSubmitButton();
		validationPage.validateSuccessfulTextMessage();
	}

	@AfterMethod
	public void tearDown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
