package main;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.FlashSale;
import pages.Login;
import testCases.FlashSaleDaraz;
import testCases.LoginToDaraz;
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
        LoginToDaraz login = new LoginToDaraz(driver);
        assert userData != null;
        login.loginToDaraz(userData);

        // for flash sale
//        FlashSaleDaraz flashSaleDaraz = new FlashSaleDaraz(driver);
//        flashSaleDaraz.flashSale();

    }
}