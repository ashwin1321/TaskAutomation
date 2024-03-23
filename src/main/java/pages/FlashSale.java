package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class FlashSale {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.daraz.com.np/");

        WebElement locateFlashSale = driver.findElement(By.id("hp-flash-sale"));
        boolean isFlashSale = locateFlashSale.getText().contains("Flash Sale");
        WebElement hourLeft = locateFlashSale.findElement(By.id("hours"));
        WebElement minuteLeft = locateFlashSale.findElement(By.id("minutes"));

        // to know the time if its above 3 hours or less than 5, we can calculate in minute for precise decision
        int hour = Integer.parseInt(hourLeft.getText());
        int minute = Integer.parseInt(minuteLeft.getText());
        int time = hour*60 + minute;

        if(isFlashSale){

            WebElement saleItems = locateFlashSale.findElement(By.cssSelector(".card-fs-content-body.J_FSBody")); // flash sale items div has 2 classes
            // the saleItems has multiples items inside in anchor tags to get all the items in anchor tags
            List<WebElement> aTagElement = saleItems.findElements(By.tagName("a"));

            if (time <=180){  // 3 hours
                getItemFromSale(0, aTagElement, driver);
            }else if(time<=300){ // less than 5 hr and 3:   less than 3 is covered in first else case so no need extra checking
                getItemFromSale(1, aTagElement, driver);
            }else{
                getItemFromSale(3, aTagElement, driver);
            }
        }

    }

    public static void getItemFromSale(int index, List<WebElement> aTagElement,  WebDriver driver){
        WebElement getItem = aTagElement.get(index);
        WebElement getNameOfItem = getItem.findElement(By.className("fs-card-title"));
        System.out.println("Sale Item Name = " + getNameOfItem.getText());
        System.out.println("Position of the item  = " + index+1);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        getItem = wait.until(ExpectedConditions.elementToBeClickable(getItem));
        getItem.click();

        // add to cart // make this common method somewhere as it is used again and again
        WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Add to Cart')]")));
        addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCart));
        addToCart.click();
    }
}
