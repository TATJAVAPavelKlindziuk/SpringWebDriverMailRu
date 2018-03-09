package com.klindziuk.mail.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public final class JavaScriptUtil {
	private static final Logger LOGGER = Logger.getLogger(JavaScriptUtil.class);

	private JavaScriptUtil() {
	}

	public static String getWindowTitle() {
		LOGGER.info("Get window title");
		JavascriptExecutor js = (JavascriptExecutor) BrowserDriver.getDriver();
		return js.executeScript("return document.title;").toString();
	}

	public static void scrollDownPage( int x, int y) {
		LOGGER.info(String.format("Scroll by (%d,%d)", x, y));
		JavascriptExecutor js = (JavascriptExecutor) BrowserDriver.getDriver();
		js.executeScript(String.format("window.scrollBy (%d,%d)", x, y));
	}

	public static void refresh() {
		LOGGER.info("Refreshing page");
		JavascriptExecutor js =  (JavascriptExecutor) BrowserDriver.getDriver();
		js.executeScript("history.go(0)");
	}

	public static String getDomain() {
		LOGGER.info("Get document domain");
		JavascriptExecutor js = (JavascriptExecutor) BrowserDriver.getDriver();
		return js.executeScript("return document.domain;").toString();
	}

	public static String getPageUrl() {
		JavascriptExecutor js = (JavascriptExecutor) BrowserDriver.getDriver();
		String url = js.executeScript("return document.URL;").toString();
		LOGGER.trace(String.format("Get page url - '%s' ",url));
		return url;
	}

	public static void highlightElement(WebElement webElement) {
		LOGGER.info(String.format("Highlight element : '%s' ", webElement));
		((JavascriptExecutor) BrowserDriver.getDriver()).executeScript("arguments[0].style.border='5px solid lime'", webElement);
	}

	public static void unHighlightElements(WebElement... webElement) {
		WebDriver webDriver = BrowserDriver.getDriver();
		for (WebElement element : webElement) {
			LOGGER.info(String.format("Unhighlight element : '%s' ", element));
			((JavascriptExecutor) webDriver).executeScript("arguments[0].style.border='0px'", element);
		}
	}

}
