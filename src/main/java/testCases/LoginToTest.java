package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import utils.WaitingTime;

import java.util.List;

public class LoginToTest {

    private final WebDriver webDriver;
    private final LoginPage login;

    public LoginToTest(WebDriver driver){
        this.webDriver = driver;
        login = new LoginPage(webDriver);
    }

    public void loginToDaraz(List<Object[]> userData){

        WebDriverWait wait = WaitingTime.wait(webDriver);
        WebElement click = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Login')]")));
        // click login button from home page
        login.clickLogin(click);

        assert userData != null;
        for (Object[] data : userData) {
            String username = (String) data[1];
            String password = (String) data[2];

            // insert uname and pwd in the login page
            login.login(username, password);

            boolean errorLogging = false;
            try {
                // when login error, a modal pops up, having div block with classname next-feedback-title with one div with message Error
                WebElement errorMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("next-feedback-error")));

                if(errorMessage.getText().contains("Error")){
                    errorLogging =  true;
                    System.out.println("Invalid username or password.....");
                }
            } catch (Exception e) {
                System.out.println("Error occured " + e.getMessage());
            }

            if (errorLogging) {
                webDriver.navigate().refresh(); // Reload the page
            } else {
                System.out.println("Login successful for username: " + username);
                break;
            }
        }

    }
}
