package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AddToCart;
import utils.WaitingTime;

import java.util.List;

public class FlashSale {

    public int remainingTime(WebElement hourLeft, WebElement minuteLeft) {
        int hour = Integer.parseInt(hourLeft.getText());
        int minute = Integer.parseInt(minuteLeft.getText());
        return hour * 60 + minute;
    }

    public void handleFlashSale(boolean isFlashSale, int time, WebElement locateFlashSale, WebDriver driver) {
        if (isFlashSale) {
            WebElement saleItems = locateFlashSale.findElement(By.cssSelector(".card-fs-content-body.J_FSBody"));
            List<WebElement> aTagElement = saleItems.findElements(By.tagName("a"));

            if (time <= 180) {
                getItemFromSale(0, aTagElement, driver);
            } else if (time <= 300) {
                getItemFromSale(1, aTagElement, driver);
            } else {
                getItemFromSale(2, aTagElement, driver);
            }
        } else {
            System.out.println("There is no flash sale available......");
        }
    }

    public void getItemFromSale(int index, List<WebElement> aTagElement,  WebDriver driver){
        WebElement getItem = aTagElement.get(index);
        WebElement getNameOfItem = getItem.findElement(By.className("fs-card-title"));
        System.out.println("Sale Item Name = " + getNameOfItem.getText());
        System.out.println("Position of the item  = " + index);

        // flash sale is not visible in default viewport so scroll abit
        scrollIntoView(driver, 500);

        WebDriverWait wait = WaitingTime.wait(driver);
        getItem = wait.until(ExpectedConditions.elementToBeClickable(getItem));
        getItem.click();

        // add to cart // make this common method somewhere as it is used again and again
        AddToCart.addItemToCart(wait);
    }

    public void scrollIntoView(WebDriver driver, int offset) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, arguments[0]);", offset);
    }
}