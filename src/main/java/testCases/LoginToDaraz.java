package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.Login;

import java.time.Duration;

public class LoginToDaraz {

    private final WebDriver webDriver;
    private final Login login;

    public LoginToDaraz(){
        webDriver = new ChromeDriver();
        login = new Login(webDriver);
    }

    public void loginToDaraz(String username, String password){
        webDriver.manage().window().maximize();
        webDriver.get("https://www.daraz.com.np/");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement click = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Login')]")));
        login.clickLogin(click);

        login.login(username, password);

    }
}
