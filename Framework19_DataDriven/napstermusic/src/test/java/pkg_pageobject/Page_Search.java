package pkg_pageobject;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pkg_global.GlobalObjects;
import java.util.List;
import java.util.Random;

public class Page_Search extends GlobalObjects {

    private static final Logger myLog = Logger.getLogger(Page_Search.class);
    private WebDriverWait localWait    = null;
    private WebDriver localDriver     = null;

    public Page_Search(WebDriver localDriver){
        PageFactory.initElements(localDriver, this);
        localDriver = localDriver;
        localWait = utilGeneral.ExplicitWaitNormal();
    }

    @FindBy(how = How.ID, using = "search_query_top")
    private WebElement Txtbx_Search;
    @FindBy(how = How.CSS, using = "button[name='submit_search']")
    private WebElement Btn_SearchSubmit;
    @FindBy(how = How.ID, using = "uniform-selectProductSort")
    private List<WebElement> lstDrpdwn_Sort;
    @FindBy(how = How.XPATH, using = "//span[text()='Add to cart']")
    private List<WebElement> lstBtn_AddToCart;
    @FindBy(how = How.CSS, using = "div[class='product-image-container']")
    private List<WebElement> lstBtn_InStock;
    @FindBy(how = How.CSS, using = "a[title='Proceed to checkout']")
    private WebElement Btn_ProceedToCheckout;
    //@FindBy(how = How.ID, using = "list")
    @FindBy(how = How.CSS, using = "a[title='List']")
    private WebElement Element_ViewAsList;



    public void PerformSearch(String sSearchItem){
        Txtbx_Search.sendKeys(sSearchItem);
        Btn_SearchSubmit.click();
        localWait.until(ExpectedConditions.visibilityOf(Txtbx_Search));
    }

    public void ValidateSearchResult(boolean bExpectedResult){
        if(bExpectedResult){
            if(lstDrpdwn_Sort.size() > 0){
                myLog.info("Log: Search activity passed");
                return;
            }
        }else{
            if(lstDrpdwn_Sort.size() == 0){
                myLog.info("Log: Search activity passed");
                return;
            }
        }
        Assert.fail("Log: Search activity NOT passed");
    }

    public void AddItemToCart(){
        Actions objActions = new Actions(realDriver);
        objActions.moveToElement(lstBtn_InStock.get(0)).click().perform();
        localWait.until(ExpectedConditions.visibilityOf(lstBtn_AddToCart.get(0)));

        lstBtn_AddToCart.get(0).click();
        localWait.until(ExpectedConditions.visibilityOf(Btn_ProceedToCheckout));

        Btn_ProceedToCheckout.click();
    }


}
