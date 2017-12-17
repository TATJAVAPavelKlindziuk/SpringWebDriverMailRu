package com.klindziuk.mail.listener;

/**
 * Created by Hp on 17/12/2017.
 */

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.klindziuk.mail.util.BrowserDriver;
import com.klindziuk.mail.util.ThreadLocalWebDriver;

@ContextConfiguration(locations = {"file:src/test/resources/springcontext.xml"})
public class WebDriverListener extends AbstractTestNGSpringContextTests implements IInvokedMethodListener {
    private static final Logger LOGGER = Logger.getLogger(WebDriver.class);
    @Autowired
    private ThreadLocalWebDriver threadLocalWebDriver;

    //https://jira.spring.io/browse/SPR-4072 -
    //Spring beans not available within @BeforeTest(BeforeInvocation) methods
    protected void springTestContextPrepareTestInstance() throws Exception {
        super.springTestContextPrepareTestInstance();
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        try {
            springTestContextPrepareTestInstance();
        } catch (Exception e) {
            LOGGER.error("Error during prepare springTestContext", e);
        }
        if (method.getTestMethod().isBeforeClassConfiguration()) {
            WebDriver driver = threadLocalWebDriver.getDriver();
            LOGGER.info("Initializing driver with hashcode: " + driver.hashCode());
            LOGGER.info("Thread id = " + Thread.currentThread().getId());
            BrowserDriver.setWebDriver(driver);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            WebDriver driver = threadLocalWebDriver.getDriver();
            if (driver != null) {
                driver.quit();
                threadLocalWebDriver.killDriver();
                LOGGER.info("Killing driver with hashcode: " + driver.hashCode());
            }
        }
    }
}
