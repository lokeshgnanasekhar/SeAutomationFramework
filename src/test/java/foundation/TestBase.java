package foundation;

import modules.ProjectConfigReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import pages.HomePage;

import java.util.concurrent.TimeUnit;

/**
 * Created by Lokesh_GnanaSekhar on 6/21/2017.
 */
public class TestBase {

    WebDriver driver;
    ProjectConfigReader projectConfigReader;


    @BeforeMethod
    public void beforeTestExecution() {
        initialize();
        createDriver();
        System.out.println("Before Test Execution");
    }

    @AfterMethod
    public void afterTestExecution() {
        driver.quit();
        System.out.println("After Test Execution");

    }

    public void initialize(){
        projectConfigReader = new ProjectConfigReader();
    }

    public void createDriver() {

        String browserName = projectConfigReader.getBrowserName();

        switch (browserName){
            case "chrome" :
                System.setProperty("webdriver.chrome.driver","./src/test/java/testresources/chromedriver.exe");
                driver = new ChromeDriver();
                break;
            case "firefox" :
                System.setProperty("webdriver.firefox.marionette","./src/test/java/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;
            case "ie" :
                System.setProperty("webdriver.ie.driver","./src/test/java/testresources/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                break;
            default:
                System.out.println("No Driver Available for a given Browser");

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    public HomePage launchWebsite() {
        driver.get(projectConfigReader.getURL());
        return new HomePage(driver);
    }




}
