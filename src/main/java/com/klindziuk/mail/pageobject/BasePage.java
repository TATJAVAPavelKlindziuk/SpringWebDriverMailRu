package com.klindziuk.mail.pageobject;

import com.klindziuk.mail.annotation.Anno;
import com.klindziuk.mail.constant.TimeConstants;
import com.klindziuk.mail.element.AnnoElement;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.text.html.HTML;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by Hp on 16/12/2017.
 */
public abstract class BasePage {
    protected WebDriver webDriver;

    BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
        initElements();
    }

    public abstract void waitForPageLoaded();

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

    protected void pause(int time) {
        try {
            TimeUnit.SECONDS.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public final void initElements() {

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

    public AnnoElement updateElement(final AnnoElement element, final String... values) {
        return element.updateElement(values);
    }

    public WebElement findElement(final AnnoElement element) {
        return webDriver.findElement(element.getLocator());
    }

    public void click(final AnnoElement element) {
        findElement(element).click();
    }

    public String getText(final AnnoElement element) {
        return findElement(element).getText();
    }
}
