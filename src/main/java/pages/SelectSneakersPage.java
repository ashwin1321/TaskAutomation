package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AddToCart;
import utils.DelayLoading;
import utils.ScrollToElement;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SelectSneakersPage {

    public void exportToCSV(List<WebElement> getAllInfoForSneaker) {

        List<String[]> productList = new ArrayList<>();
        Set<String> uniqueProducts = new HashSet<>();
        String fileName = "ShoeList.csv";
        int count = 0;

        for (WebElement item : getAllInfoForSneaker) {
            if (count >= 20) {
                break;
            }
            String name = item.findElement(By.xpath(".//div[contains(@id, 'title')]")).getText();
            // there are 2 span tag so get the one with price
            WebElement priceElement = item.findElement(By.xpath(".//span[@class='currency--GVKjl']"));
            String price = priceElement.getText().replace(",", "");
            String productKey = name + "," + price; // Concatenate name and price to form a unique key
            if (uniqueProducts.contains(productKey)) { // If product already exists, skip it
                continue;
            }
            uniqueProducts.add(productKey);
            productList.add(new String[]{name, price});
            count++;
        }

        try (FileWriter writer = new FileWriter(fileName)) {
            writer.append("Name,Price\n");

            for (String[] product : productList) {
                writer.append(String.join(",", product)).append("\n");
            }

            System.out.println("CSV file exported successfully: " + fileName);
        } catch (IOException e) {
            System.out.println("exception occured = " + e);
        }
    }

    public void selectSpecificSneaker(List<WebElement> listOfSneakers, WebDriverWait wait, WebDriver driver){

        // select the first item with AirForce 1
        for (WebElement item: listOfSneakers){
            String title = item.findElement(By.cssSelector("div[id*='title']")).getText();

            if (title.startsWith("Air Force 1")) {
                System.out.println("Selecting the first Air Force 1.....");
                ScrollToElement.scrollIntoView(driver, item);
                DelayLoading.delayFiveSecond();
                item.click();
                break;
            }
        }
        // select size
        WebElement getSize43 =  driver.findElement(By.xpath("//span[@class='sku-variable-size' and text()='43']"));
        getSize43.click();

        // add quantity for the item
        WebElement addQuantity = driver.findElement(By.cssSelector("a.next-number-picker-handler.next-number-picker-handler-up i.next-icon-add"));
        addQuantity.click();

        // add to cart
        AddToCart.addItemToCart(wait);
    }

    public void clickMensFashion(WebDriver driver, WebDriverWait wait){
        WebElement locateMensFashion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Level_1_Category_No9")));
        Actions actions = new Actions(driver);
        actions.moveToElement(locateMensFashion).perform();
    }

    public void selectSneakers(WebDriver driver, WebDriverWait wait){
        WebElement selectShoes = driver.findElement(By.xpath("//li[@class='lzd-site-menu-sub-item'][@data-cate='cate_3_3']"));
        selectShoes.click();

        WebElement selectSneakersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Sneakers']")));
        selectSneakersLink.click();
    }

    public List<WebElement> getAllSneakersInfo(WebDriverWait wait){
        WebElement sneakersInfos = wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-qa-locator='general-products']"))));
        //get all the products
        return sneakersInfos.findElements(By.cssSelector("div[data-qa-locator='product-item']"));
    }
}
