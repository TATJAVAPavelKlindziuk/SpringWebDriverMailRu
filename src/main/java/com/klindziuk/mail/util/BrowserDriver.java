package com.klindziuk.mail.util;

/**
 * Created by Hp on 16/12/2017.
 */
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import java.io.IOException;

public enum BrowserDriver {

    CHROME(1),
    FIREFOX(2),
    IEXPLORER(3);

    private int index;

    BrowserDriver(int browserIndex) {
        this.index = browserIndex;
    }

    public  WebDriver selectDriver() {
        switch (index) {
            case 1: {
                ChromeOptions ops = new ChromeOptions();
                ops.addArguments("--disable-notifications");
                System.setProperty("webdriver.chrome.driver", "D:/Java/Chrome/chromedriver.exe");
                return new ChromeDriver(ops);
            }
            case 2: {
                System.setProperty("webdriver.gecko.driver", "D:/Java/FireFox/geckodriver.exe");
                return new FirefoxDriver();
            }
            case 3: {
                System.setProperty("webdriver.ie.driver", "D:/Java/IE/IEDriverServer.exe");
                return new InternetExplorerDriver();
            }
            default: {
                ChromeOptions ops = new ChromeOptions();
                ops.addArguments("--disable-notifications");
                System.setProperty("webdriver.chrome.driver", "D:/Java/Chrome/chromedriver.exe");
                return new ChromeDriver(ops);
            }
        }
    }

    public void killDriver() {
        try {
            switch (index) {
                case 1: {
                    Runtime.getRuntime().exec("taskkill /f /IM chromedriver.exe");
                }
                case 2: {
                    Runtime.getRuntime().exec("taskkill /f /IM geckodriver.exe");
                }
                case 3: {
                    Runtime.getRuntime().exec("taskkill /f /IM IEDriverServer.exe");
                }
            }
        } catch (IOException ioex) {
            ioex.printStackTrace();
        }
    }
}