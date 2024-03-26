package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitingTime {

    public static WebDriverWait wait(WebDriver driver){
        return new WebDriverWait(driver, Duration.ofSeconds(10));
    }
}
