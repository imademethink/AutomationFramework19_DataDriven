package pkg_pageobject;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import pkg_global.GlobalObjects;

import java.util.List;

public class Page_Payment extends GlobalObjects {

    private static final Logger myLog = Logger.getLogger(Page_Payment.class);
    private WebDriverWait localWait    = null;
    private WebDriver localDriver     = null;

    public Page_Payment(WebDriver localDriver){
        PageFactory.initElements(localDriver, this);
        localDriver = localDriver;
        localWait = utilGeneral.ExplicitWaitNormal();
    }

    @FindBy(how = How.ID, using = "search_query_top")
    private WebElement Txtbx_Search;
    @FindBy(how = How.CSS, using = "button[name='submit_search']")
    private WebElement Btn_SearchSubmit;
    @FindBy(how = How.ID, using = "cart_title")
    private List<WebElement> lstBtn_AddToCart;

    @FindBy(how = How.CLASS_NAME, using = "cheque")
    private WebElement Btn_PayByCheque;
    @FindBy(how = How.CLASS_NAME, using = "bankwire")
    private WebElement Btn_PayByBankCard;
    //@FindBy(how = How.CSS, using = "button[type='submit']")
    @FindBy(how = How.XPATH, using = "//span[text()='I confirm my order']")
    private WebElement Btn_ConfirmOrder;
    @FindBy(how = How.XPATH, using = "//h1[text()='Order confirmation']")
    private WebElement Txt_CartSummary;


    public void PerformPayment(String sPaymentMethod){
        if(sPaymentMethod.equals("paybycheque")){
            Btn_PayByCheque.click();
            utilGeneral.ImplicitWait(3000);
            Btn_ConfirmOrder.click();
        }else if(sPaymentMethod.equals("paybycard")){
            Btn_PayByBankCard.click();
            utilGeneral.ImplicitWait(3000);
            Btn_ConfirmOrder.click();
        }else{
            // do nothing
        }
    }

    public void ValidatePayment(boolean bExpected){
        if(bExpected){
            try{
                if(Txt_CartSummary.isDisplayed()){ return;}
            }catch (Exception payment){}
        }else{
            try{
                if(! Btn_PayByCheque.isDisplayed()){return;}
            }catch (Exception payment){}
        }
        Assert.fail("Log: Payment activity NOT passed");
    }




}
