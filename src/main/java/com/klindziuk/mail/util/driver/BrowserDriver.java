package com.klindziuk.mail.util.driver;

import org.openqa.selenium.WebDriver;

/**
 * Created by Hp on 16/12/2017.
 */
public final class BrowserDriver {
    private static BrowserDriver instance;
    private static WebDriver driver;

    private BrowserDriver() {
    }

    public static BrowserDriver getInstance(){
        if(instance == null){
            synchronized (BrowserDriver.class) {
                if(instance == null){
                    instance = new BrowserDriver();
                }
            }
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public static void setWebDriver(WebDriver webDriver) {
        driver = webDriver;
    }
}