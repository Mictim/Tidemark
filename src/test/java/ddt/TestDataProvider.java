package ddt;

import org.testng.annotations.DataProvider;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * TestNG data provider for test methods.
 */
public class TestDataProvider {

    @DataProvider(name = "c0001", parallel=false)
    public static Iterator<Object[]> createDataC0001() {
        List<Object[]> data = new ArrayList<Object[]>();

        data.add(new Object[] {"tidemark", 10, 10});

        return data.iterator();
    }
}
