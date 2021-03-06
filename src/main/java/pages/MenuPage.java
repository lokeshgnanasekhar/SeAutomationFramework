package pages;

import modules.EventHandler;
import modules.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Lokesh_GnanaSekhar on 6/22/2017.
 */
public class MenuPage extends EventHandler{


    @FindBy(id = "locationAccordion")
    WebElement locationSelectionBlock;

    @FindBy(xpath = "//*[@id='locationSection']/div/a")
    WebElement startOverLink;


    public MenuPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitFor(3);
        if (!(driver.getTitle().contains("Menu"))) {
            throw new IllegalStateException("Menu page is expected, but not displayed!");
        }
        Log.info("Navigated to Menu Page");
    }

    public LocationSelectionPage editLocation(){
        Log.info("Wait for 2 secs");
        waitFor(2);
        Log.info("Waiting for Location selection block");
        waitForElementToBeVisible(locationSelectionBlock);
        Log.info("Clicked on Location selection block");
        click(locationSelectionBlock);
        Log.info("Clicked on Start over link");
        click(startOverLink);
        return new LocationSelectionPage(driver);

    }

}
