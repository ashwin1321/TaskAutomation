package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.AddToCart;

import java.util.ArrayList;
import java.util.List;

public class SearchItem {

    public  boolean allSuggestionWithSam(List<WebElement> suggestionElements){

        for (WebElement suggestion : suggestionElements) {
            if (!suggestion.getText().startsWith("Sam")) {
                return false;
            }
        }
        return true;
    }
    public  void clickForthItem(List<WebElement> suggestionElements){
        if (suggestionElements.size() >= 4) {
            WebElement forthItem = suggestionElements.get(3);
            forthItem.click();
        } else {
            System.out.println("There are less than 4 suggestions");
        }
    }
    public  List<WebElement> selectItemWithPrice(WebDriver driver){
        WebElement AllItemsInDashboard = driver.findElement(By.cssSelector("[data-qa-locator='general-products']"));

        List<WebElement> indProducts = AllItemsInDashboard.findElements(By.cssSelector("div[data-qa-locator='product-item']"));
        List<WebElement> desiredProduct = new ArrayList<>();

        for (WebElement product : indProducts) {
            WebElement item = product.findElement(By.cssSelector("span[class='currency--GVKjl']"));
            if (item.getText().equals("28,999")) {
                desiredProduct.add(product);
            }
        }
        return desiredProduct;
    }
    public  void clickFirstProduct(List<WebElement> items, WebDriverWait wait){
        if (!items.isEmpty()) {
            WebElement firstProduct = items.get(0);
            firstProduct.click();
            AddToCart.addItemToCart(wait);
        } else {
            System.out.println("No product found with price 28,999");
        }
    }

    public void allItemStartWithSam(List<WebElement> suggestionElements){
        boolean allSuggestionsStartWithSam = allSuggestionWithSam(suggestionElements);
        if (allSuggestionsStartWithSam) {
            System.out.println("All suggestions start with 'Sam'");
        } else {
            System.out.println("Not everything is Sam");
        }
    }
}
