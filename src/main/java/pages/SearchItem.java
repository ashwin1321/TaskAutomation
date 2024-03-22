package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SearchItem {

    public static void main(String[] args){

        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.daraz.com.np/");
        WebElement locatePrompt = driver.findElement(By.xpath("//input[@type='search']"));

        locatePrompt.sendKeys("sam");


    }
}
