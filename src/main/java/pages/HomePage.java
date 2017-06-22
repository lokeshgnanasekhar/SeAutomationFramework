package pages;

import modules.EventHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(xpath = "//*[@id='modal-T38']/div/header/a']")
    WebElement promoPopupCloseButton;


    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        if (!(driver.getTitle().contains("Order for Delivery or Carryout"))) {
            throw new IllegalStateException("My Template page is expected, but not displayed!");
        }
        ;
    }

    public void login(String emailId, String password) {

        if(waitForElementToBeVisible(promoPopupCloseButton)){
            click(promoPopupCloseButton);
        }
        if (waitForElementToBeVisible(signinText)) {
            click(signinText);
            enterText(emailAddressTextField, emailId);
            enterText(passwordTextField, password);
            click(signinSubmitButton);
        }

    }
}
