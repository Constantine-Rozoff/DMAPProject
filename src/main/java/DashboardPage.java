import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class DashboardPage {
    private WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = "//div[@class='sidebar-logo-item']")
    private WebElement dmapLogo;
    @FindBy(xpath = "//div[@class='sidebar-item']/label[(text()='Campaigns')]")
    private WebElement sideBarCampaigns;
    @FindBy(xpath = "//div[@class='sidebar-item']//a[@href='/campaigns']")
    private WebElement subMenuCampaigns;
    @FindBy(xpath = "//div[@class='sidebar-item']/label[(text()='Data')]")
    private WebElement sideBarData;
    @FindBy(xpath = "//div[@class='sidebar-item']//a[@href='/data/audiences']")
    private WebElement subMenuAudiences;
}
