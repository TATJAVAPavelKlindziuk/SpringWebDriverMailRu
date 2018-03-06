package com.klindziuk.mail.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public final class ActionUtil {
    private static final Logger LOGGER = Logger.getLogger(ActionUtil.class);

    private ActionUtil() {
    }

    public static void moveCursorToElement(WebElement webElement, WebDriver webDriver) {
        LOGGER.info(String.format("Move cursor to '%s'", webElement.toString()));
        new Actions(webDriver).moveToElement(webElement).click(webElement).build().perform();
    }

    public static void doubleClickOnElement(WebElement webElement, WebDriver webDriver) {
        LOGGER.info(String.format("Double click on '%s'", webElement.toString()));
        new Actions(webDriver).doubleClick(webElement).build().perform();
    }

    public static void contextClickOnElement(WebElement webElement, WebDriver webDriver) {
        LOGGER.info(String.format("Context click on '%s'", webElement.toString()));
        new Actions(webDriver).contextClick(webElement).build().perform();
    }

    public static void dragAndDrop(WebElement source, WebElement to, WebDriver webDriver) {
        LOGGER.info(String.format("Drag element '%s' and drop it to '%s'", source.toString(), to.toString()));
        new Actions(webDriver).dragAndDrop(source, to).build().perform();
    }

}
