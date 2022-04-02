package pkg_stepdefinitionEcom;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import pkg_global.GlobalObjects;
import pkg_pageobject.*;
import pkg_utility.Utility_Filehandler;

public class StepDef_DataDrivenExternalSource extends GlobalObjects {

    private static final Logger myLog = Logger.getLogger(StepDef_DataDrivenExternalSource.class);
    
    @When("^Fetch data from source \"(.*?)\" for case \"(.*?)\"$")
    public void fetch_data_from_source_for_case(String sExternalDataFilePath, String sDataDrivenCaseNum) {
        myLog.info("Log: Fetch data from "+sExternalDataFilePath + " for " + sDataDrivenCaseNum);
        new Utility_Filehandler().ExcelReadDataRowWithUniqueId(sDataDrivenCaseNum);

        myLog.info("Log: action_login      " + hmUserData.get("action_login"));
        myLog.info("Log: action_search     " + hmUserData.get("action_search"));
        myLog.info("Log: action_checkout   " + hmUserData.get("action_checkout"));
        myLog.info("Log: action_payment    " + hmUserData.get("action_payment"));
        myLog.info("Log: login_email       " + hmUserData.get("login_email"));
        myLog.info("Log: login_pwd         " + hmUserData.get("login_pwd"));
        myLog.info("Log: login_expected    " + hmUserData.get("login_expected"));
        myLog.info("Log: search_term       " + hmUserData.get("search_term"));
        myLog.info("Log: search_result_count_expected  " + hmUserData.get("search_result_count_expected"));
        myLog.info("Log: checkout_qty      " + hmUserData.get("checkout_qty"));
        myLog.info("Log: checkout_expected " + hmUserData.get("checkout_expected"));
        myLog.info("Log: payment_method    " + hmUserData.get("payment_method"));
        myLog.info("Log: payment_expected  " + hmUserData.get("payment_expected"));

        hmGlobalData.put("login_success", "no");
    }

    @When("^Perform login action$")
    public void perform_login_action() {
        myLog.info("Log: Navigating to login section " + hmGlobalData.get("sUrlHome"));
        realDriver.get(hmGlobalData.get("sUrlHome"));
        utilGeneral.ImplicitWait(8000);

        if(hmUserData.get("action_login").isEmpty()) return;

        myLog.info("Log: attempting login with given data");
        pgHome.PerformLogin(hmUserData.get("login_email"), hmUserData.get("login_pwd"));
    }

    @Then("^Validate login result$")
    public void validate_login_result() {
        if( hmUserData.get("action_login").isEmpty()) return;

        myLog.info("Log: validating login");
        if(hmUserData.get("login_expected").equals("success")){
            pgHome.ValidateLogin(true);
        }else{
            pgHome.ValidateLogin(false);
        }
    }

    @When("^Perform search action$")
    public void perform_search_action() {
        if( hmUserData.get("action_search").isEmpty()) return;

        myLog.info("Log: attempting search with given data");
        pgSearch.PerformSearch(hmUserData.get("search_term"));
    }

    @Then("^Validate search result$")
    public void validate_search_result() {
        if( hmUserData.get("search_result_count_expected").isEmpty()) return;

        myLog.info("Log: validating search");
        if(hmUserData.get("search_result_count_expected").equals("non zero")){
            pgSearch.ValidateSearchResult(true);
        }else{
            pgSearch.ValidateSearchResult(false);
        }
    }

    @When("^Perform checkout action$")
    public void perform_checkout_action() {
        if( hmUserData.get("action_checkout").isEmpty()) return;

        myLog.info("Log: attempting checkout with given quantity");
        pgCheckout.PerformCheckout(Integer.valueOf(hmUserData.get("checkout_qty")));
    }

    @Then("^Validate checkout result$")
    public void validate_checkout_result() {
        if( hmUserData.get("checkout_expected").isEmpty()) return;

        myLog.info("Log: validating checkout");
        if(hmUserData.get("checkout_expected").equals("payment_screen")){
            pgCheckout.ValidateCheckoutResult(true);
        }else{
            pgCheckout.ValidateCheckoutResult(false);
        }
    }

    @When("^Perform payment action$")
    public void perform_payment_action() {
        if( hmUserData.get("action_payment").isEmpty()) return;

        myLog.info("Log: attempting payment with given data");
        pgPayment.PerformPayment(hmUserData.get("payment_method"));
    }

    @Then("^Validate payment result$")
    public void validate_payment_result() {
        if( hmUserData.get("payment_expected").isEmpty()) {
            if(hmGlobalData.get("login_success").equals("yes")){
                // mandatory last step
                pgHome.ActualLogout();
            }
            return;
        }

        myLog.info("Log: validating payment");
        pgPayment.ValidatePayment(true);

        // mandatory last step
        pgHome.ActualLogout();
    }














    @When("^Perform action and fetch data from \"(.*?)\" for case \"(.*?)\"$")
    public void perform_action_and_fetch_data_from_for_case(String sExternalDataFilePath, String sDataDrivenCaseNum) {
        myLog.info("Log: Fetch data from "+sExternalDataFilePath + " for " + sDataDrivenCaseNum);
        new Utility_Filehandler().ExcelReadDataRowWithUniqueId(sDataDrivenCaseNum);
        myLog.info("Log: action_login      " + hmUserData.get("action_login"));
        myLog.info("Log: action_search     " + hmUserData.get("action_search"));
        myLog.info("Log: action_checkout   " + hmUserData.get("action_checkout"));
        myLog.info("Log: action_payment    " + hmUserData.get("action_payment"));
        myLog.info("Log: login_email       " + hmUserData.get("login_email"));
        myLog.info("Log: login_pwd         " + hmUserData.get("login_pwd"));
        myLog.info("Log: login_expected    " + hmUserData.get("login_expected"));
        myLog.info("Log: search_term       " + hmUserData.get("search_term"));
        myLog.info("Log: search_result_count_expected  " + hmUserData.get("search_result_count_expected"));
        myLog.info("Log: checkout_qty      " + hmUserData.get("checkout_qty"));
        myLog.info("Log: checkout_expected " + hmUserData.get("checkout_expected"));
        myLog.info("Log: payment_method    " + hmUserData.get("payment_method"));
        myLog.info("Log: payment_expected  " + hmUserData.get("payment_expected"));
        hmGlobalData.put("login_success", "no");

        myLog.info("Log: Navigating to login section " + hmGlobalData.get("sUrlHome"));
        realDriver.get(hmGlobalData.get("sUrlHome"));
        utilGeneral.ImplicitWait(8000);

        if(! hmUserData.get("action_login").isEmpty()){
            myLog.info("Log: attempting login with given data");
            pgHome.PerformLogin(hmUserData.get("login_email"), hmUserData.get("login_pwd"));
            myLog.info("Log: validating login");
            if(hmUserData.get("login_expected").equals("success")){
                pgHome.ValidateLogin(true);
            }else{
                pgHome.ValidateLogin(false);
            }
        }


        if( ! hmUserData.get("action_search").isEmpty()){
            myLog.info("Log: attempting search with given data");
            pgSearch.PerformSearch(hmUserData.get("search_term"));
            if( ! hmUserData.get("search_result_count_expected").isEmpty()){
                myLog.info("Log: validating search");
                if(hmUserData.get("search_result_count_expected").equals("non zero")){
                    pgSearch.ValidateSearchResult(true);
                }else{
                    pgSearch.ValidateSearchResult(false);
                }
            }
        }


        if( ! hmUserData.get("action_checkout").isEmpty()){
            myLog.info("Log: attempting checkout with given quantity");
            pgCheckout.PerformCheckout(Integer.valueOf(hmUserData.get("checkout_qty")));
            if( ! hmUserData.get("checkout_expected").isEmpty()){
                myLog.info("Log: validating checkout");
                if(hmUserData.get("checkout_expected").equals("payment_screen")){
                    pgCheckout.ValidateCheckoutResult(true);
                }else{
                    pgCheckout.ValidateCheckoutResult(false);
                }
            }
        }


        if( ! hmUserData.get("action_payment").isEmpty()){
            myLog.info("Log: attempting payment with given data");
            pgPayment.PerformPayment(hmUserData.get("payment_method"));
            if( ! hmUserData.get("payment_expected").isEmpty()) {
                myLog.info("Log: validating payment");
                pgPayment.ValidatePayment(true);
            }
        }

        // mandatory last step
        pgHome.ActualLogout();

    }













    @When("^Navigate to forget password section$")
    public void Navigate_to_forget_password_section(){
        myLog.info("Log: Navigating to forget password section " + hmGlobalData.get("url_forget_password") + "\n");
        realDriver.get(hmGlobalData.get("url_forget_password"));
        utilGeneral.ImplicitWait(8000);

        pgOtherForgetPwd = new Page_VarietyForgetPwd(realDriver);
        pgOtherForgetPwd.PerformForgetPassword();
    }

    @Then("^Forget password activity should be successful$")
    public void Forget_password_activity_should_be_successful(){
        pgOtherForgetPwd.ValidateForgetPassword();
    }



    @When("^Navigate to pop up section$")
    public void Navigate_to_pop_up_section(){
        myLog.info("Log: Navigating to forget password section " + hmGlobalData.get("url_popup") + "\n");
        realDriver.get(hmGlobalData.get("url_popup"));
        utilGeneral.ImplicitWait(8000);

        pgOtherPopup = new Page_VarietyPopup(realDriver);
        pgOtherPopup.PerformPopupTest();
    }

    @Then("^Pop up activity should be successful$")
    public void Pop_up_activity_should_be_successful(){
        pgOtherPopup.ValidatePopup();
    }


    @When("^Navigate to window handling section$")
    public void Navigate_to_window_handling_section(){
        myLog.info("Log: Navigating to window handling section " + hmGlobalData.get("url_multi_window") + "\n");
        realDriver.get(hmGlobalData.get("url_multi_window"));
        utilGeneral.ImplicitWait(8000);

        pgOtherMultiwindow = new Page_VarietyMultiwindow(realDriver);
        pgOtherMultiwindow.PerformWindowHandling();
    }

    @Then("^Window handling activity should be successful$")
    public void Window_handling_activity_should_be_successful(){
        pgOtherMultiwindow.ValidateMultiWindow();
    }



    @When("^Navigate to iframe handling section$")
    public void Navigate_to_iframe_handling_section(){
        myLog.info("Log: Navigating to iframe handling section " + hmGlobalData.get("url_iframe") + "\n");
        realDriver.get(hmGlobalData.get("url_iframe"));
        utilGeneral.ImplicitWait(8000);

        pgOtherIframe = new Page_VarietyIframe(realDriver);
        pgOtherIframe.PerformIframeHandling();
    }

    @Then("^Iframe handling activity should be successful$")
    public void Iframe_handling_activity_should_be_successful(){
        pgOtherIframe.ValidateIframe();
    }






    @When("^Navigate to javascript pop up handling section$")
    public void Navigate_to_javascript_pop_up_handling_section(){
        myLog.info("Log: Navigating to javascript pop up handling section " + hmGlobalData.get("url_javascript_popup") + "\n");
        realDriver.get(hmGlobalData.get("url_javascript_popup"));
        utilGeneral.ImplicitWait(8000);

        pgOther = new Page_VarietyRobot(realDriver);
        pgOther.InvokeJavascriptPop();
    }

    @Then("^Javascript pop up handling activity should be successful$")
    public void Javascript_pop_up_handling_activity_should_be_successful(){
        pgOther.HandleJavascriptpopupUsingRobot();
    }

    @When("^Navigate to google ads handling section$")
    public void Navigate_to_google_ads_handling_section(){
        myLog.info("Log: Navigating to javascript pop up handling section " + hmGlobalData.get("url_google_ad") + "\n");
        realDriver.get(hmGlobalData.get("url_google_ad"));
        utilGeneral.ImplicitWait(8000);

        pgOther = new Page_VarietyRobot(realDriver);
        pgOther.HandleGoogleAds();
    }

    @Then("^Googls ads handling activity should be successful$")
    public void Googls_ads_handling_activity_should_be_successful(){
        pgOther.GoogleAdsshouldbeClosed();
    }




    @When("^Perform scrolling$")
    public void Perform_scrolling$(){
        myLog.info("Log: Navigating to other section \n");
        realDriver.get("http://the-internet.herokuapp.com/infinite_scroll");
        utilGeneral.ImplicitWait(8000);
        utilGeneral.ScrollUsingJavaScript("300");
        utilGeneral.Sleep(1500);
        utilGeneral.ScrollUsingJavaScript("300");
        utilGeneral.Sleep(1500);
        utilGeneral.ScrollUsingJavaScript("300");
        utilGeneral.Sleep(1500);
        utilGeneral.ScrollUsingJavaScriptBottom();
        utilGeneral.Sleep(1500);
    }

    @Then("^Perform click using Javascript$")
    public void Perform_click_using_Javascript$(){
        utilGeneral.ClickUsingJavaScriptBottom(
                realDriver.findElement(By.cssSelector("img[alt='Fork me on GitHub']"))
        );
        utilGeneral.Sleep(1000);
    }







}
