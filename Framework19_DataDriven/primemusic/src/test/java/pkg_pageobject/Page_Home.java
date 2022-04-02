package pkg_pageobject;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pkg_global.GlobalObjects;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class Page_Home extends GlobalObjects {

    private static final Logger myLog = Logger.getLogger(Page_Home.class);
    private WebDriverWait localWait    = null;
    private WebDriver localDriver     = null;

    public Page_Home(WebDriver localDriver){
        PageFactory.initElements(localDriver, this);
        localDriver = localDriver;
        localWait = utilGeneral.ExplicitWaitNormal();
    }

    @FindBy(how = How.CSS, using = "a[class='login']")
    private WebElement Btn_SignIn;
    @FindBy(how = How.ID, using = "email")
    private WebElement Txtbx_SignInEmail;
    @FindBy(how = How.ID, using = "passwd")
    private WebElement Txtbx_SignInPwd;
    @FindBy(how = How.CSS, using = "i[class='icon-lock left']")
    private WebElement Btn_SignInActual;
    @FindBy(how = How.CSS, using = "a[title='Log me out']")
    private List<WebElement> lstBtn_SignOut;
    @FindBy(how = How.ID, using = "search_query_top")
    private WebElement Txtbx_Search;


    public void PerformLogin(String sUser, String sPwd){
        Btn_SignIn.click();
        utilGeneral.ImplicitWait(4000);
        Txtbx_SignInEmail.sendKeys(sUser);
        Txtbx_SignInPwd.sendKeys(sPwd);
        Btn_SignInActual.click();
        localWait.until(ExpectedConditions.visibilityOf(Txtbx_Search));
    }

    public void ValidateLogin(boolean bExpected){
        if(bExpected){
            if(lstBtn_SignOut.size() > 0){
                hmGlobalData.put("login_success", "yes");
                myLog.info("Log: Login check passed");
                return;
            }else{
                Assert.fail("Log: Login check NOT passed");
            }
        }else{
            if(lstBtn_SignOut.size() > 0){
                hmGlobalData.put("login_success", "yes");
                Assert.fail("Log: Login check NOT passed");
            }else{
                myLog.info("Log: Login check passed");
                return;
            }
        }
        Assert.fail("Log: Login check NOT passed");
    }

    public void ActualLogout(){
        if(lstBtn_SignOut.size() > 0){
            // Actual log out
            lstBtn_SignOut.get(0).click();
            utilGeneral.ImplicitWait(4000);
        }
    }


}
