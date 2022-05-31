import org.junit.After;
import org.junit.Before;
import org.junit.Test;
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
        driver.manage().timeouts().implicitlyWait(12, TimeUnit.SECONDS);
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
        assertTrue(driver.findElement(dashboardPage.getNotifications()).isDisplayed());
        assertEquals("Notifications", heading);
    }

    @Test
    public void loginWithCamelcaseUsernameTest () {
        loginPage.signIntoTheSystem("Danielle", "monsterPass123!");
        String error = loginPage.getWrongPasswordErrorText();
        assertTrue(driver.findElement(loginPage.getTheInvalidDataError()).isDisplayed());
        assertEquals("The login information provided is invalid. Please check your submission and try again.", error);
    }

    @Test
    public void loginWithEmptyPasswordTest () {
        loginPage.signIntoTheSystem("danielle", "");
        String error = loginPage.getPasswordFieldIsRequireErrorText();
        assertTrue(driver.findElement(loginPage.getThePasswordFieldIsRequireError()).isDisplayed());
        assertEquals("This field is required.", error);
    }

    @Test
    public void loginWithEmptyUsernameTest () {
        loginPage.signIntoTheSystem("", "monsterPass123!");
        String error = loginPage.getUsernameFieldIsRequireErrorText();
        assertTrue(driver.findElement(loginPage.getTheUsernameFieldIsRequireError()).isDisplayed());
        assertEquals("This field is required.", error);
    }

    @Test
    public void loginWithEmptyFieldsTest () {
        loginPage.signIntoTheSystem("", "");
        String usernameError = loginPage.getUsernameFieldIsRequireErrorText();
        assertTrue(driver.findElement(loginPage.getTheUsernameFieldIsRequireError()).isDisplayed());
        assertEquals("This field is required.", usernameError);
        String passwordError = loginPage.getPasswordFieldIsRequireErrorText();
        assertTrue(driver.findElement(loginPage.getThePasswordFieldIsRequireError()).isDisplayed());
        assertEquals("This field is required.", passwordError);
    }

    @Test
    public void loginWithUsernameFilledWithSpacesTest () {
        loginPage.signIntoTheSystem("          ", "monsterPass123!");
        String error = loginPage.getUsernameFieldIsRequireErrorText();
        assertTrue(driver.findElement(loginPage.getTheUsernameFieldIsRequireError()).isDisplayed());
        assertEquals("This field is required.", error);
    }

    @Test
    public void loginWithPasswordFilledWithSpacesTest () {
        loginPage.signIntoTheSystem("danielle", "          ");
        String error = loginPage.getWrongPasswordErrorText();
        assertTrue(driver.findElement(loginPage.getTheInvalidDataError()).isDisplayed());
        assertEquals("The login information provided is invalid. Please check your submission and try again.", error);
    }

    @Test
    public void loginWithInvalidUsernameTest () {
        loginPage.signIntoTheSystem("danielleggg", "monsterPass123!");
        String error = loginPage.getWrongPasswordErrorText();
        assertTrue(driver.findElement(loginPage.getTheInvalidDataError()).isDisplayed());
        assertEquals("The login information provided is invalid. Please check your submission and try again.", error);
    }

    @Test
    public void loginWithInvalidPasswordTest () {
        loginPage.signIntoTheSystem("danielle", "monsterPass123!!!");
        String error = loginPage.getWrongPasswordErrorText();
        assertTrue(driver.findElement(loginPage.getTheInvalidDataError()).isDisplayed());
        assertEquals("The login information provided is invalid. Please check your submission and try again.", error);
    }

    @Test
    public void clickOnForgotPasswordLinkTest () {
        ForgotPasswordPage fpp = loginPage.clickForgotPasswordLink();
        String heading = fpp.getHeading();
        assertTrue(driver.findElement(fpp.getResetPasswordTitle()).isDisplayed());
        assertEquals("Reset Your Password", heading);
    }

    @Test
    public void checkMaxLengthUsernameTest () {
        loginPage.signIntoTheSystem("tahnert tahnert tahnert tahnert tahnert tahnert taf", "monsterPass123!!!");
        String error = loginPage.getTooLongUsernameErrorText();
        assertTrue(driver.findElement(loginPage.getTooLongUsernameError()).isDisplayed());
        assertEquals("Maximum length for the field is 50 characters.", error);
    }

    @Test
    public void checkMaxLengthPasswordTest () {
        loginPage.signIntoTheSystem("danielle", "tahnert tahnert tahnert tahnert tahnert tahnert taf");
        String error = loginPage.getTooLongPasswordErrorText();
        assertTrue(driver.findElement(loginPage.getTooLongPasswordError()).isDisplayed());
        assertEquals("Maximum length for the field is 50 characters.", error);
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
