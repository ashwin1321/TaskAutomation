package testCases;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Login;

import java.time.Duration;
import java.util.List;

public class LoginToDaraz {

    private final WebDriver webDriver;
    private final Login login;

    public LoginToDaraz(){
        webDriver = new ChromeDriver();
        login = new Login(webDriver);
    }

    public void loginToDaraz(List<Object[]> userData){
        webDriver.manage().window().maximize();
        webDriver.get("https://www.daraz.com.np/");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(15));
        WebElement click = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Login')]")));
        login.clickLogin(click);

        assert userData != null;
        for (Object[] data : userData) {
            String username = (String) data[1];
            String password = (String) data[2];

            login.login(username, password);

            boolean errorLogging = false;
            try {
                // when login error, a modal pops up, having div block with classname darazmod-loading-warp
                errorLogging = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("darazmod-loading-warp"))) != null;
            } catch (Exception e) {
                System.out.println("Error occured " + e.getMessage());
            }

            if (errorLogging) {
                System.out.println("Error while logging...");
                webDriver.navigate().refresh(); // Reload the page
            } else {
                System.out.println("Login successful for username: " + username);
                break;
            }
        }

    }
}
