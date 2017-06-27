package modules;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.util.concurrent.TimeUnit;

/**
 * Created by Lokesh_GnanaSekhar on 6/21/2017.
 */
public class EventHandler {

    public WebDriver driver;

    /*
	 * Clicking a Button , Link Text , Partial LinkText
	 */
    public void click(WebElement element) {
        try {
            waitForElementToBeVisible(element);
            element.click();
        } catch (ElementNotVisibleException expection) {
            System.out.println("Unable to Locate the element");
        } catch (Exception ex) {
            System.out.println("Exception Caught:" + ex.getMessage());
        }
    }

    /*
     * Sending Text to Input fields
     */
    public void enterText(WebElement element, String textToEnter) {
        try {
            waitForElementToBeVisible(element);
            element.clear();
            element.sendKeys(textToEnter);
        } catch (ElementNotVisibleException exception) {
            System.out.println("Unable to Locate the element");
        } catch (Exception ex) {
            System.out.println("Exception Caught:" + ex.getMessage());
        }
    }

    public boolean waitForElementToBeVisible(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 20);
            WebElement elementToWait = wait.until(ExpectedConditions
                    .visibilityOf(element));
            if (elementToWait != null) {
                return true;
            }

        } catch (ElementNotVisibleException exception) {
            System.out.println("Unable to Locate the element");
        } catch (Exception ex) {
            System.out.println("Exception Caught:" + ex.getMessage());
        }
        return false;

    }

    public void waitForPageToLoad(){
        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
    }

    public void waitFor(int time){
        try {
            Thread.sleep(time*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
