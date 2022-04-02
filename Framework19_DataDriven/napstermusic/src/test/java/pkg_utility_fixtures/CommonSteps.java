package pkg_utility_fixtures;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import pkg_global.GlobalObjects;

public class CommonSteps extends GlobalObjects {
    private static final Logger myLog = Logger.getLogger(CommonSteps.class);

    @BeforeSuite
    public void CommonBeforeSuite(){
        myLog.info("=CommonBeforeSuite==");
    }

    @AfterSuite
    public void CommonAfterSuite(){
        myLog.info("=CommonAfterSuite==");
    }


    @BeforeTest
    public void CommonBeforeTest(){
        myLog.info("==CommonBeforeTest==");
    }

    @AfterTest
    public void CommonAfterTest(){
        myLog.info("==CommonAfterTest==");
    }

}
