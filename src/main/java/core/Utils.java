package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Utils {

    public static boolean isElementPresent(By locator) {
        Driver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        List<WebElement> elements = Driver.get().findElements(locator);
        Driver.get().manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("test.timeout")), TimeUnit.SECONDS);
        return elements.size() > 0 && elements.get(0).isDisplayed();
    }

    public static boolean isElementPresent(By locator, int timeout) {
        Driver.get().manage().timeouts().implicitlyWait(timeout, TimeUnit.MILLISECONDS);
        List<WebElement> elements = Driver.get().findElements(locator);
        Driver.get().manage().timeouts().implicitlyWait(Integer.parseInt(Config.getProperty("test.timeout")), TimeUnit.SECONDS);
        return elements.size() > 0 && elements.get(0).isDisplayed();
    }

    public static WebElement setText(WebElement someField, String someText){
        someField.clear();
        someField.sendKeys(someText);
        return someField;
    }
}
