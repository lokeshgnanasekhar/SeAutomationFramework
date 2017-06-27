package pages;

import modules.EventHandler;
import modules.Log;
import org.openqa.selenium.Alert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * Created by Lokesh_GnanaSekhar on 6/22/2017.
 */
public class HomePage extends EventHandler {

    @FindBy(xpath = "//a[@id='accountAccordion']/strong")
    WebElement signinText;

    @FindBy(id = "omnibar-account-sign-in-email")
    WebElement emailAddressTextField;

    @FindBy(id = "omnibar-account-sign-in-password")
    WebElement passwordTextField;

    @FindBy(xpath = "//input[@value='Sign In']")
    WebElement signinSubmitButton;

    @FindBy(xpath = "//div[@id='modal-T38']/div/header/a")
    WebElement promoPopupCloseButton;

    @FindBy(id = "omnibar-recaptcha_error_msg")
    WebElement errorMsgForInvalidLogin;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        if (!(driver.getTitle().contains("Order for Delivery or Carryout"))) {
            throw new IllegalStateException("Home page is expected, but not displayed!");
        }

        Log.info("Navigated to Home Page");
    }

    public MenuPage login(String emailId, String password) {

       // closePromoPopup();
        if (waitForElementToBeVisible(signinText)) {
            click(signinText);
            Log.info("Clicked on Signin Text");
            enterText(emailAddressTextField, emailId);
            Log.info("Entered "+emailId+" in E-maild field");
            enterText(passwordTextField, password);
            Log.info("Entered "+password+" in Password field");
            click(signinSubmitButton);
            Log.info("Clicked on Submit button");
            waitFor(5);
            return new MenuPage(driver);
        }
        return null;
    }

    public void invalidLogin(String emailId, String password) {

        //closePromoPopup();
        if (waitForElementToBeVisible(signinText)) {
            click(signinText);
            Log.info("Clicked on Signin Text");
            enterText(emailAddressTextField, emailId);
            Log.info("Entered "+emailId+" in E-maild field");
            enterText(passwordTextField, password);
            Log.info("Entered "+password+" in Password field");
            click(signinSubmitButton);
            Log.info("Clicked on Submit button");
            if(errorMsgForInvalidLogin.isDisplayed()){
               Log.info("Error Dialog Appeared");
            }

        }
    }

    public void closePromoPopup() {
        waitForPageToLoad();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
        }
        click(promoPopupCloseButton);
        waitFor(3);

    }
}
