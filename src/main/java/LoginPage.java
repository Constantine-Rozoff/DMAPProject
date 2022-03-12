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

    public DashboardPage signInToTheSystem (String userName, String password) {
        this.typeUserName(userName);
        this.typePassword(password);
        this.clickSignIn();
        return new DashboardPage(driver);
    }
}
