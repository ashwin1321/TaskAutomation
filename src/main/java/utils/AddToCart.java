package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddToCart {

    public static void addItemToCart( WebDriverWait wait){
        try {
            WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Add to Cart')]")));
            addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCart));
            addToCart.click();
            System.out.println("Item added to cart.....");
        }catch (Exception e){
            System.out.println("Something went wrong...."+ e.getMessage());
        }
    }
}
