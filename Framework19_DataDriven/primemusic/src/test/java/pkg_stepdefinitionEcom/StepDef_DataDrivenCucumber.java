package pkg_stepdefinitionEcom;
import cucumber.api.java.en.*;
import org.apache.log4j.Logger;
import org.junit.Assert;
import pkg_global.GlobalObjects;

public class StepDef_DataDrivenCucumber extends GlobalObjects {

    private static final Logger myLog = Logger.getLogger(StepDef_DataDrivenCucumber.class);

    @When("^Prepare sample test data$")
    public void prepare_sample_test_data() {
        myLog.info("Log: Preparing sample data");
        myLog.info("Log: valid_login_user    " + hmGlobalData.get("valid_login_user"));
        myLog.info("Log: valid_login_pwd     " + hmGlobalData.get("valid_login_pwd"));
        myLog.info("Log: invalid_login_user  " + hmGlobalData.get("invalid_login_user"));
        myLog.info("Log: invalid_login_pwd   " + hmGlobalData.get("invalid_login_pwd"));
        hmGlobalData.put("login_success", "no");
        myLog.info("Log: valid_login_user    " + hmGlobalData.get("valid_login_user"));
        myLog.info("Log: invalid_login_user  " + hmGlobalData.get("invalid_login_user"));
    }

    @When("^Perform login operation using \"(.*?)\"$")
    public void perform_login_operation_using(String sLoginType) {
        myLog.info("Log: Navigating to login section " + hmGlobalData.get("sUrlHome"));
        realDriver.get(hmGlobalData.get("sUrlHome"));
        utilGeneral.ImplicitWait(8000);

        if(sLoginType.equals("NA")) return;

        if(sLoginType.equals("valid")){
            myLog.info("Log: attempting login with valid data");
            pgHome.PerformLogin(hmGlobalData.get("valid_login_user"), hmGlobalData.get("valid_login_pwd"));
        }else{
            myLog.info("Log: attempting login with invalid data");
            pgHome.PerformLogin(hmGlobalData.get("invalid_login_user"), hmGlobalData.get("invalid_login_pwd"));
        }
    }

    @Then("^Validate login \"(.*?)\"$")
    public void validate_login(String sLoginResult) {
        if(sLoginResult.equals("NA")) return;

        myLog.info("Log: validating login");
        if(sLoginResult.equals("valid")){
            pgHome.ValidateLogin(true);
        }else{
            pgHome.ValidateLogin(false);
        }
    }

    @When("^Perform search operation using \"(.*?)\"$")
    public void perform_search_operation_using(String sSearchType) {
        if(sSearchType.equals("NA")) return;

        if(sSearchType.equals("valid")){
            myLog.info("Log: attempting search with valid data");
            pgSearch.PerformSearch(hmGlobalData.get("search_items_valid"));
        }else{
            myLog.info("Log: attempting search with invalid data");
            pgSearch.PerformSearch(hmGlobalData.get("search_items_invalid"));
        }
    }

    @Then("^Validate search \"(.*?)\"$")
    public void validate_search(String sSearchResult) {
        if(sSearchResult.equals("NA")) return;

        myLog.info("Log: validating search");
        if(sSearchResult.equals("valid")){
            pgSearch.ValidateSearchResult(true);
        }else{
            pgSearch.ValidateSearchResult(false);
        }
    }

    @When("^Perform checkout operation using \"(.*?)\"$")
    public void perform_checkout_operation_using(String sCheckoutType) {
        if(sCheckoutType.equals("NA")) return;

        if(sCheckoutType.equals("valid")){
            myLog.info("Log: attempting checkout with valid quantity");
            pgCheckout.PerformCheckout(1);
        }else{
            myLog.info("Log: attempting checkout with invalid quantity");
            // checkout with zero item
            pgCheckout.PerformCheckout(0);
        }
    }

    @Then("^Validate checkout \"(.*?)\"$")
    public void validate_checkout(String sCheckoutResult) {
        if(sCheckoutResult.equals("NA")) return;

        myLog.info("Log: validating checkout");
        if(sCheckoutResult.equals("valid")){
            pgCheckout.ValidateCheckoutResult(true);
        }else{
            pgCheckout.ValidateCheckoutResult(false);
        }
    }

    @When("^Perform payment operation using \"(.*?)\"$")
    public void perform_payment_operation_using(String sPaymentType) {
        if(sPaymentType.equals("NA")) return;

        if(sPaymentType.equals("valid")){
            myLog.info("Log: attempting payment with valid data");
            pgPayment.PerformPayment("paybycheque");
        }else{
            myLog.info("Log: attempting payment with invalid data");
            pgPayment.PerformPayment("");
        }
    }

    @Then("^Validate payment \"(.*?)\"$")
    public void validate_payment(String sPaymentResult) {
        if(sPaymentResult.equals("NA")) {
            if(hmGlobalData.get("login_success").equals("yes")){
                // mandatory last step
                pgHome.ActualLogout();
            }
            return;
        }

        myLog.info("Log: validating payment");
        if(sPaymentResult.equals("valid")){
            pgPayment.ValidatePayment(true);
        }else{
            pgPayment.ValidatePayment(false);
        }

        // mandatory last step
        pgHome.ActualLogout();
    }











    @When("^Ecom user login with Username \"(.*?)\" and Password \"(.*?)\"$")
    public void User_attempts_to_login_with_Username_and_Password(String sUser, String sPwd){

        myLog.info("Log: Navigating to login section " + hmGlobalData.get("sUrlHome") + "\n");
        realDriver.get(hmGlobalData.get("sUrlHome"));
        utilGeneral.ImplicitWait(8000);

        pgHome.PerformLogin(sUser, sPwd);

//        LocalDriver = new GlobalObjects().getDriver();
//        LocalDriver.get(sUrlLogin);
//        LocalDriver.manage().timeouts().implicitlyWait(8000, TimeUnit.MILLISECONDS);
//        myLog.info("Log: Attempting to login");
//        LocalDriver.findElement(By.id("email")).sendKeys(sUser);
//        LocalDriver.findElement(By.id("passwd")).sendKeys(sPwd);
//        LocalDriver.findElement(By.cssSelector("i[class='icon-lock left']")).click();
//        LocalDriver.manage().timeouts().implicitlyWait(8000, TimeUnit.MILLISECONDS);
    }

    @When("^Ecom user login should be successful$")
    public void Ecom_user_login_should_be_successful(){
        pgHome.ValidateLogin(true);
    }

    @Then("^Ecom user login should NOT be successful$")
    public void Ecom_user_login_should_NOT_be_successful(){
        pgHome.ValidateLogin(false);
    }



    @When("^Ecom user registration with following data$")
    public void Ecom_user_registration_with_following_data(){
        //new Utility_General().InitAllPageObject();

        myLog.info("Log: Navigating to registration section " + hmGlobalData.get("sUrlRegister") + "\n");
        realDriver.get(hmGlobalData.get("sUrlRegister"));
        utilGeneral.ImplicitWait(8000);

        // User data from Excel will be used
        pgRegister.RegistrationStart();
        pgRegister.RegistrationFormFill();
    }

    @When("^Ecom user registration should be successful$")
    public void Ecom_user_registration_should_be_successful(){
        pgRegister.ValidateRegistration();
    }




    @When("^Ecom user attempts to search for \"(.*?)\" item$")
    public void Ecom_user_attempts_to_search_for_item(String sSearchType){
        //new Utility_General().InitAllPageObject();

        myLog.info("Log: Navigating to home " + hmGlobalData.get("sUrlHome") + "\n");
        realDriver.get(hmGlobalData.get("sUrlHome"));
        utilGeneral.ImplicitWait(8000);

        pgSearch.PerformSearch(sSearchType);
    }

    @Then("^Successful Ecom search results should be shown$")
    public void Successful_Ecom_search_results_should_be_shown(){
        pgSearch.ValidateSearchResult(true);
    }

    @Then("^Successful Ecom search results should NOT be shown$")
    public void Successful_Ecom_search_results_should_NOT_be_shown(){
        pgSearch.ValidateSearchResult(false);
    }



    @And("^Ecom user adds item to cart and proceeds to checkout$")
    public void Ecom_user_adds_item_to_cart_and_proceeds_to_checkout(){
        pgSearch.AddItemToCart();
    }

    @Then("^Selected items should be available in basket$")
    public void Selected_items_should_be_available_in_basket(){
        pgCheckout.ValidateCheckoutResult(true);
    }




    @When("^dummy step2$")
    public void dummy_step2(){
        myLog.info("Log: Dummy step2");
        Assert.fail("Log: Dummy step2 failed");
    }

}
