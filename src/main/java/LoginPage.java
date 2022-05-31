import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    private By userNameField = By.xpath("//input[@formcontrolname='username']");
    private By passwordField = By.xpath("//input[@formcontrolname='password']");
    private By signInButton = By.xpath("//span[text()=' SIGN IN ']");
    private By forgotPasswordLink = By.xpath("//span[text()=' Forgot Password? ']");
    private By systemStatusLink = By.xpath("//div[@class='auth-content']//a[text()='System Status']");
    private By theUsernameFieldIsRequireError = By.xpath("//input[@placeholder='Username']/../../..//*[text()[contains(., 'This field is required.')]]");
    private By thePasswordFieldIsRequireError = By.xpath("//input[@placeholder='Password']/../../..//*[text()[contains(., 'This field is required.')]]");
    private By theWrongPasswordError = By.xpath("//div[text()[contains(., '  The login information provided is invalid. Please check your submission and try again. ')]]");
    private By tooLongInputError = By.xpath("//div[text()[contains(., 'Maximum length for the field is 50 characters.')]]");

    public DashboardPage clickSignIn() {
        driver.findElement(signInButton).click();
        return new DashboardPage(driver);
    }

    public ForgotPasswordPage clickForgotPasswordLink() {
        driver.findElement(forgotPasswordLink).click();
        return new ForgotPasswordPage(driver);
    }

    public void clickSystemStatusLink() {
        driver.findElement(systemStatusLink).click();
    }

    public LoginPage typeUserName (String userName) {
        driver.findElement(userNameField).sendKeys(userName);
        return this;
    }

    public LoginPage typePassword (String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public DashboardPage signIntoTheSystem(String userName, String password) {
        this.typeUserName(userName);
        this.typePassword(password);
        this.clickSignIn();
        return new DashboardPage(driver);
    }

    public String getUsernameFieldIsRequireErrorText() {
        return driver.findElement(theUsernameFieldIsRequireError).getText();
    }

    public String getPasswordFieldIsRequireErrorText() {
        return driver.findElement(thePasswordFieldIsRequireError).getText();
    }

    public String getWrongPasswordErrorText() {
        return driver.findElement(theWrongPasswordError).getText();
    }

    public String getTooLongInputErrorText() {
        return driver.findElement(tooLongInputError).getText();
    }
}
