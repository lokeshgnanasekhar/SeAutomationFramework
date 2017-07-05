package testutils;

import modules.DataSource;
import modules.ProjectConfigReader;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import pages.HomePage;
import modules.Log;

import java.io.*;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by Lokesh_GnanaSekhar on 6/21/2017.
 */
public class TestBase {

    WebDriver driver;
    ProjectConfigReader projectConfigReader;


    @BeforeMethod
    public void beforeTestExecution(Method method) {
        initialize(method.getName());
        createDriver();


    }

    @AfterMethod
    public void afterTestExecution(Method method) {
        driver.quit();
        Log.endTestCase(method.getName());


    }

    public void initialize(String testcaseName){

        projectConfigReader = new ProjectConfigReader();
        Logger logger=Logger.getLogger(projectConfigReader.getURL());


        try {
            ClassLoader classLoader = getClass().getClassLoader();
            File file = new File(classLoader.getResource("ProjectConfig.properties").getFile());
            InputStream input = new FileInputStream(file);
            PropertyConfigurator.configure(input);
            Log.startTestCase(testcaseName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void createDriver() {

        String browserName = projectConfigReader.getBrowserName();

        switch (browserName){
            case "chrome" :
                System.setProperty("webdriver.chrome.driver","./src/test/java/testresources/chromedriver.exe");
                driver = new ChromeDriver();
                Log.info("Launching Chrome Browser");
                break;
            case "firefox" :
                System.setProperty("webdriver.gecko.driver","./src/test/java/testresources/geckodriver.exe");
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", true);
                driver = new FirefoxDriver(capabilities);
                Log.info("Launching Firefox Browser");
                break;
            case "ie" :
                System.setProperty("webdriver.ie.driver","./src/test/java/testresources/IEDriverServer.exe");
                driver = new InternetExplorerDriver();
                Log.info("Launching Internet Explorer Browser");
                break;
            default:
                Log.error("No Suitable Driver available for the Browser: "+browserName);
                System.exit(0);

        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    public HomePage launchWebsite() {
        driver.get(projectConfigReader.getURL());
        Log.info("Launching "+projectConfigReader.getURL());
        return new HomePage(driver);
    }

    @DataProvider(name = "TestDataProvider")
    public static Object[][] testDataProvider(Method method) {

        Object testData[][] = null;
        try {
            testData = DataSource.getDataForTestCase(method.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return testData;
    }

    @DataProvider(name = "TestDataProvider_Optimized")
    public static Object[][] testDataProvider_Optimized(Method method) {

        Object testData[][] = null;
        try {
            List<Map<String, String>> testDataList = DataSource
                    .getDataForTestCase_Optimized(method.getName());
            testData = new Object[testDataList.size()][1];
            for (int i = 0; i < testDataList.size(); i++) {
                testData[i][0] = testDataList.get(i);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return testData;
    }


}
