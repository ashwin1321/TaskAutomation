package testCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.SelectSneakers;
import utils.WaitingTime;

import java.util.List;

public class SelectSneakersDaraz {

    public final WebDriver driver;

    public SelectSneakersDaraz(WebDriver driver) {
        this.driver = driver;
    }
    public void selectSneakers(){

        WebDriverWait wait = WaitingTime.wait(driver);
        SelectSneakers selectSneakers = new SelectSneakers();
        // locate mens fashion section having unique if as:
        selectSneakers.clickMensFashion(driver, wait);

        // click on shoes and sneakers
        selectSneakers.selectSneakers(driver, wait);

        // get all the sneakers info
        List<WebElement> getAllInfoForSneaker = selectSneakers.getAllSneakersInfo(wait);

        // get shoes list and export to csv
        selectSneakers.exportToCSV(getAllInfoForSneaker);

        // select sneaker with name Air Force 1 and add to cart
        selectSneakers.selectSpecificSneaker(getAllInfoForSneaker, wait, driver);
    }
}
