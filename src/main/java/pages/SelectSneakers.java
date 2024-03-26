package pages;

import net.bytebuddy.description.type.TypeDescription;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AddToCart;
import utils.WaitingTime;

import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SelectSneakers {

    public static void main(String[] args) {

        WebDriver driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://www.daraz.com.np/");

        WebDriverWait wait = WaitingTime.wait(driver);
        // locate mens fashion section having unique if as:
        WebElement locateMensFashion = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Level_1_Category_No9")));

        Actions actions = new Actions(driver);
        actions.moveToElement(locateMensFashion).perform();
//        locateMensFashion.click();

        WebElement selectShoes = driver.findElement(By.xpath("//li[@class='lzd-site-menu-sub-item'][@data-cate='cate_3_3']"));
       selectShoes.click();

        WebElement selectSneakersLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Sneakers']")));
        selectSneakersLink.click();

        WebElement sneakersInfos = wait.until((ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-qa-locator='general-products']"))));
        //get all the products
       List<WebElement> getAllInfoForSneaker = sneakersInfos.findElements(By.cssSelector("div[data-qa-locator='product-item']"));

       /*
       * Get 20 items and exporting in csv file
       * */
       // add first 20 products in the list
        List<String[]> productList = new ArrayList<>();
        int count = 0;
        for (WebElement item : getAllInfoForSneaker) {
            if (count >= 20) {
                break;
            }
            String name = item.findElement(By.xpath(".//div[contains(@id, 'title')]")).getText();
            // there are 2 span tag so get the one with price
            WebElement priceElement = item.findElement(By.xpath(".//span[@class='currency--GVKjl']"));
            String price = priceElement.getText();
            productList.add(new String[]{name, price});
            count++;
        }
        exportToCSV(productList);

        /*
        * Adding the 1st item starting with Air Force 1 to cart
        * */
        selectSneaker(getAllInfoForSneaker, wait, driver);

    }

    private static void exportToCSV(List<String[]> productList) {
        String fileName = "ShoeList.csv";
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

    public  static void selectSneaker(List<WebElement> listOfSneakers, WebDriverWait wait, WebDriver driver){

        // select the first item with AirForce 1
        for (WebElement item: listOfSneakers){
            String title = item.findElement(By.cssSelector("div[id*='title']")).getText();

            if (title.startsWith("Air Force 1")) {
                System.out.println("Selecting the first Air Force 1.....");
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

}
