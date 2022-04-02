package pkg_locators;

import org.openqa.selenium.By;
import pkg_global.GlobalObjects;

public class Locator_ForgetPwd extends GlobalObjects {

    public static By Txtbx_ForgetPwd        = By.id("email");
    public static By Txtbx_ForgetPwdSubmit  = By.xpath("//i[text()='Retrieve password']");
    public static By Lnk_ForgetPwdSuccess   = By.id("content");

}
