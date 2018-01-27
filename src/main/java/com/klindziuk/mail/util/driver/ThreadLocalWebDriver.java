package com.klindziuk.mail.util.driver;

/**
 * Created by Hp on 17/12/2017.
 */
import com.klindziuk.mail.constant.BrowserConstants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.safari.SafariDriver;

public class ThreadLocalWebDriver {
    static String browserName;

    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
        @Override
        public WebDriver initialValue() {
            WebDriver driver = null;
            if (browserName.toLowerCase().contains(BrowserConstants.CHROME)) {
                ChromeOptions ops = new ChromeOptions();
                ops.addArguments("--disable-notifications");
                ops.addArguments("--disable-infobars");
                System.setProperty("webdriver.chrome.driver", "D:/Java/Chrome/chromedriver.exe");
                driver = new ChromeDriver();
                return driver;
            }
            if (browserName.toLowerCase().contains(BrowserConstants.FIREFOX)) {
                driver = new FirefoxDriver();
                // TODO Implement for firefox
                return null;
            }
            if (browserName.toLowerCase().contains(BrowserConstants.SAFARI)) {
                driver = new SafariDriver();
                // TODO Implement for safari
                return null;
            }
            if (browserName.toLowerCase().contains(BrowserConstants.IE)) {
                driver = new InternetExplorerDriver();
                // TODO Implement for Internet explorer
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