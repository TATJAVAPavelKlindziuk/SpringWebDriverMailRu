package com.klindziuk.mail.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

/**
 * Created by Hp on 16/12/2017.
 */
public final class ActionUtil {

	private ActionUtil() {}

	public static void moveCursorToElement(WebElement webElement, WebDriver webDriver){
		new Actions(webDriver).moveToElement(webElement).click(webElement).build().perform();
	}

	public static void doubleClickOnElement(WebElement webElement, WebDriver webDriver){
		new Actions(webDriver).doubleClick(webElement).build().perform();
	}

	public static void contextClickOnElement(WebElement webElement, WebDriver webDriver){
		new Actions(webDriver).contextClick(webElement).build().perform();
	}

	public static void dragAndDrop (WebElement source, WebElement to, WebDriver webDriver){
		new Actions(webDriver).dragAndDrop(source,to).build().perform();
	}

}
