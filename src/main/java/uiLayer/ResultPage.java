package uiLayer;

import org.testng.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class ResultPage extends BasePage {

    List<String[]> resultStore;
    @FindBy(css = "#lindm div.durl>span")
    private List<WebElement> resultsUrlList;

    @FindBy (css = "#lindm .ptbs>div:first-child")
    private List<WebElement> resultsTitleList;

    @FindBy (css = "#lindm div.abstract")
    private List<WebElement> resultsTextList;

    @FindBy (css = ".txt3.title.l_nu")
    private WebElement nextPageLink;

    @FindBy (css = "#paging>div.dsi>div.pgc:nth-child(1)")
    private WebElement firstBtn;

    @FindBy (css = "#paging>div.dsi>div.pgc:nth-child(2)")
    private WebElement secondBtn;

    public List<String[]> printResult(int expectedSize) {

        resultStore = new ArrayList<String[]>();
        checkAllListSize(expectedSize);

        System.out.println("- PRINT RESULTS -");
        for(int i=0; i<expectedSize; i++) {
            String[] line = new String[3];
            System.out.println((i+1) + ")            | ");
            System.out.println("TITLE:        | " + resultsTitleList.get(i).getText());
            line[0] = resultsTitleList.get(i).getText();
            System.out.println("URL:          | " + resultsUrlList.get(i).getText());
            line[1] = resultsUrlList.get(i).getText();
            System.out.println("DESCRIPTIONS: | " + resultsTextList.get(i).getText().replaceAll(" Verder lezen »", ""));
            line[2] = resultsTextList.get(i).getText().replaceAll(" Verder lezen »", "");
            resultStore.add(line);
            System.out.println("--------------|");
        }
        Assert.assertEquals(resultStore.size(), expectedSize, "Size collection was not as expected! \n");
        return resultStore;
    }

    public void checkAllListSize(int quantity) {
        Assert.assertEquals(resultsTitleList.size(), quantity, "Size 'resultTitleList' was not as expected! \n");
        Assert.assertEquals(resultsUrlList.size(), quantity, "Size 'resultUrlList' was not as expected! \n");
        Assert.assertEquals(resultsTextList.size(), quantity, "Size 'resultTextList' was not as expected! \n");
    }

    /**
     * Go to the 'Next' (second) page and check paging buttons
     */
    public void goSecondPage() {

        Assert.assertEquals(firstBtn.getAttribute("class"), "pgc pgcsel");
        Assert.assertEquals(secondBtn.getAttribute("class"), "pgc");
        nextPageLink.click();
        Assert.assertEquals(firstBtn.getAttribute("class"), "pgc");
        Assert.assertEquals(secondBtn.getAttribute("class"), "pgc pgcsel");
    }
}
