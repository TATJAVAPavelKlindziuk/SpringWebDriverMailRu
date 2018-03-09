package com.klindziuk.mail.pageobject;

import com.klindziuk.mail.constant.TimeConstant;
import com.klindziuk.mail.util.BrowserDriver;
import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by Hp on 16/12/2017.
 */
public abstract class BasePage {
    private static final Logger LOGGER = Logger.getLogger(BasePage.class);
    protected WebDriver webDriver;

    BasePage() {
        this.webDriver = BrowserDriver.getDriver();
    }

    public abstract void waitForPageLoaded();

    protected void waitForElementVisible(WebElement webElement) {
        new WebDriverWait(webDriver, 15)
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitUntilElementNotDisplayed(final WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(webDriver, TimeConstant.SECONDS_5);
        ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                try {
                    webElement.isDisplayed();
                    return false;
                } catch (NoSuchElementException | StaleElementReferenceException ex) {
                    return true;
                }
            }
        };
        wait.until(elementIsDisplayed);
    }

    protected boolean isElementVisible(WebElement webElement) {
        try {
            return webElement.isDisplayed() && webElement.isEnabled();
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            return false;
        }
    }

    protected void pause(int time) {
        try {
            LOGGER.warn(String.format("Pause for '%d' seconds", time));
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
