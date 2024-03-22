package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ClickLoginLink extends DarazUrl{

    public ClickLoginLink(WebDriver driver) {
        super(driver);
    }

    public void loginLink(String id){
        WebElement link = driver.findElement(By.id(id));
    }

}
