package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverSingleton;

@Getter
public class AuthPage {
    @FindBy(css="#loginAuth")
    private WebElement loginInput;

    @FindBy(css="#passwordAuth")
    private WebElement passwordInput;

    @FindBy(css="#submitBtnAuth")
    private WebElement submit;

    @FindBy(css = "input:invalid")
    private WebElement invalidInput;

    private WebDriver driver;

    public AuthPage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }
}
