package pkg_utility;
import com.cucumber.listener.Reporter;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import pkg_pageobject.*;
import pkg_global.GlobalObjects;
import java.io.File;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.apache.commons.mail.*;

import java.util.concurrent.TimeUnit;

public class Utility_General extends GlobalObjects {

    private static final Logger myLog = Logger.getLogger(Utility_General.class);

    public WebDriverWait ExplicitWaitNormal(){
        return new WebDriverWait(realDriver, 8);
    }

    public WebDriverWait ExplicitWaitLow(){
        return new WebDriverWait(realDriver, 5);
    }

    public WebDriverWait ExplicitWaitHigh(){
        return new WebDriverWait(realDriver, 15);
    }

    public void ImplicitWait(int nMilliSec){
        realDriver.manage().timeouts().implicitlyWait(nMilliSec, TimeUnit.MILLISECONDS);
        //try{TimeUnit.MILLISECONDS.sleep(nMilliSec);}catch (Exception t){}
    }

    public static void Sleep(int nMillisec){
        try{Thread.sleep(nMillisec);}catch(Exception time){}
    }

    public void ScrollUsingJavaScript(String sHeightInPixel){
        ((JavascriptExecutor)realDriver).executeScript("window.scrollBy(0," + sHeightInPixel + ")");
    }

    public void ScrollUsingJavaScriptBottom(){
        ((JavascriptExecutor)realDriver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void ClickUsingJavaScriptBottom(WebElement clickMe){
        ((JavascriptExecutor)realDriver).executeScript("arguments[0].click();",clickMe);
    }

    public void GetCommandLineParam(){
        hmGlobalData.put("env",          System.getProperty("env"));             // -Denv=SIT
        hmGlobalData.put("browserType",  System.getProperty("browserType"));    // -Dbrowsertype=chrome
        hmGlobalData.put("baseUrl",      System.getProperty("baseUrl"));        // -DbaseUrl=http://sit.flipcart.com:8900
        hmGlobalData.put("dbAutomation", System.getProperty("dbAutomation"));  // -DdbAutomation=true
        // optional
//        hmGlobalData.put("inputDataFolder", System.getProperty("inputDataFolder"));
//        hmGlobalData.put("userCount",    System.getProperty("userCount"));
//        hmGlobalData.put("testPriority", System.getProperty("testPriority"));
    }

    public void DbConnectionInit(){
        try {
            Class.forName("org.sqlite.JDBC");
            sqliteConn = DriverManager.getConnection(sDbUrl);
        }catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    public void DbConnectionClose(){
        try {
            sqliteConn.close();
        }catch (SQLException eDb) {
            eDb.printStackTrace();
            Assert.fail(eDb.getMessage());
        }
    }

    public void InitAllPageObject(){
        pgHome     = new Page_Home(realDriver);
        pgRegister = new Page_Register(realDriver);
        pgSearch   = new Page_Search(realDriver);
        pgCheckout = new Page_Checkout(realDriver);
        pgPayment  = new Page_Payment(realDriver);
    }

    public void GenerateReport(){
        try{
            Reporter.loadXMLConfig(new File(System.getProperty("user.dir") + "/src/test/resources/extent-config.xml"));
            Reporter.setSystemInfo("Test User", "Tony Stark");
            Reporter.setSystemInfo("Operating System Type", "Windows");
            Reporter.setSystemInfo("Build version", "v 1.2.3");
            Reporter.setTestRunnerOutput("Extent Report for Regression");

            myLog.info("Log: Html report generated");
        }catch(Exception eReport){
            myLog.info("Log: Html report NOT generated");
        }
    }

    public void SendReportViaEmail(){
        if(hmGlobalData.get("param_email_report").contains("false")) return;

        myLog.info("\nLog: Please make sure to disable 2 step login for your GMAIL\n");
        myLog.info("\nLog: Please make sure to enable Less secure app login from your GMAIL\n");

        try{
            String sHost      = "smtp.gmail.com";
            int nPort         = 587;   //  465 is for SSL and 587 is for TLS
            String sGmailuser = hmGlobalData.get("param_email_report_from_email");
            String sGmailPwd  = hmGlobalData.get("param_email_report_gmail_pwd");

            String sEmailAddrFrom=hmGlobalData.get("param_email_report_from_email");
            String sEmailAddrTo  =hmGlobalData.get("param_email_report_to_email");
            String sEmailSubject = "Automation Test Report";
            String sEmailBody    = "Please find attached Automation Test Report";

            HtmlEmail email      = new HtmlEmail ();
            email.setHostName(sHost);
            email.setSmtpPort(nPort);
            email.setAuthenticator(new DefaultAuthenticator(sGmailuser, sGmailPwd));
            email.setSSLOnConnect(true);
            email.setFrom(sEmailAddrFrom);
            email.setSubject(sEmailSubject);
            email.setMsg(sEmailBody);
            email.addTo(sEmailAddrTo);

            if(hmGlobalData.get("param_email_attachment").contains("true") ){
                String sReportFilePath = System.getProperty("user.dir") + "\\target\\html\\report.html";
                EmailAttachment attachment = new EmailAttachment();
                attachment.setPath(sReportFilePath);
                attachment.setDisposition(EmailAttachment.ATTACHMENT);
                attachment.setName("report.html");
                email.attach(attachment);
                // Remember to all attach all screenshots generated under "\\target\\html" folder
            }

            email.send();
            myLog.info("Log: Sending report via email success");

        }catch(Exception eReportEmail){
            eReportEmail.printStackTrace();
            myLog.info("Log: Sending report via email failed");
        }
    }

    public static final String sBootText = "\n  _____        _          _____       _                 \n" + " |  __ \\      | |        |  __ \\     (_)                \n" + " | |  | | __ _| |_ __ _  | |  | |_ __ ___   _____ _ __  \n" + " | |  | |/ _` | __/ _` | | |  | | '__| \\ \\ / / _ \\ '_ \\ \n" + " | |__| | (_| | || (_| | | |__| | |  | |\\ V /  __/ | | |\n" + " |_____/ \\__,_|\\__\\__,_| |_____/|_|  |_| \\_/ \\___|_| |_|\n";
    static {myLog.fatal(sBootText);}

}
