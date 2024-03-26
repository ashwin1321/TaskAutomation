package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.FlashSale;

public class FlashSaleDaraz {

    public final WebDriver driver;
    public FlashSaleDaraz(WebDriver driver) {
        this.driver = driver;
    }

    public void flashSale() {

        WebElement locateFlashSale = driver.findElement(By.id("hp-flash-sale"));
        boolean isFlashSale = locateFlashSale.getText().contains("Flash Sale");
        WebElement hourLeft = locateFlashSale.findElement(By.id("hours"));
        WebElement minuteLeft = locateFlashSale.findElement(By.id("minutes"));

        int time = FlashSale.remainingTime(hourLeft, minuteLeft);
        FlashSale.handleFlashSale(isFlashSale, time, locateFlashSale, driver);
    }
}
