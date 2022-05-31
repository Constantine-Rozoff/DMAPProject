import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {
    private WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    private By dmapLogo = By.xpath("//div[@class='sidebar-logo-item']");
    private By sideBarCampaigns = By.xpath("//div[@class='sidebar-item']/label[(text()='Campaigns')]");
    private By subMenuCampaigns = By.xpath("//div[@class='sidebar-item']//a[@href='/campaigns']");
    private By sideBarData = By.xpath("//div[@class='sidebar-item']/label[(text()='Data')]");
    private By subMenuAudiences = By.xpath("//div[@class='sidebar-item']//a[@href='/data/audiences']");
    private By notifications = By.xpath("//div[@class='dashboard-notifications-container']/h2");

    public By getNotifications() {
        return notifications;
    }

    public String getHeading() {
        return driver.findElement(notifications).getText();
    }
}
