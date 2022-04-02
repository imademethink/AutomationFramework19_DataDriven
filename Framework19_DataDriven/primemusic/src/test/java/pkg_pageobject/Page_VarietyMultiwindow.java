package pkg_pageobject;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pkg_locators.Locator_Multiwindow;

import java.util.Set;

public class Page_VarietyMultiwindow extends Locator_Multiwindow {

    private static final Logger myLog = Logger.getLogger(Page_VarietyMultiwindow.class);
    private WebDriverWait localWait    = null;
    private WebDriver localDriver      = null;

    public Page_VarietyMultiwindow(WebDriver driver){
        localDriver = driver;
        localWait = utilGeneral.ExplicitWaitNormal();
    }

    public void PerformWindowHandling(){
        hmGlobalData.put("sCurrentWindowHandle",localDriver.getWindowHandle());

        localDriver.findElement(Lnk_Multiwindow).click();
        localWait.until(ExpectedConditions.numberOfWindowsToBe(2));
    }

    public void ValidateMultiWindow(){

        Set<String> allWindowHandles = localDriver.getWindowHandles();
        if(2 != allWindowHandles.size()){
            Assert.fail("Log: Multi window validation NOT successful");
        }

        for(String handle : allWindowHandles) {
            if(handle.toLowerCase() == hmGlobalData.get("sCurrentWindowHandle").toLowerCase() ){
                localDriver.switchTo().window(handle);
                localDriver.close();
            }
        }
        localDriver.switchTo().window(hmGlobalData.get("sCurrentWindowHandle"));
        myLog.info("Log: Multi window validation successful");

    }



}
