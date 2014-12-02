package core;

import org.testng.annotations.*;

/**
 * Run & Stop browser only one time
 */

public class BaseTest {

    @BeforeSuite
    public void init() {
        Driver.init();
    }

    @AfterSuite
    public void cleanup() {
        Driver.tearDown();
    }
}
