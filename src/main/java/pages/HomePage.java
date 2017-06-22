package pages;

import modules.EventHandler;
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
            throw new IllegalStateException("My Template page is expected, but not displayed!");
        }
        ;
    }

    public void login(String emailId, String password) {

        closePromoPopup();
        if (waitForElementToBeVisible(signinText)) {
            click(signinText);
            enterText(emailAddressTextField, emailId);
            enterText(passwordTextField, password);
            click(signinSubmitButton);

        }
    }

    public void invalidLogin(String emailId, String password) {

        closePromoPopup();
        if (waitForElementToBeVisible(signinText)) {
            click(signinText);
            enterText(emailAddressTextField, emailId);
            enterText(passwordTextField, password);
            click(signinSubmitButton);
            if(errorMsgForInvalidLogin.isDisplayed()){
               System.out.println("Error Dialog Appeared");
            }

        }
    }

    public void closePromoPopup() {
        waitForPageToLoad();
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle); // switch focus of WebDriver to the next found window handle (that's your newly opened window)
        }
        click(promoPopupCloseButton);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
