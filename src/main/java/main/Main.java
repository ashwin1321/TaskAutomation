package main;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import testCases.FlashSaleTest;
import testCases.LoginToTest;
import testCases.SearchItemTest;
import testCases.SelectSneakersTest;
import utils.GetUserRows;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // create a session db and inserts and gets data
        List<Object[]> userData = GetUserRows.getUserRows();

        // create a webdriver
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.daraz.com.np/");

        // attempts login to daraz
        LoginToTest login = new LoginToTest(driver);
        assert userData != null;
        login.loginToDaraz(userData);

        // searchItem from searchbar
        SearchItemTest searchItemTest = new SearchItemTest(driver);
        searchItemTest.searchItem();

         // for flash sale
        FlashSaleTest flashSaleTest = new FlashSaleTest(driver);
        flashSaleTest.flashSale();

        // select specific sneaker
        SelectSneakersTest selectSneakersTest = new SelectSneakersTest(driver);
        selectSneakersTest.selectSneakers();

        driver.close();
    }
}