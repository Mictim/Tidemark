package uiLayer;

import core.Config;
import core.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static core.Utils.setText;

public class HomePage extends BasePage {

    private WebDriverWait wait = new WebDriverWait(Driver.get(), 10);

    @FindBy(name = "q")
    private WebElement questionFld;

    @FindBy(css = "input[type=submit]")
    private WebElement searchBtn;

    @FindBy(css = "#paging")
    private WebElement paging;

    /**
     * Static method for load start URL from config/property file
     * @return HomePage object
     * usually this method using for load start page
     */
    public static HomePage open() {

        Driver.get().get(Config.getBaseURL());
        return new HomePage();
    }

    private void setQuestionFld(String text) {
        setText(questionFld, text);
    }

    private void clickSearchBtn() {
        searchBtn.click();
    }

    public ResultPage searchText(String text) {
        setQuestionFld(text);
        clickSearchBtn();
        //wait.until(ExpectedConditions.visibilityOf(paging));
        wait.until(ExpectedConditions.elementToBeClickable(paging));
        return new ResultPage();
    }
}
