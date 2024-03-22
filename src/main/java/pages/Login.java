package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login extends DarazUrl{

    public Login(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password){
        WebElement locateUsername = driver.findElement(By.id("uname"));
        WebElement locatepass = driver.findElement(By.id("pass"));
        WebElement locateloginButton = driver.findElement(By.id("loginButton"));

        locateUsername.sendKeys(username);
        locatepass.sendKeys(password);

        locateloginButton.click();
    }

}
