package com.klindziuk.mail.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public final class ActionUtil {
    private static final Logger LOGGER = Logger.getLogger(ActionUtil.class);

    private ActionUtil() {
    }

    public static void moveCursorToElement(WebElement webElement) {
        LOGGER.debug(String.format("Move cursor to '%s'", webElement));
        new Actions(BrowserDriver.getDriver()).moveToElement(webElement).click(webElement).build().perform();
    }

    public static void doubleClickOnElement(WebElement webElement) {
        LOGGER.debug(String.format("Double click on '%s'", webElement));
        new Actions(BrowserDriver.getDriver()).doubleClick(webElement).build().perform();
    }

    public static void contextClickOnElement(WebElement webElement) {
        LOGGER.debug(String.format("Context click on '%s'", webElement));
        new Actions(BrowserDriver.getDriver()).contextClick(webElement).build().perform();
    }

    public static void dragAndDrop(WebElement source, WebElement to, WebDriver webDriver) {
        LOGGER.debug(String.format("Drag element '%s' and drop it to '%s'", source, to));
        new Actions(BrowserDriver.getDriver()).dragAndDrop(source, to).build().perform();
    }

}
