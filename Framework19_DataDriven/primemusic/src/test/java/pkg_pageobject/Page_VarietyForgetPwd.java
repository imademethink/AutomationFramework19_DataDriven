package pkg_pageobject;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pkg_locators.Locator_ForgetPwd;

import java.util.Set;

public class Page_VarietyForgetPwd extends Locator_ForgetPwd {

    private static final Logger myLog = Logger.getLogger(Page_VarietyForgetPwd.class);
    private WebDriverWait localWait    = null;
    private WebDriver localDriver      = null;

    public Page_VarietyForgetPwd(WebDriver driver){
        localDriver = driver;
        localWait   = utilGeneral.ExplicitWaitNormal();
    }

    public void PerformForgetPassword(){
        localDriver.findElement(Txtbx_ForgetPwd).sendKeys("random.123@io.com");
        localDriver.findElement(Txtbx_ForgetPwdSubmit).click();
        localWait.until(ExpectedConditions.visibilityOfElementLocated(Lnk_ForgetPwdSuccess));
    }

    public void ValidateForgetPassword(){
        if(localDriver.findElement(Lnk_ForgetPwdSuccess).isDisplayed()){
            myLog.info("Log: Forget password validation successful");
        }else{
            Assert.fail("Log: Forget password validation NOT successful");
        }
    }



}
