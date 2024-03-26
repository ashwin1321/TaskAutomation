package testCases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SearchItem;
import utils.DelayLoading;
import utils.WaitingTime;

import java.util.List;

public class SearchItemDaraz {

    public final WebDriver driver;

    public SearchItemDaraz(WebDriver driver) {
        this.driver = driver;
    }

    public void searchItem(){
        SearchItem searchItem = new SearchItem();
        WebElement locatePrompt = driver.findElement(By.xpath("//input[@type='search']"));
        locatePrompt.sendKeys("sam");

        WebDriverWait wait = WaitingTime.wait(driver);
        // parent suggestion div class
        WebElement suggestionsContainer = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='suggest-list--3Tm8']")));
        // all the children suggestions have same class
        List<WebElement> suggestionElements = suggestionsContainer.findElements(By.xpath("//a[@class='suggest-common--2KmE ']"));

        // checking if all suggestion items starts with sam or not
        searchItem.allItemStartWithSam(suggestionElements);

        // click fourthItem
        searchItem.clickForthItem(suggestionElements);

        // select the item with specific price
        List<WebElement> desiredItems = searchItem.selectItemWithPrice(driver);
        // click on the first filtered item
        searchItem.clickFirstProduct(desiredItems, wait);

        // delay 5 second
        DelayLoading.delayFiveSecond();
    }
}

