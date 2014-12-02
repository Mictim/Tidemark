package core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.util.concurrent.TimeUnit;

import static core.Config.getProperty;

public class Driver {

    /**
     * something like 'Singleton pattern'
     */
    private static WebDriver driver;

    /**
     * get one static Driver instance
     *
     * @return Static singleton driver
     */
    public static WebDriver get() {
        return driver;
    }

    /**
     * Setup WebDriver implementation
     *
     * @param driverInput any WebDriver interface
     */
    public static void set(WebDriver driverInput) {
        driver = driverInput;
    }

    /**
     * Initialize singleton static driver with required webdriver implementation.
     * Be careful switch with strings required JDK7 or above
     */
    public static void init() {
        WebDriver driverInput;
        switch (getProperty("test.browser")) {
            case "firefox":
                driverInput = new FirefoxDriver();
                break;
            case "iexplore":
                System.setProperty("webdriver.ie.driver", System.getProperty("user.dir") + "\\driver\\IEDriverServer.exe");
                driverInput = new InternetExplorerDriver();
                break;
            case "chrome":
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\driver\\chromedriver.exe");
                driverInput = new ChromeDriver();
                break;
            default:
                throw new AssertionError("Unsupported browser: " + getProperty("test.browser"));
        }

        driverInput.manage().timeouts().implicitlyWait(Integer.parseInt(getProperty("test.timeout")), TimeUnit.SECONDS);
        Driver.set(driverInput);
        get().manage().window().setSize(new Dimension(1024, 768));
    }

    /**
     * Close driver connection. Usually using after running all tests, at the end of Test Suite
     */
    public static void tearDown() {
        Driver.get().quit();
    }
}