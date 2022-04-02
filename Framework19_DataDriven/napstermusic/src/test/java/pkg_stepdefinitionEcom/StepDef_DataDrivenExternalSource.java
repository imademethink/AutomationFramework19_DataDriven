package pkg_stepdefinitionEcom;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pkg_global.GlobalObjects;
import pkg_pageobject.*;
import pkg_utility.Utility_Filehandler;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

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







}
