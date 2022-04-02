package pkg_pageobject;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pkg_locators.Locator_Popup;

public class Page_VarietyPopup extends Locator_Popup {

    private static final Logger myLog = Logger.getLogger(Page_VarietyPopup.class);
    private WebDriverWait localWait    = null;
    private WebDriver localDriver      = null;

    public Page_VarietyPopup(WebDriver driver){
        localDriver = driver;
        localWait   = utilGeneral.ExplicitWaitNormal();
    }


    public void PerformPopupTest(){
        localDriver.findElement(Lnk_Popup).click();
        utilGeneral.ImplicitWait(8000);
        localWait.until(ExpectedConditions.visibilityOfElementLocated(Btn_Popupclose));
    }

    public void ValidatePopup(){
        if(localDriver.findElement(Btn_Popupclose).isDisplayed()){
            localDriver.findElement(Btn_Popupclose).click();
            myLog.info("Log: Pop up validation successful");
        }else{
            Assert.fail("Log: Pop up validation NOT successful");
        }
    }





}
