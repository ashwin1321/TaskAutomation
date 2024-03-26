package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Login{

    private final WebDriver driver;

    public Login(WebDriver driver) {
        this.driver =  driver;
    }

    public void login(String username, String password){
        WebElement locateUsername = driver.findElement(By.xpath("//input[@type='text']"));
        WebElement locatepass = driver.findElement(By.xpath("//input[@type='password']"));
        WebElement locateloginButton = driver.findElement(By.xpath("//button[contains(text(),'LOGIN')]"));

        locateUsername.sendKeys(username);
        locatepass.sendKeys(password);

        locateloginButton.click();
    }

    public void clickLogin(WebElement id){
        id.click();
    }

}
