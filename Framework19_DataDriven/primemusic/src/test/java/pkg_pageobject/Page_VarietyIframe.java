package pkg_pageobject;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pkg_locators.Locator_Iframe;

public class Page_VarietyIframe extends Locator_Iframe {

    private static final Logger myLog = Logger.getLogger(Page_VarietyIframe.class);
    private WebDriverWait localWait    = null;
    private WebDriver localDriver      = null;

    public Page_VarietyIframe(WebDriver driver){
        localDriver = driver;
        localWait = utilGeneral.ExplicitWaitNormal();
    }


    public void PerformIframeHandling(){
        localDriver.switchTo().frame("mce_0_ifr");
        localDriver.findElement(Txtbx_Iframe).clear();
        localDriver.findElement(Txtbx_Iframe).sendKeys("Summer of 69");
    }

    public void ValidateIframe(){
        localDriver.switchTo().defaultContent();
        myLog.info("Log: Iframe validation successful");
    }



}
