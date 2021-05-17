package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverSingleton {
    private static WebDriver driver;

    private DriverSingleton() {    }

    public static WebDriver getDriver() {
        if(driver == null) {
            Property property = new Property();
            System.setProperty(property.getChromeDriver(), "./drivers/chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            options.addArguments("start-maximized");
            driver = new ChromeDriver(options);
        }
        return driver;
    }

    public void quit() {
        driver.quit();
    }
}
