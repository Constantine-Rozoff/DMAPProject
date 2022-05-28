import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginPageTest {

    private WebDriver driver;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://qa.dmap.cloud/auth/login");
        loginPage = new LoginPage(driver);
    }

    //Lesson #71

    @Test
    public void loginWithProperCredsTest () {
        DashboardPage dashboardPage = new DashboardPage(driver);
        loginPage.signIntoTheSystem("danielle", "monsterPass123!");
        String heading = dashboardPage.getHeading();
        assertTrue(driver.findElement(By.xpath("//div[@class='dashboard-notifications-container']/h2")).isDisplayed());
        //assertEquals("Notifications", heading);
    }

    @Test
    @Ignore
    public void loginWithCamelcaseUsernameTest () {
        DashboardPage dashboardPage = new DashboardPage(driver);
        loginPage.signIntoTheSystem("Danielle", "monsterPass123!");
        String heading = dashboardPage.getHeading();
        assertEquals("Notifications", heading);
    }

    @Test
    public void loginWithEmptyUsernameTest () {
        loginPage.signIntoTheSystem("danielle", "");
        String error = loginPage.getFieldIsRequireErrorText();
        assertTrue(driver.findElement(By.xpath("//*[text()[contains(., 'This field is required.')]]")).isDisplayed());
        assertEquals("This field is required.", error);
    }

    @Test
    public void loginWithEmptyPasswordTest () {
        loginPage.signIntoTheSystem("", "monsterPass123!");
        String error = loginPage.getFieldIsRequireErrorText();
        assertTrue(driver.findElement(By.xpath("//*[text()[contains(., 'This field is required.')]]")).isDisplayed());
        assertEquals("This field is required.", error);
    }

    @Test
    public void loginWithEmptyFieldsTest () {
        loginPage.signIntoTheSystem("", "");
        String error = loginPage.getFieldIsRequireErrorText();
        assertTrue(driver.findElement(By.xpath("//div[@class='mat-form-field-subscript-wrapper ng-tns-c28-0']//*[text()[contains(., 'This field is required.')]]")).isDisplayed());
        assertTrue(driver.findElement(By.xpath("//div[@class='mat-form-field-subscript-wrapper ng-tns-c28-1']//*[text()[contains(., 'This field is required.')]]")).isDisplayed());
        assertEquals("This field is required.", error);
    }

    @Test
    public void loginWithUsernameFilledWithSpacesTest () {
        loginPage.signIntoTheSystem("          ", "monsterPass123!");
        String error = loginPage.getFieldIsRequireErrorText();
        assertTrue(driver.findElement(By.xpath("//*[text()[contains(., 'This field is required.')]]")).isDisplayed());
        assertEquals("This field is required.", error);
    }

    @Test
    public void loginWithPasswordFilledWithSpacesTest () {
        loginPage.signIntoTheSystem("danielle", "          ");
        String error = loginPage.getWrongPasswordErrorText();
        assertTrue(driver.findElement(By.xpath("//div[text()[contains(., '  The login information provided is invalid. Please check your submission and try again. ')]]")).isDisplayed());
        assertEquals("The login information provided is invalid. Please check your submission and try again.", error);
    }

    @Test
    public void loginWithInvalidUsernameTest () {
        loginPage.signIntoTheSystem("danielleggg", "monsterPass123!");
        String error = loginPage.getWrongPasswordErrorText();
        assertTrue(driver.findElement(By.xpath("//div[text()[contains(., '  The login information provided is invalid. Please check your submission and try again. ')]]")).isDisplayed());
        assertEquals("The login information provided is invalid. Please check your submission and try again.", error);
    }

    @Test
    public void loginWithInvalidPasswordTest () {
        loginPage.signIntoTheSystem("danielle", "monsterPass123!!!");
        String error = loginPage.getWrongPasswordErrorText();
        assertTrue(driver.findElement(By.xpath("//div[text()[contains(., '  The login information provided is invalid. Please check your submission and try again. ')]]")).isDisplayed());
        assertEquals("The login information provided is invalid. Please check your submission and try again.", error);
    }

    @Test
    public void clickOnForgotPasswordLinkTest () {
        ForgotPasswordPage fpp = loginPage.clickForgotPasswordLink();
        String heading = fpp.getHeading();
        assertTrue(driver.findElement(By.xpath("//h2[contains(text(),'Reset Your Password')]")).isDisplayed());
        assertEquals("Reset Your Password", heading);
    }

    @Test
    public void checkMaxLengthUsernameTest () {}

    @Test
    public void checkMaxLengthPasswordTest () {}

    @After
    public void tearDown() {
        driver.quit();
    }
}
