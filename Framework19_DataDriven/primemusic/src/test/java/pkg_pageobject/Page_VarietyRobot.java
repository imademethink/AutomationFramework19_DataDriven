package pkg_pageobject;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pkg_global.GlobalObjects;
import pkg_locators.Locator_Popup;
import pkg_locators.Locator_VarietyOther;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

public class Page_VarietyRobot extends Locator_VarietyOther {

    private static final Logger myLog = Logger.getLogger(Page_VarietyRobot.class);
    private WebDriverWait localWait    = null;
    private WebDriver localDriver      = null;

    public Page_VarietyRobot(WebDriver driver){
        localDriver = driver;
        localWait   = utilGeneral.ExplicitWaitNormal();
    }


    public void InvokeJavascriptPop(){
        localDriver.findElement(Btn_AlertBox).click();
        utilGeneral.ImplicitWait(8000);
        localWait.until(ExpectedConditions.alertIsPresent());
        try{Thread.sleep(2000);}catch (Exception t){}
    }

    public void HandleJavascriptpopupUsingRobot(){
        try{
            Thread.sleep(2000);
            utilRobot                       = new Robot();
            utilRobot.keyPress(KeyEvent.VK_BACK_SPACE);
            Thread.sleep(2000);
            StringSelection stringSelection = new StringSelection("No time to die");
            Clipboard clipboard             = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            utilRobot.keyPress(KeyEvent.VK_CONTROL);
            utilRobot.keyPress(KeyEvent.VK_V);
            utilRobot.keyRelease(KeyEvent.VK_V);
            utilRobot.keyRelease(KeyEvent.VK_CONTROL);
            Thread.sleep(2000);
            utilRobot.keyPress(KeyEvent.VK_ENTER);
            Thread.sleep(4000);

        }catch (Exception robt){
            robt.printStackTrace();
            myLog.info("=========>>>>>>>"+robt.getStackTrace());
        }


    }



    public void HandleGoogleAds(){
    // TBD
    }

    public void GoogleAdsshouldbeClosed(){
        // TBD
    }






}
