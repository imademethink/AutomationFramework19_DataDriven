package pkg_pageobject;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pkg_global.GlobalObjects;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Page_Register extends GlobalObjects {

    private static final Logger myLog = Logger.getLogger(Page_Register.class);
    private WebDriverWait localWait    = null;
    private WebDriver localDriver     = null;

    public Page_Register(WebDriver localDriver){
        PageFactory.initElements(localDriver, this);
        localDriver = localDriver;
        localWait = utilGeneral.ExplicitWaitNormal();
    }

    @FindBy(how = How.ID, using = "email_create")
    private WebElement Txtbx_RegisterEmail;
    @FindBy(how = How.CSS, using = "i[class*='icon-user']")
    private WebElement Btn_CreateAccount;
    @FindBy(how = How.ID, using = "id_gender1")
    private WebElement RadioBtn_Gender;
    @FindBy(how = How.ID, using = "customer_firstname")
    private WebElement Txtbx_CustFirstName;
    @FindBy(how = How.ID, using = "customer_lastname")
    private WebElement Txtbx_CustLastName;
    @FindBy(how = How.ID, using = "passwd")
    private WebElement Txtbx_Password;
    @FindBy(how = How.ID, using = "days")
    private WebElement Drpdwn_DD;
    @FindBy(how = How.ID, using = "months")
    private WebElement Drpdwn_MM;
    @FindBy(how = How.ID, using = "years")
    private WebElement Drpdwn_YYYY;
    @FindBy(how = How.ID, using = "firstname")
    private WebElement Txtbx_AddrFirstName;
    @FindBy(how = How.ID, using = "lastname")
    private WebElement Txtbx_AddrLastName;
    @FindBy(how = How.ID, using = "address1")
    private WebElement Txtbx_Addr1;
    @FindBy(how = How.ID, using = "city")
    private WebElement Txtbx_City;
    @FindBy(how = How.ID, using = "id_state")
    private WebElement Drpdwn_State;
    @FindBy(how = How.ID, using = "postcode")
    private WebElement Txtbx_Postcode;
    @FindBy(how = How.ID, using = "other")
    private WebElement Txtbx_AdditionalInfo;
    @FindBy(how = How.ID, using = "phone_mobile")
    private WebElement Txtbx_Mobile;
    @FindBy(how = How.ID, using = "submitAccount")
    private WebElement Btn_Submit;
    @FindBy(how = How.ID, using = "search_query_top")
    private WebElement Txtbx_Search;
    @FindBy(how = How.CSS, using = "a[title='Log me out']")
    private List<WebElement> lstBtn_SignOut;



    public void RegistrationStart(){
        Txtbx_RegisterEmail.sendKeys(hmUserData.get("Email") +
                                       new Random().nextInt(99999) + "@mailinator.com");
        Btn_CreateAccount.click();
        localWait.until(ExpectedConditions.visibilityOf(RadioBtn_Gender));
    }

    public void RegistrationFormFill(){
        RadioBtn_Gender.click();
        Txtbx_CustFirstName.sendKeys(hmUserData.get("NameFirst"));
        Txtbx_CustLastName.sendKeys(hmUserData.get("NameLast"));
        Txtbx_Password.sendKeys(hmUserData.get("Password"));

        new Select(Drpdwn_DD).selectByValue(hmUserData.get("DOB_DD"));
        new Select(Drpdwn_MM).selectByValue(hmUserData.get("DOB_MM"));
        new Select(Drpdwn_YYYY).selectByValue(hmUserData.get("DOB_YYYY"));

        Txtbx_AddrFirstName.sendKeys(hmUserData.get("AddressNameFirst"));
        Txtbx_AddrLastName.sendKeys(hmUserData.get("AddressNameLast"));
        Txtbx_Addr1.sendKeys(hmUserData.get("Address"));
        Txtbx_City.sendKeys(hmUserData.get("City"));
        new Select(Drpdwn_State).selectByVisibleText(hmUserData.get("State"));
        Txtbx_Postcode.sendKeys(hmUserData.get("Zip"));

        Txtbx_AdditionalInfo.sendKeys(hmUserData.get("AdditionalInfo"));
        Txtbx_Mobile.sendKeys(hmUserData.get("Mobile"));

        Btn_Submit.click();
        localWait.until(ExpectedConditions.visibilityOf(Txtbx_Search));
    }

    public void ValidateRegistration(){
        if(lstBtn_SignOut.size() > 0){
            myLog.info("Log: Registration is successful");
            // Actual log out
            lstBtn_SignOut.get(0).click();
            utilGeneral.ImplicitWait(4000);
            return;
        }
        Assert.fail("Log: Registration is NOT successful");
    }


}
