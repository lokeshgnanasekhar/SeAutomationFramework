package pages;

import modules.EventHandler;
import modules.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Created by Lokesh_GnanaSekhar on 6/22/2017.
 */
public class LocationSelectionPage extends EventHandler{

    @FindBy(xpath = "//*[@id='carryoutFormAccordion']/span")
    WebElement carryoutBlock;

    @FindBy(id = "locations-zipPostalcode")
    WebElement carryoutZipPostalTextField;

    @FindBy(xpath = "//*[@id='carryoutFormSection']/form/div[1]/div[3]/input")
    WebElement carryoutSubmitBtn;

    @FindBy(xpath = "//*[@id='store-details-section-1']/div/div[3]/div[2]/form/button")
    WebElement selectCarryoutStore;

    @FindBy(xpath = "//*[@id='store-details-accordion-1']/div[2]/a/i")
    WebElement expandSelectedCarryoutStore;

    @FindBy(id = "locations-streetaddress")
    WebElement deliveryStreetAddressTextField;

    @FindBy(id = "locations-usa-zipcode")
    WebElement deliveryZipTextField;

    @FindBy(xpath = "//form[@id='locations-form']//input[@value='Submit']")
    WebElement deliverySubmitBtn;

    public LocationSelectionPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitFor(5);
        if (!(driver.getTitle().contains("Stores Near Me"))) {
            throw new IllegalStateException("Location selection page is expected, but not displayed!");
        }
        Log.info("Navigated to Locations Page");
    }

    public MenuPage selectCarryout(String zippostal){
        Log.info("Waiting for Carryout Block to be visible");
        waitForElementToBeVisible(carryoutBlock);
        click(carryoutBlock);
        Log.info("Clicked on Carryout block");
        enterText(carryoutZipPostalTextField,zippostal);
        Log.info("Entered Postal code as :" +zippostal);
        click(carryoutSubmitBtn);
        Log.info("Clicked on Submit button in Carryout block");
        click(expandSelectedCarryoutStore);
        click(selectCarryoutStore);
        Log.info("Select Second carryout store");
        return new MenuPage(driver);

    }

    public MenuPage selectDelivery(String streetAddress , String zipcode){
        Log.info("Waiting for Delivery Block to be visible");
        waitForElementToBeVisible(deliveryStreetAddressTextField);
        enterText(deliveryStreetAddressTextField,streetAddress);
        Log.info("Entered Delivery Address as :"+streetAddress);
        enterText(deliveryZipTextField,zipcode);
        Log.info("Entered Zipcode as :"+zipcode);
        click(deliverySubmitBtn);
        Log.info("Clicked on Submit button in Delivery block");
        return  new MenuPage(driver);

    }
}
