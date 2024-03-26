package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.FlashSalePage;
import utils.DelayLoading;

public class FlashSaleTest {

    public final WebDriver driver;
    public FlashSaleTest(WebDriver driver) {
        this.driver = driver;
    }

    public void flashSale() {

        driver.get("https://www.daraz.com.np/");

        WebElement locateFlashSale = driver.findElement(By.id("hp-flash-sale"));
        boolean isFlashSale = locateFlashSale.getText().contains("Flash Sale");
        WebElement hourLeft = locateFlashSale.findElement(By.id("hours"));
        WebElement minuteLeft = locateFlashSale.findElement(By.id("minutes"));

        FlashSalePage flashSale = new FlashSalePage();

        int time = flashSale.remainingTime(hourLeft, minuteLeft);
        flashSale.handleFlashSale(isFlashSale, time, locateFlashSale, driver);

        DelayLoading.delayFiveSecond();
    }
}
