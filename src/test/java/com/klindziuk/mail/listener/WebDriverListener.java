package com.klindziuk.mail.listener;

/**
 * Created by Hp on 17/12/2017.
 */

import com.klindziuk.mail.annotation.EnableWebDriver;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.IInvokedMethod;
import org.testng.IInvokedMethodListener;
import org.testng.ITestResult;

import com.klindziuk.mail.util.BrowserDriver;
import com.klindziuk.mail.util.ThreadLocalWebDriver;

import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.Calendar;

@ContextConfiguration(locations = {"file:src/test/resources/springcontext.xml"})
public class WebDriverListener extends AbstractTestNGSpringContextTests implements IInvokedMethodListener {
    private static final Logger LOGGER = Logger.getLogger(WebDriverListener.class);

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

        if (checkEnableWebDriverAnno(method)) {
            WebDriver webDriver = threadLocalWebDriver.getDriver();
            LOGGER.info("Initializing driver with hashcode: " + webDriver.hashCode());
            LOGGER.info("Thread id = " + Thread.currentThread().getId());
            BrowserDriver.setWebDriver(webDriver);
        }
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        if (method.isTestMethod()) {
            WebDriver webDriver = threadLocalWebDriver.getDriver();
            takeScreenshotOnFailure(testResult, webDriver);
            if (webDriver != null) {
                webDriver.close();
                webDriver.quit();
                LOGGER.info("Killing driver with hashcode: " + webDriver.hashCode());
            }
        }
    }

    private boolean checkEnableWebDriverAnno(IInvokedMethod method){
        Annotation[] methodAnnotations = method.getTestMethod().getConstructorOrMethod().getMethod().getDeclaredAnnotations();

        for (Annotation annotation : methodAnnotations) {
            if (annotation instanceof EnableWebDriver) {
                return true;
            }
        }
        return false;
    }

    private void takeScreenshotOnFailure(ITestResult testResult, WebDriver webDriver) {
        if (testResult.getStatus() == ITestResult.FAILURE) {
            File scrFile = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
            try {
                String pathName =
                        "./target/screenshots/" + testResult.getName() + "()-" + Calendar.getInstance().getTime()
                                + ".jpg";
                FileUtils.copyFile(scrFile, new File(pathName));
                LOGGER.info("Take screenshot to : " + pathName);
            } catch (IOException ioEx) {
                LOGGER.error("Cannot take screenshot", ioEx);
            }
        }
    }
}
