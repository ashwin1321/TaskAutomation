package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class SearchItem {
    public static void main(String[] args)  {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.daraz.com.np/");

        WebElement locatePrompt = driver.findElement(By.xpath("//input[@type='search']"));
        locatePrompt.sendKeys("sam");

        // wait for 10 sec
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // parent suggestion div class
        WebElement suggestionsContainer = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='suggest-list--3Tm8']")));
        System.out.println("suggestionsContainer = " + suggestionsContainer);
        // all the children suggestions have same class
        List<WebElement> suggestionElements = suggestionsContainer.findElements(By.xpath("//a[@class='suggest-common--2KmE ']"));
        System.out.println("suggestionElements = " + suggestionElements);
        boolean allSuggestionsStartWithSam = true;

        for (WebElement suggestion : suggestionElements) {
            String suggestionText = suggestion.getText();

            // if any suggestion doesn't start with sam it returns false
            if (!suggestionText.startsWith("Sam")) {
                allSuggestionsStartWithSam = false;
                break;
            }
        }

        if (allSuggestionsStartWithSam) {
            System.out.println("All suggestions start with 'Sam'");
        } else {
            System.out.println("Not everything is Sam");
        }

        if (suggestionElements.size() >= 4) {
            WebElement forthItem = suggestionElements.get(3);
            forthItem.click();
        } else {
            System.out.println("There are less than 4 suggestions");
        }

        WebElement AllItemsInDashboard = driver.findElement(By.cssSelector("[data-qa-locator='general-products']"));

        List<WebElement> indProducts = AllItemsInDashboard.findElements(By.cssSelector("div[data-qa-locator='product-item']"));
        System.out.println("productDivs = " + indProducts);

        List<WebElement> desiredProduct = new ArrayList<>();

        for (WebElement product : indProducts) {
            WebElement item = product.findElement(By.cssSelector("span[class='currency--GVKjl']"));
            if (item.getText().equals("28,999")) {
                desiredProduct.add(product);
            }
        }

        if (!desiredProduct.isEmpty()) {
            WebElement firstProduct = desiredProduct.get(0);
            firstProduct.click();
        } else {
            System.out.println("No product found with price 28,999");
        }

        WebElement addToCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[contains(text(),'Add to Cart')]")));
        addToCart = wait.until(ExpectedConditions.elementToBeClickable(addToCart));
        addToCart.click();


    }
}
