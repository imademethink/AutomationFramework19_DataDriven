package pkg_hooks;

import com.cucumber.listener.Reporter;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import pkg_global.GlobalObjects;
import pkg_utility.Utility_General;
import pkg_utility.Utility_Filehandler;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HooksHelper extends GlobalObjects {

    private static final Logger myLog = Logger.getLogger(HooksHelper.class);

    @Before  // before each scenario
    public void beforeEachScenario(){
        myLog.info("Log: Before every Scenario hook");
        new GlobalObjects().getDriver();
        utilFileHandler= new Utility_Filehandler();
        utilGeneral    = new Utility_General();
        utilGeneral.InitAllPageObject();
    }

    @After  // after each scenario
    public void afterEachScenarioWithScreenShot(Scenario scenario){
        myLog.info("Log: After the every Scenario hook");

        // Add screenshot only if scenario fails
        if (scenario.isFailed()) {
            myLog.info("Log: Attaching screenshot for failed scenario "+ scenario.getName());
            String sDDMMYY = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
            File src       = ((TakesScreenshot) realDriver).getScreenshotAs(OutputType.FILE);
            String dest    = System.getProperty("user.dir") + "//target//html//"+"screenshot_"+sDDMMYY+".jpg";
            File finalDestination = new File(dest);

            try {
                FileUtils.copyFile(src, finalDestination);
                dest    = "screenshot_"+sDDMMYY+".jpg";
                Reporter.addScreenCaptureFromPath(dest);
            }catch (IOException eScreenshot) {
                eScreenshot.printStackTrace();
            }
        }
        realDriver.quit();
        realDriver = null;
        bBrowserInvoked = false;
        myLog.info("Log: Chrome browser is closed\n");
    }


}
