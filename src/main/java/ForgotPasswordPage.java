import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ForgotPasswordPage {
    private WebDriver driver;

    public By getResetPasswordTitle() {
        return resetPasswordTitle;
    }

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    private By emailField = By.xpath("//input[@placeholder='Email']");
    private By submitButton = By.xpath("//span[text()=' SUBMIT ']/..//parent::button");
    private By cancelButton = By.xpath("//span[text()=' CANCEL ']//parent::button");
    private By systemStatusLink = By.xpath("//div[@class='auth-content']//a[text()='System Status']");
    private By resetPasswordTitle = By.xpath("//h2[contains(text(),'Reset Your Password')]");
    private By successNotification = By.xpath("//div[contains(text(),' Email with link was successfully sent! ')]");
    private By doneButton = By.xpath("//span[text()=' DONE ']//parent::button");
    private By notificationForInvalidEmail = By.xpath("//div[contains(text(),'Should be a valid email address.')]");
    private By notificationForEmptyEmail = By.xpath("//div[contains(text(),'This field is required.')]");


    public ForgotPasswordPage typeEmail (String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public ForgotPasswordPage clickSubmitButton() {
        driver.findElement(submitButton).click();
        return this;
    }

    public LoginPage clickCancelButton() {
        driver.findElement(cancelButton).click();
        return new LoginPage(driver);
    }

    public LoginPage clickDoneButton() {
        driver.findElement(doneButton).click();
        return new LoginPage(driver);
    }

    public void clickSystemStatusLink() {
        driver.findElement(systemStatusLink).click();
    }

    public String getHeading() {
        return driver.findElement(resetPasswordTitle).getText();
    }

    public ForgotPasswordPage sendEmailWithRestoreLink (String email) {
        this.typeEmail(email);
        this.clickSubmitButton();
        driver.findElement(successNotification);
        //click on Done button can be added if needed
        return this;
    }

    public ForgotPasswordPage sendInvalidEmail (String invalidEmail) {
        this.typeEmail(invalidEmail);
        this.clickSubmitButton();
        driver.findElement(notificationForInvalidEmail);
        //click on Done button can be added if needed
        return this;
    }

    public ForgotPasswordPage sendEmptyEmail () {
        this.clickSubmitButton();
        driver.findElement(notificationForEmptyEmail);
        //click on Done button can be added if needed
        return this;
    }

}