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

            login.login(username,password);

            // check the modal for error message, if logged in break the loop
            try{
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                String message = alert.getText();
                System.out.println("message = " + message);
                alert.accept();
            }catch(Exception e){
                System.out.println("Modal didn't appear");
            }

        }
    }
}
