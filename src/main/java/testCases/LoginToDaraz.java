package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.Login;

public class LoginToDaraz {

    private final WebDriver webDriver;
    private final Login login;

    public LoginToDaraz(){
        webDriver = new ChromeDriver();
        login = new Login(webDriver);
    }

    public void loginToDaraz(String username, String password){
        webDriver.get("https://www.daraz.com.np/");

        login.clickLogin("loginLinkId");
        login.login(username, password);

        webDriver.close();
    }
}
