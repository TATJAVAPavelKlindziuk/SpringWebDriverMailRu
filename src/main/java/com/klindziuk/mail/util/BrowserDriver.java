package com.klindziuk.mail.util;

/**
 * Created by Hp on 16/12/2017.
 */

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;


public final class BrowserDriver {
    private static WebDriver driver;

    private BrowserDriver() {
    }

    public static WebDriver getDriver() {
        return driver;
    }

    public static void setWebDriver(WebDriver webDriver) {
        driver = webDriver;
    }
}