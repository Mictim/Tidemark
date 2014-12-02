package uiLayer;

import core.Driver;
import org.openqa.selenium.support.PageFactory;

/**
 * Abstract class for initialize Page Object pattern
 */

public abstract class BasePage {

    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }
}
