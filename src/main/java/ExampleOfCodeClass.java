import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import java.util.concurrent.TimeUnit;

public class ExampleOfCodeClass {

    static WebDriver driver;

    public static void main(String[] args) {

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://qa.dmap.cloud/auth/login");

        driver.findElement(By.xpath("//input[@formcontrolname='username']")).sendKeys("tahnert");
        driver.findElement(By.xpath("//input[@formcontrolname='password']")).sendKeys("monsterPass123!");
        WebElement submit_button = driver.findElement(By.xpath("//span[text()=' SIGN IN ']"));
        submit_button.click();
        System.out.println(submit_button.getText());

        Actions hoverMouseOver = new Actions(driver);

        WebElement logo = driver.findElement(By.xpath("//div[@class='sidebar-logo-item']"));
        WebElement sideMenuCampaigns = driver.findElement(By.xpath("//div[@class='sidebar-item']/label[(text()='Campaigns')]"));
        WebElement subMenuCampaigns = driver.findElement(By.xpath("//div[@class='sidebar-item']//a[@href='/campaigns']"));
        WebElement sideMenuData = driver.findElement(By.xpath("//div[@class='sidebar-item']/label[(text()='Data')]"));
        WebElement subMenuAudiences = driver.findElement(By.xpath("//div[@class='sidebar-item']//a[@href='/data/audiences']"));

        //Enter to audiences
        hoverMouseOver.moveToElement(logo).perform();
        sideMenuData.click();
        hoverMouseOver.moveToElement(sideMenuCampaigns).perform();
        subMenuAudiences.click();
        //more methods from course
//        hoverMouseOver.dragAndDrop().build().perform();
//        hoverMouseOver.doubleClick().build().perform();
//        hoverMouseOver.contextClick().build().perform();
//        hoverMouseOver.clickAndHold().build().perform();



        WebElement loanTypeFilter = driver.findElement(By.xpath("//div[@class='header-item ml-3']//div[contains(text(), 'Select Type')]"));
        hoverMouseOver.moveToElement(loanTypeFilter).click().perform();

        selectCheckBox("Conventional");
        selectCheckBox("VA");
//        WebElement checkBox = driver.findElement(By.xpath("//div[@title=' Conventional ']"));
//        checkBox.click();
        findAudience("Conventional Cash Out");
        findAudience("Conventional Rate And Term");
        findAudience("VA Cash Out");
        findAudience("VA IRRRL");

        driver.quit();
    }

    public static void selectCheckBox(String loanType) {
        //div[@title=' %s ']"
        String checkBoxXpath = "//div[@title=' %s ']";
        driver.findElement(By.xpath(String.format(checkBoxXpath, loanType))).click();
    }

    public static void findAudience(String audienceName) {
        String audienceNameXpath = "//div/h3[@class='audience-title' and contains(text(), '%s')]";
        driver.findElement(By.xpath(String.format(audienceNameXpath, audienceName)));
    }
}