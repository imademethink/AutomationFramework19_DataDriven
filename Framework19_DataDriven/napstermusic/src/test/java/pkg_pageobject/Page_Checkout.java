package pkg_pageobject;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pkg_global.GlobalObjects;

import java.util.List;

public class Page_Checkout extends GlobalObjects {

    private static final Logger myLog = Logger.getLogger(Page_Checkout.class);
    private WebDriverWait localWait    = null;
    private WebDriver localDriver     = null;

    public Page_Checkout(WebDriver localDriver){
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
    @FindBy(how = How.CSS, using = "a[title='Proceed to checkout']")
    private WebElement Btn_ProceedToCheckout;
    @FindBy(how = How.ID, using = "cart_title")
    private WebElement Txt_CartSummary;
    @FindBy(how = How.CSS, using = "tr[id*='product_']")
    private WebElement Element_OverallProductsCheckout;
    @FindBy(how = How.CSS, using = "a[title='Log me out']")
    private List<WebElement> lstBtn_SignOut;
    @FindBy(how = How.CSS, using = "img[alt='Printed Summer Dress']")
    private List<WebElement> lstElement_SearchResult;
    @FindBy(how = How.NAME, using = "Submit")
    private WebElement Btn_CheckoutSubmit;
    @FindBy(how = How.CSS, using = "input[class*='cart_quantity_input']")
    private WebElement Txtbx_Checkoutqty;
    //@FindBy(how = How.CSS, using = "a[title='Add to cart']")
    @FindBy(how = How.ID, using = "add_to_cart")
    private List<WebElement> lstBtn_AddToCartSmall;
    @FindBy(how = How.CSS, using = "a[title='Close']")
    private WebElement Btn_InvalidQtyPopup;
    @FindBy(how = How.CSS, using = "i[class*='icon-chevron-right']")
    private List<WebElement> lstBtn_ProceedToCheckout2;
    @FindBy(how = How.NAME, using = "processAddress")
    private WebElement Btn_ProceedToCheckout3;
    @FindBy(how = How.ID, using = "cgv")
    private WebElement CheckBox_TermsnCondn;
    @FindBy(how = How.NAME, using = "processCarrier")
    private WebElement Btn_ProceedToCheckout4;
    @FindBy(how = How.ID, using = "email")
    private WebElement Txtbx_Email;



    public void PerformCheckout(int nQty){
        lstElement_SearchResult.get(0).click();
        utilGeneral.ImplicitWait(4000);
        lstBtn_AddToCartSmall.get(0).click();
        utilGeneral.ImplicitWait(4000);
        //localWait.until(ExpectedConditions.visibilityOf(Btn_ProceedToCheckout));
        Btn_ProceedToCheckout.click();
        utilGeneral.ImplicitWait(4000);
        //localWait.until(ExpectedConditions.visibilityOf(Btn_ProceedToCheckout2));
        Txtbx_Checkoutqty.clear();
        Txtbx_Checkoutqty.sendKeys(String.valueOf(nQty));
        utilGeneral.ScrollUsingJavaScriptBottom();
        utilGeneral.Sleep(4000);
        lstBtn_ProceedToCheckout2.get(lstBtn_ProceedToCheckout2.size()-1).click();
        utilGeneral.ImplicitWait(4000);
    }

    public void ValidateCheckoutResult(boolean bExpected){
        if(bExpected){
            try{
                if(Btn_ProceedToCheckout3.isDisplayed()){
                    Btn_ProceedToCheckout3.click();
                    utilGeneral.ImplicitWait(4000);
                    utilGeneral.ScrollUsingJavaScriptBottom();
                    CheckBox_TermsnCondn.click();
                    Btn_ProceedToCheckout4.click();
                    return;
                }
            }catch (Exception checkout){}
        }else{
            try{
                if(Btn_InvalidQtyPopup.isDisplayed()){
                    Btn_InvalidQtyPopup.click();
                    myLog.info("Log: Checkout validation successful");
                    return;
                }
            }catch (Exception checkout){}
            try{
                if(Txtbx_Email.isDisplayed()){
                    myLog.info("Log: Checkout validation successful");
                    return;
                }
            }catch (Exception checkout){}
        }
        Assert.fail("Log: Search activity NOT passed");
    }




}
