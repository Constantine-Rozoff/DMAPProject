import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@formcontrolname='username']")
    private WebElement userNameField;
    @FindBy(xpath = "//input[@formcontrolname='password']")
    private WebElement passwordField;
    @FindBy(xpath = "//span[text()=' SIGN IN ']")
    private WebElement signInButton;
    @FindBy(xpath = "//span[text()=' Forgot Password? ']")
    private WebElement forgotPasswordLink;
    @FindBy(xpath = "//div[@class='auth-content']//a[text()='System Status']")
    private WebElement systemStatusLink;

    public DashboardPage clickSignIn() {
        signInButton.click();
        return new DashboardPage(driver);
    }

    public ForgotPasswordPage clickForgotPasswordLink() {
        forgotPasswordLink.click();
        return new ForgotPasswordPage(driver);
    }

    public void clickSystemStatusLink() {
        systemStatusLink.click();
    }

    public LoginPage typeUserName (String userName) {
        userNameField.sendKeys(userName);
        return this;
    }

    public LoginPage typePassword (String password) {
        passwordField.sendKeys(password);
        return this;
    }

    public DashboardPage signInToTheSystem (String userName, String password) {
        this.typeUserName(userName);
        this.typePassword(password);
        this.clickSignIn();
        return new DashboardPage(driver);
    }
}
