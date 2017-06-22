package pages;

import modules.EventHandler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PageTemplate extends EventHandler{

    @FindBy(id="Element ID")
    WebElement ID_elementName;

    @FindBy(name ="Element Name")
    WebElement name_elementName;

    @FindBy(className="Element ClassName")
    WebElement class_elementName;

    @FindBy(linkText="Element linkText")
    WebElement link_elementName;

    @FindBy(partialLinkText="Element partialLinkText")
    WebElement Partial_linkelementName;

    @FindBy(xpath="Element Xpath")
    WebElement xpath_elementName;

    @FindBy(tagName="Element Tag")
    WebElement tag_elementName;

    @FindBy(css ="Element CSS")
    WebElement css_elementName;

    public PageTemplate(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        if(!(driver.getTitle().equals("My TemplatePage Title"))){
            throw new IllegalStateException("My Template page is expected, but not displayed!");
        };
    }

	/* Other Functionalities of WebPage*/

    public void clickTemplateButton()
    {
        click(ID_elementName);
    }

}
