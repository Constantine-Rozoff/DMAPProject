import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgotPasswordPage {
    private WebDriver driver;

    public ForgotPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//input[@placeholder='Email']")
    private WebElement emailField;
    @FindBy(xpath = "//span[text()=' SUBMIT ']/..//parent::button")
    private WebElement submitButton;
    @FindBy(xpath = "//span[text()=' CANCEL ']//parent::button")
    private WebElement cancelButton;
    @FindBy(xpath = "//div[@class='auth-content']//a[text()='System Status']")
    private WebElement systemStatusLink;
    @FindBy(xpath = "//h2[contains(text(),'Reset Your Password')]")
    private WebElement resetPasswordTitle;
    @FindBy(xpath = "//div[contains(text(),' Email with link was successfully sent! ')]")
    private WebElement successNotification;
    @FindBy(xpath = "//span[text()=' DONE ']//parent::button")
    private WebElement doneButton;
    @FindBy(xpath = "//div[contains(text(),'Should be a valid email address.')]")
    private WebElement notificationForInvalidEmail;
    @FindBy(xpath = "//div[contains(text(),'This field is required.')]")
    private WebElement notificationForEmptyEmail;


    public ForgotPasswordPage typeEmail (String email) {
        emailField.sendKeys(email);
        return this;
    }

    public ForgotPasswordPage clickSubmitButton() {
        submitButton.click();
        return this;
    }

    public LoginPage clickCancelButton() {
        cancelButton.click();
        return new LoginPage(driver);
    }

    public LoginPage clickDoneButton() {
        doneButton.click();
        return new LoginPage(driver);
    }

    public void clickSystemStatusLink() {
        systemStatusLink.click();
    }

    public String getHeading() {
        return resetPasswordTitle.getText();
    }

    public ForgotPasswordPage sendEmailWithRestoreLink (String email) {
        this.typeEmail(email);
        this.clickSubmitButton();
        successNotification.isDisplayed();
        //click on Done button can be added if needed
        return this;
    }

    public ForgotPasswordPage sendInvalidEmail (String invalidEmail) {
        this.typeEmail(invalidEmail);
        this.clickSubmitButton();
        notificationForInvalidEmail.isDisplayed();
        //click on Done button can be added if needed
        return this;
    }

    public ForgotPasswordPage sendEmptyEmail () {
        this.clickSubmitButton();
        notificationForEmptyEmail.isDisplayed();
        //click on Done button can be added if needed
        return this;
    }

}
