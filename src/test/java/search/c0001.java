package search;

import core.BaseTest;
import ddt.TestDataProvider;
import org.testng.Reporter;
import org.testng.annotations.Test;
import uiLayer.HomePage;
import uiLayer.ResultPage;

import java.util.List;

public class c0001 extends BaseTest {

    HomePage homePage;
    ResultPage resultPage;
    List<String[]> firstResultPage;
    List<String[]> secondResultPage;

    @Test(dataProvider = "c0001", dataProviderClass = TestDataProvider.class)
    public void runTest(String askTerm, int page1cnt, int page2cnt) {

        homePage = HomePage.open();
        Reporter.log("1). Opened home page");

        resultPage = homePage.searchText(askTerm);
        Reporter.log("2). Search text");

        firstResultPage = resultPage.printResult(page1cnt);
        Reporter.log("3) Check result quantity at the first page");

        resultPage.goSecondPage();
        Reporter.log("4) Go to the next page");

        secondResultPage = resultPage.printResult(page2cnt);
        Reporter.log("5) Check result quantity at the second page");

        System.out.println();
        System.out.println("Done");
        int cnt = countWord(askTerm);
        Reporter.log("\n<br>Number of found term '" + askTerm + "' equal " + cnt);
        System.out.println("\nNumber of found term '" + askTerm + "' equal " + cnt);
    }

    private int countWord(String text) {

        int cnt = 0;
        StringBuffer sb = new StringBuffer();

        //*Find from all stored data
        /*
        for(String[] line:firstResultPage) {
            for(String el: line) {
                sb.append(el.toLowerCase());
            }
        }
        for(String[] line:secondResultPage) {
            for(String el: line) {
                sb.append(el.toLowerCase());
            }
        }
        */

        //*Find from only 'description'
        for(String[] line:firstResultPage) {
            sb.append(line[2].toLowerCase());
        }
        for(String[] line:secondResultPage) {
            sb.append(line[2].toLowerCase());
        }

        System.out.println("--------------------------------");
        System.out.println(sb);
        Reporter.log(sb.toString() + "\n");
        System.out.println("--------------------------------");
        //*Search and count the number of times string appears
        while (sb.toString().contains(text.toLowerCase())) {
            cnt++;
            int start = sb.indexOf(text);
            int end = start + text.length();
            sb.delete(start, end);
        }
        return cnt;
    }
}
