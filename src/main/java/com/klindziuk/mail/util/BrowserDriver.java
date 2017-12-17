package com.klindziuk.mail.util;

import org.openqa.selenium.WebDriver;

/**
 * Created by Hp on 16/12/2017.
 */
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