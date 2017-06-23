package pages;

import modules.EventHandler;
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
    }

    public LocationSelectionPage editLocation(){
        waitFor(2);
        waitForElementToBeVisible(locationSelectionBlock);
        click(locationSelectionBlock);
        click(startOverLink);
        return new LocationSelectionPage(driver);

    }

}
