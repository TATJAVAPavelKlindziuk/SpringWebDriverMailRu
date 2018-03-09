package com.klindziuk.mail.util;

/**
 * Created by Hp on 17/12/2017.
 */

import com.klindziuk.mail.constant.BrowserConstant;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class ThreadLocalWebDriver {
    private static String browserName;
    private static String propertyName;
    private static String path;

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
        @Override
        public WebDriver initialValue() {
            WebDriver driver = null;
            if (browserName.toLowerCase().contains(BrowserConstant.CHROME)) {
                ChromeOptions ops = new ChromeOptions();
                ops.addArguments("--disable-notifications");
                ops.addArguments("--disable-infobars");
                System.setProperty(propertyName, path);
                driver = new ChromeDriver();
                return driver;
            }
            if (browserName.toLowerCase().contains(BrowserConstant.FIREFOX)) {
                System.setProperty(propertyName, path);
                driver = new FirefoxDriver();
                // TODO Implement for firefox
                return null;
            }
            if (browserName.toLowerCase().contains(BrowserConstant.SAFARI)) {
                System.setProperty(propertyName, path);
                driver = new SafariDriver();
                // TODO Implement for safari
                return null;
            }
            if (browserName.toLowerCase().contains(BrowserConstant.IE)) {
                System.setProperty(propertyName, path);
                driver = new InternetExplorerDriver();
                // TODO Implement for Internet explorer
                return null;
            }
            return driver;
        }
    };

    public ThreadLocalWebDriver(String browserName, String propertyName, String path) {
        ThreadLocalWebDriver.browserName = browserName;
        ThreadLocalWebDriver.propertyName = propertyName;
        ThreadLocalWebDriver.path = path;
    }

    public WebDriver getDriver() {
        return threadDriver.get();
    }
}
