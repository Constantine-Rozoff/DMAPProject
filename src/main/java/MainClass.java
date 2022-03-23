import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class MainClass {
    static WebDriver driver;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get("https://qa.dmap.cloud/auth/login");

      //  LoginPage loginPage = new LoginPage(driver);
        LoginPage loginPage = PageFactory.initElements(driver, LoginPage.class);

        loginPage.signInToTheSystem("danielle", "monsterPass123!");
    }
}