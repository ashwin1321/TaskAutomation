package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SearchItem {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.daraz.com.np/");

        WebElement locatePrompt = driver.findElement(By.xpath("//input[@type='search']"));
        locatePrompt.sendKeys("sam");

        // wait for 10 sec
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // parent suggestion div class
        WebElement suggestionsContainer = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@class='suggest-list--3Tm8']")));

        // all the children suggestions have same class
        List<WebElement> suggestionElements = suggestionsContainer.findElements(By.xpath("//div[@class='suggest-common--2KmE ']"));

        boolean allSuggestionsStartWithSam = true;

        for (WebElement suggestion : suggestionElements) {
            String suggestionText = suggestion.getText();

            // Sam must be in bold inside span tag like <span><b>Sam</b>Sung....</span>
            // if any suggestion doesn't start with sam it returns false
            if (!suggestionText.startsWith("<b>Sam</b>")) {
                allSuggestionsStartWithSam = false;
                break;
            }
        }

        if (allSuggestionsStartWithSam) {
            System.out.println("All suggestions start with 'Sam'");
        } else {
            System.out.println("Not all suggestions start with 'Sam'");
        }

    }
}
