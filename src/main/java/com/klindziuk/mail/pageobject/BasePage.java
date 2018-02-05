package com.klindziuk.mail.pageobject;

import com.klindziuk.mail.annotation.Anno;
import com.klindziuk.mail.constant.TimeConstants;
import com.klindziuk.mail.element.AnnoElement;
import com.klindziuk.mail.title.TitleFinder;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Hp on 16/12/2017.
 */
public abstract class BasePage implements TitleFinder {
    protected WebDriver webDriver;

    BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    protected abstract void waitForPageLoaded();

    protected void waitForElementVisible(WebElement webElement) {
        new WebDriverWait(webDriver, 15)
                .until(ExpectedConditions.visibilityOf(webElement));
    }

    protected void waitUntilElementNotDisplayed(final WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(webDriver, TimeConstants.SECONDS_5);
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

    protected AnnoElement updateElement(final AnnoElement element, final String... values) {
        return element.updateElement(values);
    }

    protected WebElement findElement(final AnnoElement element) {
        return webDriver.findElement(element.getLocator());
    }

    protected void click(final AnnoElement element) {
        findElement(element).click();
    }

    protected String getText(final AnnoElement element) {
        return findElement(element).getText();
    }

    private void initElements() {

        final List<Field> fields = new ArrayList<>();
        Class currentPageObject = this.getClass();

        while (currentPageObject != BasePage.class) {
            fields.addAll(new ArrayList<>(Arrays.asList(currentPageObject.getDeclaredFields())));
            currentPageObject = currentPageObject.getSuperclass();
        }

        for (Field field : fields) {
            final Anno fieldAnnotation = field.getAnnotation(Anno.class);
            final boolean accessible = field.isAccessible();

            if (fieldAnnotation != null) {
                try {
                    field.setAccessible(true);
                    field.set(this, new AnnoElement(fieldAnnotation.searchBy(), fieldAnnotation.value()));
                    field.setAccessible(accessible);
                } catch (IllegalAccessException e) {
                    // Log or throw your exception here
                }
            }
        }
    }
}
