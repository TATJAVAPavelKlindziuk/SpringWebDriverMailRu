package com.klindziuk.mail.util;

/**
 * Created by Hp on 17/12/2017.
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.Arrays;

public class ThreadLocalWebDriver {
    static String browserName;

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
        @Override
        public WebDriver initialValue() {
            WebDriver driver = null;
            if (browserName.toLowerCase().contains("chrome")) {
                ChromeOptions ops = new ChromeOptions();
                ops.addArguments("--disable-notifications");
                ops.addArguments(Arrays.asList("disable-infobars"));
                System.setProperty("webdriver.chrome.driver", "D:/Java/Chrome/chromedriver.exe");
                driver = new ChromeDriver();
                return driver;
            }
            if (browserName.toLowerCase().contains("firefox")) {
                driver = new FirefoxDriver();
                //TODO
                return null;
            }
            if (browserName.toLowerCase().contains("safari")) {
                driver = new SafariDriver();
                //TODO
                return null;
            }
            if (browserName.toLowerCase().contains("ie")) {
                driver = new InternetExplorerDriver();
                //TODO
                return null;
            }
            if (browserName.toLowerCase().contains("opera")) {
                driver = new OperaDriver();
                //TODO
                return null;
            }
            return driver;
        }
    };

    public ThreadLocalWebDriver(String browserName){
        ThreadLocalWebDriver.browserName = browserName;
    }

    public WebDriver getDriver() {
        return threadDriver.get();
    }
}