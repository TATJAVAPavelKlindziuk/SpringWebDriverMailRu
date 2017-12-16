package com.klindziuk.mail.pageobject;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by Hp on 16/12/2017.
 */
public abstract class BasePage {
    protected WebDriver webDriver;

     BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public abstract void waitForPageLoaded();

    protected void waitForElementDisplayed(WebElement webElement) {
        new WebDriverWait(webDriver, 15)
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitUntilElementNotDisplayed(final WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        ExpectedCondition elementIsDisplayed = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver arg0) {
                try {
                    webElement.isDisplayed();
                    return false;
                }
                catch (NoSuchElementException nfEx) {
                    return true;
                }
                catch (StaleElementReferenceException serEx ) {
                    return true;
                }
            }
        };
        wait.until(elementIsDisplayed);
    }
}
