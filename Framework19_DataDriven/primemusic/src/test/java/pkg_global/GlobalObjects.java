package pkg_global;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import pkg_pageobject.*;
import pkg_utility.Utility_General;
import pkg_utility.Utility_Filehandler;
import java.awt.*;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class GlobalObjects {

    // Logger
    private static final Logger myLog = Logger.getLogger(GlobalObjects.class);

    // Browser instance
    public static WebDriver realDriver                    = null;
    public static boolean bBrowserInvoked                 = false;

    // A hash-map of all data items
    public static HashMap<String, String> hmGlobalData    = new HashMap<>();

    // User data read from Excel table
    public static FileInputStream exlInStream             = null;
    public static XSSFWorkbook exlWorkBook                = null;
    public static XSSFSheet exlWorkSheet                  = null;
    public static final String sSheetName                = "ecommerce";
    public static final String sUserDataExcelPath       = System.getProperty("user.dir") + "\\src\\test\\resources\\externalData\\Ecommerce_data.xlsx";
    public static HashMap<String, String> hmUserData      = new HashMap<>();

    // Properties file
    public static final String sAutomationPropertiesPath = System.getProperty("user.dir") + "\\src\\test\\resources\\externalData\\Config.properties";

    // Csv file
    public static final String sSearchtermCsvFilePath  = System.getProperty("user.dir") + "\\src\\test\\resources\\externalData\\Searchterm.csv";

    // Db connection
    public static Connection sqliteConn                   = null;
    public static final String sDbUrl                    = "jdbc:sqlite:" + System.getProperty("user.dir") + "\\src\\test\\resources\\simple_sqlite\\Tony.db";

    // Utility handler objects
    public static Utility_Filehandler utilFileHandler     = null;
    public static Utility_General utilGeneral             = null;
    public static Robot utilRobot                         = null;

    // Page object instances
    public static Page_Home pgHome                        = null;
    public static Page_Register pgRegister                = null;
    public static Page_Search pgSearch                    = null;
    public static Page_Checkout pgCheckout                = null;
    public static Page_Payment pgPayment                  = null;

    public static Page_VarietyForgetPwd pgOtherForgetPwd    = null;
    public static Page_VarietyPopup pgOtherPopup            = null;
    public static Page_VarietyMultiwindow pgOtherMultiwindow= null;
    public static Page_VarietyIframe pgOtherIframe          = null;
    public static Page_VarietyRobot pgOther                 = null;


    // Urls
//    public static String sUrlHome      = "http://automationpractice.com/index.php";
    public static String sUrlLogin     = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    public static String sUrlSearch    = "http://automationpractice.com/index.php";
    public static String sUrlRegister  = "http://automationpractice.com/index.php?controller=authentication&back=my-account";
    public static String sStackOverflow= "https://stackoverflow.com/";




    // Launch browser instance -- Currently only Chrome is supported
    // Note by default image loading is disabled to speedup the operation
    public WebDriver getDriver(){
        if (null != realDriver){
            realDriver = null;
        }

        if(false == bBrowserInvoked){

            // If selenium grid to be used
            if( null != System.getProperty("browserGrid") ){
                // launch remote chrome using selenium grid if specified as system property    -DbrowserGrid=true

                // Disable image loading - to speedup test execution
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("profile.managed_default_content_settings.images", 2);

                ChromeOptions options = new ChromeOptions();
                options.addArguments("window-size=1400,800");
                options.addArguments("disable-infobars"); // disabling infobars
                options.addArguments("--disable-extensions"); // disabling extensions
                options.addArguments("--no-sandbox"); // Bypass OS security model
                options.setExperimentalOption("useAutomationExtension", false);
                options.setExperimentalOption("prefs", prefs);
                options.addArguments("--incognito");
                options.addArguments("--disable-popup-blocking");
                DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
                chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, options);

                realDriver = null;
                myLog.info("Log: Chrome browser is launched on Selenium Grid");

                try{
                    realDriver=new RemoteWebDriver(new URL(hmGlobalData.get("url_selenium_grid")),chromeCapabilities);
                }catch (MalformedURLException eUrl){
                    Assert.fail("Log: Chrome launching failed on Selenium Grid");
                }

            } else{
                String sChromeBinary=System.getProperty("user.dir") + "\\src\\test\\resources\\browserDriver\\chromedriver.exe";
                System.setProperty("webdriver.chrome.driver", sChromeBinary);
                System.setProperty("webdriver.chrome.silentOutput", "true");

                // Disable image loading - to speedup test execution
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("profile.managed_default_content_settings.images", 2);

                ChromeOptions options = new ChromeOptions();
                options.addArguments("window-size=1400,800");
                options.addArguments("disable-infobars"); // disabling infobars
                options.addArguments("--disable-extensions"); // disabling extensions
                options.addArguments("--no-sandbox"); // Bypass OS security model
                options.setExperimentalOption("useAutomationExtension", false);
                //options.setExperimentalOption("prefs", prefs);

                realDriver = null;
                realDriver = new ChromeDriver(options);
                myLog.info("Log: Chrome browser is launched");
            }
            realDriver.manage().deleteAllCookies();
            bBrowserInvoked = true;
        }
        return realDriver;
    }

}
