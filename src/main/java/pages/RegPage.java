package pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverSingleton;

@Getter
public class RegPage {
    @FindBy(css="#loginReg")
    private WebElement loginInput;

    @FindBy(css="#emailReg")
    private WebElement emailInput;

    @FindBy(css="#passwordReg")
    private WebElement passwordInput;

    @FindBy(css="#passwordRepeatReg")
    private WebElement confirmPasswordInput;

    @FindBy(css="#submitBtnReg")
    private WebElement submit;

    @FindBy(css = "input:invalid")
    private WebElement invalidInput;

    private WebDriver driver;

    public RegPage() {
        driver = DriverSingleton.getDriver();
        PageFactory.initElements(driver, this);
    }
}
